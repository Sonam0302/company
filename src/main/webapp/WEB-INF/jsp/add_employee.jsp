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
 
<link rel="stylesheet" type="text/css" href="/resources/css/add_editEmployee.css">	
<link rel="stylesheet" type="text/css" href="/resources/css/w3.css">	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
<div>
	 <div class="header">
	       <ul>
    			<li><h2>Welcome <div id="name" class="name"></h2></li>    			
				<li style="float: right;"><a href="logout"><p>LOGOUT</p></a></li>
			</ul>
      	</div>    
    


<div class="main">
  <h3>Details</h3>
  <form  action="saveEmployee" method="POST" id="userlogin" >
 <div class="w3-row-padding">
  <div class="w3-half">
  
    <label>Name</label>
    <input class="w3-input w3-border" type="text" name="name" id="name">
    <div id="name_error" style="color:red;"></div>
    <br>
    <label>Date of Birth</label>
    <input class="w3-input w3-border" type="date" name="dob" id="date" >
    <div id="date_error" style="color:red;"></div>
    <br>
     <label>Gender</label>
     <select class="w3-input w3-border"   name="gender" id="gender" >
       <option value="">select Gender</option>
        <option value="female">Female</option>
        <option value="male">Male</option>
        <option value="other">Other</option>
     </select>
     <div id="gender_error" style="color:red;"></div>
    <br>
    
      
     <label>Email</label>
    <input class="w3-input w3-border" type="text" name="email" id="email" >
    <div id="email_error" style="color:red;"></div>
    <br>
   <label>Password</label>
    <input class="w3-input w3-border" type="password" name="password" id="password" >
    <div id="password_error" style="color:red;"></div>
    <br>
    <label>Confirm Password</label>
    <input class="w3-input w3-border" type="password"  id="con_password" >
    <div id="conpassword_error" style="color:red;"></div>
    <br>
  </div>

  <div class="w3-half">
 <label>Contact</label>
    <input class="w3-input w3-border" type="text" name="contact_no" id="contact" maxlength="10" >
    <div id="contact_error" style="color:red;"></div>
    <br>
    <label>Address</label>
    <input class="w3-input w3-border" type="text"  name="address" id="address">
    <div id="address_error" style="color:red;"></div>
    <br>
    <label>City</label>
    <input class="w3-input w3-border" type="text"  name="city" id="city">
    <div id="city_error" style="color:red;"></div>
    <br>
     <label>State</label>
    <input class="w3-input w3-border" type="text"  name="state" id="state" >
    <div id="state_error" style="color:red;"></div>
    <br>
     <label>Pincode</label>
    <input class="w3-input w3-border" type="text" name="pincode" id="pincode" maxlength="6"><div id="pincode_error" style="color:red;"></div>
    <br>
    <label>Date ofJoining</label>
    <input class="w3-input w3-border" type="date" name="doj" id="doj" >
    <div id="doj_error" style="color:red;"></div>
    <br>
       
    <input class="w3-input w3-border" type="hidden" name="com_id" id="id"  >
    <br>
     
  
  

  </div>
  <input class="w3-input w3-border" type="submit" name="submit" value="Submit" id="submit">
</div>

</form>

</div>
</div>
<script src="/resources/js/validation/addEmployeeValidation.js"></script>
 <script src="/resources/js/company_intro.js"></script>
<%@include file="/resources/side/admin_sidebar.jsp" %>
</body>
</html> 
