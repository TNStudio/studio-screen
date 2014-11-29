/**
 * 
 */
package application.view;

import observerPattern.MyObserver;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.util.Duration;
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
	private AnchorPane container;
	
	@FXML
	private StackPane stackPane;

	@FXML
	private ImageView current;

	private ImageView next;
	private Main main; //the model
	private Image imageToDisplay; //the image to display in full screen
	private ObservableList<Photo> photos; //the photographer of the image
	private int indice = 0; //the number of the image in the photographer's list

	@FXML
	private void initialize(){
//		updatePhotoList();
//		nextImage();
	}

	public void updatePhotoList(){
		photos = FXCollections.observableArrayList();
		try {
			main.setPhotoList(main.getRoutine().loadImagesRoutine(main.getFolder(), false));
		} catch (Exception e1) {
			System.out.println("Unable to update photolist");
			e1.printStackTrace();
		}
		photos = main.getPhotoList();
	}

	@Override
	public void update() { //re-build the view
		ObservableList<Node> c = stackPane.getChildren();

		if (current != null)
			c.remove(current);

		current = next;
		next = null;

		// Create fade-in for new image.
		next = new ImageView(imageToDisplay);

		next.setFitHeight(Screen.getPrimary().getBounds().getHeight()); //set its size
		next.setPreserveRatio(true);
		c.add(next);

		FadeTransition fadein = new FadeTransition(Duration.seconds(1), next);

		fadein.setFromValue(0);
		fadein.setToValue(1);

		PauseTransition delay = new PauseTransition(Duration.seconds(1));
		SequentialTransition st;
		if (current != null) {
			ScaleTransition dropout;

			dropout = new ScaleTransition(Duration.seconds(1), current);
			dropout.setInterpolator(Interpolator.EASE_OUT);
			dropout.setFromX(1);
			dropout.setFromY(1);
			dropout.setToX(0.75);
			dropout.setToY(0.75);
			st = new SequentialTransition(
					new ParallelTransition(fadein, dropout), delay);
		} else {
			st = new SequentialTransition(
					fadein, delay);
		}

		st.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				nextImage();
			}
		});

		st.playFromStart();
	}

	//Listeners
	/**
	 * Diplay the next image in the list
	 */
	public void nextImage(){
		if(indice==photos.size()-1){ //control limits
			updatePhotoList();
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
