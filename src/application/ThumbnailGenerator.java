package application;

import java.io.File;

import javax.imageio.ImageIO;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * Adaptation of the thumbnail generation class
 * @author Axel
 *
 */

public class ThumbnailGenerator {
	private int thumbWidth;
	private Image image;
	
	public ThumbnailGenerator(int thumbWidth) {
		this.thumbWidth = thumbWidth;
	}
	
	public StringProperty transform(String originalFile, String thumbnailFile) throws Exception {
		//Path of the thumb
		StringProperty path = new SimpleStringProperty(thumbnailFile+".png");

		//Create the output file
		File outFileImage = new File(path.getValue());
		outFileImage.getParentFile().mkdirs();
		
		//If thumb exist return path
		if(outFileImage.exists()) {
			return path;
		}

		//Load the image in required dimensions
		image = new Image("file:\\"+originalFile, thumbWidth, thumbWidth, true, true, false);

		//Write the thumb down
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", outFileImage);

			//If success return the thumbnail path
			return path;
		} catch (Exception e) {
			System.err.println("Thumb generation error for "+originalFile);
			e.printStackTrace();
		}
		return null;
	}
}