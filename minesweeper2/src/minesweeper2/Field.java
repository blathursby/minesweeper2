package minesweeper2;

import java.util.ArrayList;
import java.util.List;

public class Field {
	private boolean mine;
	private boolean flag = false;
	private boolean checked = false;
	private Integer positionX, positionY;
	private ApplicationFrame application;
	
	public Field( boolean _mine, Integer _positionX, Integer _positionY, ApplicationFrame _application ){
		this.mine = _mine;
		this.positionX = _positionX;
		this.positionY = _positionY;
		this.application = _application;
	}
	
	public Integer[] getPostion(){
		Integer position[] = {positionX, positionY};
		return position;
	}
	
	public boolean hasMine(){
		return mine;
	}
	
	public boolean isFlaged(){
		return flag;
	}
	
	public void setFlag(boolean _flag){
		flag = _flag;
	}
	
	public boolean isChecked(){
		return checked;
	}
	
	public void setChecked(){
		checked = true;
	}
	
	public ApplicationFrame getApplication(){
		return application;
	}
	
	public List<FieldViewerButton> getNeighbors(){
		List<FieldViewerButton> neighbors = new ArrayList<FieldViewerButton>();
		
		for( FieldViewerButton fieldViewerButton : application.getFieldViewerButtons() )
		{
			if( ( fieldViewerButton.getField().positionX == positionX + 1  )  || 
					(fieldViewerButton.getField().positionX == positionX - 1) || 
					(fieldViewerButton.getField().positionX == positionX ) )
			{
				if( ( fieldViewerButton.getField().positionY == positionY + 1 )   ||
						(fieldViewerButton.getField().positionY == positionY - 1) ||
						(fieldViewerButton.getField().positionY == positionY ) )
				{
					neighbors.add(fieldViewerButton);
				}
			}
		}
		
		return neighbors;
	}
}
