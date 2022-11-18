<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.example.dao.MemberDAO, com.example.bean.MemberVO"%>
<%@ page import="com.example.util.FileUpload" %>


<%
	String sid = request.getParameter("id");
	if (sid != ""){  
		int id = Integer.parseInt(sid);
		MemberVO u = new MemberVO();
		String filename = MemberDAO.getPhotoFilename(id);
		if(filename!=null)
			FileUpload.deleteFile(request,filename);
		u.setSid(id);
		MemberDAO memberDAO = new MemberDAO();
		memberDAO.deleteMember(u);
	}
	response.sendRedirect("posts.jsp");
%>