
<%@page import="mx.org.banxico.jakarta.entity.Actor"%>
<%@page import="java.util.List"%>
<%@ page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<td>Id</td>
				<td>Nombre</td>
				<td>Primer apellido</td>
			</tr>
		</thead>
		<tbody>
			<%
				List<Actor> actores = (List<Actor>)request.getAttribute("actorList");
				if (actores != null) {
					for(Actor actor: actores) {
			%>
						<tr>
							<td><%= actor.getId() %></td>
							<td><%= actor.getFirstName() %></td>
							<td><%= actor.getLastName() %></td>
							<td>Eliminar</td>
							<td>Actualizar</td>
						</tr>
			<% 			
					}
				} else {
			%>
				<h3>No hay registros para mostrar</h3>
			<% } %>
		</tbody>
	</table>
</body>
</html>