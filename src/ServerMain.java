import java.util.Scanner;

public class ServerMain {
	private static String type;
	private static int port;

	public static void main(String[] args) {
		// Start scanner to read in user input
		Scanner scn = new Scanner( System.in );

		// Placeholder for user input
		String input = "";

		// Prompt for type for server to start
		System.out.printf("Enter TCP or UDP: ");
		input = scn.nextLine().toUpperCase().trim();
		type = input;

		// Prompt for port for server to start
		System.out.printf("Enter Port: ");
		input = scn.nextLine().trim();

		// Check if input is number
		try {
			port = Integer.parseInt(input);
			if(port < 0 || port > 655355) {
				System.out.println("Please enter a valid port number! (Between 0 and 65535, inclusively");
				System.exit(1);
			}
		}
		catch(NumberFormatException nfe) {
			System.out.println("Please enter a valid port number! (Between 0 and 65535, inclusively");
			System.exit(1);
		}

		// Given options start either UDP or TCP server
		if(type.equals("TCP")) {
			// Start TCP Server
			new TCPServer(port);
		}
		else if(type.equals("UDP")) {
			// Start UDP Server
			new UDPServer(port);
		}
		else {
			System.out.println("Please indicate either TCP or UDP only!");
			System.exit(1);
		}
	}
}
