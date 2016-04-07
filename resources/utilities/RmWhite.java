import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by williamrosenbloom on 4/1/16.
 */
public class RmWhite {
    public static void main(String[] args) {
        try {
            for (String ment : args) {
                File file = new File(ment);
                BufferedImage image = ImageIO.read(file);
                BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
                for (int i = 0; i < image.getHeight(); i++) {
                    for (int j = 0; j < image.getWidth(); j++) {
                        int argb = image.getRGB(j, i);
                        int red = (argb >> 16) & 0xff;
                        int green = (argb >> 8) & 0xff;
                        int blue = argb & 0xff;
                        if (red > 240 && green > 240 & blue > 240) {
                            copy.setRGB(j, i, 0);
                        } else {
                            copy.setRGB(j, i, argb);
                        }
                    }
                }
                ImageIO.write(copy, "png", file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
