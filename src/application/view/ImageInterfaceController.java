package application.view;

import observerPattern.MyObserver;
import application.Main;
import application.model.MyImage;
import application.model.Photographe;
import application.model.PrintRequest;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;

public class ImageInterfaceController implements MyObserver{
	
	@FXML
	private Button print;
	@FXML
	private Button quit;
	@FXML
	private Button previous;
	@FXML
	private Button next;
	@FXML
	private ImageView imageView;
	
	private Main main; //the model
	private Image imageToDisplay; //the image to diplay in full screen
	private Photographe photographe; //the photographer of the image
	private int indice = 0; //the number of the image in the photographer's list
	
	@FXML
	private void initialize(){
		//initialize the listeners
		quit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				main.loadInterface(main.getMainInterfaceController(), main.getSceneMain()); //return to main screen
				
			}
		});
		
	}
	
	@Override
	public void update() { //what to do when update
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
		if(indice==photographe.getPhotoList().size()-1){ //control limits
			indice = 0;
		} else {
			indice++;
		}
		try {
			imageToDisplay = new MyImage("file:\\"+photographe.getPhotoList().get(indice).getPath().getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		update();
	}
	/**
	 * Display the previous image in the list
	 */
	public void previousImage(){
		if(indice == 0){ //control limits
			indice = photographe.getPhotoList().size()-1;
		} else {
			indice--;
		}
		try {
			imageToDisplay = new MyImage("file:\\"+photographe.getPhotoList().get(indice).getPath().getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		update();
	}
	/**
	 * Create a new print request
	 */
	public void printAction(){
		main.getPrintRequest().add(new PrintRequest(main.getPrintRequest().size(), photographe.getPhotoList().get(indice).getPath().getValue()));
		main.getPrintInterfaceController().getPrintQueueTable().scrollTo(main.getPrintInterfaceController().getList().size()-1); //autoscroll
	}
	//-----------------------------------------------------------------------------------------------------------------------------
	
	//Getters and Setters
	public void setMain(Main main){
		this.main=main;
	}

	public Image getImageToDisplay() {
		return imageToDisplay;
	}

	public void setImageToDisplay(Photographe photographe, int indice) {
		this.photographe = photographe;
		this.indice = indice;
		imageToDisplay = new MyImage("file:\\"+photographe.getPhotoList().get(indice).getPath().getValue());
	}

	public Main getMain() {
		return main;
	}	
}
