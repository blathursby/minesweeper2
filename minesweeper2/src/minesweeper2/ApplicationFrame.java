package minesweeper2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ApplicationFrame extends JFrame {
	
	public Integer columns, rows, mines, time, flags, option;
	public Integer clickedField;
	
	private List<FieldViewerButton> fieldViewerButtons;
	private boolean canPlay = true, gameStarted = false;
	private JLabel flagCounterLabel, timeLabel, stateLabel;
	
	public ApplicationFrame( Integer _columns, Integer _rows, Integer _mines, Integer _option ) {
		
		columns = _columns;
		rows = _rows;
		mines = _mines;
		option = _option;
		
		flags = mines;
		time = 0;
		clickedField = 0;
		
		this.setSize( 150 + columns *37, 100 + rows *37 );
		this.setLayout( new BorderLayout() );
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.createComponents();
		this.setVisible(true);
	}
	
	public boolean getGameStarted(){
		return gameStarted;
	}
	
	public void increaseFlags(){
		flags ++;
		flagCounterLabel.setText("mines left: " + flags);
	}
	
	public void decreaseFlags(){
		flags --;
		flagCounterLabel.setText("mines left: " + flags);
	}
	
	public boolean hasFlags(){
		return (flags > 0);
	}
	
	public void startGameSetUp(){
		gameStarted = true;
		
		if( stateLabel.getText().equals("ready to play!") ){
    		stateLabel.setText("playing...");
			
			class runTimeExec extends TimerTask {
	            public void run() {
	            	if( canPlay ){
	            		time++;
	            		timeLabel.setText( "time: " + time );
	            	}
	            }
	         }
	
	         Timer timer = new Timer();
	         timer.schedule(new runTimeExec(), 0, 1000);
    	}
    }
	
	public boolean getCanPlay(){
		return canPlay;
	}
	
	private void createComponents(){
		this.add( createStatusPanel(), BorderLayout.NORTH );
		this.add( crateBoardPanel(), BorderLayout.CENTER );
		setJMenuBar( new Menu(this, option, columns, rows, mines) );
	}
	
	 private JPanel createStatusPanel() {
		 JPanel statusPanel = new JPanel();
         statusPanel.setLayout( new FlowLayout() );
         statusPanel.setPreferredSize(new Dimension(this.getWidth() - 20, 30));
         statusPanel.add( createStateLabel() );
         statusPanel.add( createFlagCounterLabel() );
         statusPanel.add( createTimeLabel() );
         
		 return statusPanel;
	}
	
	private JLabel createFlagCounterLabel(){
		flagCounterLabel = new JLabel("mines left: " + flags);
		Border paddingBorder = BorderFactory.createEmptyBorder(0,0,0,15);
		flagCounterLabel.setBorder(paddingBorder); 
		return flagCounterLabel;
	}
	
	private JLabel createTimeLabel(){
		timeLabel = new JLabel("time: " + time);
		Border paddingBorder = BorderFactory.createEmptyBorder(0,0,0,15);
		timeLabel.setBorder(paddingBorder); 
		return timeLabel;
	}
	 
	private JLabel createStateLabel(){
		stateLabel = new JLabel("ready to play!");
		Border paddingBorder = BorderFactory.createEmptyBorder(0,0,0,15);
		stateLabel.setBorder(paddingBorder); 
		return stateLabel;
	}
	
	private JPanel crateBoardPanel() {
		JPanel board = new JPanel(new GridLayout( rows, columns ));
		createFields( board );
		
		return board; 
	}
	
	private void createFields(JPanel board ) {		
		boolean[][] mines = placeMines();
		
		fieldViewerButtons = new ArrayList<FieldViewerButton>();
		
		for( Integer x = 0; x < rows; x++ )
		{
			for( Integer y = 0; y < columns; y++ )
			{
				FieldViewerButton field = new FieldViewerButton( this, new Field( mines[x][y], x, y, this) );
				fieldViewerButtons.add( field );
				board.add( field );
			}
		}
	}
	
	private boolean[][] placeMines(){
		Integer countOfMines = mines;
		Random generator = new Random();
		
		boolean[][] mines = new boolean[rows][columns];
		
		while( countOfMines > 0 )
		{
			int x = 0;
			int y = 0;
			do{
				x = generator.nextInt( rows );
				y = generator.nextInt( columns );
			}while( mines[x][y] == true );
			
			mines[x][y] = true;
			countOfMines--;
		}
		
		return mines;
	}
	
	public List<FieldViewerButton> getFieldViewerButtons(){
		return fieldViewerButtons;
	}
	
	public void wonGame(){
		canPlay = false;
		stateLabel.setText("Game won!");
	}
	
	public void failedGame(){
		stateLabel.setText("Game over!");
		
		for( FieldViewerButton fieldViewerButton : fieldViewerButtons )
		{
			if( fieldViewerButton.getField().hasMine() && !fieldViewerButton.getField().isChecked() )
			{
				fieldViewerButton.setBackground( Color.GRAY );
				fieldViewerButton.setText("X");
			}
		}
		canPlay = false;
	}
}
