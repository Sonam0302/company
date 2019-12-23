<!DOCTYPE html>
<html>
<head>
	<title></title>
	
	<link rel="stylesheet" type="text/css" href="/resources/css/login.css">
	
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	 <script type="text/javascript">
  
</script>
	
	</head>
<body>
<div class="login-page">
  <div class="form">
  	  <h1>Register Here</h1>
 <form action="save" method="POST" id="userlogin" class="login-form"  enctype="multipart/form-data">
 	  <input type="text" name="com_name" id="com_name" placeholder="Please Enter Your company's Name"/>
 	  <div id="comname_error" style="color:red;"></div>
      <input type="email" name="com_email" id="com_email" placeholder="Please Enter Your Email"/>
      <div id="comemail_error" style="color:red;"></div>
      <input type="password" name="com_pass" id="com_pass"placeholder="Please Enter Your Password"/>
      <div id="compass_error" style="color:red;"></div>
      <input type="password" id="con_password"  placeholder="Please Confirm Your Password"/><div id="conpassword_error" style="color:red;"></div>
     
<label class="upload-btn">
      <input type="file"  name="image" onchange="changeText()" id="logo" size="50"  accept=".jpg,.png,.bmp"/>
      <span id="selectedFileName">Please Upload Your Company Logo</span><div id="logo_error" style="color:red;"></div>
     </label>


      <input type="submit" name="submit" value="submit" class="submit" id="submit" />
    </form>
    <p class="message">-----Or Registered With-----</p>
    <button onclick="window.location.href = 'https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/callback&response_type=code&client_id=931062670962-h40kejm06seg3vuvtvicoe1vm302mm0t.apps.googleusercontent.com&approval_prompt=force';" id="google">Sign up With Google</button>
    <p class="message">Already Registered? <a href="/company/">Login Here</a></p>
  </div>
</div>
</body>
<script src="/resources/js/validation/registrationValidation.js">

</script>

</html>