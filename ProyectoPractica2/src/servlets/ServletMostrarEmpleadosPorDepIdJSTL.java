/**
 * 
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.EmpleadoHibernateDAO;
import tablas_Clases.Employees;

/**
 * @author Alberto Vivas
 *	Clase doget que obtiene la lista de departamentos por hibernate
 *	y hace un forward a un jsp
 *	Adicionalmente, seteamos la lista como atributo en la request y 
 *	la session de hibernate la hemos creado en el context.
 * 
 */
public class ServletMostrarEmpleadosPorDepIdJSTL extends HttpServlet{

	private final Logger log = LogManager.getRootLogger();
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		EmpleadoHibernateDAO ehdao = new EmpleadoHibernateDAO();
		ServletContext sc = req.getServletContext();
		SessionFactory sf = (SessionFactory) sc.getAttribute("sf");
		Session session = sf.openSession();
		EmpleadoHibernateDAO.setSession(session);
		log.debug("doGet ServletMostrarEmpleadosPorDepIdJSTL"); 
		String [] array_pam = req.getParameterValues("DepID");
		int num_dep_select = array_pam.length;
		int [] array_depid = new int[num_dep_select];
		
		for (int i = 0; i < num_dep_select; i++) {
			array_depid[i]=Integer.parseInt(array_pam[i]);
		}
		
		List<List<Employees>> listadelista = new ArrayList<List<Employees>>();
		
		for (int i = 0; i < num_dep_select; i++) {
			List<Employees> le = ehdao.listEmpPorDepId(array_depid[i]);
			listadelista.add(le);
		}
		
		req.setAttribute("ListaEmpleados", listadelista);
		//int depid =Integer.parseInt(req.getParameter("DepID"));
		//log.debug("depid="+depid);
		//List<Employees> le = ehdao.listEmpPorDepId(depid);
		session.close();
		//req.setAttribute("ListaEmpleados", le);
		req.getRequestDispatcher("EmpPorDepJSTL.jsp").forward(req, resp);
	}
}
