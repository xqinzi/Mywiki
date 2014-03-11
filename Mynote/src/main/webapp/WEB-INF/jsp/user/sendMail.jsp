<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="user" action="/user/sendMail" method="post">    
  
	<table>
	    <tr>  
			<td>收件人</td>  
			<td><input id="mail" name="mail" type="text" value=""/></td>  
			<td></td>
	    </tr>
	    <tr>  
			<td>主题</td>  
			<td><input id="subject" name="subject" type="text" value=""/></td>  
			<td></td>
	    </tr>
	    <tr>  
			<td>内容</td>  
			<td><input id="content" name="content" type="text" value=""/></td>  
			<td></td>
	    </tr>
	    <tr>  
			<td colspan="3"><input type="submit" /></td>  
	    </tr>  
	</table>  
</form> 
</body>
</html>