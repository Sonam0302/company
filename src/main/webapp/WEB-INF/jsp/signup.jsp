<!DOCTYPE html>
<html>
<head>
	<title></title>
	<link rel="stylesheet" type="text/css" href="/resources/css/login.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/fonts.css">
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<div class="login-page">
  <div class="form">
  	  <h1>Register Here</h1>
 <form action="/company/myForm" method="post" id="userlogin" class="login-form">
 <input type="hidden" name="google_id" id="google_id" value="${google_id}"  />		
 	  <input type="text" name="com_name" id="com_name" value="${com_name}" placeholder="Please Enter Your company's Name"/>
 	  <div id="comname_error" style="color:red;"></div>
      <input type="email" name="com_email" id="com_email" value="${email}" placeholder="Please Enter Your Email" readonly/>
      <div id="comemail_error" style="color:red;"></div>
      <input type="password" name="com_pass" id="com_pass" value="${password}"placeholder="Please Enter Your Password" readonly/>
      <div id="compass_error" style="color:red;"></div>
      <input type="password" id="con_password" value="${password}" placeholder="Please Confirm Your Password" readonly/><div id="conpassword_error" style="color:red;"></div>
      <input type="text" name="picture_url" value="${image}" id="logo" readonly/>
      <div id="logo_error" style="color:red;"></div>
      <input type="submit" name="submit" value="submit" class="submit" id="submit" />
    </form>
  
    <p class="message">Company Already Registered?</p>
    <p class="message"> <a href="/company/">Login Here</a></p>
  </div>
</div>
</body>
<script src="/resources/js/validation/signupValidation.js"></script>

</html>