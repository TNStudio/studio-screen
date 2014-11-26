package application.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PrintRequest {
	
	private IntegerProperty number;
	private StringProperty path;
	private BooleanProperty workDone;
	
	public PrintRequest(int number, String path){
		this.number = new SimpleIntegerProperty();
		this.path = new SimpleStringProperty();
		this.workDone = new SimpleBooleanProperty();
		this.number.set(number);
		this.path.setValue(path);
		this.workDone.setValue(false);
	}

	public IntegerProperty getNumber() {
		return number;
	}

	public void setNumber(IntegerProperty number) {
		this.number = number;
	}

	public StringProperty getPath() {
		return path;
	}

	public void setPath(StringProperty path) {
		this.path = path;
	}

	public BooleanProperty getWorkDone() {
		return workDone;
	}

	public void setWorkDone(BooleanProperty workDone) {
		this.workDone = workDone;
	}

	
	

}
