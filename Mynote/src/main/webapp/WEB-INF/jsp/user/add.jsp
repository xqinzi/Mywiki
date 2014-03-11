<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form:form method="post" commandName="user">    
<form:errors path="*" cssClass="error" />  
	<table>
	    <tr>  
			<td>名称</td>  
			<td><form:input path="email"/></td>  
			<td><form:errors path="email" /></td>
	    </tr>
	    <tr>  
			<td>密码</td>
			<td><form:input path="password" /></td>  
			<td><form:errors path="password" /></td>
	    </tr>
	    <tr>  
			<td>简介</td>
			<td><form:input path="description" /></td>  
			<td><form:errors path="description" /></td>
	    </tr>
	    <tr>  
			<td>昵称</td>
			<td><form:input path="nickname" /></td>  
			<td><form:errors path="nickname" /></td>
	    </tr>
	    <tr>  
			<td>姓</td>
			<td><form:input path="firstName" /></td>  
			<td><form:errors path="firstName" /></td>
	    </tr>
	    <tr>  
			<td>名字</td>
			<td><form:input path="lastName" /></td>  
			<td><form:errors path="lastName" /></td>
	    </tr>
	    <tr>  
			<td>手机号码</td>
			<td><form:input path="phoneNumber" /></td>  
			<td><form:errors path="phoneNumber" /></td>
	    </tr>
	    <tr>  
			<td>角色</td>
			<td><form:input path="authorities" /></td>  
			<td><form:errors path="authorities" /></td>
	    </tr>
	    <tr>  
			<td>启用</td>
			<td><form:checkbox path="enabled" id="enabled"/></td>  
			<td><form:errors path="enabled" /></td>
	    </tr>
	    <tr>  
			<td>过期</td>
			<td><form:checkbox path="accountExpired" id="accountExpired"/></td>  
			<td><form:errors path="accountExpired" /></td>
	    </tr>
	    <tr>  
			<td>锁定</td>
			<td><form:checkbox path="accountLocked" id="accountLocked"/></td>  
			<td><form:errors path="accountLocked" /></td>
	    </tr>
	    <tr>  
			<td>证书过期</td>
			<td><form:checkbox path="credentialsExpired" id="credentialsExpired"/></td>  
			<td><form:errors path="credentialsExpired" /></td>
	    </tr>
	    
	    
	    
	    <tr>  
			<td colspan="3"><input type="submit" /></td>  
	    </tr>  
	</table>  
</form:form> 
</body>
</html>