/**
 * 
 */
package hibernate;

import java.sql.SQLException;

/**
 * @author Alberto Vivas
 *	interfaz con la que obligo el metodo comun a los servicios de empleados
 * 
 */
public interface Recuperable {
	Object leerEmpleado(Object id) throws SQLException;
}
