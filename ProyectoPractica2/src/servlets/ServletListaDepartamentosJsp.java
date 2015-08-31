/**
 * 
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import hibernate.DepartamentosHibernate;
import tablas_Clases.Departments;

/**
 * @author Alberto Vivas
 *	Servlet que me genera un html con un desplegable de los departamentos y la opcion
 *	seleccionada la envia como parametro al siguiente.
 *	asi mismo se añaden dos botones (volver y enviar)
 *	Y se usa hibernate para el acceso a la BBDD
 * 
 */
public class ServletListaDepartamentosJsp extends HttpServlet {
	Session session;
	private String boton_volver="<form method=\"get\" action=\"Login.html\"> <button type= \"submit\">Back</button> </form>";
	private String boton_enviar1="<form method=\"get\" action=\"ServletMostrarEmpleadosPorDepIdJSTL\">";
	private String boton_enviar2="<button type= \"submit\">Enviar</button> </form>";
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
Departments d;
		
		List<Departments> list = DepartamentosHibernate.obtenerDepartamentos(req);
		resp.setContentType("text/html");
		PrintWriter printWriter = resp.getWriter();
		printWriter.println("<p>");
		printWriter.println("<b>Lista de departaments</b><br>");
		Iterator<Departments> it = list.iterator();
		printWriter.println(boton_enviar1);
		printWriter.println("<select name=\"DepID\" multiple=\"multiple\" size=\"27\">");
		while(it.hasNext()){
			d = it.next();
			printWriter.println("<option value=\""+d.getDepartmentId()+"\">"+d.getDepartmentName()+"</option>");
		}
		printWriter.println("</select>");
		printWriter.println("</p>");
		printWriter.println(boton_enviar2);
		printWriter.println("seleccione uno o mas departamentos (con ctrl) y pulse enviar");
		printWriter.println("<p>"+boton_volver+"</p>");
	}
	
}
