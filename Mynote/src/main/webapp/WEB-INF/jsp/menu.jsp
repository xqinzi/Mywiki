<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>strute menu</title>
</head>
<body>
	<menu:useMenuDisplayer name="TabbedMenu">
		<menu:displayMenu name="Home" />
		<menu:displayMenu name="About" />
	</menu:useMenuDisplayer>



	<menu:useMenuDisplayer name="Velocity" config="displayMenu.vm"
		permissions="rolesAdapter">
		<security:authorize ifAnyGranted="ROLE_SysadminUser">
			<menu:displayMenu name="Admin" />
		</security:authorize>
	</menu:useMenuDisplayer>
</body>
</html>