 $(document).ready(function(){
  $("#submit").click(function() {
 if($("#password_old").val() == '') {
      $("#passwordold_error").html ('Please Enter Your Password');
      $("#password_old").focus();
      return false;
    }  $('#passwordold_error').html("");

if($("#password").val() == '') {
      $("#password_error").html ('Please Enter Your New Password');
      $("#password").focus();
      return false;
    }  $('#password').html("");


    if($("#password").val() != '') {
      if(!/^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,}).*$/.test($("#password").val())){
      $("#password_error").html ("6 to 20 characters which contain at least one numeric digit, one uppercase and one lowercase letter");
      $("#password").focus();
      return false;
    } $('#password_error').html("");
} if($('#con_password').val() == ''){
        $('#conpassword_error').html('Please confirm your Password');
        $('#con_password').focus();
        return false;
      }$('#conpassword_error').html('');
 if($('#con_password').val() !=$('#password').val()){
        $('#conpassword_error').html('confirm password does not matched');
          $('#con_password').focus();
          return false;
      }
     
  
    else {
      $("#userlogin").submit();
    }
  });
});


