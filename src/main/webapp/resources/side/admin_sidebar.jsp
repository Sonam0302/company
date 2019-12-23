 <div class="sidebar">

         <div id="image"  ></div>  
   
   
  	<a href="introduction">Introduction</a>
  	<a href="line">Graph</a>
<button class="dropdown-btn">Employee<i class="fa fa-caret-down"></i>
</button>

<div class="dropdown-container">
 
   <a href="employee_view"><i class="fa fa-circle-o"></i>List</a>
   <a href="add_employee"><i class="fa fa-circle-o"></i>Add</a>
   
</div>
<a href="resetpassword">Reset Password</a>

  
</div>


  <script>
/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
var dropdown = document.getElementsByClassName("dropdown-btn");
var i;

for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener("click", function() {
  this.classList.toggle("active");
  var dropdownContent = this.nextElementSibling;
  if (dropdownContent.style.display === "block") {
  dropdownContent.style.display = "none";
  } else {
  dropdownContent.style.display = "block";
  }
  });
}
</script>