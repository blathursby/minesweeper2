package minesweeper2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class Options extends JFrame {

	private Menu menu;
	
	private JRadioButton simple = new JRadioButton("Simple : 8 x 8 with 6 mines");
	private JRadioButton intermediate = new JRadioButton("Intermediate : 15 x 12 with 12 mines");
	private JRadioButton advanced = new JRadioButton("Advanced : 20 x 16 with 25 mines");
	
	public Options(Menu _menu){
		menu = _menu;
		
		this.setLayout( new FlowLayout(FlowLayout.LEFT) );
		this.setSize( 320, 150 );
		this.setLocationRelativeTo(null);
	    this.createComponents();
		this.setVisible(true);
	}
	
	private void createComponents(){
		createOptions();
		createButons();
	}
	
	private void createOptions(){
		
		JRadioButton[] radioButtonArray = {simple, intermediate, advanced};
    	
    	for(int i=0; i<3; i++){
    		JRadioButton radioButton = radioButtonArray[i];
    		radioButton.addActionListener( createSelectOption() );
    		this.add( radioButton );
    		if( menu.option == i )
    			radioButton.setSelected(true);
    	}
	}
	
	private ActionListener createSelectOption(){
    	ActionListener exitGameAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	JRadioButton[] radioButtonArray = {simple, intermediate, advanced};
            	
            	for( JRadioButton radiobutton : radioButtonArray )
            	{
            		if( radiobutton != event.getSource() ){
            			radiobutton.setSelected(false);
            		}else{
            			radiobutton.setSelected(true);
            		}
            	}
            }
        };
        
        return exitGameAction;
    }

	private void createButons(){
		JButton cancelButton = new JButton("cancel");
		cancelButton.addActionListener( createCancelListener() );
		
		JButton saveButton = new JButton("save");
		saveButton.addActionListener( createSaveListener() );
		
		this.add( saveButton );
		this.add( cancelButton );
	}
	
	private void close(){
		this.dispose();
	}
	
	private ActionListener createCancelListener(){
    	ActionListener cancelOption = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	close();
            }
    	};
    	return cancelOption;
	}
	
	private ActionListener createSaveListener(){
    	ActionListener saveOption = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	if(simple.isSelected()){
            		menu.width = 8;
            		menu.height = 8;
            		menu.mines = 6;
            		menu.option = 0;
            	}
            	if(intermediate.isSelected()){
            		menu.width = 15;
            		menu.height = 12;
            		menu.mines = 12;
            		menu.option = 1;
            	}
            	if(advanced.isSelected()){
            		menu.width = 20;
            		menu.height = 16;
            		menu.mines = 25;
            		menu.option = 2;
            	}
            	close();
            }
        };
        
        return saveOption;
 }
	
}
