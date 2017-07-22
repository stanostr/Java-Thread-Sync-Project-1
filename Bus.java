public class Bus implements Runnable {
	private String name;
	private Commute commute; //monitor object
	private static long time = System.currentTimeMillis();
	
	public Bus (int id, Commute commute)
	{
		setName("Bus-" + id);
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
	
	//generates a random time between 1000 and 3000 milliseconds
	private int simulatedBusTime()
	{
		int busTime = ((int)(Math.random()*2000 + 1000));
		msg("Traveling to school for " + busTime + " ms");
					//that was a quick drive!
		return busTime;
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
		while(commute.getS() > 0) //while there are still students running
		{
			if(commute.groupReady())
			{
				msg("Loading bus");
				try {
					Thread.sleep(simulatedBusTime());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				commute.busPickUp();
				msg("Returning for more students. " + commute.getS() + " students left");
				try {
					Thread.sleep((int)(Math.random()*2000 +1000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		msg("No more students to bus. Finished job. Students left:" + commute.getS() + " . ");
		return;
	}
}
