package clothesarchive.gui.customSettings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CAImageResizer{

    private static ImageIcon imageIcon;


    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param file image to be resized
     * @throws IOException if the file reader fails
     */
    public static ImageIcon resize(File file) throws IOException {

        // reads input image

        BufferedImage inputImage = ImageIO.read(file);
        Dimension imgSize = new Dimension(inputImage.getWidth(), inputImage.getHeight());
        Dimension boundary = new Dimension(450, 650);

        Dimension newImgSize = getScaledDimension(imgSize, boundary);
        // creates output image
        BufferedImage outputImage = new BufferedImage(newImgSize.width, newImgSize.height, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, newImgSize.width, newImgSize.height, null);
        g2d.dispose();


        imageIcon = new ImageIcon(outputImage);
        return imageIcon;
    }

    /**
     * Method that resizes image based on maximum size
     * @param imgSize
     * @param boundary
     * @return the new dimensions of the image
     */
    private static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

}
