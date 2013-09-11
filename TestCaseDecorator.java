/**
 * TetraStar Simulation
 * Decorator interface or abstract class.
 * @author Sushma and Neha
 *
 */
public abstract class TestCaseDecorator extends TetraFaceDisplayScreen 
{
	TetraFaceDisplayScreen scenario;
	
	/**
	 * Constructor
	 * @param scenario
	 */
	public TestCaseDecorator(TetraFaceDisplayScreen scenario)
	{
		this.scenario = scenario;
	}
}
