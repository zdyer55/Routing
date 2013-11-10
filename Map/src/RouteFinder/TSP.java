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
		int firstfound =-1;
		for(int i=1; i<numVertexes; i++)
		{
			if(vertexUsed[i])
				key+=Math.pow(2,i);
			else
			{
				firstfound = i;
				num++;
			}
		}
		if(num==1)
		{
			ListNode returnToStart = new ListNode(0,0,null);
			return new ListNode(distances[firstfound][0],firstfound,returnToStart); //Base case, there is one vertex left, build it from here
		}
		if(num==numVertexes-1)
		{
			ListNode[] topLevelList = new ListNode[numVertexes-1];
			for(int i=1; i<numVertexes; i++)
			{

					boolean[] newUsed = vertexUsed.clone();
					
					newUsed[i] = true;
					newUsed[0] = true;
					
					ListNode tempNode= solveOptimal(newUsed,totalweight);
					tempNode = new ListNode(tempNode.weight+distances[tempNode.num][i], i, tempNode);
					topLevelList[i-1] = new ListNode(tempNode.weight+distances[i][0], 0, tempNode);
			}
			ListNode best = topLevelList[0];
			for(int i=1; i<numVertexes-1; i++)
			{
				if(topLevelList[i].weight<best.weight)
					best = topLevelList[i];
			}
			return best;
		}
		if(totalweight[key]!= null)
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
