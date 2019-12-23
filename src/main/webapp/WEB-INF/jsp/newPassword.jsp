<%@include file="/resources/header/company_head.jsp"%>
<div class="main">
	<h3>Details</h3>
	<form action="updatePassword" method="POST" id="userlogin">
		<label>Old Password</label> <input class="w3-input w3-border"
			type="password" name="com_pass_old" id="password_old">
		<div id="passwordold_error" style="color: red;"></div>
		<br> <label>Password</label> <input class="w3-input w3-border"
			type="password" name="com_pass" id="password">
		<div id="password_error" style="color: red;"></div>
		<br> <label>Confirm Password</label> <input
			class="w3-input w3-border" type="password" id="con_password">
		<div id="conpassword_error" style="color: red;"></div>
		<br> <input class="w3-input w3-border" type="hidden"
			name="com_id" id="id"> <input class="w3-input w3-border"
			type="hidden" name="com_email" id="email"> <input
			class="w3-input w3-border" type="hidden" name="com_name" id="name1">
		<br> <input class="w3-input w3-border" type="submit"
			name="submit" value="Submit" id="submit">
	</form>

</div>


</div>

<script src="/resources/js/validation/newPasswordValidation.js"></script>
<script src="/resources/js/company_intro.js"></script>
<%@include file="/resources/side/admin_sidebar.jsp"%>

</body>
</html>
