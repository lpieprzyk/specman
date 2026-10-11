package specman.graphics;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class IconReader {

  public static ImageIcon readImageIcon(String iconBasename) {
    String resource = "images/" + iconBasename + ".png";
    try {
      URL imageURL = IconReader.class.getClassLoader().getResource(resource);
      Image image = ImageIO.read(imageURL);
      if (image == null) {
        throw new IllegalArgumentException("Can't load image icon " + resource);
      }
      return new ImageIcon(image);
    }
    catch (IOException iox) {
      iox.printStackTrace();
      throw new IllegalArgumentException("Error reading image icon " + resource + ": " + iox.getMessage());
    }
  }

  /**
   * Loads an SVG classpath resource as vector icon. Pure black is mapped to the current label foreground
   * color, so the icon follows the look-and-feel; all other colors stay untouched.
   */
  public static FlatSVGIcon readSvgIcon(String svgPath, int size) {
    assertIconResourceNotNull(svgPath);
    FlatSVGIcon icon = new FlatSVGIcon(svgPath, size, size, IconReader.class.getClassLoader());
    icon.setColorFilter(new FlatSVGIcon.ColorFilter(
        color -> Color.BLACK.equals(color) ? UIManager.getColor("Label.foreground") : color));
    return icon;
  }

  private static void assertIconResourceNotNull(String svgPath) {
    if (IconReader.class.getClassLoader().getResource(svgPath) == null) {
      throw new IllegalArgumentException("Can't load svg icon " + svgPath);
    }
  }

}
