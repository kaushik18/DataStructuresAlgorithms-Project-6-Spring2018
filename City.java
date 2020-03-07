// Kaushik Nadimpalli
// CS3345.003
// Project 6
// Dijkstra's algorithm with Priority Queues

//We need to import the class comparators
//import for class comparator is below

import java.util.Comparator;


class City implements Comparator<City>
{
	public String cityName;  //variable to store cityName name
	public int cityCost; //variable to store cityCost of travel to cityName

	public City() //empty constructor - we don't have to do anything
	// provided for better coding/encapsulation practice
	{
	}

// The function below serves to compare the nodes, respectively the cities.
	@Override
	public int compare(City node1, City node2)
	{
		if (node1.cityCost < node2.cityCost) 
 	 	{
  	 		 return -1; //if cityCost of node one is less than that of node two
			 // return -1
 		}
 		if (node1.cityCost > node2.cityCost)
  		{
 			 return 1; // if cityCost of node one is more than that of node two
			 // return 
  	    }
  
  		return 0; 
	}

	public City(String cityName, int cityCost)
	{
 	   this.cityName = cityName; // reference variable to refer to current city name
 	   this.cityCost = cityCost; // reference variable to refer to current city cost
	}
}