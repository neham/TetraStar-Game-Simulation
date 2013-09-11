import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;


/**
 * TVader is a singleton class.
 * @author Sushma and Neha
 *
 */
public class TetraVader extends TetraPeople
{
	private int rowNumber;
	private int colNumber;
	private String vaderText = "VADER";
	private int vaderId;
	private int vaderLocation;
	private Image img, img2 = null;
	private TFlier flyVehicle;
	private StarMapComponent starMap;
	private boolean enteredMapbase = false;
	private Stack<TFaceGrid> backTrackPath;
	private boolean backTrackPathStatus = false;
	
	private static TetraVader vader = null;
		

	/**
	 *  Constructor
	 */
	private TetraVader()
	{
		peopleObservable = new TetraPeopleObservable();
		backTrackPath = new Stack<TFaceGrid>();
		addToPath(new TFaceGrid(3,3));
		flyVehicle = new TFlier();
	}

	/**
	 * Get star map
	 * @return
	 */
	public StarMapComponent getStarMap() {
		return starMap;
	}

	/**
	 *  set the map
	 * @param starMap
	 */
	public void setStarMap(StarMapComponent starMap) {
		this.starMap = starMap;
	}

	/**
	 *  set the back track status 
	 * @param status
	 */
	public void setBackTrackPathStatus(boolean status) {
		backTrackPathStatus = status;
	}
	
	/**
	 *  Add the location to paths 
	 * @param newLoc
	 */
	public void addToPath(TFaceGrid newLoc) {
		if(newLoc != null) {
			backTrackPath.push(newLoc);
		}
	}
	/**
	 * Extract from path a location
	 * @return  TFaceGrid
	 */
	public TFaceGrid extractFromPath() {
		if(!backTrackPath.isEmpty()) {
			return backTrackPath.pop();
		}
		return null;
	}
	
	/**
	 *  Initialize the path
	 */
	public void reInitPath() {
		while(!backTrackPath.isEmpty()) {
			backTrackPath.pop();
		}
		addToPath(new TFaceGrid(3,3));
	}
	
	@Override
	/**
	 *  Calls the appropriate move depending upon the direction given.
	 */
	void makeMove(String direction, JButton[][] locations) 
	{
		// If the movement is in north direction.		 
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
	 * Moves the TVader in the North direction by one step.
	 * @param locations
	 */
	private void makeNorthMove(JButton[][] locations)
	{
		int newRow = currentLocation.getRow() - 1;
		if(newRow < 0)
		{
			String s="Cannot move North. Out of the Grid";
			JOptionPane.showMessageDialog(null,s, "Test Case Simulation"
					, JOptionPane.PLAIN_MESSAGE);
		}
		else
		{	  
			newLocation = new TFaceGrid(newRow, currentLocation.getColumn());

			checkAndMove(locations, newLocation);	
		}
	}

	/**
	 * Moves Thero in south direction by one step.
	 * @param locations
	 */
	private void makeSouthMove(JButton[][] locations)
	{
		int newRow = currentLocation.getRow() + 1;
		if(newRow > maxRow)
		{
			String s="Cannot move South. Out of the Grid";
			JOptionPane.showMessageDialog(null,s, "Test Case Simulation"
					, JOptionPane.PLAIN_MESSAGE);
		}
		else
		{	  
			newLocation = new TFaceGrid(newRow, currentLocation.getColumn());

			checkAndMove(locations, newLocation);	
		}
	}

	/**
	 * MOves THero in East direction by one step.
	 * @param locations
	 */
	private void makeEastMove(JButton[][] locations)
	{
		int newCol = currentLocation.getColumn() + 1;
		if(newCol > maxCol)
		{
			String s="Cannot move East. Out of the Grid";
			JOptionPane.showMessageDialog(null,s, "Test Case Simulation"
					, JOptionPane.PLAIN_MESSAGE);
		}
		else
		{	  
			newLocation = new TFaceGrid(currentLocation.getRow(), newCol);

			checkAndMove(locations, newLocation);	
		}
	}
	/**
	 * MOves THero in WEst direction by one step.
	 * @param locations
	 */
	private void makeWestMove(JButton[][] locations)
	{
		int newCol = currentLocation.getColumn() - 1;
		if(newCol < 0)
		{
			String s="Cannot move West. Out of the Grid";
			JOptionPane.showMessageDialog(null,s, "Test Case Simulation"
					, JOptionPane.PLAIN_MESSAGE);
		}
		else
		{	  
			newLocation = new TFaceGrid(currentLocation.getRow(), newCol);

			checkAndMove(locations, newLocation);	
		}
	}

	/**
	 *  Set Icons in given location.
	 * @param locations
	 */
	private void setIcons(JButton[][] locations)
	{
		locations[currentLocation.getRow()][currentLocation.getColumn()].setIcon(null);
		locations[currentLocation.getRow()][currentLocation.getColumn()].setText("");

		locations[newLocation.getRow()][newLocation.getColumn()].setIcon(new ImageIcon(img));
		locations[newLocation.getRow()][newLocation.getColumn()].setDisabledIcon(new ImageIcon(img));

		currentLocation = newLocation;
	}


	/**
	 * Set the vader icon on the grid, in the location given.
	 */
	@Override
	void setLocation(JButton[][] locations, TFaceGrid loc, String type) 
	{
		// TODO Auto-generated method stub
		rowNumber = loc.getRow();
		colNumber = loc.getColumn();

		try {
			img = ImageIO.read(getClass().getResource("tVader.jpg"));
			img2 = ImageIO.read(getClass().getResource("river.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		locations[rowNumber][colNumber].setIcon(new ImageIcon(img));
		locations[rowNumber][colNumber].setDisabledIcon(new ImageIcon(img));

		// set the image of river around the vader.
		locations[rowNumber - 1][colNumber].setIcon(new ImageIcon(img2));
		locations[rowNumber - 1][colNumber].setDisabledIcon(new ImageIcon(img2));
		locations[rowNumber - 1][colNumber].setText("RIVER");
		
		locations[rowNumber + 1][colNumber].setIcon(new ImageIcon(img2));
		locations[rowNumber + 1][colNumber].setDisabledIcon(new ImageIcon(img2));
		locations[rowNumber + 1][colNumber].setText("RIVER");
		
		locations[rowNumber][colNumber - 1].setIcon(new ImageIcon(img2));
		locations[rowNumber][colNumber - 1].setDisabledIcon(new ImageIcon(img2));
		locations[rowNumber][colNumber - 1].setText("RIVER");
		
		locations[rowNumber][colNumber + 1].setIcon(new ImageIcon(img2));
		locations[rowNumber][colNumber + 1].setDisabledIcon(new ImageIcon(img2));
		locations[rowNumber][colNumber + 1].setText("RIVER");

		// set the current location of the vader which will be used in the simulation
		currentLocation = loc;
	}

	/**
	 *  Check if location is empty
	 * @param locations
	 * @param loc
	 * @return
	 */
	public String checkLocation(JButton[][] locations, TFaceGrid loc) 
	{
		// TODO Auto-generated method stub
		int row = loc.getRow();
		int col = loc.getColumn();
		String characterObject;

		if(locations[row][col].getText().equals(""))
			characterObject = "empty";
		else 
			characterObject = locations[row][col].getText();

		return characterObject;
	}

	@Override
	/**
	 * get the vader id.
	 */
	int getId() 
	{
		// TODO Auto-generated method stub
		return vaderId;
	}

	@Override
	/**
	 *  Set the vader id.
	 */
	void setId(int id) 
	{
		// TODO Auto-generated method stub
		vaderId = id;
	}

	@Override
	/**
	 *  Fly to a given location
	 */
	void fly(JButton[][] locations, TFaceGrid loc) throws IOException 
	{		
		
		Image flyImg = null;

		String characterObj = checkLocation(locations, loc);

		if(characterObj.equals("empty")||characterObj.equals("HEROBASE") || characterObj.equals("MAPBASE") || characterObj.equals("VADERBASE"))
		{		
			ArrayList newSetLocation = (ArrayList) flyVehicle.flyToLocation(locations, currentLocation, loc, "VADER");		

			// Set the old and the new locations of hero
			oldLocation = currentLocation;							
			currentLocation = loc;

			String oldLocationText = locations[oldLocation.getRow()][oldLocation.getColumn()].getText();
			System.out.println("--->" +oldLocationText);

			// Check if the vader had entered the Mapbase anytime.						
				if(oldLocationText.equals("MAPBASE"))

				{
					flyImg = ImageIO.read(getClass().getResource("MapBase.jpg"));
					locations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(flyImg));
					locations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(flyImg));
				}
				else if(oldLocationText.equals("VADERBASE"))
				{
					flyImg = ImageIO.read(getClass().getResource("vaderHouse.jpg"));
					locations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(flyImg));
					locations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(flyImg));
				}
				else if (oldLocationText.equals("empty") ||(oldLocationText.equals("")))
				{					
					locations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(null);
				}
				else if (oldLocationText.equals("HEROBASE"))
				{
					flyImg = ImageIO.read(getClass().getResource("TetRoverHome.jpg"));
					locations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(flyImg));
					locations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(flyImg));
				}
		}

		if(! characterObj.equals("empty"))
		{
			PeopleNotify notification = new PeopleNotify();
			notification.people = this;
			notification.baseLoc = loc;

			if(characterObj.equals("HEROBASE"))
				notification.type = "HEROBASE";
			else if (characterObj.equals("VADERBASE"))
				notification.type = "VADERBASE";
			else
			{
				notification.type = "MAPBASE";
				enteredMapbase = true;
			}
			peopleObservable.setChanged();
			peopleObservable.notifyObservers(notification);
		}
		else {
			if(!backTrackPathStatus) {
				addToPath(currentLocation);
			}
		}
	}

/**
 *  Fly with the map
 * @param map
 * @param locations
 * @param currentLoc
 * @param newLoc
 */
	public void flyWithMap(StarMapComponent map, JButton[][] locations, TFaceGrid currentLoc, TFaceGrid newLoc)
	{
		this.starMap = map;		

		try {
			this.fly(locations, newLoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *  Request fly vehicle.  - Does nothing
	 */
	void requestFly()
	{}


	@Override
	String getStrategy() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 *  Check and make a move to a given location.
	 * @param locations
	 * @param newLocation
	 */
	private void checkAndMove(JButton[][] locations, TFaceGrid newLocation)
	{
		String characterObject = checkLocation(locations, newLocation);
		if(characterObject.equals("empty"))
		{
			try {
				img = ImageIO.read(getClass().getResource("tVader.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!backTrackPathStatus) {
				addToPath(newLocation);
			}
			setIcons(locations);				  

		}
		else
		{
			String s="TFACE GRID OCCUPIED. FLY TO SOME OTHER LOCATION";
			JOptionPane.showMessageDialog(null,s, "Test Case Simulation"
					, JOptionPane.PLAIN_MESSAGE);
		}
	}

	/**
	 *  Get an instance of TetraVader.
	 * @return TetraPeople
	 */
	public static TetraPeople getInstance() {
		
		// TODO Auto-generated method stub
		if (vader == null) {
			vader = new TetraVader();
		}
		return vader;
	}

}
