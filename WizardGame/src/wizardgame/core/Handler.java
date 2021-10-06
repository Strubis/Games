package wizardgame.core;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Emerson
 */
public class Handler {
    
    LinkedList<GameObject> objects = new LinkedList<>();
    
    public void tick(){
        for (int i = 0; i < objects.size(); i++) {
            GameObject gameObj = objects.get( i );
            
            gameObj.tick();
        }
    }
    
    public void render(Graphics g){
        for (int i = 0; i < objects.size(); i++) {
            GameObject gameObj = objects.get( i );
            
            gameObj.render( g );
        }
    }
    
    public void addObject(GameObject gameObj){
        objects.add( gameObj );
    }
    
    public void removeObject(GameObject gameObj){
        objects.remove( gameObj );
    }
    
}
