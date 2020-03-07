// Kaushik Nadimpalli
// CS3345.003
// Project 6
// Dijkstra's algorithm with Priority Queues

// Project Decription: In this project, you will determine the shortest/cheapest flight plans for a person wishing to travel between 
// two different cities serviced by an airline (assuming a path exists), using Dijkstra’s algorithm with priority queues. 
// You will also calculate the total cost incurred for all parts of the trip. For this project, you will use information from two 
//different input files in order to calculate the trip plan and total cost. 

//Required imports are imported.We will be using multiple ones so we are including all
import java.util.*;
import java.io.*;


public class ProjectSix
{
	// First let us declare the variables we will be using in this program
	// We must ensure they are private for encapsulation purposes
	
	private int numOne,numTwo;
	
	private List<String> nodesList; //list of all the nodes in the algorithm
	private List<Root> VisitedNodeList; //keep track of previosuly visited nodes
	// Unique principle for Dijkstra's 
	
	private Set<String> List;
	private String CostInTime[][], vertexOne, vertexTwo;
	
	private HashMap<String, Integer> minScope; //Hash Map to calculate minimum distance between cities
	private HashMap<String, Integer> vertexArea; //Hash Map to get distance between each vertex
	
	private PriorityQueue<City> priorityQueue;  //our priority queue we will be using
	// We will use this with Dijkstra's algorithm to find the shortest distance path between
	// the two cities

	public ProjectSix(List<String> nodesList)
		 //constructor class
		// The purpose of this function is to ensure the we define all propeerties 
		// necessary to create our priority queue
		// we are doing this in our constructor
	
	{   
  	  	this.nodesList = nodesList; //set node list to reference current node list
  	  	List = new HashSet<String>(); // Create new set
  	  	VisitedNodeList = new ArrayList<Root>(); // Create our array list
		
  		minScope = new HashMap<String,Integer>(); //define new Hash Map
  		vertexArea = new HashMap<String,Integer>(); //define new Hash Map
 	   	priorityQueue = new PriorityQueue<City>(new City());      
	}


	private void adjacentVertex(String evaluationNode, Root checkNodeList)
	{
 	   int x = -1; //declare necessary variables
 	   int secondX = -1; // new adjacent 

 	   for (int i = 0; i<CostInTime.length; i++)
  		{
  	  	  if(!CostInTime[i][0].equals(evaluationNode))
		  {
			  continue; // contoinue if time is approx equal
		  }
  
   		  String currNode; //our current target
   
  	 	 for (int j = 0; j < nodesList.size(); j++)
   	 	 {
    	   currNode = nodesList.get(j); // for loop to check through all nodes in the list 
		   // to check and get the current node
   
     	   if(!CostInTime[i][1].equals(currNode))
	 	  	{
    		  continue; // continue if cost in time is not equal to current node 
 		  	}
    
		   if (!List.contains(currNode))
			   // We do the following if list is not in current node
    	   {
     		  x = Integer.parseInt(CostInTime[i][numOne]);
     		  secondX = minScope.get(evaluationNode) + x;
			  
     		  if (secondX < minScope.get(currNode)) //check scope and replace it 
     		  {
     	  		minScope.replace(currNode,secondX);
      	  	    vertexArea.replace(currNode,vertexArea.get(evaluationNode) + Integer.parseInt(CostInTime[i][numTwo])); 
	  			for (Root path : VisitedNodeList)
     		   	{
      			 if(path.exists(currNode))
	   		  	 {
        			 	path.delete(currNode); // delete the already existing path
      			 }
				 break;
			   }
     			 checkNodeList.add(currNode); //add current node to our list
     		 }
    		priorityQueue.add(new City(currNode,minScope.get(currNode))); // add current node to our queue
          }  
       }
    }
}


	private String getNode()	
	// The purpose of this method is to return the node with the minimum distance
	{
 	   String node = priorityQueue.remove().cityName;
 	   return node;
	}


	public void dijkastra(String costInTime[][], String requiredPath[])
	// In the following function, we are defining our Dijkstra's algorithm and 
	// using its principles to set and determine our shortest path between cities
	{
  	  String checkVertex;
  	  vertexOne = requiredPath[0];
      vertexTwo = requiredPath[1];
      if(requiredPath[2].equalsIgnoreCase("C"))
		  // check require path through valid argument provided in file
  		{
   		 	numOne = 2;
   		 	numTwo = 3;
  	  	}
  	  else
  		{
   		 	numOne = 3;
  		  	numTwo = 2;
  	  	}

  	  this.CostInTime = costInTime; //set cost to current cost through referencing 


  		for (String vertex:nodesList)
 	 	{
  	   	 minScope.put(vertex, Integer.MAX_VALUE); //put in min distance max value
  	   	 vertexArea.put(vertex, Integer.MAX_VALUE); // put in vertext distance max value
     	}

 		 priorityQueue.add(new City(vertexOne, 0));
  		 minScope.replace(vertexOne,0); //replace with new shorter path
    	 vertexArea.replace(vertexOne, 0); //replace with new shorter path
   	
		 while (!priorityQueue.isEmpty()) // if the queue is empty 
		 // we must first get a new node, add it to queue and list,
		 // check the adjacent node, distance etc 
  		 {
   			 checkVertex = getNode();
   	 		 Root checkNodeList = new Root();
   	  		 checkNodeList.setNode(checkVertex);
   	  		 List.add(checkVertex);
   	  		 adjacentVertex(checkVertex, checkNodeList);
  	   	 
		  if(!vertexExists(VisitedNodeList, checkVertex))
			{
    			VisitedNodeList.add(checkNodeList); //add vertex if there is none provided
			}
 		}
	}


	private boolean vertexExists(List<Root> listOfVisitedVertex, String node)	
	// The main function of this method is to ensure the vertex and node exist in the file
	// we will be using it to move forward later on in the program by checking if there is a node in file
	//  to begin with.
	{
		for (Root p : VisitedNodeList)
 	   {
  		 if(p.getNode().equals(node))
   		  {
			  return true; //return true if node is one we are looking for 
		  }
 	   }
	   
 	  return false; // otherwise return false.
	}


	private static List<String> PathofTheRoot(List<Root> cityVisited, String currNode)
		// In this function we are finding our destination node and the complete path
	{
 	   List<String> fullPath = new ArrayList<String>();
  	 for( Root path : cityVisited) //check for city visited
  	  {
   		 if(!path.exists(currNode))
		 {
   	 		continue; // continue if path exists
  	 	 }
   		
		fullPath = PathofTheRoot(cityVisited, path.getNode());
  	  	fullPath.add(currNode);
   	 	return fullPath; //gives us the complete path to our destination
  	  }
  
  	fullPath.add(currNode); //adds the path as a current node and returns it back
 	return fullPath;
	}

// Main Method for Project 6 
public static void main(String[] arg) throws FileNotFoundException
{
  // Declare and Instantiate all variables
  String costOfTravelTime[][],PathList[][];
  BufferedReader dataOfTheFlight, requestedData;
  List<String> listOfTheNode;
  PrintWriter output = new PrintWriter("OutputFile.txt");

  try
  {
   // Here we are reading the data from both input files
   // The TA can change these file names since they are set here.
   dataOfTheFlight = new BufferedReader(new FileReader("FlightData.txt"));
   requestedData = new BufferedReader(new FileReader("RequestedData.txt"));

   String string;

   listOfTheNode = new ArrayList<String>();
   costOfTravelTime = new String[Integer.parseInt(dataOfTheFlight.readLine())][4];
   PathList = new String[Integer.parseInt(requestedData.readLine())][3];

   int i=0,j; String _node;

   // Make tokens of the data of the flightData file
   while((string = dataOfTheFlight.readLine()) != null)
   {
    j = 0;
    StringTokenizer data = new StringTokenizer(string,"|"); //if null return
    int k = 1;
    
	while(k<=2)
    	{
    		if(!listOfTheNode.contains(_node = data.nextToken()))
    		{
    			costOfTravelTime[i][j++] = _node;
    			listOfTheNode.add(_node);
   		 }
    	 else
     		costOfTravelTime[i][j++] = _node;
    	 k++;
   	 }

    while(data.hasMoreTokens())
  	  {
   	   costOfTravelTime[i][j++] = data.nextToken();
   	  }
   	
	 i++;         
   	}
  	 i=0;

   // Make tokens of the data of the requestedFlightData file
   while((string = requestedData.readLine()) != null)
   {
     j=0;
    StringTokenizer data = new StringTokenizer(string,"|");
   
    while(data.hasMoreTokens())       
     PathList[i][j++] = data.nextToken();
    i++;         
   }       

    i=1;

   // Check the type of the cost
   for(String neededPath[] : PathList)
   {
    if(!(listOfTheNode.contains(neededPath[0])&& listOfTheNode.contains(neededPath[1])))
    {
     output.println("Path can not be find !!!!!");
     continue;
    }
    String s1,s2; //set and get time and cost respectively

    if(neededPath[2].equals("T"))
    {
     s1 = "Time";
     s2 = "Cost";
    }

    else
    {
     s1 = "Cost";
     s2 = "Time";
    }

    ProjectSix priorityQueue = new ProjectSix(listOfTheNode); //create new priority queue
	// calls the dijkstra priority queue to make our new priority queue

    // Call the dijkstra_algorithm method to run the Dijkstra's algorithm
    priorityQueue.dijkastra(costOfTravelTime, neededPath);

    output.println("Flight "+i+": "+priorityQueue.vertexOne+", "+ priorityQueue.vertexTwo+" ("+s1+")");
   
    for (String vertex:listOfTheNode)
    {
     if(!vertex.equals(priorityQueue.vertexTwo))
      continue;
     List<String> list = PathofTheRoot(priorityQueue.VisitedNodeList, priorityQueue.vertexTwo); //get the priority queue with vertices
     for (int k = 0; k < list.size(); k++)
     {
      if(k == list.size()-1 )
       output.print(list.get(k)+". ");
      else
       output.print(list.get(k)+" --> ");
     }                  
     output.println(s1+": " + priorityQueue.minScope.get(vertex)+" "  +s2+": "+priorityQueue.vertexArea.get(vertex));
     break;
    }
    i++;
   }

  } catch (Exception e)
  {
   System.out.println("Exception has occured:" + e.toString()); //return exception if one has occurred
  }
  output.close(); //close output file
}
}