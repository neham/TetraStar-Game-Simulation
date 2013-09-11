import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

/**
 * Tetra Star Simulation
 * Contains logic for hero movement and flight.
 * @author Sushma and Neha
 *
 */
public class TetraHero extends TetraPeople
{
	private int rowNumber;
	private int colNumber;
	private String heroText = "HERO";
	private int heroId;
	private int heroLocation;
	private Image img, currentImage, newImage = null;
	private TFlier flyVehicle;	
	private char symbol;
	private StarMapComponent oldstarMap = null; // Original map - encrypted
	private StarMapComponent NewstarMap = null; // cloned
	private TFaceGrid mapToVader;
	private String strategy;
	private boolean enteredMapbase = false;
	private boolean enteredHomebase = false;
	private String heroType;

	/**
	 * Constructor 
	 * @param Symbol
	 */
	public TetraHero(char Symbol)
	{
		peopleObservable = new TetraPeopleObservable();
		this.symbol = Symbol;
		strategy = "simpleEncryption";
		oldLocation = new TFaceGrid();
	}
	/**
	 *  Get the strategy
	 */
	public String getStrategy() {
		return strategy;
	}
	/**
	 * Set the map to vader loc
	 * @param loc
	 */
	public void setMapToVader(TFaceGrid loc)
	{
		mapToVader = loc;
	}

	/**
	 *  Get the old star map
	 * @return StarMapComponent
	 */
	public StarMapComponent getOldstarMap() {
		return oldstarMap;
	}

	/**
	 *  Set the old star map
	 * @param oldstarMap
	 */
	public void setOldstarMap(StarMapComponent oldstarMap) {
		this.oldstarMap = oldstarMap;
	}

	/**
	 * Get the new star map after encryption
	 * @return StarMapComponent
	 */
	public StarMapComponent getNewstarMap() {
		return NewstarMap;
	}

	/**
	 * Set the new star map
	 * @param newstarMap
	 */
	public void setNewstarMap(StarMapComponent newstarMap) {
		NewstarMap = newstarMap;
	}

	/**
	 * Get the map to vader location.
	 * @return
	 */
	public TFaceGrid getMapToVader()
	{
		return mapToVader;
	}
	public int getId()
	{
		return heroId;
	}
	/**
	 * Get the symbol
	 * @return
	 */
	public char getSymbol()
	{
		return symbol;
	}

	@Override
	public void setId(int id) 
	{
		heroId = id;		
	}


	/**
	 * @param  : String , JButton[]
	 * @return : void
	 * 
	 * Makes a move on the TFACE depending upon  the direction given
	 */
	public void makeMove(String direction, JButton[][] locations)
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
	 * Moves the THero in the North direction by one step.
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
			createMsg(s);

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
			createMsg(s);   	             
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
			createMsg(s);	                
		}
		else
		{	  
			newLocation = new TFaceGrid(currentLocation.getRow(), newCol);			  
			checkAndMove(locations, newLocation);		 
		}
	}

	/**
	 * Set the icons in the given locations
	 * @param locations
	 * @param currentImg
	 * @param newImg
	 */
	public void setIcons(JButton[][] locations,Image currentImg, Image newImg)    
	{
		// TODO = change back the code if problem of button text appears.
		if(currentImg == null)
		{
			locations[currentLocation.getRow()][currentLocation.getColumn()].setIcon(null);
			locations[currentLocation.getRow()][currentLocation.getColumn()].setText("");
			//locations[currentLocation.getRow()][currentLocation.getColumn()].setBorder(new LineBorder(Color.white,1));
		}
		else
		{
			locations[currentLocation.getRow()][currentLocation.getColumn()].setIcon(new ImageIcon(currentImg));
			locations[currentLocation.getRow()][currentLocation.getColumn()].setDisabledIcon(new ImageIcon(currentImg));

		}		  		 

		locations[newLocation.getRow()][newLocation.getColumn()].setIcon(new ImageIcon(newImg));
		locations[newLocation.getRow()][newLocation.getColumn()].setDisabledIcon(new ImageIcon(newImg));
		//locations[newLocation.getRow()][newLocation.getColumn()].setBorder(new LineBorder(Color.RED, 2));

		currentLocation = newLocation;
	}

	/**
	 *  Set the initial locations of hero
	 */
	public void setLocation(JButton[][] locations, TFaceGrid loc,String type)
	{
		rowNumber = loc.getRow();
		colNumber = loc.getColumn();	
		heroType = type;

		try 
		{
			if(type.equals("hero1"))
			{	
				img = ImageIO.read(getClass().getResource("thero.png"));
			}
			else
				img = ImageIO.read(getClass().getResource("hero2.jpg"));
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		locations[rowNumber][colNumber].setText("HERO");
		locations[rowNumber][colNumber].setIcon(new ImageIcon(img));
		locations[rowNumber][colNumber].setDisabledIcon(new ImageIcon(img));

		currentLocation = loc;
	}

	/**
	 * Check if something present at the given location
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

	/**
	 *  Fly to a given location
	 */
	void fly(JButton[][] locations, TFaceGrid loc) throws IOException
	{
		Image flyImg = null;
		ArrayList newSetLocation;

		System.out.println("hero wants to fly to" + loc.getRow() + " " + loc.getColumn());

		if(flyVehicle == null)
		{
			String s=" Hero cannot fly without the flier. Request flier";
			createMsg(s);
		}
		else
		{
			String characterObj = checkLocation(locations, loc);

			if(characterObj.equals("empty")||characterObj.equals("HEROBASE") || characterObj.equals("MAPBASE") || characterObj.equals("VADERBASE"))
			{	

				if(heroType.equals("hero1"))
					newSetLocation = (ArrayList) flyVehicle.flyToLocation(locations, currentLocation, loc, "hero1");
				else
					newSetLocation = (ArrayList) flyVehicle.flyToLocation(locations, currentLocation, loc, "hero2");

				TFaceGrid newCurrentLocation = (TFaceGrid) newSetLocation.get(1);

				// Set the old and the new locations of hero
				oldLocation = currentLocation;							
				currentLocation = loc;

				String oldLocationText = locations[oldLocation.getRow()][oldLocation.getColumn()].getText();

				// Check if the hero had entered the Mapbase anytime.
				if(enteredMapbase)
				{	if(oldLocationText.equals("MAPBASE"))
				{
					flyImg = ImageIO.read(getClass().getResource("MapBase.jpg"));
					locations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(flyImg));
					locations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(flyImg));
				}
				else if(oldLocationText.equals("VADERBASE"))
				{

					if(vaderExit)
						flyImg = ImageIO.read(getClass().getResource("vaderHouse.jpg"));
					else
						flyImg = ImageIO.read(getClass().getResource("tVader.jpg"));
					locations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(flyImg));
					locations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(flyImg));
				}
				else if (oldLocationText.equals("empty"))
				{

					locations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(null);
				}
				else if (oldLocationText.equals("HEROBASE"))
				{
					flyImg = ImageIO.read(getClass().getResource("heroBase.jpg"));
					locations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(flyImg));
					locations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(flyImg));
				}

				}
				else
				{
					locations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(null);
					locations[oldLocation.getRow()][oldLocation.getColumn()].setText("");
				}

				if(! characterObj.equals("empty"))
				{
					PeopleNotify notification = new PeopleNotify();
					notification.people = this;
					notification.baseLoc = loc;

					if(characterObj.equals("HEROBASE"))
					{	notification.type = "HEROBASE";

					}	
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
			}
		}
	}	
	/**
	 *  Request to fly
	 */
	void requestFly()
	{

		flyVehicle = new TFlier();
	}

	/**
	 *  Check location and then make a move
	 * @param locations
	 * @param newLocation
	 */
	private void checkAndMove(JButton[][] locations, TFaceGrid newLocation)
	{
		String  characterObject = checkLocation(locations, newLocation);
		if(characterObject.equals("empty"))
		{
			try
			{
				System.out.println("Hero wants to move" + newLocation.getRow() + "  " + newLocation.getColumn());
				if(heroType.equals("hero1"))
					newImage = ImageIO.read(getClass().getResource("thero.png"));
				else
					newImage = ImageIO.read(getClass().getResource("hero2.jpg"));
				if(enteredHomebase == true)
				{
					currentImage = ImageIO.read(getClass().getResource("heroBase.jpg"));
					enteredHomebase = false;
				}
				else
					currentImage = null;

			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			setIcons(locations,currentImage, newImage);
		}
		else if (characterObject.equals("HEROBASE") || characterObject.equals("MAPBASE"))
		{
			System.out.println("TetraHero:CheckandMove");
			PeopleNotify notification = new PeopleNotify();
			notification.people = this;
			notification.baseLoc = newLocation;
			if(characterObject.equals("HEROBASE"))
			{
				notification.type = "HEROBASE";
				enteredHomebase = true;
			}

			else
				notification.type = "MAPBASE";

			// Notify observers that the hero has entered the base.
			peopleObservable.setChanged();
			peopleObservable.notifyObservers(notification);
		}
		else if (characterObject.equals("VADERBASE"))
		{
			String s="HERO needs a fly vehicle to enter the vaderbase.";
			createMsg(s);			
		}    		
		else
		{
			String s="TFACE occupied. Fly to some other location";
			createMsg(s);
		}

	}
	/**
	 *  Fly with the map to a given location.
	 * @param originalMap
	 * @param newMap
	 * @param locations
	 * @param currentLoc
	 * @param newLoc
	 */
	public void flyWithMap(StarMapComponent originalMap, StarMapComponent newMap ,JButton[][] locations, TFaceGrid currentLoc, TFaceGrid newLoc)
	{
		this.oldstarMap = originalMap;
		this.NewstarMap = newMap;


		try {
			this.fly(locations,newLoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
