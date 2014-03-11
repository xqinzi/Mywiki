package com.mywiki.test;

import java.util.List;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mywiki.dao.UserDao;
import com.mywiki.model.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestHibernateSearch {

	@Autowired
	UserDao userDao;
	
    private SessionFactory sessionFactory;
	
	@Autowired
    @Required
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Session getSession() throws HibernateException {
        Session sess = getSessionFactory().openSession();
        if (sess == null) {
            sess = getSessionFactory().openSession();
        }
        return sess;
    }
	
	@Before
	public void setUp(){
		User u1 = new User();
		u1.setEmail("mywiki95@gamil.com");
		u1.setPassword("1111111");
		u1.setNickname("mywiki");
		u1.setNickname("张宗耀");
		u1.setFirstName("我喜欢吴云静");
		u1.setLastName("老婆");
		u1.setDescription("前面所讲的就是Hibernate持久化Car对象和Hibernate Search 让Cars变得可搜索所需要的。");
		userDao.save(u1);
		
		User u2 = new User();
		u2.setEmail("394907365@qq.com");
		u2.setPassword("2222222");
		u2.setNickname("开拓者");
		u2.setNickname("张宗耀");
		u2.setFirstName("我喜欢吴云静");
		u2.setLastName("老婆");
		u2.setDescription("前面所讲的就是Hibernate持久化Car对象和Hibernate Search 让Cars变得可搜索所需要的。");
		userDao.save(u2);
	}
    
	
	@Test
	public void testUsingLuceneBooleanQueryReturningFullEntity() throws Exception {
		
		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		BooleanQuery bq = new BooleanQuery();
		/*TermQuery gt350TermQuery = new TermQuery(new Term("model", "GT 350"));
		TermQuery belAirTermQuery = new TermQuery(new Term("model", "Bel Air"));
		bq.add(gt350TermQuery, BooleanClause.Occur.SHOULD);
		bq.add(belAirTermQuery, BooleanClause.Occur.SHOULD);*/
		
		
		
		TermQuery desc = new TermQuery(new Term("desc", "前"));
		bq.add(desc, BooleanClause.Occur.SHOULD);
		Query q = new QueryParser(Version.LUCENE_36, "desc", new SimpleAnalyzer(Version.LUCENE_36)).parse(bq.toString());

		org.hibernate.Query hibernateQuery = fullTextSession.createFullTextQuery(q, User.class);
		List<User> searchResults = hibernateQuery.list();
		System.out.println();
		System.out.println(searchResults.size());
		System.out.println();
		for (User user : searchResults) {
			System.out.println();
			System.out.println(user);
			System.out.println();
		}
	}
}
