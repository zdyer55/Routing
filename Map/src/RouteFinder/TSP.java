package RouteFinder;

/**
 * Class to solve the routing problem
 * Takes a 2D array of distances between the cities
 * @author Zach
 *
 */
public class TSP {
	//each index represents a distance between two cities
	//distances[0][3] would be the distance from the first and 4th city
	//distances[3][0] would be the distance from the 4th to the first
	int[][] distances;
	int numVertexes;
	
	public TSP(int[][] distances) {
		this.distances = distances;
		numVertexes = distances.length;
	}
	
	public ListNode solve()
	{
		if(numVertexes<30) //optimal algorithm is too slow over 30 cities and the key system relies 2^numVertexes
		{
			
			boolean[] vertexUsed= new boolean[numVertexes];
			ListNode[] totalweight = new ListNode[(int)Math.pow(2,numVertexes)];
			return solveOptimal(vertexUsed,totalweight);
		}
		else
			return null; //eventually want to implement an approximation
	}
	
	public ListNode solveOptimal(boolean[] vertexUsed, ListNode[] totalweight)
	{
		int key=0; //represents a combination of used vertexes for memoization
		int num=0; //number of unused vertexes for this call
		int firstfound =-1;
		
		for(int i=1; i<numVertexes; i++)
		{
			if(vertexUsed[i])
				key+=Math.pow(2,i); //unique number for vertex
			else
			{
				firstfound = i; //first unused vertex
				num++;
			}
		}
		if(num==1)//Base case, there is one vertex left, build it from here
		{
			ListNode returnToStart = new ListNode(0,0,null);
			return new ListNode(distances[firstfound][0],firstfound,returnToStart); 			
		}
		if(num==numVertexes-1)//first recursive call requires the first and last vertex to be 0
		{
			ListNode[] topLevelList = new ListNode[numVertexes-1];
			for(int i=1; i<numVertexes; i++)
			{

					boolean[] newUsed = vertexUsed.clone();
					
					newUsed[i] = true; //start with next city
					newUsed[0] = true; //first city is always used 
					
					ListNode tempNode= solveOptimal(newUsed,totalweight);
					tempNode = new ListNode(tempNode.weight+distances[tempNode.num][i], i, tempNode);
					//store each start in a list
					topLevelList[i-1] = new ListNode(tempNode.weight+distances[i][0], 0, tempNode); // add 0 to top of list
			}
			ListNode best = topLevelList[0];
			for(int i=1; i<numVertexes-1; i++)
			{
				if(topLevelList[i].weight<best.weight) //find shortest of the paths
					best = topLevelList[i]; //update best
			}
			return best;
		}
		if(totalweight[key]!= null) //if result is memoized
		{
			return totalweight[key];
		}
		
		ListNode best = null;
		ListNode temp = null;
		int bestnum=-1;
		for(int i=1; i<numVertexes; i++) // loop for every index
		{
			if(!vertexUsed[i]) //if current node is not used
			{
				boolean[] newUsed = vertexUsed.clone();
				
				newUsed[i] = true; //this index is being used for these recursive calls
				if(best==null) //if there is no best, create one
				{
					best = solveOptimal(newUsed,totalweight);
					bestnum=i;
				}
				else
				{
					temp = solveOptimal(newUsed,totalweight);
					if(temp.weight < best.weight) //if better than best, replace
					{
						best = temp;
						bestnum = i;
					}
					
				}
			}
		}
		//update memoized value
		totalweight[key] = new ListNode(distances[bestnum][best.num]+best.weight,bestnum,best);
		return totalweight[key];
	}
	

}
