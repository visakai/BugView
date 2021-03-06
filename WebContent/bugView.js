//global variables
var currentBugNumber;
var lineOddEven;
var odd_even;
var minAgo;

function loadPage() {
	
	getCSVDataFromBackEnd();
	getPropDataFromBackEnd();
	setInterval(updateMinAgo, 1000 * 60); // minAgo + 1 every min
		
}

//populate the result table based on server side csv data
function getCSVDataFromBackEnd(){
	      $.ajax({
	            type: 'GET',
	            url: 'ReadCsvServlet',
	            cache: false,
	            success: function (response) { 
	            	
	            	for(i = 0; i<response.length; i++) {
	            		lineOddEven = ! lineOddEven;
	            		 if(lineOddEven){
	            			 odd_even = 'odd';
	            		 } else {
	            			 odd_even = 'even';
	            		 }
	            		$('#result-table tr:last').after("<tr class='"+ odd_even +"'><td>" + response[i].id + "</td><td><a  onclick='openQueryPage(this)' href='#'>" + response[i].query + "</a></td><td class='center' ><a id= '" + response[i].query + "'  onclick='drawBugTable(this)' href='#'>" + response[i].bug + "</a></td><td class='center'><a id= " + response[i].id + " href='#' onclick='showChart(this)'><img src='chart.png' height='30' width='30'></td></tr>");
	            	}
	            	
	            } 
	       });	   
}

//to update the time information
function getPropDataFromBackEnd(){
	
    $.ajax({
          type: 'GET',
          url: 'ReadPropServlet',
          cache: false,
          success: function (response) { 
        	     $("#updateFrequency").html( response.updateFrequency ); 	
		         var date = new Date( response.lastUpdated * 1000);
		         //var year = data.getYear();
		         //var hours = date.getHours();
		         
		         //var minutes = "0" + date.getMinutes();
		        // var formattedTime = hours + ':' + minutes.substr(-2);
		         $('#lastUpdated').html(date);
		         $("#batchTimeInMin").html( response.batchTimeInMin ); 	
		         minAgo = Math.ceil ( ( Date.now()/1000 - response.lastUpdated ) / 60 );
		         $('#minAgo').html(minAgo);
		         
          	
          } 
     });	   
}

function updateMinAgo() {
	minAgo++;
	$('#minAgo').html(minAgo);
}

					/*$("#submit_button").click( function()  {
					       
					       $.ajax({
					            type: 'GET',
					            url: 'QueryFromUserServlet',
					            cache: false,
					            data: $('form').serialize(), 
					            beforeSend:function(){
					                $('#submit').html('<div class="center"><img src="ajax-loader.gif" alt="Searching..."></div>');
					              },
					            success: function (data) {  
					            	var obj = $.parseJSON(data);
					            	
					            	$("#result").html(obj.length + " bugs are found:"); 
					            	$('#submit').html('<div id="submit"><input class="center" id="submit_button" type="submit" value="Run"></div>'); 
					            	
					            	var d = drawTable(data);
					            } 
					       });
					            
					    });
					

					$("#save_button").click(
							function() {
								var queryId = document.getElementById("queryId").value;
								var query = document.getElementById("query").value;
								
								//add to the table
								$("#query-table").append("<tr><td>" + queryId + "</td><td>" + query + "</td><td>?</td></tr>");
								
								//save to server
								$.ajax({
						            type: 'POST',
						            url: 'SaveCsvServlet',
						            cache: false,
						            data: $('form').serialize(), 

						            success: function (data) {  
						            	alert("query successfully saved")
						            } 
						       });
							});

				});*/

function checkUniqueID(ID){
	
	var trimmed = ID.trim();
	
	$.ajax({
        type: 'GET',
        url: 'ReadCsvServlet',
        cache: false,
        success: function (response) { 
        	
        	for(i = 0; i<response.length; i++) {		
        		if (response[i].id == trimmed){
        			$('#checkMark').html("<img src='no.png' height='20px' width='20px'>");
        			$('#UniqueIDMessage').html("<label class='red'>Already exists!</label>");
        			return;
        		}
        	}
        	
        	if(trimmed != null && trimmed){
        		$('#checkMark').html("<img src='yes.jpg' height='20px' width='20px'>");
        		$('#UniqueIDMessage').html('Valid ID');
        	}
        }
   });	
}

function doTest(){
	if(validateTest()) {
		 $.ajax({
	            type: 'GET',
	            url: 'QueryFromUserServlet',
	            cache: false,
	            data: $('form').serialize(), 
	            beforeSend:function(){
	            	
	            	//replace test button with loading image
	                $('#test_button_span').html("<img id='load_img' src='ajax-loader.gif' alt='Searching...'>");
	                //disable both two textarea
	                $('#queryId').prop('disabled', true);
	                $('#query').prop('disabled', true);
	              },
	            success: function (data) {  
	            	var obj = $.parseJSON(data);
	            	var bug;
	            	$('#querycheckMark').html("<img src='yes.jpg' height='20px' width='20px'>");
	            	
	            	if(obj.length === 0 || obj.length ===1) {
	            		$("#QueryMessage").html(obj.length + " bug is found."); 
	            	} else {
	            		$("#QueryMessage").html(obj.length + " bugs are found."); 
	            	}
	            	//set it to global variable
	            	currentBugNumber = obj.length;
	            	//re-show test button
	            	$('#test_button_span').html("<button id='test_button' type='button' onclick = 'doTest()'>Test</button>"); 
	            	
	            	//enable add button
	            	$('#add_button').prop('disabled', false);
	            },
	            
	              error: function(xhr){
	                  //alert("An error occured: " + xhr.status + " " + xhr.statusText);
	            	  // show error mark and message
	            	  $('#querycheckMark').html("<img src='no.png' height='20px' width='20px'>");
	            	  $("#QueryMessage").html("Invalid query"); 
	            	  
	            	//re-show test button
		            $('#test_button_span').html("<button id='test_button' type='button' onclick = 'doTest()'>Test</button>"); 
		            
		            //enable two textareas, so that the user can modify the query
	                $('#queryId').prop('disabled', false);
	                $('#query').prop('disabled', false);
		            
	              }
	              
	       });
	}
}

// this function is to validate that required fields are filled
function validateTest(){
	
	// first validate ID is not empty
	var ID = $('#queryId').val();
	if (!ID.trim() || ID==null) {
		$('#checkMark').html("<img src='no.png' height='20px' width='20px'>");
		$('#UniqueIDMessage').html("<label class='red'>ID required!</label>");
		return false;
	}
	
	// check query
	var query = $('#query').val();
	if (!query.trim() || query==null) {
		$('#querycheckMark').html("<img src='no.png' height='20px' width='20px'>");
		$('#QueryMessage').html("<label class='red'>Query required!</label>");
		return false;
	}
	
	return true;
}

function doAdd(){
	//hide/disable add button, prevent multiple click
	$('#add_button').prop('disabled', true);
	
	
	//add it to backend server, then add it to UI
	$.ajax({
        type: 'GET',
        url: 'AddCsvServlet',
        cache: false,
        data: {
        	'queryId': $('#queryId').val(),
        	'query': $('#query').val(),
	        'currentBugNumber':  currentBugNumber
        },
        success: function (response) { 
        		        	    
        		//clear message area, warning area
        	    $('#checkMark').html("");
        		$('#UniqueIDMessage').html("");
        		$('#querycheckMark').html("");
        		$('#QueryMessage').html("");
        		
        		//add to UI
        		lineOddEven = ! lineOddEven;
        		 if(lineOddEven){
        			 odd_even = 'odd';
        		 } else {
        			 odd_even = 'even';
        		 }
        		
        		$('#result-table tr:last').after("<tr class='"+ odd_even +"'><td>" +  $('#queryId').val() + "</td><td><a  onclick='openQueryPage(this)' href='#'>" +  $('#query').val() + "</a></td><td class='center' ><a id= '" + $('#query').val() + "'  onclick='drawBugTable(this)' href='#'>" + currentBugNumber + "</a></td><td class='center'><a id= " + $('#queryId').val() + " href='#' onclick='showChart(this)'><img src='chart.png' height='30' width='30'></td></tr>");
        	  //$('#result-table tr:last').after("<tr class='"+ odd_even +"'><td>" + response[i].id + "     </td><td><a  onclick='openQueryPage(this)' href='#'>" + response[i].query + "</a></td><td class='center' ><a id= '" + response[i].query + "'  onclick='drawBugTable(this)' href='#'>" + response[i].bug + "</a></td>   <td class='center'><img src='chart.png' heihgt='30' width='30'></td></tr>");
	            
        		//clear textarea
        		$('#queryId').val('');
        	    $('#query').val('');
        	    //enable textarea
        	    $('#queryId').prop('disabled', false);
                $('#query').prop('disabled', false);
        }
   });	
}


function expand () {
	 $("td").css("white-space","normal");
	 $('#expand_fold_span').html("<a  onclick='fold()' href='#'>Fold -</a>");
	 
}

function fold () {
	 $("td").css("white-space","nowrap");
	 $('#expand_fold_span').html("<a  onclick='expand()' href='#'>Expand +</a>");
}

function drawBugTable(caller) {
	var uri = "http://wwwin-metrics.cisco.com/cgi-bin/ddts_query.cgi?expert="  + encodeURIComponent(caller.id) + "&type=directweb";
	window.open(uri);
}

function openQueryPage(caller) {
	var uri = "http://wwwin-metrics.cisco.com/cgi-bin/ddts_query.cgi?expert="  + encodeURIComponent($(caller).text()) ;
	window.open(uri);
	
}

function showChart(caller) {
	//alert(caller.id +' in show chart');
	$('#chartDialog').dialog({
		height:'auto',
		width: 'auto',
		show: { effect: "scale", duration: 1000 },
		hide: { effect: "scale", duration: 1000 },
		
		
	});
}

