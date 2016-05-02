package fx.util;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;

public abstract class FXUtils {
	public static Image getImage(String url, InputStream stream) {
		Image result = url != null ? new Image(url) : new Image(stream);
		Exception error = result.getException();
		if (error != null) {
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println(error.fillInStackTrace());
			System.out.println("---------------------------------------------------------------------------------");
		}
		return result;
	}
	public static BufferedImage readImage(String url, InputStream stream) {
		BufferedImage readImage = null;
		URL Url = null;
		try { Url = url == null ? null : new URI(url).toURL(); } catch (MalformedURLException | URISyntaxException e) { e.printStackTrace(); }
		try { readImage = Url != null ? ImageIO.read(Url) : ImageIO.read(stream); } catch (Exception e) { }
		return readImage;
	}
	public static Background getBackground(Image img) {
		return new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
	}
	public static Background getBackground(String URL, InputStream stream) {
		return getBackground(getImage(URL, stream));
	}
	public static void setBackground(Region region, Background bg) {
		if (bg != null)
			region.setBackground(bg);
	}
	public static void setBackground(Region region, String url, InputStream stream) {
		setBackground(region, getBackground(url, stream));
	}
}