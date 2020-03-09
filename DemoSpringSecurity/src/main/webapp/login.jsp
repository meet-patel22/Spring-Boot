<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>Login Page</h1>
	${SPRING_SECURITY_LAST_EXCEPTION.message}
		
		<form action="login" method="post">
			<br />Username:<input type="text" name="username"> <br />
			Password:<input type="password" name="password"> <br />
			<input type="submit" value="Submit">
		</form>
	

</body>
</html>
