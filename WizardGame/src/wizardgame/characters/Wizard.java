package wizardgame.characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import wizardgame.core.GameObject;
import wizardgame.core.Handler;
import wizardgame.main.Game;
import wizardgame.utils.Animation;
import wizardgame.utils.ID;
import wizardgame.utils.SpriteSheet;

/**
 *
 * @author Emerson
 */
public class Wizard extends GameObject{
    
    private final Handler handler;
    private Game game;
    private BufferedImage[] wizardImage = new BufferedImage[3];
    private Animation anim;
    
    public Wizard(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
        super(x, y, id, ss);
        
        this.handler = handler;
        this.game = game;
        
        wizardImage[0] = ss.grabImage( 1, 1, 32, 48 );
        wizardImage[1] = ss.grabImage( 2, 1, 32, 48 );
        wizardImage[2] = ss.grabImage( 3, 1, 32, 48 );
        
        anim = new Animation( 3, wizardImage[0], wizardImage[1], wizardImage[2] );
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        collision();
        
        if( handler.isUp() ) velY = -5;
        else if( !handler.isDown() ) velY = 0;
        
        if( handler.isDown() ) velY = 5;
        else if( !handler.isUp() ) velY = 0;
        
        if( handler.isRight() ) velX = 5;
        else if( !handler.isLeft() ) velX = 0;
        
        if( handler.isLeft() ) velX = -5;
        else if( !handler.isRight() ) velX = 0;
        
        anim.runAnimation();
    }
    
    private void collision(){
        for (int i = 0; i < handler.getObject().size(); i++) {
            GameObject tempObject = handler.getObject().get(i);
            
            if( tempObject.getId() == ID.Block ){
                if( getBounds().intersects( tempObject.getBounds() ) ){
                    x += velX * -1;
                    y += velY * -1;
                }
            }
            
            if( tempObject.getId() == ID.Crate ){
                if( getBounds().intersects( tempObject.getBounds() ) ){
                    game.ammo += 10;
                    handler.removeObject(tempObject);
                }
            }
            
            if( tempObject.getId() == ID.Enemy ){
                if( getBounds().intersects( tempObject.getBounds() ) ){
                    game.hp--;
                }
            }
            
        }
    }
    
    @Override
    public void render(Graphics g) {
        if( velX == 0 && velY == 0 )
            g.drawImage( wizardImage[0], x, y, null );
        else
            anim.drawAnimation( g, x, y, 0 );
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle( x, y, 32, 48 );
    }
    
}
