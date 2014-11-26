/**
 * 
 */
package application.model;

import javafx.beans.property.StringProperty;

/**
 * A class that represent the photos in the data model
 * @author Martin, Axel
 *
 */
public class Photo {

	private StringProperty path;
	private StringProperty thumbPath;

	public Photo(StringProperty path, StringProperty thumbPath){
		this.path = path;
		this.thumbPath = thumbPath;
	}

	public StringProperty getPath() {
		return path;
	}
	
	public StringProperty getThumbPath() {
		return thumbPath;
	}

	public void setPath(StringProperty path) {
		this.path = path;
	}
	public void setThumbPath(StringProperty thumbPath) {
		this.thumbPath = thumbPath;
	}
}
