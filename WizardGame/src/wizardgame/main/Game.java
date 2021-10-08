package wizardgame.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import wizardgame.characters.Wizard;
import wizardgame.core.Handler;
import wizardgame.core.Window;
import wizardgame.utils.ID;
import wizardgame.utils.KeyInput;

/**
 *
 * @author Emerson
 */
public class Game extends Canvas implements Runnable{

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    
    public Game(){
        new Window( 1000, 563, "Wizard Game", this );
        start();
        
        handler = new Handler();
        //handler.addObject( new Box( 100, 100, ID.Block ) );
        this.addKeyListener( new KeyInput( handler ) );
        
        handler.addObject( new Wizard( 100, 100, ID.Player, handler ) );
    }
    
    private void start(){
        isRunning = true;
        
        thread = new Thread( this );
        thread.start();
    }
    
    private void stop(){
        isRunning = false;
            
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        this.requestFocus();
        
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        
        while( isRunning ){
            long now = System.nanoTime();
            delta += ( now - lastTime ) / ns;
            lastTime = now;
            
            while( delta >= 1 ){
                tick();
                delta--;
            }
            
//            if( isRunning ){
//                render();
//            }
            render();
            frames++;
            
            if( System.currentTimeMillis() - timer > 1000 ){
                timer += 1000;
                //System.out.println( "FPS: " + frames );
                frames = 0;
            }
        }
        
        stop();
    }
    
    // Update
    public void tick(){
        handler.tick();
    }
    
    // Render
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        
        if( bs == null ){
            this.createBufferStrategy( 3 );
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, 1000, 563);
        
        handler.render( g );
        
        g.dispose();
        bs.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Game();
    }

}
