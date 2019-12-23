 $(document).ready(function() {
	  $.ajax({
	 url: "emp_info",
	 type: "get",
	
	success : function(data){
		$('#name').html(data.name);
	 	$('#name1').html(data.name);
	 	$('#dob').html(data.dob);
	 	$('#gender').html(data.gender);
	 	$('#contact').html(data.contact);
	 	$('#address').html(data.address);
	 	$('#city').html(data.city);
	 	$('#state').html(data.state);
	 	$('#pincode').html(data.pincode);
	 	$('#doj').html(data.doj);
	 	$('#email').html(data.email);
	 	$('#random_key').html(data.emprandom_key);
	 	if(data.imageurl!=null){
			$('#image').html('<img src="' + data.imageurl + '",class="avatar",width="420px" height="150px"  />');
		}
		else{	
				$('#image').html('<img src="data:image/png;base64,' + data.image + '",class="avatar",width="250px" height="150px"  />');
				
		}
	 	if(data.emprandom_key==null){
							$('.reg').val("Generate");
		 	}
	 	else{
	 		$('.reg').val("Re-Generate");
		 	}
	 }

	 });
	 });
	 