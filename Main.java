
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int numStudents = 20;
		int numBuses = 2;
		int numCoordinators = 3;
		int busCapacity = 4;
		Commute commute = new Commute (numStudents, busCapacity, numCoordinators);
		
		//creates the students, buses, and coordinators
		//+1 is just to make the id's begin at 1 instead of 0
		for (int i = 0; i < numStudents; i++)
	         new Student(i+1, commute);
		for (int i = 0; i < numBuses; i++)
	         new Bus(i+1, commute);
		for (int i = 0; i < numCoordinators; i++)
	         new Coordinator(i+1, commute);
		

	}

}
