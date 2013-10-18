package DistanceMatrixUtils;

/**
 * Represents the distance matrix in such a way that the TSP algorithm can solve it
 * @author Zach
 *
 */
public class SplitArrayMatrix 
{
	private int[][] distances;
	private int[][] durations;
	private String[][] statuses;

	private String[] places;
	
	public SplitArrayMatrix(DistanceMatrix dmat)
	{
		int length = dmat.destination_addresses.length;
		distances = new int[length][length];
		durations = new int[length][length];
		statuses = new String[length][length];
		places = new String[length];
		int i =0;
		int j;
		for(Rows rows : dmat.rows)
		{
			j=0;
			for(Element element : rows.elements)
			{
				distances[i][j] = element.distance.value;
				durations[i][j] = element.duration.value;
				statuses[i][j] = element.status;
				j++;
			}
			i++;
		}
		i=0;
		for(String s: dmat.destination_addresses)
		{
			places[i] = s;
			i++;
		}
	}
	public String[] getPlaces() {
		return places;
	}
	public void setPlaces(String[] places) {
		this.places = places;
	}
	public int[][] getDistances() {
		return distances;
	}
	public void setDistances(int[][] distances) {
		this.distances = distances;
	}
	
	public int[][] getDurations() {
		return durations;
	}
	public void setDurations(int[][] durations) {
		this.durations = durations;
	}
	public String[][] getStatuses() {
		return statuses;
	}
	public void setStatuses(String[][] statuses) {
		this.statuses = statuses;
	}
}
