$(document).ready(function(){
  $("#submit").click(function() {
if($("#com_name").val() == '') {
      $("#comname_error").html ('Please Enter Your Company Name');
      $("#com_name").focus();
      return false;
    }  $('#comname_error').html("");

	    if($("#com_name").val() != '') {
	      if ($("#com_name").val().length <=3){
	      $("#comname_error").html ('Please Enter Your Full Company Name');
	      $("#com_name").focus();
	      return false;
	    } 
	  }$('#comname_error').html("");
if($("#com_email").val() == '') {
      $("#comemail_error").html ('Please Enter Your Company Email Id');
      $("#com_email").focus();
      return false;
    }  $('#comemail_error').html("");
    if($("#com_email").val() != '') {
     if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,6})+$/.test($("#com_email").val())){
      $("#comemail_error").html ('Please Enter Your Valid Email Id');
      $("#com_email").focus();
      return false;
    } 
  }$('#comemail_error').html("");
   if($("#com_pass").val() == '') {
      $("#compass_error").html ('Please Enter Your Password');
      $("#com_pass").focus();
      return false;
    }  $('#compass_error').html("");
    if($("#com_pass").val() != '') {
      if(!/^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,}).*$/.test($("#com_pass").val())){
      $("#compass_error").html ("6 to 20 characters which contain at least one numeric digit, one uppercase and one lowercase letter");
      $("#com_pass").focus();
      return false;
    } 
} if($('#con_password').val() == ''){
        $('#conpassword_error').html('Please confirm your Password');
        $('#con_password').focus();
        return false;
      }
 if($('#con_password').val() !=$('#com_pass').val()){
        $('#conpassword_error').html('confirm password does not matched');
          $('#con_password').focus();
          return false;
      }$('#conpassword_error').html('');
     
      if($('#logo').val()==''){
        $('#logo_error').html( 'Please select one image');
        $('#logo').focus();
        return false;
      }



    else {
      $("#userlogin").submit();
    }
  });
});