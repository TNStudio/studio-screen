package application;

import java.io.File;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.model.Photo;

/**
 * A class that load each photo in an array
 * @author Axel
 *
 */
public class LoadingRoutines {

	private ObservableList<Photo> photoList;

	public LoadingRoutines(int thumbWeight) {
	}

	public ObservableList<Photo> loadImagesRoutine(String directory, boolean background) throws Exception {		
		File[] files;
		File dir = new File(directory);
		File subdir;
		String[] photographes = dir.list();
		StringProperty photo = new SimpleStringProperty();

		photoList = FXCollections.observableArrayList();

		//Browse each element in folder
		for(String s : photographes) {

			//If element is a folder
			if(!s.contains(".")) {
				subdir = new File(directory + "\\" + s);
				files = subdir.listFiles();


				//Browse each file in folder
				for(File f : files) {
					if(f.getName().matches("^(.*?)")){

						//If file is not a thumb, create a thumb
						if (!f.getAbsolutePath().contains("thumb")) {
							photo = new SimpleStringProperty();
							photo.setValue(f.getAbsolutePath());

							//Add photo in list of photo
							photoList.add(new Photo(photo, photo));
						}
					}
				}
			}
		}
		FXCollections.shuffle(photoList);
		return (ObservableList<Photo>) photoList.subList(0, 50);   
	}
}
