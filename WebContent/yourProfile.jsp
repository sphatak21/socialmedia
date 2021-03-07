<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Your Profile</title>
	<style type = "text/css">
		<%@ include file = "css/style.css"%>
	</style>
</head>
<body>
	<div id = "profilegrid">
		<div id = "pfpdiv">
		<c:if test ="${profile.encodedpfp != null}">
			<img id = "pfp" src = "data:image/jpg;base64,${profile.encodedpfp}"/>
		</c:if>
		</div>
	
	<div>${profile.first_name} ${profile.last_name }
		<div>
			<c:if test = "${profile.college_name != null}">
				${profile.college_name}
			</c:if>
		</div>
		<div>
			<c:if test = "${profile.highschool_name != null}">
				${profile.highschool_name}
			</c:if>
		</div>
	</div>
	
	<div id = "logOutDiv">
		<form action = "logout" method = "post">
			<div class = form-actions>					
				<input type="submit" value="logout" name = "logout">			
			</div>
		</form>		
	</div>
	<div id = "profileButtonDiv">
		<form action = "editProfile" method = "post">
			<div class = form-actions>
				<input type = "submit" value = "Edit Profile" name = "submit"/>		
			</div>	
		</form>
	</div>
	</div>
	
	
</body>
</html>