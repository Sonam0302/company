<%@include file="/resources/header/company_head.jsp"%>
<!-- main -->
<div style="height: 440px">
	<div class="main">
		<p>Company Id:</p>
		<div id="id" class="id"></div>
		<br>
		<p>Name:</p>
		<div id="name1" class="id"></div>
		<br>
		<p>Email:</p>
		<div id="email" class="id"></div>
		<br>
		<p>Random_Key:</p>
		<div id="random_key" class="id"></div>
		<div id="summary"></div>
		<input type="submit" class="reg" id="btnAddkey">
	</div>
</div>
<!-- sidebar -->
<script src="/resources/js/company_intro.js"></script>
<script src="/resources/js/random_key.js"></script>
<%@include file="/resources/side/admin_sidebar.jsp"%>
</body>
</html>