package application.view;

import application.Main;
import application.model.PrintRequest;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import observerPattern.MyObserver;

public class PrintQueueInterfaceController implements MyObserver {
	@FXML
	private TableView<PrintRequest> printQueueTable;
	@FXML
	private TableColumn<PrintRequest, Number> number;
	@FXML
	private TableColumn<PrintRequest, String> path;
	@FXML
	private TableColumn<PrintRequest, Boolean> check;
	
	private Main main;
	private ObservableList<PrintRequest> list;
	
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		
		number.setCellValueFactory(cellData -> cellData.getValue().getNumber());
		path.setCellValueFactory(cellData -> cellData.getValue().getPath());
		check.setCellValueFactory(cellData -> cellData.getValue().getWorkDone());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		/*printQueueTable.setItems(list);
		number.setCellFactory(new PropertyValueFactory("number"));
		path.setCellFactory(new PropertyValueFactory("path"));
		check.setCellFactory(new PropertyValueFactory("workDone"));*/
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
		printQueueTable.setItems(list);
	}

	public TableView<PrintRequest> getPrintQueueTable() {
		return printQueueTable;
	}

	public void setPrintQueueTable(TableView<PrintRequest> printQueueTable) {
		this.printQueueTable = printQueueTable;
	}

	public ObservableList<PrintRequest> getList() {
		return list;
	}

	public void setList(ObservableList<PrintRequest> list) {
		this.list = list;
	}
	
	
	

}
