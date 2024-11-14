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
	private static int LEER_POR_ID = 5;
	
	public void doGet(
		HttpServletRequest request,
		HttpServletResponse response) {
		
		try {
			
			Integer tipoOperacion = request.getParameter("tipoOperacion") != null
				? Integer.parseInt(request.getParameter("tipoOperacion"))
				: 0;
			
			Integer id = request.getParameter("id") != null
				? Integer.parseInt(request.getParameter("id"))
				: 0;
			
			if (tipoOperacion == LEER_POR_ID) {
				request.setAttribute("actor", ActorDao.findById(id));
			} else if (tipoOperacion == ELIMINAR) {
				ActorDao.delete(id);
			}
			
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
		
		Integer tipoOperacion = 0;
		
		if (request.getParameter("tipoOperacion") != null) {
			tipoOperacion = Integer.parseInt(request.getParameter("tipoOperacion"));
		}
		try {
			saveOrUpdate(request, tipoOperacion);
			findAll(request);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void saveOrUpdate(
		HttpServletRequest request,
		Integer tipoOperacion) 
				throws ClassNotFoundException, SQLException {
		
		Actor actor = new Actor();
		actor.setFirstName(request.getParameter("firstName"));
		actor.setLastName(request.getParameter("lastName"));
		
		if (tipoOperacion == GUARDAR) {
			ActorDao.save(actor);
		} else if (tipoOperacion == ACTUALIZAR) {
			actor.setId(Integer.parseInt(request.getParameter("id")));
			ActorDao.update(actor);
		}
	}
	
	private void findAll(
		HttpServletRequest request) 
			throws ClassNotFoundException, SQLException {
		
		List<Actor> actores = ActorDao.findAll();
		request.setAttribute("actorList", actores);
	}
}
