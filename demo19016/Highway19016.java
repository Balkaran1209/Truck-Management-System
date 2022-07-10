package demo19016;
import base.*;
import java.util.ArrayList;
// Highways are unidirectional

public class Highway19016 extends Highway {
	
	private ArrayList<Truck> trucksOnHighway = new ArrayList<Truck>();
	// returns true if Highway is not full
	// i.e. number of trucks is below capacity
	public boolean hasCapacity()
	{
		if(trucksOnHighway.size()<getCapacity())
		{
			return true;
		}
		return false;
	}
	
	// fails if already at capacity
	public boolean add(Truck truck)
	{
		if(trucksOnHighway.size()<getCapacity())
		{
			trucksOnHighway.add(truck);
			return true;
		}
		return false;

	}
	public void remove(Truck truck)
	{	
		trucksOnHighway.remove(truck);
	}
		
	
}