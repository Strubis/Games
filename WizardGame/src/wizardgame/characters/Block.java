package wizardgame.characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import wizardgame.core.GameObject;
import wizardgame.utils.ID;
import wizardgame.utils.SpriteSheet;

/**
 *
 * @author Emerson
 */
public class Block extends GameObject{
    
    private BufferedImage blockImage;
    
    public Block(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);
        
        blockImage = ss.grabImage( 5, 2, 32, 32 );
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage( blockImage, x, y, null );
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle( x, y, 32, 32 );
    }
    
}
