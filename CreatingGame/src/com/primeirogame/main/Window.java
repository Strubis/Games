package com.primeirogame.main;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Emerson
 */
public class Window extends Canvas{
    
    // Long = -240840600533728354L
    private static final long serialVersionUID = Long.MIN_VALUE;
    
    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame( title );
        
        frame.setPreferredSize( new Dimension( width, height ) );
        frame.setMaximumSize( new Dimension( width, height ) );
        frame.setMinimumSize( new Dimension( width, height ) );
        
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setResizable( false );
        frame.setLocationRelativeTo( null );
        frame.add( game );
        frame.setVisible( true );
        game.start();
    }
    
}
