<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Inventory Management</title>
		<style type = "text/css">
			<%@ include file = "css/style.css"%>
		</style>
	</head>
	<body>
		<div>
			<h1>Edit Profile</h1>
		</div>
		<div>
				<form action = "update" method ="post">
					
					<label>First Name<input type = "text" name = "first_name" value = "<c:out value = "${profile.first_name}"/>" /></label>
					<label>Last Name<input type = "text" name = "last_name" value = "<c:out value = "${profile.last_name}"/>" /></label>					
					<label>College Name<input type = "text" name = "college_name" value = "<c:out value = "${profile.college_name}"/>" /></label>					
					<label>High School Name<input type = "text" name = "highschool_name" value = "<c:out value = "${profile.highschool_name}"/>" /></label>					
					<label>Birthday<input type = "text" name = "dob" value = "<c:out value = "${profile.dob.toString()}"/>" readonly/></label>										
					<label>Email Address<input type = "text" name = "email_address" value = "<c:out value = "${profile.email_address}"/>" /></label>					
					<label>Phone Number<input type = "text" name = "phone_number" value = "<c:out value = "${profile.phone_number}"/>" /></label>					
					<label>Last Login<input type = "text" name = "last_login" value = "<c:out value = "${profile.last_login.toString()}"/>" readonly/></label>					
					<label>Last Modified<input type = "text" name = "dob" value = "<c:out value = "${profile.last_profile_modified.toString()}"/>"readonly /></label>					
   						 
					<div class = form-actions>
						<input type = "submit" value = "Save" name = "submit"/>
					</div>		
				</form>	
				<form action = "UploadServlet" method = "post" enctype = "multipart/form-data">
					<label>Upload a new picture<input type="file" name="file"></label>	
					<div class = form-actions>
						<input type="submit" value="upload" name = "upload">		
					</div>
					 
				</form>		
		</div>
	</body>	
</html>