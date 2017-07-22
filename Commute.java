public class Commute {
	//these variables are horribly named as per instructions
	private int s;
	public int bc;
	public int c;
	
	public int busGroup = 0;
	private int waitingBusStudents = 0;
	private int waitingCoordStudents = 0;
	
	public Object coord = new Object();
	public Object aGroup = new Object();
	
	public Commute(int s, int bc, int c)
	{
		this.s = s;
		this.bc = bc;
		this.c = c;
	}
	
	public synchronized int getS() {
		return s;
	}

	public synchronized void decrementS() {
		s--;
	}
	
	//checks if a group is ready for boarding bus
	public synchronized boolean groupReady()
	{
		if(busGroup >= bc || (waitingBusStudents == 0 && busGroup > 0))
			return true;
		else return false;
	}
	
	
	public void findBus() //for bussed students
	{
		waitingBusStudents++;
		while(true) {
			if(!groupReady())
				synchronized(aGroup)
				{
					waitingBusStudents--;
					busGroup++;
					try { 
						aGroup.wait(); return; }
					catch (InterruptedException e) { 
						System.out.println("Something bad happened.");
					}
				}
		}
	}
	
	public void findCoordinator() //for car students
	{
		waitingCoordStudents++;
		synchronized(coord)
		{
			
			try { 
				coord.wait(); 
				waitingCoordStudents--;
				return; 
			}
			catch (InterruptedException e) { 
				System.out.println("Something bad happened.");
			}
			
		}
		
	}
	
	
	public synchronized void busPickUp() //for bus
	{
			if(groupReady())
				synchronized(aGroup)
				{
					//notify the current group
					aGroup.notifyAll();
					busGroup = 0;
				}
	}
	
	public synchronized void pickUpStudents() //for coordinator
	{
			synchronized(coord)
			{
				coord.notify();
			}
	}

	public synchronized int getWaitingCoordStudents() {
		return waitingCoordStudents;
	}

}
