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
<link rel="stylesheet" type="text/css" href="/resources/css/intro.css">	
<link rel="stylesheet" type="text/css" href="/resources/css/w3.css">	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
  <div class="header">
	       <ul>
    			<li><h2>Welcome <div id="com_name" class="name"></div> </h2></li>    			
				<li style="float: right;"><a href="logout"><p>LOGOUT</p></a></li>
			</ul>
      	</div>    
<div style="height:440px">
<div class="main">
  <h3>Details</h3>
  <form commandname="user" action="/company/update" method="POST" >
  
 <input type="hidden"  name="id" id="emp_id">
 <input type="hidden" id="password" name="password">
 
  <input type="hidden" id="com_id" name="com_id">
  <div class="w3-row-padding">
  <div class="w3-half">
    <label>Name</label>
  
    <input class="w3-input w3-border" type="text" name="name"  id="emp_name">
    <br>
    <label>Date of Birth</label>
    <input class="w3-input w3-border" type="date" name="dob" id="dob">
    <br>
     <label>Gender</label>
     <select class="w3-input w3-border"   name="gender" id="gender">
       <option ></option>
        <option value="female">Female</option>
        <option value="male">Male</option>
        <option value="other">Other</option>
     </select>
    <br>
    
     <label>Contact</label>
    <input class="w3-input w3-border" type="text" name="contact_no" id="contact">
    <br>
     <label>Email</label>
    <input class="w3-input w3-border" type="text" name="email" id="emp_email">
    <br>
  
  </div>

  <div class="w3-half">
    <label>Address</label>
    <input class="w3-input w3-border" type="text"  name="address"  id="address">
    <br>
    <label>City</label>
    <input class="w3-input w3-border" type="text"  name="city" id="city">
    <br>
     <label>State</label>
    <input class="w3-input w3-border" type="text"  name="state" id="state">
    <br>
     <label>Pincode</label>
    <input class="w3-input w3-border" type="text" name="pincode" id="pincode">
    <br>
    <label>Date of Joining</label>
    <input class="w3-input w3-border" type="date" name="doj" id="doj">
    <br>
  
  </div>
  <input class="w3-input w3-border" type="submit" name="submit" value="Submit">
</div>

</form>
</div>
</div>


 
<script src="/resources/js/edit_employee.js"></script>
<%@include file="/resources/side/admin_sidebar.jsp" %>  
</body>
</html> 
