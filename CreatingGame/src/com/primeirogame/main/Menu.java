package com.primeirogame.main;

import static com.primeirogame.main.Game.HEIGHT;
import com.primeirogame.main.Game.STATE;
import static com.primeirogame.main.Game.WIDTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 *
 * @author Emerson
 */
public class Menu extends MouseAdapter{
    
    private final Game game;
    private final Handler handler;
    private final Random r = new Random();
    private final HUD hud;
    
    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }
    
    @Override
    public void mousePressed(MouseEvent evt){
        int mx = evt.getX();
        int my = evt.getY();
        
        if( Game.gameState == STATE.Menu ){
            // Play button
            if( mouseOver( mx, my, 210, 150, 200, 64 ) ){
                //Game.gameState = STATE.Game;
                
                //handler.addObject( new Player( Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler ) );
                //handler.clearEnemys();
                ////handler.clearM();
                //handler.addObject( new BasicEnemy( r.nextInt( Game.WIDTH - 50 ), 
                        //r.nextInt( Game.HEIGHT - 50 ), ID.BasicEnemy, handler ) );
                Game.gameState = STATE.Select;
                return;
            }
            
            // Help button
            if( mouseOver( mx, my, 210, 250, 200, 64 ) ){
                Game.gameState = STATE.Help;
            }
            
            // Quit button
            if( mouseOver( mx, my, 210, 350, 200, 64 ) ){
                System.exit( 0 );
            }
        }
        
        if( Game.gameState == STATE.Select ){
            // Normal button
            if( mouseOver( mx, my, 210, 150, 200, 64 ) ){
                Game.gameState = STATE.Game;
                
                handler.addObject( new Player( Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler ) );
                handler.clearEnemys();
                //handler.clearM();
                handler.addObject( new BasicEnemy(r.nextInt( Game.WIDTH - 50 ), 
                        r.nextInt( Game.HEIGHT - 50 ), ID.BasicEnemy, handler ) );
                Game.diff = 0;
            }
            
            // Hard button
            if( mouseOver( mx, my, 210, 250, 200, 64 ) ){
                Game.gameState = STATE.Game;
                
                handler.addObject( new Player( Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler ) );
                handler.clearEnemys();
                //handler.clearM();
                handler.addObject( new HardEnemy( r.nextInt( Game.WIDTH - 50 ), 
                        r.nextInt( Game.HEIGHT - 50 ), ID.BasicEnemy, handler ) );
                Game.diff = 1;
            }
            
            // Back button
            if( mouseOver( mx, my, 210, 350, 200, 64 ) ){
                Game.gameState = STATE.Menu;
            }
        }
        
        // Back button for help
        if( Game.gameState == STATE.Help ){
            if( mouseOver( mx, my, 210, 350, 200, 64 ) ){
                Game.gameState = STATE.Menu;
            }
        }
        
        // Try again button
        if( Game.gameState == STATE.End ){
            if( mouseOver( mx, my, 210, 350, 200, 64 ) ){
                Game.gameState = STATE.Menu;
                
                hud.setLevel( 1 );
                hud.setScore( 0 );
                
                
                //handler.addObject( new Player( Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler ) );
                //handler.clearEnemys();
                //handler.addObject( new BasicEnemy( r.nextInt( Game.WIDTH - 50 ), 
                //        r.nextInt( Game.HEIGHT - 50 ), ID.BasicEnemy, handler ) ); 
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent evt){
        
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if( mx > x && mx < x + width ){
            return my > y && my < y + height;
        }
        
        return false;
    }
    
    public void tick(){
        
    }
    
    public void render(Graphics g){
        Font font = new Font( "Arial", 1, 50 );
        Font font2 = new Font( "Arial", 1, 30 );
        
        if( game.gameState == STATE.Menu ){
            drawStrings( "Wave", 260, 70, g, font2, Color.WHITE );

            // Play
            g.drawRect( 210, 150, 200, 64 );
            drawStrings( "Play", 270, 190, g, font2, Color.WHITE );

            // Help
            g.drawRect( 210, 250, 200, 64 );
            drawStrings( "Help", 270, 290, g, font2, Color.WHITE );

            // Quit
            g.drawRect( 210, 350, 200, 64 );
            drawStrings( "Quit", 270, 390, g, font2, Color.WHITE );
        }else if( game.gameState == STATE.Help ){
            Font font3 = new Font( "Arial", 1, 20 );
            
            drawStrings( "Help", 240, 70, g, font, Color.WHITE );
            
            String help = "Use WASD keys to move player and dodge enemies";
            drawStrings( help, 50, 190, g, font3, Color.WHITE );
            
            g.drawRect( 210, 350, 200, 64 );
            drawStrings( "Back", 270, 390, g, font2, Color.WHITE );
        }else if( game.gameState == STATE.End ){
            Font font3 = new Font( "Arial", 1, 20 );
            
            drawStrings( "Game Over", 180, 70, g, font, Color.WHITE );
            
            String help = "You lose with a score: " + hud.getScore();
            drawStrings( help, 175, 200, g, font3, Color.WHITE );
            
            g.drawRect( 210, 350, 200, 64 );
            drawStrings( "Try Again", 245, 390, g, font2, Color.WHITE );
        }else if( game.gameState == STATE.Select ){
            drawStrings( "Select Difficulty", 200, 70, g, font2, Color.WHITE );

            // Normal
            g.drawRect( 210, 150, 200, 64 );
            drawStrings( "Normal", 260, 190, g, font2, Color.WHITE );

            // Hard
            g.drawRect( 210, 250, 200, 64 );
            drawStrings( "Hard", 270, 290, g, font2, Color.WHITE );

            // Back
            g.drawRect( 210, 350, 200, 64 );
            drawStrings( "Back", 270, 390, g, font2, Color.WHITE );
        }
    }
    
    private void drawStrings(String text, int x, int y, Graphics g, Font font, Color color){
        g.setFont( font );
        g.setColor( color );
        g.drawString( text, x, y );
    }
}
