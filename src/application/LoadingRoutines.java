package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import application.model.Photo;
import application.model.Photographe;

/**
 * A class that load each photo in an array, call thumbnail generation and start the daemon
 * @author Axel
 *
 */
public class LoadingRoutines {

	private ObservableList<Photo> photoList;
	private ObservableList<Photographe> photographeList;
	private WatchDir watchDir;
	private Task<Void> backgroundFolderScan;
	private ThumbnailGenerator thumbGen;
	private BackgroundThread bgThread;	
	
	public LoadingRoutines(int thumbWeight) {
		thumbGen = new ThumbnailGenerator(thumbWeight);
	}

	public ObservableList<Photographe> loadImagesRoutine(String directory, boolean background) throws Exception {		
		File[] files;
		File dir = new File(directory);
		File subdir;
		String[] photographes = dir.list();
		StringProperty photo = new SimpleStringProperty();
		StringProperty thumb = new SimpleStringProperty();
		IntegerProperty number = new SimpleIntegerProperty();
		photographeList = FXCollections.observableArrayList();

		//Browse each element in folder
		for(String s : photographes) {
			
			//If element is a folder
			if(!s.contains(".")) {
				number = new SimpleIntegerProperty();
				number.setValue(Integer.parseInt(s));
				photographeList.add(new Photographe(number));
				subdir = new File(directory + "\\" + s);
				files = subdir.listFiles();
				photoList = FXCollections.observableArrayList();
				
				//Browse each file in folder
				for(File f : files) {
					if(f.getName().matches("^(.*?)")){
						photo = new SimpleStringProperty();
						thumb = new SimpleStringProperty();
						
						//If file is not a thumb, create a thumb
						if (!f.getAbsolutePath().contains("thumb")) {
							photo.setValue(f.getAbsolutePath());
							
							//Generate thumb
							thumb = thumbGen.transform(f.getAbsolutePath(), subdir+"\\thumbs\\"+f.getName()+".thumb");
							
							//Add photo in list of photo
							photoList.add(new Photo(photo, thumb));
						}
					}
				}
				
				//Add the photo list in the photograph list
				photographeList.get(photographeList.size()-1).setPhotoList(photoList);
			}
		}
		
		//Launch on demand
		if (background) {
			startWatchDir(directory);
		}
		return photographeList;   
	}
	
	public void startWatchDir(String dir) {
		try {
			watchDir = new WatchDir(Paths.get(dir), true, thumbGen);
		} catch (IOException e) {
			System.err.println("Dossier Introuvable !\n" + dir);
			e.printStackTrace();
		}
		backgroundFolderScan = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				watchDir.processEvents();
				return null;
			}
		};
		bgThread = new BackgroundThread(backgroundFolderScan);
		bgThread.start();
	}
}
