import java.awt.Image;
import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Fly vehicle object which is used by the thero and tvader to fly from 
 * one location to another.
 * @author Sushma and Neha
 *
 */
public class TFlier 
{
	private int flierId;
	private boolean flightSuccess = false;
	private Image img = null;
	private String message = null;
	private ArrayList<TFaceGrid> locationSet;

	/**
	 *  Default constructor
	 */
	public TFlier(){}


	/**
	 *  Constructor
	 * @param id
	 */
	public TFlier(int id)
	{
		flierId = id;
	}

	/**
	 * Set the vehicle id
	 * @param id
	 */
	public void setVehicleId(int id)
	{
		flierId = id;
	}

	/**
	 * Sets the character to the new fly location and returns the currrent and new position values.
	 * @param locations
	 * @param currentLocation
	 * @param newLocation
	 * @param type
	 * @return
	 * @throws IOException
	 */
	public ArrayList<TFaceGrid> flyToLocation(JButton[][] locations, TFaceGrid currentLocation, TFaceGrid newLocation, String type) throws IOException
	{
		int row = newLocation.getRow();
		int col = newLocation.getColumn();					


		// Set the image according to the type of character.
		if(type.equals("hero1"))
		{
			img = ImageIO.read(getClass().getResource("thero.png"));
			message = "HERO";
		}
		else if(type.equals("hero2"))
		{
			img = ImageIO.read(getClass().getResource("hero2.jpg"));
			message = "HERO";
		}
		else			
		{
			img = ImageIO.read(getClass().getResource("tVader.jpg"));
			message = "VADER";
		}

		// Set the new location with the image.		
		locations[newLocation.getRow()][newLocation.getColumn()].setIcon(new ImageIcon(img));	
		locations[newLocation.getRow()][newLocation.getColumn()].setDisabledIcon(new ImageIcon(img));

		// Pass the current and New location back.
		locationSet = new ArrayList<TFaceGrid>();
		locationSet.add(currentLocation);
		locationSet.add(newLocation);

		return locationSet;


	}
}
