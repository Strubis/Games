package wizardgame.characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import wizardgame.core.GameObject;
import wizardgame.core.Handler;
import wizardgame.utils.Animation;
import wizardgame.utils.ID;
import wizardgame.utils.SpriteSheet;

/**
 *
 * @author EmersonPC
 */
public class Enemy extends GameObject{

    private Handler handler;
    private BufferedImage[] enemyImage = new BufferedImage[3];
    private Animation anim;
    private Random r = new Random();
    private int choose = 0, hp = 100;
    
    public Enemy(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        
        enemyImage[0] = ss.grabImage( 4, 1, 32, 32);
        enemyImage[1] = ss.grabImage( 5, 1, 32, 32);
        enemyImage[2] = ss.grabImage( 6, 1, 32, 32);
        
        anim = new Animation(3, enemyImage[0], enemyImage[1], enemyImage[2] );
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        choose = r.nextInt(10);
        
        for (int i = 0; i < handler.getObject().size(); i++) {
            GameObject tempObject = handler.getObject().get(i);
            
            if( tempObject.getId() == ID.Block ){
                if( getBoundsBig().intersects( tempObject.getBounds() ) ){
                    x += (velX * 5) * -1;
                    y += (velY * 5) * -1;
                    velX *= -1;
                    velY *= -1;
                }else if( choose == 0 ){
                    velX = ( r.nextInt(4 - -4) + -4 );
                    velY = ( r.nextInt(4 - -4) + -4 );
                }
            }
            
            if( tempObject.getId() == ID.Bullet ){
                if( getBounds().intersects( tempObject.getBounds() ) ){
                    hp -= 50;
                    handler.removeObject(tempObject);
                }
            }
        }
        
        anim.runAnimation();
        if( hp <= 0 ) handler.removeObject(this);
    }

    @Override
    public void render(Graphics g) {
        anim.drawAnimation( g, x, y, 0 );
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
    
    public Rectangle getBoundsBig(){
        return new Rectangle( x - 16, y - 16, 64, 64 );
    }
    
}
