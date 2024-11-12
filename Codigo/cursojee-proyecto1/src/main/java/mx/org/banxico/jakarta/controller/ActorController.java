package mx.org.banxico.jakarta.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.org.banxico.jakarta.dao.ActorDao;
import mx.org.banxico.jakarta.entity.Actor;

@WebServlet("/actores")
public class ActorController extends HttpServlet {

	private static int GUARDAR = 1;
	private static int ELIMINAR = 2;
	private static int ACTUALIZAR = 3;
	private static int LEER_TODOS = 4;
	
	public void doGet(
		HttpServletRequest request,
		HttpServletResponse response) {
		
		try {
			findAll(request);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(
		HttpServletRequest request,
		HttpServletResponse response) {
		
	}
	
	private void findAll(
		HttpServletRequest request) 
			throws ClassNotFoundException, SQLException {
		
		List<Actor> actores = ActorDao.findAll();
		request.setAttribute("actorList", actores);
	}
}
