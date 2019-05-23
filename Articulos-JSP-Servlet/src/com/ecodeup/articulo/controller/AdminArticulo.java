package com.ecodeup.articulo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecodeup.articulos.dao.ArticuloDAO;
import com.ecodeup.articulos.model.Articulo;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.ecodeup.articulos.dao.ArticuloDAO;
import com.ecodeup.articulos.model.Articulo;

/**
 * Servlet implementation class AdminArticulo
 */
@WebServlet("/AdminArticulo")
public class AdminArticulo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticuloDAO articuloDAO;

	public void init() {
		//Guarda en unos string los valores de URL, username,password de la base de datos para entrar y poder modificarla
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
			//Crea un objeto articuloDAO con los datos anteriores este DAO fue definido en ArtiuloDAO.java
			articuloDAO = new ArticuloDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminArticulo() {
		super();
		// TODO Auto-generated constructor stub
	}

	//metodo doget mediante el request logra capturar la accion que se ha realizado en el index 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Se ha iniciado el doGet..");
		String action = request.getParameter("action");
		System.out.println("La accion que se realizo es: "+action);
		//Captura la accion y luego decide que es lo que va a realizar, nuevo, resgistro, editar, eliminar
		try {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "nuevo":
				System.out.println("ingresar nuevo");
				nuevo(request, response);
				break;
			case "register":
				System.out.println("para registrar");
				registrar(request, response);
				break;
			case "mostrar":
				System.out.println("mostrar los elementos");
				mostrar(request, response);
				break;
			case "showedit":
				System.out.println("editar un registro");
				showEditar(request, response);
				break;	
			case "editar":
				System.out.println("seleccionar para editar");
				editar(request, response);
				break;
			case "eliminar":
				System.out.println("eliminar un registro");
				eliminar(request, response);
				break;
			default:
				break;
			}			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Se ha iniciado el doPost..");
		doGet(request, response);
	}
	
	//Regresa al menu
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//mostrar(request, response);
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	//registra un articulo, creando el objeto de tipo articulo y pasando este mismo para mapearlo a la base de datos
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Articulo articulo = new Articulo(0, request.getParameter("codigo"), request.getParameter("nombre"), request.getParameter("descripcion"), Double.parseDouble(request.getParameter("cantidad")), Double.parseDouble(request.getParameter("precio")));
		articuloDAO.insertar(articulo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	//pone el formulario para nuevo articulo
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/register.jsp");
		dispatcher.forward(request, response);
	}
	
	//mostrar los articlos que se tienen
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/mostrar.jsp");
		List<Articulo> listaArticulos= articuloDAO.listarArticulos();
		request.setAttribute("lista", listaArticulos);
		dispatcher.forward(request, response);
	}	
	
	//muestra el formulario para editar
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Articulo articulo = articuloDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("articulo", articulo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/editar.jsp");
		dispatcher.forward(request, response);
	}
	
	//edita el articulo
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Articulo articulo = new Articulo(Integer.parseInt(request.getParameter("id")), request.getParameter("codigo"), request.getParameter("nombre"), request.getParameter("descripcion"), Double.parseDouble(request.getParameter("existencia")), Double.parseDouble(request.getParameter("precio")));
		articuloDAO.actualizar(articulo);
		index(request, response);
	}
	
	//elimina un arituclo
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Articulo articulo = articuloDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		articuloDAO.eliminar(articulo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
	}
}