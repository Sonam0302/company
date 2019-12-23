 $(document).ready(function() {
	  $.ajax({
	 url: "company_info",
	 type: "get",
	
	success : function(data){
		$('#id').html(data.com_id);
	 	$('#name').html(data.name);
	 	$('#name1').html(data.name);
	 	$('#email').html(data.email);
	 	$('#random_key').html(data.random_key);
	 	$('#imageurl').val(data.imageurl);
		if(data.imageurl!=null){
			$('#image').html('<img src="' + data.imageurl + '",class="avatar",width="420px" height="150px"  />');
		}
		else{	
				$('#image').html('<img src="data:image/png;base64,' + data.image + '",class="avatar",width="250px" height="150px"  />');
				
		}
	 	if(data.random_key==null){
							$('.reg').val("Generate");
		 	}
	 	else{
	 		$('.reg').val("Re-Generate");
		 	}
	 }

	 });
	 });
	 