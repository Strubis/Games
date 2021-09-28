package com.primeirogame.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Emerson
 */
public class Trail extends GameObject{
    private float alpha = 1;
    private final Handler handler;
    private final Color color;
    private final int width, height;
    
    // Life -> 0.001 - 0.1
    private final float life;
    
    public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
        super(x, y, id);
        
        this.color = color;
        this.handler = handler;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    @Override
    public void tick() {
        if( alpha > life ){
            alpha -= ( life - 0.01f );
        }else handler.removeObject( this );
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setComposite( makeTransparent( alpha ) );
        
        g.setColor( color );
        g.fillRect( (int) x, (int) y, width, height );
        
        g2D.setComposite( makeTransparent( 1 ) );
    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        
        return ( AlphaComposite.getInstance( type, alpha ) );
    }
    
    @Override
    public Rectangle getBounds() {
        return null;
    }
    
}
