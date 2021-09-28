package com.primeirogame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Emerson
 */
public class EnemyBossBullet extends GameObject{
    private final Handler handler;
    private Random r = new Random();
    
    public EnemyBossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        
        this.handler = handler;
        
        velX = ( r.nextInt( 5 - -5 ) + -5 );
        velY = 5;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        //if( y <= 0 || y >= Game.HEIGHT - 32 ) velY *= -1;
        //if( x <= 0 || x >= Game.WIDTH - 16 ) velX *= -1;
        
        if( y >= Game.HEIGHT ) handler.removeObject( this );
        
        handler.addObject( new Trail( x, y, ID.Trail, Color.RED, 16, 16, 0.03f, handler ) );
    }

    @Override
    public void render(Graphics g) {
        g.setColor( Color.red );
        g.fillRect( (int) x, (int) y, 16, 16 );
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle( (int) x, (int) y, 16, 16 );
    }
    
}
