$(document).ready(function(){
  $("#submit").click(function() {
	  
	  if($('#name').val() ==''){
		   $('#name').focus();
		   $('#name_error').html = 'Please enter employee name';
		    return false;
		  }
		  if(!isNaN($('#name').val())){
		    $('#name_error').html = 'Please enter only alphabate';
		  $('#name').focus();
		    return false;
		  }$('#name_error').html = '';
		  if($('#date').val() ==''){
		   $('#date').focus();
		   $('#date_error').html = 'Please enter  Date of Birth';
		    return false;
		  }$('#date_error').html = '';
		if($('#gender').val() ==''){
		   $('#gender').focus();
		   $('#gender_error').html = 'Please select gender';
		    return false;
		  }$('#gender_error').html = '';
		if($('#email').val() == ''){
		   $('#email_error').html = 'Please enter  email id';
		    $('#email').focus();
		    return false;
		  }
		  if($('#email').val() != ''){
		    if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,6})+$/.test($('#email').val()))
		      {
		          $('#email_error').html = 'Please enter  valid email id';
		      $('#email').focus();
		      return false;
		      }
		  }$('#email_error').html = '';
		  if($('#password').val() == ''){
		    $('#password_error').html = 'Please enter  Password';
		    $('#password').focus();
		    return false;
		  }
		 
		  if($('#password').val() != ''){
		    if (!/^.*(?=.{6,})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$/.test($('#password').val()))
		      {
		      $('#password_error').html = 'password must contain atleast one Capital letter,one special character,numeric val()';
		      $('password').focus();
		      return (false)
		      }
		  }$('#password_error').html = '';
		if($('#con_password').val() == ''){
		    $('#conpassword_error').html = 'Please confirm your Password';
		    $('#con_password').focus();
		    return false;
		  }

		  if($('#con_password').val() !=$('password').val()){
		    $('#conpassword_error').html = 'confirm password does not matched';
		      $('#con_password').focus();
		      return (false)
		  }$('#conpassword_error').html = '';
		  if($('#contact').val()==''){
		   $('#contact_error').html = 'Please enter employee contact number';      
		    $('#contact').focus();    
		    return false;    
		  }
		if($('#contact').val() != ''){
		    if($('#contact').val().length <10){
		    $('#contact_error').html = 'Please enter  10 digit contat number';
		    $('#contact').focus();
		    return false;
		    }
		    }$('#contact_error').html = '';
		    if($('#address').val() ==''){
		   $('#address').focus();
		   $('#address_error').html = 'Please enter employee Address';
		    return false;
		  }$('#address_error').html = '';
		    if($('#city').val() ==''){
		   $('#city').focus();
		   $('#city_error').html = 'Please enter employee City';
		    return false;
		  }$('#city_error').html = '';
		    if($('#state').val() ==''){
		   $('#state').focus();
		   $('#state_error').html = 'Please enter employee State';
		    return false;
		  }
		$('#state_error').html = '';
		    if($('#pincode').val() ==''){
		   $('#pincode').focus();
		   $('#pincode_error').html = 'Please enter employee Pincode';
		    return false;
		  }

		    if($('#doj').val() == ''){
		        $('#doj_error').html = 'Please seltec the date';
		        $('#doj').focus();
		        return false;
		      }
	  
  else {
	        $("#userlogin").submit();
	      }
	    });
	  });