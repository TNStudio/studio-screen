/**
 * 
 */
package application.view;

import observerPattern.MyObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import application.Main;
import application.model.MyImage;
import application.model.Photo;

/**
 * A class that give the IDs of the GUI components and implements their behaviors
 * @author Martin, Axel
 *
 */
public class MainInterfaceController implements MyObserver {

	@FXML
	private ImageView imageView;

	private Main main; //the model
	private Image imageToDisplay; //the image to diplay in full screen
	private ObservableList<Photo> photos; //the photographer of the image
	private int indice = 0; //the number of the image in the photographer's list

	@FXML
	private void initialize(){
	}

	public void updatePhotoList(){
		photos = FXCollections.observableArrayList();
		photos = main.getPhotoList();
	}

	@Override
	public void update() { //re-build the view
		try {
			main.setPhotoList(main.getRoutine().loadImagesRoutine(main.getFolder(), false));
		} catch (Exception e1) {
			System.out.println("Unable to update photolist");
			e1.printStackTrace();
		}
		try {
			imageView.setImage(imageToDisplay); //diplay the new image
		} catch (Exception e) {
			e.printStackTrace();
		}
		imageView.setFitHeight(Screen.getPrimary().getBounds().getHeight());//set its size
	}

	//Listeners
	/**
	 * Diplay the next image in the list
	 */
	public void nextImage(){
		if(indice==photos.size()-1){ //control limits
			indice = 0;
		} else {
			indice++;
		}
		try {
			imageToDisplay = new MyImage("file:\\"+photos.get(indice).getPath().getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		update();
	}
	
	//Getters and Setters
		public void setMain(Main main){
			this.main=main;
		}

		public Image getImageToDisplay() {
			return imageToDisplay;
		}

		public void setImageToDisplay(int indice) {
			this.indice = indice;
			imageToDisplay = new MyImage("file:\\"+photos.get(indice).getPath().getValue());
		}

		public Main getMain() {
			return main;
		}	
}
