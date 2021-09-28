package com.primeirogame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Emerson
 */
public class Player extends GameObject{
    Random r = new Random();
    private final Handler handler;
    private BufferedImage playerImage;
    
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        
        SpriteSheet ss = new SpriteSheet(Game.spriteSheet);
        playerImage = ss.grabImage( 1, 1, 32, 32 );
    }
    
    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        x = Game.clamp( x, 0, Game.WIDTH - 50 );
        y = Game.clamp( y, 0, Game.HEIGHT - 70 );
        
        //handler.addObject( new Trail( x, y, ID.Trail, Color.WHITE, 32, 32, 0.05f, handler ) );
        
        collision();
    }
    
    private void collision(){
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject gameObject = handler.object.get( i );
            
            if( gameObject.getId() == ID.BasicEnemy || 
                gameObject.getId() == ID.FastEnemy ||
                gameObject.getId() == ID.SmartEnemy ||
                gameObject.getId() == ID.EnemyBoss ){
                // Collision code
                if( getBounds().intersects( gameObject.getBounds() ) ){
                    HUD.HEALTH -= 2;
                    //handler.removeObject(gameObject);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        //g.setColor( Color.CYAN );
        //g.fillRect( (int) x, (int) y, 32, 32 );
        g.drawImage( playerImage, (int) x, (int) y, null );
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle( (int) x, (int) y, 32, 32 );
    }
    
}
