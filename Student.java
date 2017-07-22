public class Student implements Runnable {
	private String name;
	private Commute commute; //monitor object
	private static long time = System.currentTimeMillis();
	
	public Student (int id, Commute commute)
	{
		setName("Student-" + id);
		this.commute = commute;
		new Thread(this).start();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void msg(String m)
	{
		System.out.println("[" + (System.currentTimeMillis()-time) + "]"
				+ getName() + ": " + m);
	}
	
	//decides whether the student will go by bus or car
	private boolean isBussed() 
	{
		if(Math.random() < 0.5)
			{
				msg("Goes by car");
				return false;
			}
		else 
			{
				msg("Goes by bus");
				return true;
			}
	}
	
	//generates a random time between 1000 and 3000 milliseconds
	private int simulatedDriveTime()
	{
		int driveTime = ((int)(Math.random()*2000 +1000));
		msg("Commuting in car for " + driveTime + " ms"); //that was a quick drive!
		return driveTime;
	}
	
	//sleeps a random amount of time within provided range
	private void simulatedWait(int range, int minTime)
	{
		int sleepTime = ((int)(Math.random()*range + minTime));
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			msg("Died  :(");
		}
	}
	
	public void run()
	{
		boolean bussed = isBussed();
		if(bussed)
		{
			msg("Waiting on bus");
			commute.findBus();
			msg("Got off bus");
		}
		else
		{
			msg("Left home by car");
			simulatedWait(2000, 2000);
			msg("Arrived at drop-off, waiting for Coordinator");
			commute.findCoordinator();
		}
		commute.decrementS();
		msg("Arrived at gymnasium");
	}
}
