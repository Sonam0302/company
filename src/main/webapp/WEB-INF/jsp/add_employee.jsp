<%@include file="/resources/header/company_head.jsp"%>
<div class="main">
	<h3>Details</h3>
	<form action="saveEmployee" method="POST" id="userlogin">
		<div class="w3-row-padding">
			<div class="w3-half">

				<label>Name</label> <input class="w3-input w3-border" type="text"
					name="name" id="emp_name">
				<div id="empname_error" style="color: red;"></div>
				<br> <label>Date of Birth</label> <input
					class="w3-input w3-border" type="date" name="dob" id="date">
				<div id="date_error" style="color: red;"></div>
				<br> <label>Gender</label> <select class="w3-input w3-border"
					name="gender" id="gender">
					<option value="">select Gender</option>
					<option value="female">Female</option>
					<option value="male">Male</option>
					<option value="other">Other</option>
				</select>
				<div id="gender_error" style="color: red;"></div>
				<br> <label>Email</label> <input class="w3-input w3-border"
					type="text" name="email" id="email">
				<div id="email_error" style="color: red;"></div>
				<br> <label>Password</label> <input class="w3-input w3-border"
					type="password" name="password" id="password">
				<div id="password_error" style="color: red;"></div>
				<br> <label>Confirm Password</label> <input
					class="w3-input w3-border" type="password" id="con_password">
				<div id="conpassword_error" style="color: red;"></div>
				<br>
			</div>

			<div class="w3-half">
				<label>Contact</label> <input class="w3-input w3-border" type="text"
					name="contact_no" id="contact" maxlength="10">
				<div id="contact_error" style="color: red;"></div>
				<br> <label>Address</label> <input class="w3-input w3-border"
					type="text" name="address" id="address">
				<div id="address_error" style="color: red;"></div>
				<br> <label>City</label> <input class="w3-input w3-border"
					type="text" name="city" id="city">
				<div id="city_error" style="color: red;"></div>
				<br> <label>State</label> <input class="w3-input w3-border"
					type="text" name="state" id="state">
				<div id="state_error" style="color: red;"></div>
				<br> <label>Pincode</label> <input class="w3-input w3-border"
					type="text" name="pincode" id="pincode" maxlength="6">
				<div id="pincode_error" style="color: red;"></div>
				<br> <label>Date ofJoining</label> <input
					class="w3-input w3-border" type="date" name="doj" id="doj">
				<div id="doj_error" style="color: red;"></div>
				<br> <input class="w3-input w3-border" type="hidden"
					name="com_id" id="id"> <br>

			</div>
			<input class="w3-input w3-border" type="submit" name="submit"
				value="Submit" id="submit">
		</div>

	</form>

</div>

<script src="/resources/js/validation/addEmployeeValidation.js"></script>
<script src="/resources/js/company_intro.js"></script>
<%@include file="/resources/side/admin_sidebar.jsp"%>
</body>
</html>
