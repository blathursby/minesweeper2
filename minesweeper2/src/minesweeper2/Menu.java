package minesweeper2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	
	ApplicationFrame application;
	public Integer width, height, mines, option;
	
	public Menu( ApplicationFrame applicationFrame, Integer _option, Integer _width, Integer _height, Integer _mines){
		application = applicationFrame;
		
		width = _width;
		height = _height;
		mines = _mines;
		option = _option;
		
		this.add( createMenu() );
	}
	
	private JMenu createMenu(){
		JMenu file = new JMenu("File");
			
		file.add( addMenuItem("New game", newGame()) );
		file.add( addMenuItem("Options", options()) );
		file.add( addMenuItem("Exit", exitGame()) );
		
		return file;
	}
	
	private JMenuItem addMenuItem( String str, ActionListener action ){
		JMenuItem eMenuItem = new JMenuItem( str );
		eMenuItem.addActionListener(action);
	        
		return eMenuItem;
	}
	
	private ActionListener newGame(){
    	ActionListener exitGameAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	new ApplicationFrame( width, height, mines, option);
            	application.dispose();
            }
        };
        
        return exitGameAction;
    }
	
	private Menu getInstance(){
		return this;
	}
	
	private ActionListener exitGame(){
    	ActionListener exitGameAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	application.dispose();
            }
        };
        
        return exitGameAction;
    }
	
	private ActionListener options(){
    	ActionListener exitGameAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	new Options( getInstance() );
            }
        };
        
        return exitGameAction;
    }
}
