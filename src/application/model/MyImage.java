package application.model;

import javafx.scene.image.Image;

public class MyImage extends Image{
	
	private String path;
	
	public MyImage(String path){
		super(path);
		this.path = path;
	}
	
	public String getURL(){
		return path;
	}

}
