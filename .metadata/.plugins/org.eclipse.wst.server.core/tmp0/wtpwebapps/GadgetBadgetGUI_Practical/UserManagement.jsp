<%@page import="model.UserServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Investment Management - GadgetBadget</title>
	
		<link href="myStyle.css" rel="stylesheet" />
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="Components/jquery-3.5.0.min.js"></script>
		<script src="Components/usermanage.js"></script>

	</head>
	
	<body>
		<div class="container">
	
			<div class="font-weight-bold">
				
					<h1>User Management - Admin View</h1>
				
			</div>
			<br><br>
			
			<fieldset>
	
				<legend><b>Register User - Admin</b></legend>
					<form id="USERMANAGE" name="USERMANAGE" class="border border-light p-5">
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Username: </label>
						    <input type="text" id="username" class="form-control" name="username">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Email:</label>
						    <input type="email" id="email" class="form-control" name="email">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Date of Birth:</label>
						    <input type="date" id="dob" class="form-control" name="dob">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">User Type:</label>
						    <select name="type" id="type">
  								<option value="investor">Investor</option>
  								<option value="buyer">Buyer</option>
  								<option value="Resercher">Researcher</option>
  							</select>						    
						</div>
						
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary btn-lg btn-block"> 
						<input type="hidden" id="usermanagesaveid" name="usermanagesavebtn" value="">
					</form>
				
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>			
			</fieldset>
			
			<br> 
			
			<div class="container" id="usermanageGrid">
				<fieldset>
					<legend><b>View User Details</b></legend>
					<form method="post" action="UserManagement.jsp" class="table table-striped">
						<%
							UserServlet user = new UserServlet();
											out.print(user.read_Post());
						%>
					</form>
					<br>
				</fieldset>
			</div>
		</div>
	</body>
</html>



