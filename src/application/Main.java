package application;


import observerPattern.MyObserver;
import application.model.Photo;
import application.view.MainInterfaceController;
import application.view.SettingsInterfaceController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Main class of the project
 * @author Martin, Axel
 *
 */
public class Main extends Application {

	private int largeur_photo = 1920; //default value
	private int nb_photo = 5; //default value
	private ObservableList<Photo> photoList; //list of photographer
	private String folder ="D:\\Utilisateur\\Dropbox\\Gala2014"; //default value
	private Stage primaryStage;  //the stage containing the user interface
	private LoadingRoutines routine; //the routine scaning the files

	/**
	 * FXML loader for the scenes
	 */
	private FXMLLoader loaderMain; //the explorer
	private FXMLLoader loaderSettings; //the settings
	
	/**
	 * The controller of the views
	 */
	private SettingsInterfaceController settingsInterfaceController;
	private MainInterfaceController mainInterfaceController;
	
	/**
	 * The root panes of the views
	 */
	private AnchorPane rootMain;
	private AnchorPane rootSettings;
	
	/**
	 * the scenes
	 */
	private Scene sceneMain;
	private Scene sceneSettings;
	
	Actionner actionCenter; //the action center
	
/**
 * Start methods
 */
	/**
	 * Start method
	 * @param args
	 */
	public static void main(String[] args) {

		launch(args); //invoke start method
	}
	/**
	 * Constructor
	 */
	public Main(){ //called at start up
		setRoutine(new LoadingRoutines(largeur_photo)); //start the routine to scan files with the desired width
	}

	/**
	 * Create the GUI
	 */
	@Override
	public void start(Stage primaryStage) { //called at start up
		this.primaryStage=primaryStage; //Instantiate the first window
		this.primaryStage.initStyle(StageStyle.UNDECORATED); //set it undecorated
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds(); //create a rectangle that fit the main screen
		primaryStage.setX(primaryScreenBounds.getMinX());//put the first window at the origin of the screen
		primaryStage.setY(primaryScreenBounds.getMinY());//see above
		primaryStage.setWidth(primaryScreenBounds.getWidth()); //fit the window to the size of the screen
		primaryStage.setHeight(primaryScreenBounds.getHeight());//see above
		primaryStage.setOnHiding(new EventHandler<WindowEvent>() { //set a always on top

			@Override
			public void handle(WindowEvent arg0) {
				primaryStage.show(); //show the window when is asked to hide

			}
		});
		try { //try to load the GUIs
			/**
			 * Instantiate the loaders
			 */
			loaderMain = new FXMLLoader();
			loaderSettings = new FXMLLoader();
			
			/**
			 * Pick up the GUI's XML files
			 */
			loaderSettings.setLocation(Main.class.getResource("view/SettingsInterface.fxml")); //find the GUI file
			loaderMain.setLocation(Main.class.getResource("view/MainInterface.fxml")); //find the GUI file
			
			/**
			 * get the root pane of the GUIs
			 */
			rootSettings = loaderSettings.load();
			rootMain = loaderMain.load(); //load the GUI in a AnchorPane
			
			/**
			 * Set the controllers of the GUIs
			 */
			settingsInterfaceController = loaderSettings.getController();
			mainInterfaceController = loaderMain.getController();

			/**
			 * Link the controllers to the model
			 */
			settingsInterfaceController.setMain(this);
			mainInterfaceController.setMain(this);
			
			/**
			 * Create the scenes
			 */
			sceneMain = new Scene(rootMain); //create a scene with the GUI
			sceneSettings = new Scene(rootSettings); //create a scene with the GUI
			
			
			/**
			 * Load the start up GUI
			 */
			this.loadInterface(settingsInterfaceController, sceneSettings);
			primaryStage.show(); //display the window
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
/*
 * --------------------------------------------------------------------------------------------------------------------------
 */

	/**
	 * Load a new interface in argument
	 * @param loader the loader of the XML interface
	 * @param obs the observer of the loaded interface
	 * @param scene the scene to display in the window
	 */
	public void loadInterface(MyObserver obs, Scene scene){

		primaryStage.setScene(scene); //put the scene in a stage (window)
		primaryStage.setTitle("GALA Slideshow Service by TN Studio"); //give a name to the window
		primaryStage.toFront();
		obs.update();
	}

//------------------------------------------------------------------------------------------------------------------------------
//Getters and Setters	
	
	public int getLargeur_photo() {
		return largeur_photo;
	}

	public void setLargeur_photo(int largeur_photo) {
		this.largeur_photo = largeur_photo;
	}

	public int getNb_photo() {
		return nb_photo;
	}

	public void setNb_photo(int nb_photo) {
		this.nb_photo = nb_photo;
	}

	public ObservableList<Photo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(ObservableList<Photo> observableList) {
		this.photoList = observableList;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public AnchorPane getRootMain() {
		return rootMain;
	}

	public void setRootMain(AnchorPane rootMain) {
		this.rootMain = rootMain;
	}

	public AnchorPane getRootSettings() {
		return rootSettings;
	}

	public void setRootSettings(AnchorPane rootSettings) {
		this.rootSettings = rootSettings;
	}

	public FXMLLoader getLoaderMain() {
		return loaderMain;
	}

	public void setLoaderMain(FXMLLoader loaderMain) {
		this.loaderMain = loaderMain;
	}

	public FXMLLoader getLoaderSettings() {
		return loaderSettings;
	}

	public void setLoaderSettings(FXMLLoader loaderSettings) {
		this.loaderSettings = loaderSettings;
	}

	public SettingsInterfaceController getSettingsInterfaceController() {
		return settingsInterfaceController;
	}

	public void setSettingsInterfaceController(
			SettingsInterfaceController settingsInterfaceController) {
		this.settingsInterfaceController = settingsInterfaceController;
	}

	public MainInterfaceController getMainInterfaceController() {
		return mainInterfaceController;
	}

	public void setMainInterfaceController(
			MainInterfaceController mainInterfaceController) {
		this.mainInterfaceController = mainInterfaceController;
	}

	public Scene getSceneMain() {
		return sceneMain;
	}

	public void setSceneMain(Scene sceneMain) {
		this.sceneMain = sceneMain;
	}

	public Scene getSceneSettings() {
		return sceneSettings;
	}

	public void setSceneSettings(Scene sceneSettings) {
		this.sceneSettings = sceneSettings;
	}

	public LoadingRoutines getRoutine() {
		return routine;
	}

	public void setRoutine(LoadingRoutines routine) {
		this.routine = routine;
	}

	public Actionner getActionCenter() {
		return actionCenter;
	}
	
	public void setActionCenter(Actionner actionCenter) {
		this.actionCenter = actionCenter;
	}
}
