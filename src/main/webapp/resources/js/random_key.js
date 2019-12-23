
$(document).ready(function() {
$("#btnAddkey").click(function(){ 
	var key=document.getElementById("random_key").value;
	$('#random_key').hide();
	
$.ajax({
url: "comrandom_key",
type: "post",
data:{data1:key},
success : function(result){
	 
	 var random_key=JSON.toString(result).uniqueID;
	 $('#summary').html(result);
	 $('.reg').val("Re-Generate");
}

});
});
});