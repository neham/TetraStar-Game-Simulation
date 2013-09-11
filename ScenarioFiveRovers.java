import java.awt.Image;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * TetraGameSimulation
 * Test case 5 scenario. Shows the movement of Rovers
 * @author Sushma and Neha
 *
 */
public class ScenarioFiveRovers extends TetraFaceDisplayScreen
{		
	/**
	 *  Constructor
	 */
	public ScenarioFiveRovers()
	{}

	/**
	 *  Contains the scenarios of the test case.
	 * @throws IOException 
	 */
	void startSimulation() throws IOException 
	{

		createMsg("Rover1 moves North");			
		rover1.makeInitialMove("NORTH", panel, tRover1Loc);
		holdClass();
		
		createMsg("Rover1 moves EAST");
		rover1.makeMove("EAST", panel);
		holdClass();
		
		createMsg("Rover1 moves EAST");
		rover1.makeMove("EAST", panel);
		holdClass();
		
		createMsg("Rover1 moves WEST");
		rover1.makeMove("WEST", panel);
		holdClass();
		
		createMsg("Rover1 moves SOUTH");
		rover1.makeMove("SOUTH", panel);
		holdClass();
		
		createMsg("Rover2 moves EAST");			
		rover2.makeInitialMove("EAST", panel, tRover2Loc);
		holdClass();
		
		createMsg("Rover2 moves EAST");
		rover2.makeMove("EAST", panel);
		holdClass();
		
		createMsg("Rover2 wants to fly to different location.");
		rover2.requestFly();
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
		tMapLoc = new TFaceGrid(2,6);
		hero1bLoc = new TFaceGrid(0, 0);
		hero2bLoc = new TFaceGrid(6, 6);
		tMapbLoc = new TFaceGrid(2,6);
		tMapb1Loc = new TFaceGrid(4,6);
		
		this.setRoversToLocation();	
		
		/* TODO: ID Generator */
		StarAtlas stAtlas1 = new StarAtlas(10, tMapLoc);
		StarMap stMap1 = new StarMap(11, tMapLoc, "Welcome to tetra Planet");
		/* Get the unique strategy of hero for encryption */
		String strategy = hero1.getStrategy();
		
		/* Using reflection to instantiate Encryption strategy */
		EncryptionAlgorithm encryptionAlgo;
		try {
			encryptionAlgo = (EncryptionAlgorithm)Class.forName(strategy).newInstance();	
			stAtlas1.addStarMap(stMap1);
			stAtlas1.setEncryptionAlgorithm(encryptionAlgo);
			//stAtlas1.encrypt(hero1.getId(), new Date(), '*');
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		mapBaseLoc.setStarMapComponent(stAtlas1);
				
		TMediator tMediator = new TMediator();
		
		// Register the tetra people to mediator
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
		
		flyLoc = new TFaceGrid(4, 6);
					
		// Register the locations to the mediator.
		tMediator.registerTHomeBase(hero1BaseLoc);	
		tMediator.registerTHomeBase(hero2BaseLoc);
		tMediator.registerTHomeBase(vaderBaseLoc);
		tMediator.registerTHomeBase(mapBaseLoc);
		
	}
	
}