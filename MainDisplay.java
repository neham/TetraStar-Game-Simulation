
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

/**  TetraGameSimulation
 * 
 * This is the main display screen which displays the grids and characters.
 * Also provides option to choose test cases and also buttons to set screen 
 * and start the simulation.
 * 
 * @author Gurupur Sushma Pai and Neha Mahajan
 */
public class MainDisplay extends JFrame implements DisplayScreen
{
	private TetraFaceDisplayScreen screen;
	private Command testCaseOneCommand;
	private Command testCaseTwoCommand;
	private TestCaseInvoker testInvoker;
	private JFrame mainFrame;
	private JComboBox testCaseCombo;
	final int numberOfTestCases = 6;
	private JButton start, reset, setScreen;
	private JPanel sidePanel;
	private JLabel testCaseDesc;
	private JOptionPane messageLabel;
	private String testCaseName = null;

	/**
	 * Constructor
	 */
	public MainDisplay() {}

	/**
	 *  Starts the game by setting the screen with the grids and characters.
	 */
	public void startGame()
	{
		//Create and set up the window.

		sidePanel = createSidePanel();

		screen = new defaultDisplay();
		screen.createTheTetraFace();
		screen.setInitialPositions();

		mainFrame = new JFrame("TetraRoverGame Simulation");
		mainFrame.pack();

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().add(screen, BorderLayout.CENTER);
		mainFrame.getContentPane().add(sidePanel , BorderLayout.EAST);
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH); 
		mainFrame.setVisible(true);

		// Sets the screen to default display 
		reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				screen = new defaultDisplay();
				screen.createTheTetraFace();
				screen.setInitialPositions();
				mainFrame.getContentPane().add(screen, BorderLayout.CENTER);
				mainFrame.setVisible(true);
			}
		});

		// Gets the testcase name choosen.
		testCaseCombo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JComboBox testCase = (JComboBox)e.getSource();
				testCaseName = (String)testCase.getSelectedItem();	                   	            	    	    	    
			}
		});

		// Starts the simulation.
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(testCaseName.equals("TEST CASE 1"))
				{

					testCaseOneCommand = new TestCaseOneCommand(screen);
					testInvoker = new TestCaseInvoker(testCaseOneCommand);
					testInvoker.invoke();


				}
				else if(testCaseName.equals("TEST CASE 2"))
				{	    			   
					testCaseTwoCommand = new TestCaseTwoCommand(screen);
					testInvoker = new TestCaseInvoker(testCaseTwoCommand);
					testInvoker.invoke();

				}
				else if(testCaseName.equals("TEST CASE 3"))
				{	    				    		
					testCaseOneCommand = new TestCaseOneCommand(screen);
					testInvoker = new TestCaseInvoker(testCaseOneCommand);
					testInvoker.invoke();

				}
				else if(testCaseName.equals("TEST CASE 4"))
				{	    				    		
					testCaseOneCommand = new TestCaseOneCommand(screen);
					testInvoker = new TestCaseInvoker(testCaseOneCommand);
					testInvoker.invoke();

				}
				else if(testCaseName.equals("TEST CASE 5"))
				{	    				    		
					testCaseOneCommand = new TestCaseOneCommand(screen);
					testInvoker = new TestCaseInvoker(testCaseOneCommand);
					testInvoker.invoke();

				}
			}
		});

		// Sets the screen depending upon the case choosen.
		setScreen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(testCaseName.equals("TEST CASE 1"))
				{	    			
					screen = new ScenarioOne();		    			
					screen.createTheTetraFace();
					screen.setInitialPositions();
					mainFrame.getContentPane().add(screen, BorderLayout.CENTER);
					mainFrame.setVisible(true);
					String text = " Case 1 : Hero1 moves in all directions";					
					testCaseDesc.setText(text);
					testCaseDesc.setFont(new Font("Serif", Font.BOLD, 25));
					testCaseDesc.setForeground(Color.white);

				}	
				else if(testCaseName.equals("TEST CASE 2"))
				{
					screen = new ScenarioOne();		    			
					screen.createTheTetraFace();
					screen.setInitialPositions();
					mainFrame.getContentPane().add(screen, BorderLayout.CENTER);
					mainFrame.setVisible(true);
					String text = "<html> Case 2: Hero2 tries to fly with/without vehicle</html>";					
					testCaseDesc.setText(text);
					testCaseDesc.setFont(new Font("Serif", Font.BOLD, 25));
					testCaseDesc.setForeground(Color.white);

				}
				else if(testCaseName.equals("TEST CASE 3"))
				{	    					    		    	
					screen = new ScenarioFour();		    			
					screen.createTheTetraFace();
					screen.setInitialPositions();
					mainFrame.getContentPane().add(screen, BorderLayout.CENTER);
					mainFrame.setVisible(true);
					String text = "<html>Case 3: Vader moving towards mapbase, stealing map<br> and returning to vaderbase</html>";					
					testCaseDesc.setText(text);
					testCaseDesc.setFont(new Font("Serif", Font.BOLD, 20));
					testCaseDesc.setForeground(Color.white);

				}
				else if(testCaseName.equals("TEST CASE 4"))
				{	    					    		    	
					screen = new ScenarioThree();		    			
					screen.createTheTetraFace();
					screen.setInitialPositions();
					mainFrame.getContentPane().add(screen, BorderLayout.CENTER);
					mainFrame.setVisible(true);
					String text = "<html>Case 4: Hero1 flies to mapbase1 <br>and Hero2 flies to same mapbase</html>";					
					testCaseDesc.setText(text);
					testCaseDesc.setFont(new Font("Serif", Font.BOLD, 20));
					testCaseDesc.setForeground(Color.white);


				}
				else if(testCaseName.equals("TEST CASE 5"))
				{
					screen = new ScenarioFiveRovers();		    			
					screen.createTheTetraFace();
					screen.setInitialPositions();
					mainFrame.getContentPane().add(screen, BorderLayout.CENTER);
					mainFrame.setVisible(true);
					String text = " Case 5: Rovers moving in all directions";					
					testCaseDesc.setText(text);
					testCaseDesc.setFont(new Font("Serif", Font.BOLD, 20));
					testCaseDesc.setForeground(Color.white);


				}
			}
		});


	}

	/**
	 * Creates the side panel containing the buttons and combo box.
	 * @return Jpanel
	 */
	private JPanel createSidePanel()
	{
		sidePanel = new JPanel();
		sidePanel.setLayout(new FlowLayout());
		sidePanel.setBackground(Color.gray);
		sidePanel.setPreferredSize(new Dimension(550,100));


		JLabel message1 = new JLabel("Welcome to the TetraRover Game Simulation \n");			
		message1.setFont(new Font("Serif", Font.BOLD, 25));	  
		message1.setForeground(Color.white);
		sidePanel.add(message1);
		sidePanel.add(Box.createRigidArea(new Dimension(0, 70)));

		JLabel message2 = new JLabel("Choose the test case :\n");
		message2.setFont(new Font("Serif", Font.ITALIC, 20));
		message2.setForeground(Color.white);
		sidePanel.add(message2);		
		sidePanel.add(Box.createRigidArea(new Dimension(350, 0)));

		String[] testCases =  new String[numberOfTestCases];
		for(int i = 0 ; i < numberOfTestCases ; i++ )
		{
			if(i==0)
				continue;
			else
				testCases[i] = "TEST CASE " +  (i);
		}

		testCaseCombo = new JComboBox(testCases);
		testCaseCombo.setSize(30, 50);
		testCaseCombo.setBackground(Color.white);
		testCaseCombo.setForeground(Color.black);
		sidePanel.add(testCaseCombo);	
		sidePanel.add(Box.createRigidArea(new Dimension(310, 100)));

		start = new JButton("Start Simulation");
		start.setActionCommand("START SIMULATION");
		start.setToolTipText("Start the simulation of the above test case");

		reset = new JButton("Reset");
		reset.setActionCommand("RESET SCREEN");
		reset.setToolTipText("Reset the screen to the start or original screen");

		setScreen = new JButton("Set Display Screen");
		setScreen.setActionCommand("SET SCREEN");
		setScreen.setToolTipText("Set the screen according to the test case specified");
		
		testCaseDesc = new JLabel();
				
		sidePanel.add(setScreen);
		sidePanel.add(Box.createRigidArea(new Dimension(50, 50)));
		sidePanel.add(start);
		sidePanel.add(Box.createRigidArea(new Dimension(50, 50)));
		sidePanel.add(reset);
		sidePanel.add(Box.createRigidArea(new Dimension(500, 60)));
		sidePanel.add(testCaseDesc);
		

		return sidePanel;

	}	

}


