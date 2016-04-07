import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by williamrosenbloom on 3/31/16.
 */
public class Clipper {
    public static void main(String[] args) {
        try {
            for (String ment : args) {
                File source = new File(ment);
                BufferedImage image = ImageIO.read(source);
                int minx = image.getWidth(), miny = image.getHeight(), maxx = 0, maxy = 0;
                for (int i = 0; i < image.getHeight(); i++) {
                    for (int j = 0; j < image.getWidth(); j++) {
                        int argb = image.getRGB(j, i);
                        int alpha = (argb >> 24) & 0xff;
                        if (alpha > 0) {
                            if (j < minx) {
                                minx = j;
                            }
                            if (i < miny) {
                                miny = i;
                            }
                            if (maxx < j) {
                                maxx = j;
                            }
                            if (maxy < i) {
                                maxy = i;
                            }
                        }
                    }
                }
                ImageIO.write(image.getSubimage(minx, miny, maxx - minx, maxy - miny), "png", source);
                System.out.printf("clipped %s (originally %d by %d) to bounds (x = %d, y = %d, width = %d, height = %d)\n",
                        source.getName(), image.getWidth(), image.getHeight(), minx, miny, maxx - minx, maxy - miny);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
