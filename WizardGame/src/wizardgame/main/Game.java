package wizardgame.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import wizardgame.characters.Block;
import wizardgame.characters.Crate;
import wizardgame.characters.Enemy;
import wizardgame.characters.Wizard;
import wizardgame.core.Camera;
import wizardgame.core.Handler;
import wizardgame.core.Window;
import wizardgame.utils.BufferedImageLoader;
import wizardgame.utils.ID;
import wizardgame.utils.KeyInput;
import wizardgame.utils.MouseInput;
import wizardgame.utils.SpriteSheet;

/**
 *
 * @author Emerson
 */
public class Game extends Canvas implements Runnable{

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Camera camera;
    private SpriteSheet ss;
    
    private BufferedImage level = null;
    private BufferedImage spriteSheet = null;
    
    // Elements
    private BufferedImage floor = null;
    
    public int ammo = 100, hp = 100;
    
    public Game(){
        new Window( 1000, 563, "Wizard Game", this );
        start();
        
        handler = new Handler();
        camera = new Camera( 0, 0 );
        //handler.addObject( new Box( 100, 100, ID.Block ) );
        this.addKeyListener( new KeyInput( handler ) );
        
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage( "/wizardgame/images/WizardLevel.png" );
        spriteSheet = loader.loadImage( "/wizardgame/images/sprite_sheet.png" );
        ss = new SpriteSheet( spriteSheet );
        
        floor = ss.grabImage( 4, 2, 32, 32 );
        
        this.addMouseListener( new MouseInput(handler, camera, this, ss));
        loadLevel( level );
        
        //handler.addObject( new Wizard( 100, 100, ID.Player, handler ) );
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
        for (int i = 0; i < handler.getObject().size(); i++) {
            if( handler.getObject().get( i ).getId() == ID.Player ){
                camera.tick( handler.getObject().get( i ) );
            }
        }
        
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
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.translate( -camera.getX(), -camera.getY() );
        
        for (int xx = 0; xx < 30*72; xx+=32) {
            for (int yy = 0; yy < 30*72; yy+=32) {
                g.drawImage( floor, xx, yy, null );
            }
        }
        
        handler.render( g );
        
        g2d.translate( camera.getX(), camera.getY() );
        
        // Bar life
        g.setColor( Color.GRAY );
        g.fillRect( 5, 5, 200, 32 );
        g.setColor( Color.GREEN );
        g.fillRect( 5, 5, hp * 2, 32 );
        g.setColor( Color.BLACK );
        g.drawRect( 5, 5, 200, 32 );
        
        // Ammo bar
        g.setColor( Color.WHITE );
        g.drawString( "Ammo: " + ammo, 5, 50 );
        
        g.dispose();
        bs.show();
    }
    
    // Loading level
    private void loadLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        
        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                
                int pixel = image.getRGB( xx, yy );
                int red = ( pixel >> 16 ) & 0xff;
                int green = ( pixel >> 8 ) & 0xff;
                int blue = ( pixel ) & 0xff;
                
                if( red == 255 )
                    handler.addObject( new Block( xx*32, yy*32, ID.Block, ss ) );
                
                if( blue == 255 && green == 0 )
                    handler.addObject( new Wizard( xx*32, yy*32, ID.Player, handler, this, ss ) );
                
                if( green == 255 && blue == 0 )
                    handler.addObject( new Enemy(xx*32, yy*32, ID.Enemy, handler, ss));
                
                if( green == 255 && blue == 255 )
                    handler.addObject( new Crate(xx*32, yy*32, ID.Crate, ss));
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Game();
    }

}
