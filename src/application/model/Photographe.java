package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Photographe {
	
	private ObservableList<Photo> photoList;
	private IntegerProperty numero;
	private StringProperty name;
	
	public Photographe(IntegerProperty numero){
		this.numero=numero;
		name = new SimpleStringProperty("Photographe numéro " + numero.getValue());
		photoList = FXCollections.observableArrayList();
	}

	public ObservableList<Photo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(ObservableList<Photo> photoList) {
		this.photoList = photoList;
	}

	public IntegerProperty getNumero() {
		return numero;
	}

	public void setNumero(IntegerProperty numero) {
		this.numero = numero;
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public String toString(){
		return name.getValue();
	}
}
