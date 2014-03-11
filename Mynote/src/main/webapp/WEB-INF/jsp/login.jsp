<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator"
	prefix="v"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
	<h1>欢迎访问，登录页面</h1>
	<form action="/login" method="post" id="user" name="user" onsubmit="return validateUser(this);">
		<table>
			<tr>
				<td>用户名：</td>
				<td><spring:bind path="user.email">
						<form:input path="user.email"/><form:errors path="user.email"/>
						<font color="red"><c:out value="${status.errorMessage}" /></font>
					</spring:bind></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><spring:bind path="user.password">
						<form:input path="user.password"/><form:errors path="user.password"/>
						<font color="red"><c:out value="${status.errorMessage}" /></font>
					</spring:bind></td>
			</tr>
			<tr>
				<td><input type="submit" value="登录"></td>
				<td></td>
			</tr>
		</table>
	</form>
</body>
</html>
<v:javascript formName="user" staticJavascript="true" xhtml="true"	cdata="false" />