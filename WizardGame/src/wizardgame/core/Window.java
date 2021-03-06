package wizardgame.core;

import java.awt.Dimension;
import javax.swing.JFrame;
import wizardgame.main.Game;

/**
 *
 * @author Emerson
 */
public class Window {
    
    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame( title );
        
        frame.setPreferredSize( new Dimension( width, height ) );
        frame.setMaximumSize( new Dimension( width, height ) );
        frame.setMinimumSize( new Dimension( width, height ) );
        
        frame.add( game );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setResizable( false );
        frame.setLocationRelativeTo( null );
        frame.setVisible( true );
        
    }
    
}
