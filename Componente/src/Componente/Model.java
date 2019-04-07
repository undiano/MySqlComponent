package Componente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Model {
		
	private String dataBase;
	private String user; 
	private String consulta;
	private String sentencia;
	private String fecha; 
	private String numRegistros;
	
	public Model() {
		
	}
	public Model(String dataBase, String user, String consulta, String sentencia, String numRegistros) {
		super();
		this.dataBase = dataBase;
		this.user = user;
		this.consulta = consulta;
		this.sentencia = sentencia;
		this.fecha = obtenerFechaHora();
		this.numRegistros = numRegistros;
	}

	public String getDataBase() {
		return dataBase;
	}
	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	public String getSentencia() {
		return sentencia;
	}
	public void setSentencia(String sentencia) {
		this.sentencia = sentencia;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getNumRegistros() {
		return numRegistros;
	}
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}
	
	@Override
	public String toString() {
		return "Model [dataBase=" + dataBase + ", user=" + user + ", consulta=" + consulta + ", sentencia=" + sentencia
				+ ", fecha=" + fecha + ", numRegistros=" + numRegistros + "]";
	}
	
	private String obtenerFechaHora() {
		 DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		 DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		 Date date = new Date();
	 	 return dateFormat2.format(date)+" "+dateFormat.format(date);
	}
	
	
}
