<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>




<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">   -->
<link rel="stylesheet" type="text/css" href="/resources/css/intro.css">	
<link rel="stylesheet" type="text/css" href="/resources/css/w3.css">	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resources/js/random_key.js"></script>


</head>
<body>
<div>
	<div class="header">
	       <ul>
    			<li><h2>Welcome <div id="name" class="name"></div></h2></li>    			
				<li style="float: right;"><a href="logout"><p>LOGOUT</p></a></li>
			</ul>
      	</div>    
     
      <!-- main -->
      	<div style="height:440px">
      		<div class="main">	
   <p>Company Id:</p><div id="id" class="id"></div><br>
	<p>Name: </p>	<div id="name1" class="id"></div><br>
	<p>Email: </p><div id="email" class="id"></div><br>
	<p>Random_Key:</p>	<div id="random_key" class="id"></div> 
	<div id="summary"></div>
			<input type="submit" class="reg"  id="btnAddkey"  > 			
						
		
 			

	
      		</div>
      	
      	</div>
      	 
      <!-- sidebar -->
           
 </div>
 <script src="/resources/js/company_intro.js"></script>
<%@include file="/resources/side/admin_sidebar.jsp" %>      


</body>
</html>