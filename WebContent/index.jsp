<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="bugView.css">
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css"> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bug View</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="bugView.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		loadPage();
		 
	});
</script>
</head>

<body>
	<div id='title'>
		<img id='left-corner' class="left" src="bug4.png" height="40"
			width="40">
		<h2 class="center">Automatic Defect Report Tool</h2>
		<img id='right-corner' class="right" src="bug4right.png" height="40"
			width="40">
	</div>

	<table id="result-table" class="outset">
		<caption>Updated every <label id='updateFrequency'></label> min. Last updated at <label id='lastUpdated'></label> (Took <label id='batchTimeInMin'></label> min. <label id='minAgo'></label> min ago).</caption>
		<tr>
			<th width='20%'>ID</th>
			<th width='60%'>Query (<span id='expand_fold_span'><a  onclick='expand()' href='#'>Expand +</a></span>)</th>
			<th width='10%'>Bug</th>
			<th width='10%'>Chart</th>
		</tr>
	</table>
	

	<form>
		<h3>Add New Query</h3>
		<table id="input-table" class="noborder">
			<tr>
				<td class='name noborder' width="20%" id='queryIdLabel'>ID:</td>
				<td class='box noborder' width="60%"><textarea name="queryId"
						class='inset' id="queryId" rows="1"
						placeholder="A unique name is required" onchange="checkUniqueID(this.value)"></textarea></td>
				<td id='checkMark' class='sign noborder ' width="4%"></td>
				<td id='UniqueIDMessage' class='sign noborder ' width="16%"></td>
			</tr>
			<tr>
				<td class='name top noborder' id='queryContentLabel'>Query:</td>
				<td class='box noborder'><textarea name="query" class='inset'
						id="query" rows="10" placeholder="Please put your QDDTS query here..."></textarea></td>
				<td id='querycheckMark' class='sign noborder ' width="4%"></td>
				<td id='QueryMessage' class='sign noborder ' width="16%"></td>
			</tr>
		</table>
	    
	    <div id='submitButtonDiv'>
			<span id='test_button_span'><button id="test_button" type="button" onclick = 'doTest()'>Test</button></span>
			<button id="add_button" type="button" disabled onclick='doAdd()'>Add</button>
		</div>
		<div id='result_msg' class='center'></div>
		
		<div id="chartDialog" title="Defects Analysis Trend Chart" style="display: none">
 			 <img src="image/chart1.png" height="100%" width="100%">
 			
		</div>

	</form>
</body>
</html>