package minesweeper2;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class FieldViewerButton extends JButton implements FieldViewer {

	private ApplicationFrame application;
	private Field field;
	
	private String bomb = "X";
	private String flag = "F";
	
	public FieldViewerButton (ApplicationFrame _application, Field _field){
		this.application = _application;
		this.field = _field;
		
		this.createComponents();
		this.setListener(_field);
	}
	
	public Field getField(){
		return field;
	}
	
	private void createComponents (){
		setBackgroundColor(Color.WHITE);
	}
	
	private void setBackgroundColor ( Color color ){
		this.setBackground(color);
	}
	
	private void setString( String text ){
		this.setText(text);
	}
	
	private void leftMouseClick( final Field field ){
		if( !field.isChecked() && application.getCanPlay() )
		{
			field.setChecked();
			
			if( !application.getGameStarted() )
				application.startGameSetUp();
				
			String state = bomb;
			if( field.hasMine() )
			{
				setBackgroundColor(Color.RED);
				setForeground(Color.WHITE);
	    		application.failedGame();
			}
			else	
			{
				if( field.isFlaged() )
					application.increaseFlags();
				
				application.clickedField ++;
				
				if( application.clickedField == (application.columns * application.rows - application.mines) )
				{
					application.wonGame();
				}
				
				setBackgroundColor(Color.LIGHT_GRAY);
				int mines_around = 0;
				
				for( FieldViewerButton neighbor : field.getNeighbors() )
				{
					if( neighbor.getField().hasMine() )
						mines_around ++;
				}
				
				if( mines_around == 0 )
				{
					state = "";
					for( FieldViewerButton neighbor : field.getNeighbors() )
					{
						if( !neighbor.getField().hasMine() && !neighbor.getField().isChecked() )
							neighbor.leftMouseClick( neighbor.getField() );
					}
				}
				else
					state = Integer.toString( mines_around );
			}
			
			setString( state );
		}
	}
	
	private void rightMouseClick( final Field field ){
		if( !field.isChecked() && application.getCanPlay() && application.hasFlags() )
		{	
			field.setFlag( !field.isFlaged() );
			
			if( field.isFlaged() )
			{
				application.decreaseFlags();
			}else
				application.increaseFlags();
			
			this.setText( (field.isFlaged() ? flag : "" ) );
			
			if( !application.getGameStarted() )
				application.startGameSetUp();
		}
	}
	
	public void setListener(final Field field){
		this.addMouseListener(( new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
		
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton( e ))
				{
					leftMouseClick(field);
				}
				else if(SwingUtilities.isRightMouseButton( e ))
				{
					rightMouseClick(field);
				}
			}
        }));
	}

}
