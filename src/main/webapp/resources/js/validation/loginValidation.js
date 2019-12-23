$(document).ready(function(){
  $("#submit").click(function() {
if($("#login_as").val() == '') {
      $("#loginas_error").html ('Please select login as');
      $("#login_as").focus();
      return false;
    }  $('#loginas_error').html("");



    if($("#email").val() == '') {
      $("#email_error").html ('Please enter your Registered email id');
      $("#email").focus();
      return false;
    }  $('#email_error').html("");
    if($("#email").val() != '') {
     if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,6})+$/.test($("#email").val())){
      $("#email_error").html ('Please enter correct email id');
      $("#email").focus();
      return false;
    } 
  }$('#email_error').html("");
   if($("#password").val() == '') {
      $("#password_error").html ('Please enter your Password');
      $("#password").focus();
      return false;
    }  $('#password_error').html("");
    if($("#password").val() != '') {
      if(!/^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,}).*$/.test($("#password").val())){
      $("#password_error").html ("6 to 20 characters which contain at least one numeric digit, one uppercase and one lowercase letter and one Special character");
      $("#password").focus();
      return false;
    } 
      }



    else {
      $("#userlogin").submit();
    }
  });
});