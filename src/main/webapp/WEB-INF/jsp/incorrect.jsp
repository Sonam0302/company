<%@include file="/resources/header/login_head.jsp"%>
<body>

	<div class="login-page">
		<h3>Sorry You Have Enter a Wrong Email Id or Password</h3>

		<div class="form">
			<h1>Login Here</h1>
			<form action="alllogin" method="POST" id="userlogin"
				class="login-form">
				<select name="login_as" id="login_as" class="sel"
					placeholder="login as">
					<option value="">select Login as</option>
					<option value="company">Company</option>
					<option value="employee">Employee</option>
				</select>
				<div id="loginas_error" style="color: red;"></div>
				<input type="email" name="email" placeholder="Enter Your Email"
					id="email" />
				<div id="email_error" style="color: red;"></div>
				<input type="password" name="password"
					placeholder="Enter Your Password" id="password" />
				<div id="password_error" style="color: red;"></div>
				<input type="submit" name="submit" value="Submit" id="submit">



			</form>
			<p id="google1" class="message">-----Or-----</p>
			<button
				onclick="window.location.href = 'https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/callback&response_type=code&client_id=931062670962-h40kejm06seg3vuvtvicoe1vm302mm0t.apps.googleusercontent.com&approval_prompt=force';"
				id="google">Login With Google</button>

			<p class="message">Company Not registered?</p>
			<p class="message">
				<a href="registration">Create an account</a>
			</p>
		</div>
	</div>
</body>
</html>