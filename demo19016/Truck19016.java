package demo19016;
import base.*;
import java.lang.*;

public class Truck19016 extends Truck{
	
	private Hub lastHub;
	private Highway currHwy;

	private boolean onHighway;

	private boolean onHub;
	int time = 0;


	// the Hub from which it last exited.
	@Override
	public String getTruckName() 
	{
		return "Truck19016";
	}

	public synchronized Hub getLastHub()
	{
		return lastHub;
	}




	public void enter(Highway hwy)
	{
		currHwy = hwy;
		lastHub = hwy.getStart();
		onHighway = true;
		onHub = false;
	}
	// called every deltaT time to update its status/position
	// If less than startTime, does nothing
	// If at a hub, tries to move on to next highway in its route...wrong
	// If on a road/highway, moves towards next Hub
	// If at dest Hub, moves towards dest
	
	protected synchronized void update(int deltaT)
	{
		time = time + deltaT;
		if(time<= getStartTime())
		{
			return;
		}

		if(getDest() == null) 
		{
			return;
		}




		if(this.getDest().getX() == this.getLoc().getX() && this.getDest().getY() == this.getLoc().getY())
		{

			return;

		}



		if(!onHub && !onHighway)
		{

			Hub nextHub = Network.getNearestHub(this.getLoc());
			
			if(this.getLoc().getX() == nextHub.getLoc().getX() && this.getLoc().getY() == nextHub.getLoc().getY())
			{

				if(nextHub.add(this))
				{
				
					onHub = true;
				
				}
			}
			
				this.setLoc(nextHub.getLoc());
		}

		else if(onHighway)
		{
			
			Hub nextHub = currHwy.getEnd();
			
			if(this.getLoc().getX() == nextHub.getLoc().getX() && this.getLoc().getY() == nextHub.getLoc().getY())
			{

				if(nextHub.add(this))
				{
					onHub = true;
					onHighway = false;
					currHwy.remove(this);
				}
			
			}

			
				volini(nextHub.getLoc(), deltaT);
		
		}

		else if(onHub)
		{
            
            Hub destHub = Network.getNearestHub(this.getDest());
            
            if(destHub.getLoc().getX() == this.getLoc().getX() && destHub.getLoc().getY() == this.getLoc().getY())
            {
            	this.setLoc(this.getDest());
            }
		
		}
	}


	private synchronized void volini(Location loc, int deltaT)
	{


		double x =this.getLoc().getX();
		double y =this.getLoc().getY();

		int x1 = loc.getX();
		int y1 = loc.getY();
		double p = (y1-y);
		double b = (x1-x);
		double h = Math.sqrt(p*p + b*b);
		double cos = b/h;
		double sin = p/h;

		double distance =deltaT * (currHwy.getMaxSpeed()/1000.0);
		if(distance>=h)
		{
			this.setLoc(loc);
			return;
		}

		x = x + (distance * cos);
		y = y + (distance* sin);

		double tx = x;
		double ty = y;
		int xl = (int)Math.ceil(tx);
		int yl = (int)Math.ceil(ty);
		Location l = new Location(xl,yl);
		this.setLoc(l);



	}
	
}
