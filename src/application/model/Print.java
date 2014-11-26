package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Print {

	private ObservableList<Photo> photoList;

	public Print() {
		photoList = FXCollections.observableArrayList();
	}

	public ObservableList<Photo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(ObservableList<Photo> photoList) {
		this.photoList = photoList;
	}
	
	public void addPhoto(Photo photo) {
		this.photoList.add(photo);
	}
}
