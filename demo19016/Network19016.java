package demo19016;
import base.*;
import java.util.*;
import java.lang.*;
// We assume only one instance of Network will be created for a program
public class Network19016 extends Network {

	private ArrayList<Hub> hubsList = new ArrayList<Hub>();
	private ArrayList<Highway> highwayList = new ArrayList<Highway>();
	private ArrayList<Truck> truckList = new ArrayList<Truck>();
	
	// keep track of the following entities
	public void add(Hub hub)
	{
		hubsList.add(hub);
	}
	public void add(Highway hwy)
	{
		highwayList.add(hwy);
	}
	public void add(Truck truck)
	{
		truckList.add(truck);
	}
	

	// finds the nearest Hub to a given location
	// Made static so that other classes don't need to keep track of Network instance

	// start the simulation
	// derived class calls start on each of the Hubs and Trucks
	public void start()
	{
		for(Hub x: hubsList)
		{
			x.start();
		}
		for(Truck x: truckList)
		{
			x.start();
		}
	}

	// derived class calls draw on each hub, highway, and truck
	// passing in display
	public void redisplay(Display disp)
	{
		for(Hub x: hubsList)
		{
			x.draw(disp);
		}
		for(Truck x: truckList)
		{
			x.draw(disp);
		}
		for(Highway x: highwayList)
		{
			x.draw(disp);
		}
	}
	
	protected Hub findNearestHubForLoc(Location loc)
	{
		ArrayList<Integer> distance = new ArrayList<Integer>();
		for(Hub h: hubsList)
		{
			Location l = h.getLoc();
			int d = l.distSqrd(loc);
			distance.add(d);
		}
		int j = distance.indexOf(Collections.min(distance)); 
		Hub res = hubsList.get(j);
		return res;
	}
	

}
