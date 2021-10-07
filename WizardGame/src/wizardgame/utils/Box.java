package wizardgame.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import wizardgame.core.GameObject;

/**
 *
 * @author Emerson
 */
public class Box extends GameObject{

    public Box(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics g) {
        g.setColor( Color.red );
        g.fillRect( x, y, 20, 20 );
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
    
}
