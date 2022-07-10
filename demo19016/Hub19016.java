package demo19016;
import base.*;
import java.util.*;

public class Hub19016 extends Hub{
	
	public Hub19016(Location loc)
	{
		super(loc);
		queueOfTrucks = new ArrayList<Truck>();
	}
	private ArrayList<Truck> queueOfTrucks;
	// add a Truck to the queue of the Hub.
	// returns false if the Q is full and add fails
	public synchronized boolean add(Truck truck)
	{
		if(queueOfTrucks.size()< getCapacity())
		{
			queueOfTrucks.add(truck);
			return true;
		}

		else
		{
			return false;
		}
	}

	protected synchronized void remove(Truck truck)
	{
		if(queueOfTrucks.contains(truck)) {
			queueOfTrucks.remove(truck);
		}
	}


private synchronized boolean dfs(Hub source, Hub destination, Set<Hub> visited)
{	
	visited.add(source);
	if(source==destination)
	{
		return true;
	}

	else
	{
		ArrayList<Highway> hwy = source.getHighways();

		for(Highway h: hwy)
		{
			if(!visited.contains(h.getEnd()))
			{
				boolean b = dfs(h.getEnd(), destination,visited);
				if(b)
				{
					return b;
				}
				
			}
		}

	}
	

	return false;
		
}
	// provides routing information
	public synchronized Highway getNextHighway(Hub last, Hub dest)
	{
		ArrayList<Highway> hwys = last.getHighways();
		boolean t = false;
		for(Highway x: hwys)
		{
			Set<Hub> visited = new HashSet<Hub>();
			visited.add(last);
			t = dfs(x.getEnd(), dest, visited);

			if(t)
			{
				return x;
			}
			

		}

		return null;
	
	}


	// to be implemented in derived classes. Called at each time step
	protected void processQ(int deltaT)
	{
		if(queueOfTrucks.isEmpty())
		{
			return ;
		}
		Truck t = queueOfTrucks.get(0);
		Hub d = Network19016.getNearestHub(t.getDest());
		Hub s = Network19016.getNearestHub(t.getLoc());
		if(s.getLoc().getX()==d.getLoc().getX() && s.getLoc().getY()==d.getLoc().getY())
		{
			queueOfTrucks.remove(t);
		}
		else
		{	
			Highway hwy = getNextHighway(s,d);
			if(hwy.hasCapacity())
			{
				hwy.add(t);
				t.enter(hwy);
				queueOfTrucks.remove(queueOfTrucks.get(0));
			}
		}
	}



}
