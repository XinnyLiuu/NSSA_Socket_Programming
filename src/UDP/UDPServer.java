package UDP;

import java.net.*;
import java.util.Scanner;
/**
 * UDP Server
 * 
 * @author Xin Liu
 */
public class UDPServer {
	private DatagramSocket ds; // Server socket for UDP

	public UDPServer(int port) {
		try {
			// Create UDP server socket
			ds = new DatagramSocket( port );

			// Get IP address of server
			InetAddress ip = InetAddress.getLocalHost();
			String address = ip.getHostAddress();

			// Get the hostname of the server
			String hostname = ip.getHostName();

			// Print server information
			System.out.printf("IP Address: %s %n", address);
			System.out.printf("IP Hostname: %s %n", hostname);
			System.out.printf("Running UDP on Port %s %n", port);

			// TODO: Server is started but need to connect multiple clients
			System.exit(1);
		}
		catch(SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}
		catch(UnknownHostException uhe) {
			uhe.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String[] args) { 
		// Start scanner to read in user input
		Scanner scn = new Scanner( System.in );

		// Placeholder for user input
		String input = "";

		// Prompt for port
		System.out.println("Enter Port: ");
		input = scn.nextLine().trim();

		// Check if input is a number
		try {
			int port = Integer.parseInt(input);
			if(port > 0) {
				// Create the server
				new UDPServer( port );
			}
		}
		catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			System.exit(1);
		}

	}
}
