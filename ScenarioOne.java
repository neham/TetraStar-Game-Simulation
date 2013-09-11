import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Test case 1 scenario.
 * Displays the hero movement in all directions.
 * @author Sushma and Neha
 *
 */
public class ScenarioOne extends TetraFaceDisplayScreen
{	
	/**
	 *  Constructor
	 */
	public ScenarioOne()
	{}

	/**
	 *  Contains the scenarios of the test case.
	 * @throws IOException 
	 */
	void startSimulation() throws IOException 
	{

		createMsg("HERO1 Moves North");			
		hero1.makeInitialMove("NORTH", panel, tHero1Loc);		
		holdClass();

		createMsg("HERO1 Moves North");
		hero1.makeMove("NORTH", panel);	
		holdClass();

		createMsg("HERO1 Moves North");
		hero1.makeMove("NORTH", panel);	
		holdClass();

		createMsg("HERO1 Moves East");
		hero1.makeMove("EAST", panel);	
		holdClass();

		createMsg("HERO1 Moves West");
		hero1.makeMove("WEST", panel);	
		holdClass();

		createMsg("HERO1 Moves South");
		hero1.makeMove("SOUTH", panel);	
		holdClass();

	}

	/**
	 *  Set the initial position of the characters on the grid.
	 */
	@Override
	void setInitialPositions() 
	{	

		// Create the different base location objects.
		vaderBaseLoc = TVaderBase.getInstance();
		hero1BaseLoc  = new THeroBase();
		hero2BaseLoc = new THeroBase();
		mapBaseLoc = new TMapBase();
		mapBase1Loc = new TMapBase();

		// create instances of heroes , vader and rovers.
		RoverFactory rover = new RoverFactory();		
		hero1 = rover.createRovers("Hero",'*');
		hero2 = rover.createRovers("Hero", '#');
		vader = rover.createRovers("Vader",'\0');
		rover1 = rover.createRovers("Rover",'\0');
		rover2 = rover.createRovers("Rover",'\0');

		Image img = null;

		tHero1Loc = new TFaceGrid(2, 2);
		tHero2Loc = new TFaceGrid(4, 2);
		tRover1Loc = new TFaceGrid(5, 0);
		tRover2Loc = new TFaceGrid(6, 4);
		tVaderLoc = new TFaceGrid(3, 3);
		tMapLoc = new TFaceGrid(3,3);
		hero1bLoc = new TFaceGrid(0, 0);
		hero2bLoc = new TFaceGrid(6, 6);
		tMapbLoc = new TFaceGrid(2,1);
		tMapb1Loc = new TFaceGrid(4,6);

		/* TODO: ID Generator */
		StarAtlas stAtlas1 = new StarAtlas(10, tMapLoc);
		StarMap stMap1 = new StarMap(11, tMapLoc, "Welcome to tetra Planet");
		stAtlas1.addStarMap(stMap1);
		vaderBaseLoc.setStarMapComponent(stAtlas1);

		TMediator tMediator = new TMediator();

		// register the people to the mediator.
		tMediator.registerTPeople(hero1);
		tMediator.registerTPeople(hero2);
		tMediator.registerTPeople(vader);
		tMediator.registerTPeople(rover1);
		tMediator.registerTPeople(rover2);


		try 
		{
			img = ImageIO.read(getClass().getResource("tFlier.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		panel[0][6].setIcon(new ImageIcon(img));
		panel[0][6].setDisabledIcon(new ImageIcon(img));

		//set the fly location
		flyLoc = new TFaceGrid(4, 6);

		this.setRoversToLocation();	

		// register the mapbase, herobase and vaderbase location to the mediator.
		tMediator.registerTHomeBase(hero1BaseLoc);	
		tMediator.registerTHomeBase(hero2BaseLoc);
		tMediator.registerTHomeBase(vaderBaseLoc);
		tMediator.registerTHomeBase(mapBaseLoc);
		hero1.requestFly();
	}

}