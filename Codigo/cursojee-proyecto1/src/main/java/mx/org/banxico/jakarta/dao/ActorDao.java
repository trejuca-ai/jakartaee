package mx.org.banxico.jakarta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import mx.org.banxico.jakarta.entity.Actor;

public class ActorDao {

	public static void save(Actor actor) 
			throws SQLException, ClassNotFoundException {
		
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO actor(")
			.append("first_name, last_name) ")
			.append("VALUES(?, ?)");
		
		Connection conexion = ConnectionHelper.getConnection();
		PreparedStatement statement = conexion.prepareStatement(query.toString());
		statement.setString(1, actor.getFirstName());
		statement.setString(2, actor.getLastName());
		statement.executeUpdate();
				
		statement.close();
		conexion.close();
	}
	
	public static void delete(Integer id) 
			throws ClassNotFoundException, SQLException  {
		
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM actor ")
			.append(" WHERE actor_id=?");
		
		Connection conexion = ConnectionHelper.getConnection();
		PreparedStatement statement = conexion.prepareStatement(query.toString());
		statement.setInt(1, id);
		statement.executeUpdate();
				
		statement.close();
		conexion.close();
	}
	
	public static void update(Actor actor) 
			throws ClassNotFoundException, SQLException {
		
		StringBuilder query = new StringBuilder();
		query.append("UPDATE actor SET ")
			.append("first_name = ?, ")
			.append("last_name = ? ")
			.append("WHERE actor_id = ?");

		Connection conexion = ConnectionHelper.getConnection();
		PreparedStatement statement = conexion.prepareStatement(query.toString());
		statement.setString(1, actor.getFirstName());
		statement.setString(2, actor.getLastName());
		statement.setInt(3, actor.getId());
		statement.executeUpdate();
				
		statement.close();
		conexion.close();
	}
	
	public static List<Actor> findAll() 
			throws SQLException, ClassNotFoundException {
		
		String query = "SELECT * FROM actor";

		Connection conexion = ConnectionHelper.getConnection();
		PreparedStatement statement = conexion.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		
		Actor actor = null;
		List<Actor> actores = new ArrayList<Actor>();
		
		while(rs.next()) {
			actor = new Actor();
			actor.setId(rs.getInt("actor_id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			
			actores.add(actor);
		}
		
		statement.close();
		conexion.close();
		
		return actores;
	}
	
	public static Actor findById(Integer id) 
			throws SQLException, ClassNotFoundException {
		
		String query = "SELECT * FROM actor WHERE actor_id = ?";

		Connection conexion = ConnectionHelper.getConnection();
		PreparedStatement statement = conexion.prepareStatement(query);
		statement.setInt(1, id);
		
		ResultSet rs = statement.executeQuery();
		
		Actor actor = null;
		
		while(rs.next()) {
			actor = new Actor();
			actor.setId(rs.getInt("actor_id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
		}
		statement.close();
		conexion.close();
		
		return actor;
	}
}
