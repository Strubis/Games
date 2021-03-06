package wizardgame.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Emerson
 */
public class BufferedImageLoader {
    
    private BufferedImage image;
    
    public BufferedImage loadImage(String path){
        try {
            image = ImageIO.read( getClass().getResource( path ) );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return image;
    }
}
