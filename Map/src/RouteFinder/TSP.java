package RouteFinder;

public class TSP {
	int[][] distances;
	int numVertexes;
	
	public TSP(int[][] distances) {
		this.distances = distances;
		numVertexes = distances.length;
	}
	
	public ListNode solve()
	{
		if(numVertexes<30)
		{
			
			boolean[] vertexUsed= new boolean[numVertexes];
			ListNode[] totalweight = new ListNode[(int)Math.pow(2,numVertexes)];
			return solveOptimal(vertexUsed,totalweight);
		}
		else
			return null;
	}
	
	public ListNode solveOptimal(boolean[] vertexUsed, ListNode[] totalweight)
	{
		int key=0;
		int num=0;
		int firstfound = -1;
		for(int i=0; i<numVertexes; i++)
		{
			if(vertexUsed[i])
				key+=Math.pow(2,i);
			else
			{
				num++;
				firstfound = i;
			}
		}
		if(num==1)
			return new ListNode(0,firstfound,null); //Base case, there is one vertex left, build it from here
		
		if(totalweight[key]!= null)
		{
			return totalweight[key];
		}
		
		ListNode best = null;
		ListNode temp = null;
		int bestnum=-1;
		for(int i=0; i<numVertexes; i++) // loop for every index
		{
			if(!vertexUsed[i]) //if current node is not used
			{
				boolean[] newUsed = vertexUsed.clone();
				
				newUsed[i] = true;
				if(best==null)
				{
					best = solveOptimal(newUsed,totalweight);
					bestnum=i;
				}
				else
				{
					temp = solveOptimal(newUsed,totalweight);
					if(temp.weight < best.weight)
					{
						best = temp;
						bestnum = i;
					}
					
				}
			}
		}
		totalweight[key] = new ListNode(distances[bestnum][best.num]+best.weight,bestnum,best);
		return totalweight[key];
	}
	

}
