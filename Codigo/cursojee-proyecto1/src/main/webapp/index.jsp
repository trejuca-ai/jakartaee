
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
	<div id="formulario">
		<% 
			Actor datosActor = (Actor)request.getAttribute("actor");
			String url = "actores?tipoOperacion=3";
			
			if (datosActor == null) {
				url = "actores?tipoOperacion=1";
				datosActor = new Actor();
				datosActor.setFirstName("");
				datosActor.setLastName("");
				datosActor.setId(0);
			}
		%>
		<form action="<%= url %>" method="post">
			<table border="1">
				<tr>
					<td>Nombre: </td>
					<td>
						<input
							type="hidden"
							name="id"
							value="<%= datosActor.getId() %>"/>
						<input 
							type="text" 
							name="firstName"
							id="firstName"
							value="<%= datosActor.getFirstName() %>"/>
					</td>
				</tr>
				<tr>
					<td>Apellido: </td>
					<td>
						<input 
							type="text" 
							name="lastName"
							id="lastName"
							value="<%= datosActor.getLastName() %>"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit">Guardar</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<table border="1">
		<thead>
			<tr>
				<td>Id</td>
				<td>Nombre</td>
				<td>Primer apellido</td>
			</tr>
		</thead>
		<tbody>
			<% //scriptlet
				List<Actor> actores = (List<Actor>)request.getAttribute("actorList");
				if (actores != null) {
					for(Actor actor: actores) {
			%>
						<tr>
							<td><%= actor.getId() %></td>
							<td><%= actor.getFirstName() %></td>
							<td><%= actor.getLastName() %></td>
							<td>
								<a href="actores?tipoOperacion=2&id=<%=actor.getId()%>">
									Eliminar
								</a>
							</td>
							<td>
								<a href="actores?tipoOperacion=5&id=<%=actor.getId()%>">
									Actualizar
								</a>
							</td>
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