package com.primeirogame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Emerson
 */
public class MenuParticle extends GameObject{
    private final Handler handler;
    private final Color color;
    private final Random r = new Random();
    
    public MenuParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        
        velX = ( r.nextInt( 5 - -5 ) + -5 );
        velY = ( r.nextInt( 5 - -5 ) + -5 );
        
        if( velX == 0 ) velX = 1;
        if( velY == 0 ) velY = 1;
        
        this.color = new Color( r.nextInt( 255 ), r.nextInt( 255 ), r.nextInt( 255 ) );
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        if( y <= 0 || y >= Game.HEIGHT - 32 ) velY *= -1;
        if( x <= 0 || x >= Game.WIDTH - 16 ) velX *= -1;
        
        handler.addObject( new Trail( x, y, ID.Trail, color, 16, 16, 0.05f, handler ) );
    }

    @Override
    public void render(Graphics g) {
        g.setColor( color );
        g.fillRect( (int) x, (int) y, 16, 16 );
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle( (int) x, (int) y, 16, 16 );
    }
    
}
