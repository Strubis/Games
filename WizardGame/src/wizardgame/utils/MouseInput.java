package wizardgame.utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import wizardgame.core.Handler;
import wizardgame.core.Camera;
import wizardgame.core.GameObject;
import wizardgame.main.Game;
import wizardgame.skills.Bullet;

/**
 *
 * @author EmersonPC
 */
public class MouseInput extends MouseAdapter{
    
    private Handler handler;
    private Camera camera;
    private Game game;
    private SpriteSheet ss;
    
    public MouseInput(Handler handler, Camera camera, Game game, SpriteSheet ss){
        this.camera = camera;
        this.handler = handler;
        this.game = game;
        this.ss = ss;
    }
    
    public void mousePressed(MouseEvent e){
        int mx = (int) (e.getX() + camera.getX());
        int my = (int) (e.getY() + camera.getY());
        
        for(int i = 0; i < handler.getObject().size(); i++){
            GameObject tempObject = handler.getObject().get(i);
            
            if( tempObject.getId() == ID.Player && game.ammo >= 1 ){
                handler.addObject( 
                        new Bullet(
                                tempObject.getX() + 16, 
                                tempObject.getY() + 24, 
                                ID.Bullet, 
                                handler, mx, my, ss));
                game.ammo--;
            }
        }
    } 
    
}
