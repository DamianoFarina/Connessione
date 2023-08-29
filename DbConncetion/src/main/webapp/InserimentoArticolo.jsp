<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="Servlet">
		<input type="hidden" name="operazione" value="inserimento">
		
		<p>Descrizione:</p>
		<input type="text" name="descrizione">
		
		<p>Quantità:</p>
		<input type="number" name="quantita">
		
		<input type="submit" value="Invia">		
	</form>
</body>
</html>