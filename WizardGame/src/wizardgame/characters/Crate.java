package wizardgame.characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import wizardgame.core.GameObject;
import wizardgame.utils.ID;

/**
 *
 * @author EmersonPC
 */
public class Crate extends GameObject{

    public Crate(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor( Color.CYAN );
        g.fillRect(x, y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
    
}