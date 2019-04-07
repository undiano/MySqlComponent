package Componente;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.PreparedStatement;

public class ConnectMysql implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String ip;
	public String dataBase;
	public String user;
	public String password;
	
	private Connection conexion;
	private Statement statement;
	
	private static Evento evento;
	PropertyChangeSupport observable;

	
	public ConnectMysql() {
		observable = new PropertyChangeSupport(this);
		evento = new Evento();
		addPropertyChangeListener(evento);
	}
	
	public void connectToMySql(String ip, String dataBase, String user, String password) {
		this.ip = ip;
		this.dataBase = dataBase;
		this.user = user;
		this.password = password;
		try {
			if (conexion != null) {
				desconnectToMySql();
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection ("jdbc:mysql://"+ip+":3306/"+dataBase,user,password);
			System.out.println("Se ha realizado la conexion correctamente a: "+dataBase);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error: No se ha podido realizar la conexion");
		}
	}
	public void desconnectToMySql() {
		try {
			conexion.close();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void select(String query) {
		try {
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(query);
			int numRegistros = 0;
			while (rs.next()) {
			    ++numRegistros;
			}
			Model m1 = new Model(dataBase,user,"select",query,Integer.toString(numRegistros));
			observable.firePropertyChange("select",null,m1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert(String query) {
		try {
			PreparedStatement preparedStmt = (PreparedStatement) conexion.prepareStatement(query);
			preparedStmt.execute();
			//statement.executeUpdate(query);
			Model m1 = new Model(dataBase,user,"insert",query,"1");
			observable.firePropertyChange("insert",null,m1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(String query) {
		try {
			statement = conexion.createStatement();
			int numeroRegistros = statement.executeUpdate(query);
			
			Model m1 = new Model(dataBase,user,"delete",query,Integer.toString(numeroRegistros));
			observable.firePropertyChange("delete",null,m1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(String query) {
		try {
			statement = conexion.createStatement();
			int numeroRegistros = statement.executeUpdate(query);
			Model m1 = new Model(dataBase,user,"update",query,Integer.toString(numeroRegistros));
			observable.firePropertyChange("update", null, m1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getRegistros(String baseDatos,String condicion) {
		if(condicion.equalsIgnoreCase("select") || condicion.equalsIgnoreCase("insert") ||
			condicion.equalsIgnoreCase("delete") || condicion.equalsIgnoreCase("update") || condicion.equalsIgnoreCase("store") ){
			for (Model registro : evento.getModelo()) {
				if (registro.getDataBase().equals(baseDatos) && registro.getConsulta().equals(condicion)) {
					System.out.println(registro.getConsulta() + " ; " + registro.getFecha() + " ; "
							+ registro.getUser());
				}
			}
		}else {
			for (Model registro : evento.getModelo()) {
				if (registro.getDataBase().equals(baseDatos) && registro.getUser().equals(condicion)) {
					System.out.println(registro.getSentencia() + " ; " + registro.getFecha() + " ; "
							+ registro.getConsulta());
				}
			}
		}
	}
	
	public void getRegistros(String baseDatos,String usuario,String tipo) {
		for (Model registro : evento.getModelo()) {
			if (registro.getDataBase().equals(baseDatos) && registro.getUser().equals(usuario) && registro.getConsulta().equalsIgnoreCase(tipo)) {
				System.out.println(registro.getSentencia() + " ; " + registro.getFecha());
			}
		}
	}
	
	public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
		observable.removePropertyChangeListener(l);
	}
	
	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		observable.addPropertyChangeListener(l);
	}


	
}
