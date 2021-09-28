package com.primeirogame.main;

import com.primeirogame.main.Game.STATE;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Emerson
 */
public class KeyInput extends KeyAdapter{
    private Handler handler;
    private Game game;
    private boolean[] keyDown = new boolean[4]; 
    
    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
        
        for(Boolean b : keyDown){
            b = false;
        }
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject gameObject = handler.object.get( i );
            
            if( gameObject.getId() == ID.Player ){
                // Events for Player One
                
                if( key == KeyEvent.VK_W ){
                    gameObject.setVelY( -handler.spd );
                    keyDown[0] = true;
                }
                if( key == KeyEvent.VK_S ){
                    gameObject.setVelY( handler.spd );
                    keyDown[1] = true;
                }
                if( key == KeyEvent.VK_D ){
                    gameObject.setVelX( handler.spd );
                    keyDown[2] = true;
                }
                if( key == KeyEvent.VK_A ){
                    gameObject.setVelX( -handler.spd );
                    keyDown[3] = true;
                }
            }
            
//            if( gameObject.getId() == ID.Player2 ){
//                // Events for Player Two
//                
//                if( key == KeyEvent.VK_UP ) gameObject.setVelY( -5 );
//                if( key == KeyEvent.VK_DOWN ) gameObject.setVelY( 5 );
//                if( key == KeyEvent.VK_RIGHT ) gameObject.setVelX( 5 );
//                if( key == KeyEvent.VK_LEFT ) gameObject.setVelX( -5 );
//            }

        }
        
        if( key == KeyEvent.VK_P ){
            if( Game.gameState == STATE.Game ){
                Game.paused = !Game.paused;
            }
        }
        
        if( key == KeyEvent.VK_SPACE ){
            if( Game.gameState == STATE.Game ){
                Game.gameState = STATE.Shop;
            }else if( Game.gameState == STATE.Shop ){
                Game.gameState = STATE.Game;
            }
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject gameObject = handler.object.get( i );
            
            if( gameObject.getId() == ID.Player ){
                // Events for Player One
                
                if( key == KeyEvent.VK_W ) keyDown[0] = false;
                if( key == KeyEvent.VK_S ) keyDown[1] = false;
                if( key == KeyEvent.VK_D ) keyDown[2] = false;
                if( key == KeyEvent.VK_A ) keyDown[3] = false;
                
                // Vertical movement
                if( !keyDown[0] && !keyDown[1]  )
                    gameObject.setVelY( 0 );
                
                // Horizontal movement
                if( !keyDown[2] && !keyDown[3] )
                    gameObject.setVelX( 0 );
            }
            
//            if( gameObject.getId() == ID.Player2 ){
//                // Events for Player Two
//                
//                if( key == KeyEvent.VK_UP ) gameObject.setVelY( 0 );
//                if( key == KeyEvent.VK_DOWN ) gameObject.setVelY( 0 );
//                if( key == KeyEvent.VK_RIGHT ) gameObject.setVelX( 0 );
//                if( key == KeyEvent.VK_LEFT ) gameObject.setVelX( 0 );
//            }
        }
    }
}
