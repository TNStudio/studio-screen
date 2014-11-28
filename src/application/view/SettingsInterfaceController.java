package application.view;


import application.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import observerPattern.MyObserver;

public class SettingsInterfaceController implements MyObserver{
	
	@FXML
	private Button validateFolder;
	
	@FXML
	private Button validateWidth;
	
	@FXML
	private Button validateNBPhoto;
	
	@FXML
	private Button quit;
	
	@FXML
	private TextField folderField;
	
	@FXML
	private TextField nb_photoField;
	
	@FXML
	private TextField width_photoField;
	
	@FXML
	private AnchorPane container;
	
	private Main main;
	
	@FXML
	private void initialize(){
		
		container.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent arg0) {
				if(arg0.getCode() == KeyCode.ENTER){
					try {
						main.setNb_photo(Integer.valueOf(nb_photoField.getText()));
						main.setFolder(folderField.getText());
						main.setLargeur_photo(Integer.valueOf(width_photoField.getText()));
						main.setPhotoList(main.getRoutine().loadImagesRoutine(main.getFolder(), true));
						main.getMainInterfaceController().updatePhotoList();
					} catch (Exception e) {
						System.err.println("Dossier non trouvé !\n" + main.getFolder());
						e.printStackTrace();
					}
					main.loadInterface(main.getMainInterfaceController(), main.getSceneMain());
				}
			}
			
		});
		
		quit.setOnMouseClicked(new EventHandler<MouseEvent>() { //go to the main scene

			@Override
			public void handle(MouseEvent event) {
				try {
					main.setNb_photo(Integer.valueOf(nb_photoField.getText()));
					main.setFolder(folderField.getText());
					main.setLargeur_photo(Integer.valueOf(width_photoField.getText()));
					main.setPhotoList(main.getRoutine().loadImagesRoutine(main.getFolder(), true));
					main.getMainInterfaceController().updatePhotoList();
				} catch (Exception e) {
					System.err.println("Dossier non trouvé !\n" + main.getFolder());
					e.printStackTrace();
				}
				main.loadInterface(main.getMainInterfaceController(), main.getSceneMain());
			}
		});
	}
	
	public void setMain(Main main){
		this.main=main;
	}

	@Override
	public void update() {
		folderField.setText(main.getFolder());
		nb_photoField.setText(String.valueOf(main.getNb_photo()));
		width_photoField.setText(String.valueOf(main.getLargeur_photo()));
	}

}
