package com.primeirogame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Emerson
 */
public class BasicEnemy extends GameObject{
    private final Handler handler;
    private BufferedImage enemyImage;
    
    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        
        this.handler = handler;
        
        velX = 5;
        velY = 5;
        
        SpriteSheet ss = new SpriteSheet(Game.spriteSheet);
        enemyImage = ss.grabImage(2, 1, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        if( y <= 0 || y >= Game.HEIGHT - 32 ) velY *= -1;
        if( x <= 0 || x >= Game.WIDTH - 16 ) velX *= -1;
        
        //handler.addObject( new Trail( x, y, ID.Trail, Color.RED, 16, 16, 0.03f, handler ) );
    }

    @Override
    public void render(Graphics g) {
        //g.setColor( Color.red );
        //g.fillRect( (int) x, (int) y, 16, 16 );
        g.drawImage(enemyImage, (int) x, (int) y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle( (int) x, (int) y, 16, 16 );
    }
    
}
