package com.mywiki.cxf;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywiki.model.User;
import com.mywiki.service.UserManager;

@Service("usercxf")
public class UserCxfImpl   {

	@Autowired
	private UserManager um;
	
	@Context
	private Request request;
	
	private final Log log = LogFactory.getLog(UserCxfImpl.class);
	
	/**
	 * 
	 */
	@GET
	@Path("/user")
	@Produces({ MediaType.APPLICATION_JSON })
	public User getUser(@QueryParam("id") String id) {
	    	if(um.exists(Long.valueOf(id)))
	    	    return um.get(Long.valueOf(id));
	    	return null;
	}
	
	
	/**
	 * 
	 */
	@GET
	@Path("/user/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<User> getList(@PathParam("id") String id) {
		return um.getAll();
	}
	
	/**
	 * 
	 */
	@POST
	@Path("/user/in")
	@Produces({ MediaType.APPLICATION_JSON })
    public Map<String, String> userin(
	    @FormParam("userName") String userName,
	    @FormParam("password") String password
	    ) {
	    	HashMap<String, String> map=new HashMap<String, String>();
        	try {
        		User u=new User();
        		u.setEmail(userName);
        		u.setPassword(password);
        		u=um.save(u);
        	    
        	    map.put("id", u.getUuid().toString());
        	    map.put("userName", u.getEmail());
        	    map.put("password", u.getPassword());
        	} catch (Exception e) {
        	    e.printStackTrace();
        	}
        	return map;
	}
}
