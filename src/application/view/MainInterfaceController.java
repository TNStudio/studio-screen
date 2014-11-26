/**
 * 
 */
package application.view;

import observerPattern.MyObserver;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import application.Actionner;
import application.Main;
import application.model.MyImage;
import application.model.Photographe;

/**
 * A class that give the ids of the GUI components and implements their behaviors
 * @author Martin, Axel
 *
 */
public class MainInterfaceController implements MyObserver, EventHandler<MouseEvent>{

	@FXML
	private ScrollPane photoScroll;
	@FXML
	private ListView<Photographe> listPhotographe;
	@FXML
	private Button settings;
	@FXML
	private Button refresh;

	private Main main; //the model
	private GridPane photoGridPane; //the layout
	Photographe selectedPhotograph; //the selected photographer in the list
	
	@FXML
	private void initialize(){
		photoGridPane = new GridPane(); //create a grid pane to display the pictures
		photoGridPane.setVgap(10); //create gaps to separate pictures
		photoGridPane.setHgap(10); //idem
		photoScroll.setContent(photoGridPane); //put the grid pane into the scrollpane (xml generated)

		//Listeners
		settings.setOnMouseClicked(new EventHandler<MouseEvent>() { //load another scene

			@Override
			public void handle(MouseEvent event) {

				main.getActionCenter().doIt(Actionner.SETTINGS);
			}
		});


		refresh.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				main.getActionCenter().doIt(Actionner.REFRESH);
			}
		});
		
		listPhotographe.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				main.getActionCenter().doIt(Actionner.REFRESH);
			}
		});
	}

	public void setMain(Main main){
		this.main=main;
	}
	
	public void updatePhotographerList(){
		listPhotographe.setItems(main.getPhotographeList());
	}

	@Override
	public void update() { //re-build the view
		try {
			main.setPhotographeList(main.getRoutine().loadImagesRoutine(main.getFolder(), false));
		} catch (Exception e1) {
			System.out.println("Unable to update photolist");
			e1.printStackTrace();
		}
		selectedPhotograph = listPhotographe.getSelectionModel().getSelectedItem();
		try {
			System.out.println( listPhotographe.getSelectionModel().getSelectedItem().getName().getValue());//try to read the selected photographer
		} catch (NullPointerException e) {
			listPhotographe.getSelectionModel().select(0); //choose default if not
			selectedPhotograph = listPhotographe.getSelectionModel().getSelectedItem();
		}
		//clean the layout
		photoGridPane.getChildren().clear();
		
		//Update photo grid
		int nb_ligne = selectedPhotograph.getPhotoList().size()/main.getNb_photo();
		for(int i = 0; i<nb_ligne; i++){ //create all the imageviews with the picture inside
			for(int j = 0; j<main.getNb_photo(); j++){
				MyImage image = new MyImage("file:\\"+selectedPhotograph.getPhotoList().get(i*main.getNb_photo()+j).getThumbPath().getValue());
				//System.out.println(selectedPhotograph.getPhotoList().get(i+j).getPath().getValue());
				ImageView imageView = new ImageView(image);
				imageView.setFitWidth(main.getLargeur_photo()); //set the width of the pictures
				imageView.setFitHeight(main.getLargeur_photo()); //set the height of the pictures
				imageView.setPreserveRatio(true);
				imageView.setOnMouseClicked(this);
				imageView.setCursor(Cursor.HAND); //change the cursor appearance
				photoGridPane.add(imageView, j, i); //add the imageView to the GUI
			}
		}
		int last_ligne = selectedPhotograph.getPhotoList().size()%main.getNb_photo();
		if(nb_ligne ==0){
			for(int i=0;i<selectedPhotograph.getPhotoList().size();i++){
				MyImage image = new MyImage("file:\\"+selectedPhotograph.getPhotoList().get(i).getThumbPath().getValue());
				ImageView imageView = new ImageView(image);
				imageView.setFitWidth(main.getLargeur_photo()); //set the width of the pictures
				imageView.setFitHeight(main.getLargeur_photo()); //set the height of the pictures
				imageView.setPreserveRatio(true);
				imageView.setOnMouseClicked(this);
				imageView.setCursor(Cursor.HAND); //change the cursor appearance
				photoGridPane.add(imageView, i, 0); //add the imageView to the GUI
			}
		} else {
			for(int i=0;i<last_ligne;i++){
				MyImage image = new MyImage("file:\\"+selectedPhotograph.getPhotoList().get(i+nb_ligne*main.getNb_photo()).getThumbPath().getValue());
				ImageView imageView = new ImageView(image);
				imageView.setFitWidth(main.getLargeur_photo()); //set the width of the pictures
				imageView.setFitHeight(main.getLargeur_photo()); //set the height of the pictures
				imageView.setPreserveRatio(true);
				imageView.setOnMouseClicked(this);
				imageView.setCursor(Cursor.HAND); //change the cursor appearance
				photoGridPane.add(imageView, i, nb_ligne); //add the imageView to the GUI
			}
		}
	}

	@Override
	public void handle(MouseEvent arg0) {
		int indice = 0;
		ImageView toDisplay = (ImageView) arg0.getSource();
		MyImage imgToDiplay = (MyImage) toDisplay.getImage();
		//System.out.println(imgToDiplay.getURL());
		for(int i=0;i<selectedPhotograph.getPhotoList().size();i++){
			//System.out.println("file:\\"+selectedPhotograph.getPhotoList().get(i).getPath().getValue());
			if(imgToDiplay.getURL().contains(selectedPhotograph.getPhotoList().get(i).getThumbPath().getValue())){
				indice = i;
				break;
			}
		}
		main.getImageInterfaceController().setImageToDisplay(selectedPhotograph, indice);
		main.getImageInterfaceController().update();
		main.loadInterface(main.getImageInterfaceController(), main.getSceneImage());
	}

}
