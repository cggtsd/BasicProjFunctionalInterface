package cgg.functionalinterface;

public class ClientTest {

	public static void main(String[] args) {
		
		createThreadUsingAnonymousClass();
		
	}

	private static void createThreadUsingAnonymousClass() {
		Runnable r = new Runnable() {
			public void run() {
				System.out.println("My task is executing....");
			}
		};
		Thread thread = new Thread(r);
		thread.start();
	}

}
