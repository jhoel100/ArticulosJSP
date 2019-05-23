<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar Artículo</title>
</head>
<body>
	<h1>Registrar Artículo</h1>
	<h3>Este es el formulario para registrar un articulo, aca se llena todo para poderlo llevar a la base de datos</h3>	
	<form action="AdminArticulo?action=register" method="post">
		<table border="1" align="center">
		<tr>
			<td>Código:</td>		
			<td><input type="text" name="codigo"/></td>	
		</tr>
		<tr>
			<td>Nombre:</td>		
			<td><input type="text" name="nombre"/></td>	
		</tr>
		<tr>
			<td>Descripción:</td>		
			<td><input type="text" name="descripcion"/></td>	
		</tr>
		<tr>
			<td>Cantidad:</td>		
			<td><input type="text" name="cantidad"/></td>	
		</tr>
		<tr>
			<td>Precio:</td>		
			<td><input type="text" name="precio"/></td>	
		</tr>		
		
	</table>
	<br>
	<table border="0" align="center">
		<tr>
		<td><input type="submit" value="Agregar" name="agregar"></td>	
		</tr>
	</table>
	</form>
</body>
</html>