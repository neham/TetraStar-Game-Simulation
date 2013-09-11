import java.awt.Image;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * TetraStarSimulation
 * Abstract class
 * @author Sushma and Neha
 *
 */
abstract class TetraPeople 
{
	
	protected final int maxRow = 7;
	protected final int maxCol = 7;
	protected TetraPeopleObservable peopleObservable;	
	protected TFaceGrid currentLocation, newLocation, oldLocation;
	protected boolean vaderExit;
	
	/**
	 * Get the old locaiton.
	 * @return TFaceGrid
	 */
	public TFaceGrid getOldLocation() 
	{
		return oldLocation;
	}

	/**
	 * Set the old location
	 * @param oldLocation
	 */
	public void setOldLocation(TFaceGrid oldLocation) 
	{
		this.oldLocation = oldLocation;
	}
	
	/**
	 *  Make the first move on the grid
	 * @param direction
	 * @param locations
	 * @param loc
	 */
	public void makeInitialMove(String direction, JButton[][] locations, TFaceGrid loc) 
	{
	   currentLocation = loc;	  
	   makeMove(direction, locations);
	   
	}
	
	/**
	 *  Display message on a JOptionPane
	 * @param msg
	 */
	public void createMsg(String msg)
	{
		String message = "<html><font name='sansserif' size='4'>" + msg +"</font></html>";
		JOptionPane optionPane = new JOptionPane(message
                , JOptionPane.PLAIN_MESSAGE
                , JOptionPane.DEFAULT_OPTION
                , null, null, null);
		
		JDialog dialog = optionPane.createDialog(null, "SCENARIO");			
        dialog.setLocation(900, 430);
        dialog.setVisible(true);
	
	}
	
	/**
	 *  Add observer to tetrapeople
	 * @param ob
	 */
	public void addObserver (Observer ob) 
	{ 
		peopleObservable.addObserver(ob);
	}
	
	// abstract methods to be implemented by the sub classes - tetravader, tetrarover and tetrahero
	 abstract void makeMove(String direction, JButton[][] locations);
	 abstract void setLocation(JButton[][] locations,TFaceGrid loc,String type);	 
	 abstract int getId();
	 abstract void setId(int id);	 
	 abstract void fly(JButton[][] locations, TFaceGrid loc) throws IOException;
	 abstract void requestFly();
	 abstract String getStrategy();
	 
	 /**
	  *  Indicates the vader has/ has not  left the vaderbase.
	  * @param val
	  */
	 public void setVaderExit(boolean val)
	 {
		 vaderExit = val;
	 }
}

class PeopleNotify 
{
	public TetraPeople people;
	public TFaceGrid baseLoc;
	public String type;
	
	
}


class TetraPeopleObservable extends Observable
{
	public void setChanged()
	{
		super.setChanged();
	}
}