<%@page import="com.Bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Bill.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" />
</head>
<body>
	<h1 class="text-light text-center mt-5">Bill Management</h1>
	<div class="container ">
	<div class="jumbotron mt-5">
		<h1 class="text-secondary text-center">Create Form</h1>
				<form id="formItem" name="formItem">
				<div class="form-group col-md-6">	
					<label>Unit</label>						
  					<input id="unit" name="unit" type="text" class="form-control" placeholder="Insert Unit Here">
				</div>
				
				<div class="form-group col-md-6">
					<label>Tax</label>							
  					<input id="tax" name="tax" type="text" class="form-control" placeholder="Insert Tax Here">
				</div>
				
				<div class="form-group col-md-6">		
					<label>Bill No</label>						
  					<input id="billNo" name="billNo" type="number"  class="form-control" placeholder="Insert Bill No Here">
				</div>
				
				<div class="form-group col-md-6">			
					<label>Amount</label>				
  					<input id="amount" name="amount" type="text" class="form-control"  placeholder="Insert Amount  Here">
				</div>
				
				<div class="text-center mt-2"> 
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary" style="width:30%; height:50px;"> 
					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">	
					</div>
				</form>
				<div id="alertSuccess" class="alert alert-success mt-5"></div>
				<div id="alertError" class="alert alert-danger mt-5"></div>
			</div>
			<h1 class="text-light text-center mt-5">Bill List</h1>
						<div id="divItemsGrid" class="float-right col-12  mt-5 text-white text-center">
					<%
					Bill BillAPIObj = new Bill();
														out.print(BillAPIObj.readBill());
					%>
			</div>
			</div>	
</body>
<style>
	body{
			background: rgb(2,0,36);
			background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(9,9,121,1) 35%, rgba(0,212,255,1) 100%);
		}
</style>
</html>