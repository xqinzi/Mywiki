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
<a href="/user/add" >添加</a><br/>
<a href="/display" >display</a><br/>
<a href="/user/sendMail" >发邮件</a><br/>
<table>
	<tr>
		<td>ID</td>  
		<td>email</td>
		<td>password</td>
		<td>description</td>
		<td>nickname</td>
		<td>firstName</td>
		<td>lastName</td>
		<td>phoneNumber</td>
		<td>authorities</td>
		<td>enabled</td>
		<td>accountExpired</td>
		<td>accountLocked</td>
		<td>credentialsExpired</td>
	</tr>
	<c:forEach var="u" items="${lu}">
	<tr>
		<td>${u.uuid}</td> 
		<td>${u.email}</td>  
		<td>${u.password}</td>
		<td>${u.description}</td>
		<td>${u.nickname}</td>
		<td>${u.firstName}</td>
		<td>${u.lastName}</td>
		<td>${u.phoneNumber}</td>
		<td>${u.authorities}</td>
		<td>${u.enabled}</td>
		<td>${u.accountExpired}</td>
		<td>${u.accountLocked}</td>
		<td>${u.credentialsExpired}</td>
	</tr>
	</c:forEach>
</table>  
</body>
</html>