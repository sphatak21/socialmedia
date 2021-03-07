<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Social Media Login</title>
</head>
<body>
	<div>
		<h1>Social Media</h1>
		<form action = "showProfile" method = "post">
			<div>
				<label>Email</label><input type = "text" name = "email_address"/>
			</div>
			<div>
				<label>Password</label><input type = "password" name = "password" />
			</div>
			<div class = form-actions>
				<input type = "submit" value = "Log In" name = "submit"/>		
			</div>	
		</form>
	</div>
	
</body>
</html>