package demo19016;
import base.*;

public class Factory19016 extends Factory {

	public Network createNetwork()
	{
		Network n = new Network19016();
		return n;
	}
	public Highway createHighway()
	{
		Highway hi = new Highway19016();
		return hi;
	}
	public Hub createHub(Location location)
	{
		Hub h = new Hub19016(location);
		return h;

	}
	public Truck createTruck()
	{
		Truck t = new Truck19016();
		return t;
	}
	
}
