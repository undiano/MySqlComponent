package Componente;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import Componente.Model;

public class Evento implements PropertyChangeListener {

	private ArrayList<Model> modelo = new ArrayList<>();

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		modelo.add((Model) evt.getNewValue());

	}

	public ArrayList<Model> getModelo() {
		return modelo;
	}

	public void setModelo(ArrayList<Model> modelo) {
		this.modelo = modelo;
	}

	public Model getModelo(int pos) {
		return modelo.get(pos);
	}

	public void setModelo(int pos,Model registro) {
		this.modelo.set(pos, registro);
	}


}
