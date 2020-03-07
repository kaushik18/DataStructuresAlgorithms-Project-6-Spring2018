// Kaushik Nadimpalli
// CS3345.003
// Project 6
// Dijkstra's algorithm with Priority Queues

// Required imports
// We are using lists and arrayLists in our program so we import them
import java.util.ArrayList;
import java.util.List;


class Root
{
	private String cityName;
	private List<String> rootVar;
	

	public String getNode()
	{
  	  return this.cityName; //get the city name from the City class
	}

	public void add(String cityName)
	{
 	   rootVar.add(cityName); // function to add node to the array list
	}
	
	public void delete(String cityName)
	{
		rootVar.remove(cityName); // function to delete node from array list
	}

	public Boolean exists(String cityName)
	{
		//function to compare and check if the node exists in the array list in the first place
 	    if(rootVar.contains(cityName))
  		return true;
  	 	return false;
	}


	public Root()
	{
  	  rootVar = new ArrayList<String>(); //create our arrayList rootVar
	}

	public void setNode(String Node)
	{
 	   this.cityName = Node; // function sets the node we are to the city name
	}
}