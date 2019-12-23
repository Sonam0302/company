<%@include file="/resources/header/company_head.jsp"%>

<div style="height: 440px">
	<div class="main">
		<h3>Details</h3>
		<form commandname="user" action="/company/update" method="POST">
			<input type="hidden" name="id" id="emp_id"> <input
				type="hidden" id="password" name="password"> <input
				type="hidden" id="com_id" name="com_id">

			<div class="w3-row-padding">
				<div class="w3-half">
					<label>Name</label> <input class="w3-input w3-border" type="text"
						name="name" id="emp_name"> <br> <label>Date
						of Birth</label> <input class="w3-input w3-border" type="date" name="dob"
						id="dob"> <br> <label>Gender</label> <select
						class="w3-input w3-border" name="gender" id="gender">
						<option></option>
						<option value="female">Female</option>
						<option value="male">Male</option>
						<option value="other">Other</option>
					</select> <br> <label>Contact</label> <input class="w3-input w3-border"
						type="text" name="contact_no" id="contact"> <br> <label>Email</label>
					<input class="w3-input w3-border" type="text" name="email"
						id="emp_email"> <br>

				</div>

				<div class="w3-half">
					<label>Address</label> <input class="w3-input w3-border"
						type="text" name="address" id="address"> <br> <label>City</label>
					<input class="w3-input w3-border" type="text" name="city" id="city">
					<br> <label>State</label> <input class="w3-input w3-border"
						type="text" name="state" id="state"> <br> <label>Pincode</label>
					<input class="w3-input w3-border" type="text" name="pincode"
						id="pincode"> <br> <label>Date of Joining</label> <input
						class="w3-input w3-border" type="date" name="doj" id="doj">
					<br>

				</div>
				<input class="w3-input w3-border" type="submit" name="submit"
					value="Submit">
			</div>

		</form>
	</div>
</div>



<script src="/resources/js/edit_employee.js"></script>
<%@include file="/resources/side/admin_sidebar.jsp"%>
</body>
</html>
