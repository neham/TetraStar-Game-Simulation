/**
 * TetraGameSimulation
 *  creates instance of TetraRovers accordingly
 * @authors: Gurupur Sushma Pai and Neha Mahajan
 *
 */
public class RoverFactory 
{
	TetraPeople tetRov = null ;
	
	/**
	 *  Constructor
	 */
	public RoverFactory() {}
	
	/**
	 *  creates instance of TetraRovers accordingly
	 * @param type
	 * @param symbol
	 * @return TetraPeople
	 */
	public TetraPeople createRovers(String type, char symbol)
	{
		if(type.equals("Hero"))
		{
			tetRov = new TetraHero(symbol);
		}
		else if (type.equals("Vader"))
		{
			tetRov = TetraVader.getInstance();
		}
		else if(type.equals("Rover"))
		{
			tetRov = new TetraRover();
		}
		
		return tetRov;
	}
	
	
}
