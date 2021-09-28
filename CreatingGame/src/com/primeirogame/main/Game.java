package com.primeirogame.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Emerson
 */
public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = Long.MIN_VALUE;
    
    static final int WIDTH = 640;
    static final int HEIGHT = WIDTH / 12 * 9;
    
    private Thread thread;
    private boolean running = false;
    
    private final Random r;
    private final Handler handler;
    private final HUD hud;
    private final Spawn spawner; 
    private final Shop shop;
    private Menu menu;
    
    public enum STATE{
      Menu,
      Select,
      Help,
      Shop,
      Game,
      End
    };
    
    public static STATE gameState = STATE.Menu;
    public static boolean paused = false;
    
    // 0 -> Normal
    // 1 -> Hard
    public static int diff = 0;
    
    public static BufferedImage spriteSheet;
    
    public Game(){
        BufferedImageLoader loader = new BufferedImageLoader();
        spriteSheet = loader.loader( "/res/teste.png" );
        
        handler = new Handler();
        hud = new HUD();
        shop = new Shop( handler, hud );
        menu = new Menu( this, handler, hud );
        
        this.addKeyListener( new KeyInput( handler, this ) );
        this.addMouseListener( menu );
        this.addMouseListener( shop );
        
        //AudioPlayer.load();
        
        new Window( WIDTH, HEIGHT, "First Game", this );
        
        r = new Random();
        spawner = new Spawn( handler, hud, this );
        
        if( gameState == STATE.Game ){
            //handler.addObject( new Player( r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Player ) );
            handler.addObject( new Player( WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler ) );
            handler.addObject( new BasicEnemy( r.nextInt( WIDTH ), r.nextInt( HEIGHT ), ID.BasicEnemy, handler ) );
        }else{
            for(int i = 0; i < 20; i++){
                handler.addObject( new MenuParticle( r.nextInt( WIDTH ), 
                        r.nextInt( HEIGHT ), ID.MenuParticle, handler ) );
            }
        }
        
    }
    
    public synchronized void start(){
        thread = new Thread( this );
        thread.start();
        running = true;
    }
    
    public synchronized void stop(){
        try{
            
            thread.join();
            running = false;
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void run(){
        this.requestFocus();
        
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        
        while( running ){
            long now = System.nanoTime();
            delta += ( now - lastTime ) / ns;
            lastTime = now;
            
            while( delta >= 1 ){
                tick();
                delta--;
            }
            
            if( running ){
                render();
            }
            frames++;
            
            if( System.currentTimeMillis() - timer > 1000 ){
                timer += 1000;
                //System.out.println( "FPS: " + frames );
                frames = 0;
            }
        }
        
        stop();
    }
    
    private void tick(){
        if( gameState == STATE.Game ){
            if( !paused ){
                hud.tick();
                spawner.tick();
                handler.tick();
        
                if( HUD.HEALTH <= 0 ){
                    HUD.HEALTH = 100;
                    gameState = STATE.End;
                    handler.clearEnemys();

                    for(int i = 0; i < 10; i++){
                        handler.addObject( new MenuParticle( r.nextInt( WIDTH ), 
                            r.nextInt( HEIGHT ), ID.MenuParticle, handler ) );
                    }
                }
            }
            
        }else if( gameState == STATE.Menu ||
                  gameState == STATE.End ||
                  gameState == STATE.Select ){
            menu.tick();
            handler.tick();
        }
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        
        if( bs == null ){
            this.createBufferStrategy( 3 );
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        g.setColor( Color.BLACK );
        g.fillRect( 0, 0, WIDTH, HEIGHT );
        
        if( paused ){
            g.setColor( Color.WHITE );
            g.drawString( "PAUSED", 100, 100 );
        }
        
        if( gameState == STATE.Game ){
            hud.render( g );
            handler.render( g );
        }else if( gameState == STATE.Shop ){
            shop.render( g );
        }else if( gameState == STATE.Menu || 
                gameState == STATE.Help || 
                gameState == STATE.End  ||
                gameState == STATE.Select ){
            menu.render( g );
            handler.render( g );
        }
        
        g.dispose();
        bs.show();
    }
    
    public static float clamp(float var, float min, float max){
        if( var >= max ){
            return max;
        }else if( var <= min ){
            return min;
        }else{
            return var;
        }
    }
    
    public static void main(String[] args) {
        new Game();
    }

}
