<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="com.example.dao.MemberDAO, com.example.bean.MemberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Form</title>
<%--	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">--%>
<%--	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">--%>
<%--	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>--%>
</head>
<body>

<%
	MemberDAO memberDAO = new MemberDAO();
	String id=request.getParameter("id");	
	MemberVO u=memberDAO.getMember(Integer.parseInt(id));
	request.setAttribute("u",u);
%>

<h1>Edit Form</h1>
<form action="editpost.jsp" method="post" enctype="multipart/form-data">
<input type="hidden" name="sid" value="<%=u.getSid() %>"/>
<table id="edit">
	<tr><td>User id:</td><td><input type="text" name="userid" value="<%= u.getUserid()%>"/></td></tr>
<tr><td>User name:</td><td><input type="text" name="username" value="<%= u.getUsername()%>"/></td></tr>
	<tr><td>Photo:</td><td><input type="file" name="photo"value="<%= u.getPhoto()%>"/></td></tr>
	<c: if test="${u.getPhoto() ne ''}"><br/><img src = "${pageContext.request.contextPath}/upload/${u.getPhoto()}" class ="photo"></c:></td>
	<tr><td>password:</td><td><input type="text" name="password" value="<%= u.getPassword()%>"/></td></tr>
<tr><td>Email:</td><td><input type="text" name="email" value="<%= u.getEmail()%>" /></td></tr>
<%--<tr><td>Content:</td><td><textarea cols="50" rows="5" name="content"><%= u.getContent()%></textarea></td></tr>--%>
<tr><td colspan="2"><input type="submit" value="Edit Post"/>
<input type="button" value="Cancel" onclick="history.back()"/></td></tr>
</table>
</form>

</body>
</html>