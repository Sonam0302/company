$.ajax({

	url: "linechartdata",
	
	success: function(result){
		
		var category = JSON.parse(result).categories;
		var series = JSON.parse(result).series;
		drawLineChart(category, series);
	}
});	
	
function drawLineChart(category, series){
	Highcharts.chart('container', {
	    chart: {
	        type: 'line',
	       
	        height:'495'
	    },
	    credits: {
	        enabled: false
	      },
	   title: {
		   
	        text: 'Employee joining the company'
	    },
	    xAxis: {
	    	name:'Date of Joining',
	        categories: category,
	        title: {
                text: 'Date of Joining'
            },
	        
	    },
	    yAxis: {
            title: {
                text: 'No. of Employee '
            },
	    },
	    tooltip: {
	        formatter: function() {
	          return '<strong>'+this.x+': </strong>'+ this.y;
	        }
	    },
	
	    series: [{
	    	name: 'No. of Employee',
	        data: series
	    }]
	});
}
	
