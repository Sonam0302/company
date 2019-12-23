$(document).ready(function() {
	
	var emp_id=GetURLParameter("id");
	$('#emp_id').val(emp_id);
	
	  $.ajax({
	 url: "editEmployee_info",
	 type: "get",
	data:{data1:emp_id},
	success : function(result){
		$('#com_id').val(result.com_id);
	 	$('#emp_name').val(result.emp_name);
	 	$('#dob').val(result.dob);
	 	$('#gender').val(result.gender);
	 	$('#emp_email').val(result.emp_email);
	 	$('#address').val(result.address);
	 	$('#city').val(result.city);
	 	$('#state').val(result.state);
 	 	$('#contact').val(result.contact);
	 	$('#pincode').val(result.pincode);
	 	$('#doj').val(result.doj);
	 	$('#password').val(result.password);
	 	$('#com_name').html(result.com_name);
	 	
	 	if(result.imageurl!=null){
			$('#image').html('<img src="' + result.imageurl + '",class="avatar",width="420px" height="150px"  />');
		}
		else{	
				$('#image').html('<img src="data:image/png;base64,' + result.image + '",class="avatar",width="250px" height="150px"  />');
				
		}
	 	
	 }

	 });
	 });
	 
function GetURLParameter(sParam)
{

    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
     var sParameterName = sURLVariables[i].split('=');
     if (sParameterName[0] == sParam)
     {
        return sParameterName[1];
        }

    }

}