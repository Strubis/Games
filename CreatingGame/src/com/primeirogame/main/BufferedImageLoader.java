package com.primeirogame.main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Emerson
 */
public class BufferedImageLoader {
    BufferedImage image;
    
    public BufferedImage loader(String path){
        try {
            image = ImageIO.read( getClass().getResource( path ) );
        } catch (IOException ex) {
            Logger.getLogger(BufferedImageLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }
}
