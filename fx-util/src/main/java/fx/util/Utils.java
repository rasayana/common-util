package fx.util;

import java.io.File;
import java.io.InputStream;
import javafx.stage.FileChooser;
import com.google.common.io.Files;

public abstract class Utils  {
    public static InputStream getFileStream(File file) throws Exception {
    	return Files.asByteSource(file).openStream();
    }
    public static String getString(String ... values) {
    	StringBuilder b = new StringBuilder();
    	for (String value : values) {
    		if (b.length() > 0)
    			b.append(',').append(' ');
    		b.append(value);
    	}
    	return b.toString();
    }
	public String selectedFlePath() {
        FileChooser fileChooser = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        return file.getAbsolutePath();
    }
	  /**
	   * Convert a byte array to a URL encoded string
	   *
	   * @param in
	   *            byte[]
	   * @return String
	   */
	public static String byteArrayToURLString(byte in[]) {
		byte ch = 0x00;
		int i = 0;
		if (in == null || in.length <= 0)
			return null;
		String pseudo[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
		StringBuffer out = new StringBuffer(in.length * 2);
		while (i < in.length) {
			// First check to see if we need ASCII or HEX
			if ((in[i] >= '0' && in[i] <= '9') || (in[i] >= 'a' && in[i] <= 'z') || (in[i] >= 'A' && in[i] <= 'Z') ||
						in[i] == '$' || in[i] == '-' || in[i] == '_' || in[i] == '.' || in[i] == '!') {
				out.append((char) in[i]);
				i++;
			} else {
				out.append('%');
				ch = (byte) (in[i] & 0xF0); // Strip off high nibble
				ch = (byte) (ch >>> 4); // shift the bits down
				ch = (byte) (ch & 0x0F); // must do this is high order bit is
				// on!
				out.append(pseudo[(int) ch]); // convert the nibble to a
				// String Character
				ch = (byte) (in[i] & 0x0F); // Strip off low nibble
				out.append(pseudo[(int) ch]); // convert the nibble to a
				// String Character
				i++;
			}
	    }
	    return new String(out);
	}
}