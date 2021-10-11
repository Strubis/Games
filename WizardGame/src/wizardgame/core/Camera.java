package wizardgame.core;

/**
 *
 * @author Emerson
 */
public class Camera {
    
    private float x, y;
    
    public Camera(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    public void tick(GameObject gameObject){
        x += ( ( gameObject.getX() - x ) - 1000/2 ) * 0.05f;
        y += ( ( gameObject.getY() - y ) - 563/2 ) * 0.05f;
        
        if( x <= 0 ) x = 0;
        if( x >= 1048 ) x = 1048;
        
        if( y <= 0 ) y = 0;
        if( y >= 563+62 ) y = 563+62;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
}
