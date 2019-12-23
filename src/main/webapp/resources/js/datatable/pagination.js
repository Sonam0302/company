$(document).ready(function() {
    $('#example').DataTable({
    	"processing": true,
        "serverSide": true,
        "bLengthChange": false,
     	"ajax":{
   			"data": function(){
     		      var info = $('#example').DataTable().page.info();
			
     		      $('#example').DataTable().ajax.url(
     		          "employeePagination/"+(info.page + 1)+"/"+10
     		      );
     		   },
	      	},
         "columns": [
        	 
 			{ "data": "id" },
 			{ "data": "name" },
 			{ "data": "email"},
 			{ "data": "contact_no" },
 			{  
                "title": "edit",  
                "data": "id",  
                "searchable": false,  
                "sortable": false,  
                "render": function (data, type, full, meta) {  
                    return '<a href="/company/edit?id=' + data + '">Edit</a>';  
                } ,
 			},
 			{  
                "title": "delete",  
                "data": "id",  
                "searchable": false,  
                "sortable": false,  
                "render": function (data, type, full, meta) {  
                    return '<a href="/company/delete/' + data + '">Delete</a>';  
                } ,
 			}	
 			
 		],
 		"paging":true,
        "pageLength":10,
        "ordering":true,
        "order":[0,"asc"]
           
    });
   
} );