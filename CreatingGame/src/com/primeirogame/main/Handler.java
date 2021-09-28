package com.primeirogame.main;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Emerson
 */
public class Handler {
    LinkedList<GameObject> object = new LinkedList<>();
    
    public int spd = 5;
    
    public void tick(){
        /*for(GameObject gameObject : object){
            gameObject.tick();
        }*/
        for (int i = 0; i < object.size(); i++) {
            object.get( i ).tick();
        }
    }
    
    public void render(Graphics g){
        /*for (GameObject gameObject : object) {
            gameObject.render( g );
        }*/
        for(int i = 0; i < object.size(); i++){
            try{
                object.get( i ).render( g );
            }catch(Exception e){
                System.out.println("Error: " + e);
            }
        }
    }
    
    public void addObject(GameObject object){
        this.object.add( object );
    }
    
    public void removeObject(GameObject object){
        this.object.remove( object );
    }
    
    public void clearEnemys() {
        for (int i = 0; i < object.size(); i++) {
            GameObject gameObject = object.get( i );
            
            if( Game.gameState != Game.STATE.Game )
                object.clear();
            
            if( gameObject.getId() != ID.Player ){
                removeObject( gameObject );
                i--;
            }
            
//            if( gameObject.getId() == ID.Player ){
//                if( Game.gameState != Game.STATE.End )
//                object.add( new Player( (int) gameObject.getX(), (int) gameObject.getY(), 
//                        ID.Player, this ) );
//            }
        }
    }
}
