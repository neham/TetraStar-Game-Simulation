import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *  Contaims logic for rover movement
 * @author Sushma and Neha
 *
 */
public class TetraRover extends TetraPeople 
{
	private int rowNumber;
	private int colNumber;
	private String roverText = "ROVER";
	private int roverId;
	private int roverLocation;
	private Image img = null;

	/**
	 *  Constructor
	 */
	public TetraRover(){
		peopleObservable = new TetraPeopleObservable();
	}
    
	@Override
	/**
	 *  Move in the given direction.
	 */
	void makeMove(String direction, JButton[][] locations) 
	{
		// TODO Auto-generated method stub		 
		if(direction.equals("NORTH"))
		{
			makeNorthMove(locations);
		}

		if(direction.equals("SOUTH"))
		{
			makeSouthMove(locations);
		}

		if(direction.equals("EAST"))
		{
			makeEastMove(locations);
		}

		if(direction.equals("WEST"))
		{
			makeWestMove(locations);
		}

	}
	/**
	 *  MAKE North move
	 * @param locations
	 */
	private void makeNorthMove(JButton[][] locations)
	{
		int newRow = currentLocation.getRow() - 1;
		if(newRow < 0)
		{
			String s="Cannot move North. Out of the Grid";
			createMsg(s);    				
		}
		else
		{	  
			newLocation = new TFaceGrid(newRow, currentLocation.getColumn());
			checkLocation(locations, newLocation);    		    		
		}
	}

	/**
	 * Moves Trover in south direction by one step.
	 * @param locations
	 */
	private void makeSouthMove(JButton[][] locations)
	{
		int newRow = currentLocation.getRow() + 1;
		if(newRow > maxRow)
		{
			String s="Cannot move South. Out of the Grid";
			createMsg(s);			
		}
		else
		{	  
			newLocation = new TFaceGrid(newRow, currentLocation.getColumn());			  
			checkLocation(locations, newLocation);			  			 
		}
	}

	/**
	 * MOves Trover in East direction by one step.
	 * @param locations
	 */
	private void makeEastMove(JButton[][] locations)
	{
		int newCol = currentLocation.getColumn() + 1;
		if(newCol > maxCol)
		{
			String s="Cannot move East. Out of the Grid";
			createMsg(s);
		}
		else
		{	  
			newLocation = new TFaceGrid(currentLocation.getRow(), newCol);			  
			checkLocation(locations, newLocation);			  			 
		}
	}

	/**
	 * MOves Trover in WEst direction by one step.
	 * @param locations
	 */
	private void makeWestMove(JButton[][] locations)
	{
		int newCol = currentLocation.getColumn() - 1;
		if(newCol < 0)
		{
			String s="Cannot move West. Out of the Grid";
			createMsg(s);
		}
		else
		{	  
			newLocation = new TFaceGrid(currentLocation.getRow(), newCol);			  
			checkLocation(locations, newLocation);			  
		}
	}

	/**
	 * Set the icons in the given locations
	 * @param locations
	 */
	private void setIcons(JButton[][] locations)
	{
		locations[currentLocation.getRow()][currentLocation.getColumn()].setIcon(null);
		locations[currentLocation.getRow()][currentLocation.getColumn()].setText("");

		locations[newLocation.getRow()][newLocation.getColumn()].setIcon(new ImageIcon(img));
		locations[newLocation.getRow()][newLocation.getColumn()].setDisabledIcon(new ImageIcon(img));
		locations[newLocation.getRow()][newLocation.getColumn()].setText(roverText);

		currentLocation = newLocation;
	}


	@Override
	/**
	 *  Set the icon at the initial location.
	 */
	void setLocation(JButton[][] locations, TFaceGrid loc, String type) 
	{
		// TODO Auto-generated method stub
		rowNumber = loc.getRow();
		colNumber = loc.getColumn();

		locations[rowNumber][colNumber].setText(roverText);

		try 
		{
			img = ImageIO.read(getClass().getResource("tRover.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		locations[rowNumber][colNumber].setIcon(new ImageIcon(img));
		locations[rowNumber][colNumber].setDisabledIcon(new ImageIcon(img));
	}

	@Override
	int getId() 
	{
		// TODO Auto-generated method stub
		return roverId;
	}

	@Override
	void setId(int id) 
	{
		// TODO Auto-generated method stub
		roverId = id; 
	}

	/**
	 *  Check location if empty or not
	 * @param locations
	 * @param loc
	 */
	void checkLocation(JButton[][] locations, TFaceGrid loc) 
	{

		// TODO Auto-generated method stub
		int row = loc.getRow();
		int col = loc.getColumn();

		boolean empty = false;			

		if(locations[row][col].getText().equals(""))
		{
			try
			{
				img = ImageIO.read(getClass().getResource("tRover.jpg"));
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setIcons(locations);
		}

		if(locations[row][col].getText().equals("HEROBASE"))
		{
			String s="Cannot move to the herobase";
			createMsg(s);
		}
		if(locations[row][col].getText().equals("VADERBASE"))
		{
			String s="Cannot move to vaderbase";
			createMsg(s);			           
		}
		if(locations[row][col].getText().equals("HERO"))
		{
			String s="Cannot enter location. Hero Present";
			createMsg(s);			           
		}

	}

	@Override
	/**
	 *  Fly method - rovers cannot fly. Hence only a message is displayed
	 */
	void fly(JButton[][] locations, TFaceGrid loc) throws IOException 
	{
		// TODO Auto-generated method stub
		String s="Rovers cannot fly.";
		createMsg(s);						
	}

	/**
	 *  Request fly vehicle.- Rovers cannot request. Hence a message is displayed/
	 */
	void requestFly()
	{
		String s="Rovers cannot request for a fly vehicle.";
		createMsg(s);				     		
	}

	@Override
	String getStrategy() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
