import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ColorLister {
    static class ColorInstance {
        public final int argb;
        private int count;

        public ColorInstance(int color) {
            argb = color;
            count = 1;
        }

        public int getAlpha() {
            return (argb >> 24) & 0xff;
        }

        public int getRed() {
            return (argb >> 16) & 0xff;
        }

        public int getGreen() {
            return (argb >> 8) & 0xff;
        }

        public int getBlue() {
            return argb & 0xff;
        }

        public int getCount() {
            return count;
        }

        public void increment() {
            count++;
        }

        public boolean equals(Object obj) {
            if (obj instanceof ColorInstance) {
                ColorInstance other = (ColorInstance) obj;
                return argb == other.argb;
            }
            return super.equals(obj);
        }

        @Override
        public String toString() {
            return String.format("(a,r,g,b) = (%d, %d, %d, %d) [%d]", getAlpha(), getRed(), getGreen(), getBlue(), count);
        }
    }

    static class ExclusiveList {
        ArrayList<ColorInstance> list = new ArrayList<>();

        public void add(ColorInstance addition) {
            for (ColorInstance element : list) {
                if (element.equals(addition)) {
                    element.increment();
                    return;
                }
            }
            list.add(addition);
        }

        public void print() {
            System.out.printf("Total Colors Found: %d\n\n", list.size());
            for (ColorInstance elment : list) {
                System.out.println(elment.toString());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        try {
            for (String ment : args) {
                BufferedImage image = ImageIO.read(new File(ment));
                ExclusiveList list = new ExclusiveList();
                for (int i = 0; i < image.getHeight(); i++) {
                    for (int j = 0; j < image.getWidth(); j++) {
                        list.add(new ColorInstance(image.getRGB(j, i)));
                    }
                }
                System.out.format("COLOR LIST FOR %s:\n\n", ment);
                list.print();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}