<%@include file="/resources/header/employee_viewTable.jsp"%> 
     
      <!-- main -->
      	<div style="height:440px">
      		<div class="main">	
      		<ul>
      		    <li>Employee</li>
      		    <li style="float: right"><button class="btn default" onclick="myFunction()">Download List</button></li>
      	
     </ul>
      	<table id="example"class="display" style="width:100%" >
				<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Contact</th>
					<th>Edit</th>
					<th>Delete</th>
				 
				</tr>
				</thead>
				<tfoot>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Contact</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				</tfoot>
			
			</table>
      	</div>
      	</div>
      
           
 </div>
  


<script src="/resources/js/datatable/jquery-3.3.1.js"></script>
<script src="/resources/js/datatable/jquery.dataTables.min.js"></script>
<script src="/resources/js/datatable/pagination.js"></script>
 <script src="/resources/js/company_intro.js"></script>
  <script src="/resources/js/empdownload.js"></script>
<%@include file="/resources/side/admin_sidebar.jsp" %>
</body>
</html>