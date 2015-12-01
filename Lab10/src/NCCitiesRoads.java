import java.util.*;

/** The NC Routes Project: Lesson 10 Start
  * The NCCitiesRoads class creates a graph 
  * from city and road information
  */
public class NCCitiesRoads
{
	/**
	 * For this program:
	 *  1. Create the City and Road ArrayLists 
	 *  2. Add 6 City objects to the City Vertex ArrayList.
	 *  3. Add 18 Road objects to the Road Edge ArrayList.  
	 *  4. Create a WeightedGraph passing in the 
	 *     City and Road ArrayLists.  
	 *  5. Next, display each of the items requested below:
	 *        (look at the Graph/WeightedGraph methods)
	 *     a. The number of Cities in the map.
	 *     b. The City object information for the 4th City
	 *        added, using a graph method to retrieve the City.
	 *     c. The City with the largest number of Roads ending there.
	 *     d. The Road edge information for each Road using a 
	 *        graph method.
	 *     e. The complete Road information for each Road from the
	 *        Road ArrayList. Cast the WeightedEdge from the
	 *        ArrayList to a Road object
	 *     f. Provide an ArrayList with the Cities sorted
	 *        and print it - (use Collections.sort)
	 */

	public static void main (String[] args)
	{
		// Create an ArrayList called cities of City objects


		// Create an ArrayList called roads of WeightedEdge Road objects


		// Create 6 Cities

		City city0 = new City ("Murphy",        -84.029924, 35.089848, 1627, 0);
		City city1 = new City ("Mars Hill",     -82.547843, 35.828496, 1869, 1);

		// Do the rest yourself


		// Add them to ArrayList

		cities.add (city0);
		cities.add (city1);

		// Do the rest yourself
		
		// Create 18 roads and add them to ArrayList

		roads.add (new Road (city0, city1));

		// Do the rest yourself


		// Create Weighted Graph      
		


		// The number of cities in the map.
		
                

		// The City object information for the 4th City added,
		// using a graph method to retrieve the City.
		
		
		

		// The City with the largest number of Roads ending there.
		// This requires you to loop thru arraylist              
		

		
		

		// The Road edge information for each Road using a graph method.
		
		
		

		// The complete Road information for each Road from the Road ArrayList.
		// Cast the WeightedEdge from the ArrayList to a Road object
                // This requires you to loop thru arraylist              
		
		
		
                
		
		// Provide an additional ArrayList with the Cities sorted and print it
		// (use Collections.sort)
		// This requires you to loop thru arraylist to create new arraylist



	}
}