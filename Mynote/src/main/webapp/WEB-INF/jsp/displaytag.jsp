<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>  
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>displaytag</title>
</head>
<body>
		<div align="center">
		<display:table name="userlist"  cellspacing="0" cellpadding="0" pagesize="5" export="true" requestURI="">
			<display:column property="uuid" title="ID"  sortable="true"/>
			<display:column property="email" title="email" sortable="true"/>
			<display:column property="password" title="password"/>
			<display:column property="description" title="description" />
			<display:column property="nickname" title="nickname" />
			<display:column property="firstName" title="firstName" />
			<display:column property="lastName" title="lastName" />
			<display:column property="phoneNumber" title="phoneNumber" />
			<display:column property="authorities" title="authorities" />
			<display:column property="enabled" title="enabled" />
			<display:column property="accountExpired" title="accountExpired" />
			<display:column property="accountLocked" title="accountLocked" />
			<display:column property="credentialsExpired" title="credentialsExpired" />
			
			<display:setProperty name="export.excel.filename" value="Post List.xls"/>
	        <display:setProperty name="export.csv.filename" value="Post List.csv"/>
	        <display:setProperty name="export.pdf.filename" value="Post List.pdf"/>
		</display:table>
<% 
/*
<display:table name="test" cellspacing="0" cellpadding="0" pagesize="2" export="true">
<display:table name="testList" sort="external" defaultsort="1" pagesize="20" id="element" partialList="true" size="resultSize"
该标签的作用是输出一个表格。name属性为接受的数据,此标签输出的就是test对象的数据。
pagesize为该标签的分页功能，指定pagesize的大小就指定了一个页面显示的数据行数，并提供分页导航功能
export="true"为提示该页面是否提供导出功能，目前提供csv,xls,pdf等输出形式。
sort="external"  告诉DisplayTag传入的数据集已经由外部程序排好序了..
defaultsort="1" 说明默认是升序(Descending is 2, Ascending is 1);
partialList="true" 说明部分装入数据;
pagesize="20" 每页显示记录数;
size="resultSize" 显示记录的总条数(此参数结合PageSize，使得表格在只拿到某一页的完整数据的同时，可以知道会有多少页，并将其他的页数也列举出来，当用户实际翻页时才去获取当页数据);
requestURI="" 设定table的链接为空，否则将会是jsp文件的路径

display:column落实到显示具体的每列数据。以此段代码为例，该列输出该条记录属性为id的数据。并显示列明为ID,sortable="true"提供的功能是该列是否提供排序功能。

Name属性：必须指定，表示scope中的数据标志，通过name来引用scope中的数据并进行显示。可以指定pageScope，requestScope，sessionScope和applicationScope。其中requestScope是缺省的scope，如果数据像request.setAttribute(“Infolist”,list)的形式放入requestScope，那么name属性可以直接像[name=“Infolist”]的形式指定。而像sessionScope的话，就要像上面的例子中那种形式指定了。

Id属性：指定显示表格的唯一标志，在后面你可以通过yourID_rowNum的形式显示或者使用每行数据的行号。比如：如果指定id=”tableID”，那么，< %=tableID_rowNum% >就会输出数据的行号。而指定id的另一个作用就是，如果一个页面中有多个分页显示表格的话，指定id后，各个表格的分页就可以工作正常。

Export属性：需要指定boolean型的值，如果指定export=true的话，表格显示完成后，下面会有一个输出项条目，指定数据导出的选项；否则，不显示数据导出条目。默认为false。

Pagesize属性：指定每页最多显示的数据总数。如果要显示的数据记录很长的话，指定pagesize后，数据将按照pagesize属性指定的数目显示记录数，其他的数据将分多个页面显示。如果不指定该属性，所有数据将在一个页面显示。

Class属性：指定表格显示所要使用的css风格，displaytag提供了ISIS，ITS，Mars，Simple，Report五种风格，默认是ISIS，也就是上面的黄色色调的风格。这些风格都是在screen.css文件中定义的，可以根据需要修改或者添加需要的风格。

RequestURI属性：当表格需要数据导出，排序或者分页显示的时候，因为要提交给指定的URL处理，而这个属性就是做这个事情的。

Sort属性：用来指定对数据进行排序的时候是对整个的数据list进行排序还是只对当前页面的数据进行排序。默认的不指定该属性的情况下，排序的时候只对当前页面数据进行排序；如果指定sort＝“list”的话，则可以对整个的数据list进行排序。


http://www.cnblogs.com/chinafine/articles/1801344.html
*/		
%>
</body>
</html>