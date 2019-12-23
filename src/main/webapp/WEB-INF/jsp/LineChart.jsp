<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/w3.css">	
<link rel="stylesheet" type="text/css" href="/resources/css/intro.css">	
</head>
<body>
	<div class="header">
	       <ul>
    			<li><h2>Welcome <div id="name" class="name"></h2></li>    			
				<li style="float: right;"><a href="logout"><p>LOGOUT</p></a></li>
			</ul>
      	</div>  
      	<div style="height:440px">
      		<div class="main">	
	<div id="container" class="con"></div>
	</div>
	</div>
	
	<script src="/resources/js/highcharts.js"></script>
	<script src="/resources/js/jquery.js"></script>
	<script src="/resources/js/index.js"></script>
 <script src="/resources/js/company_intro.js"></script>
<%@include file="/resources/side/admin_sidebar.jsp" %>
</body>
</html>