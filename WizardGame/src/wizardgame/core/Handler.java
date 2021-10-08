package wizardgame.core;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Emerson
 */
public class Handler {
    
    LinkedList<GameObject> object = new LinkedList<>();
    
    private boolean up = false, down = false, right = false, left = false;
    
    public void tick(){
        for (int i = 0; i < object.size(); i++) {
            GameObject gameObj = object.get( i );
            
            gameObj.tick();
        }
    }
    
    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++) {
            GameObject gameObj = object.get( i );
            
            gameObj.render( g );
        }
    }
    
    public void addObject(GameObject gameObj){
        object.add( gameObj );
    }
    
    public void removeObject(GameObject gameObj){
        object.remove( gameObj );
    }

    public LinkedList<GameObject> getObject() {
        return object;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
    
}
