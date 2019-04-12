package UDP;

import java.net.DatagramSocket;
import java.util.Scanner;
/**
 * UDP Client
 * 
 * @author Xin Liu
 */
public class UDPClient {
	private DatagramSocket clientSocket; // Client socket for UDP
	
	public UDPClient() {

	}

	public static void main(String[] args) {
		// Start scanner to read in user input
		Scanner scn = new Scanner( System.in );

		// Placeholder for user input
		String input = "";

		// Prompt for name or IP address of server
		System.out.println("Enter the name or IP address of the Server: ");
		input = scn.nextLine().trim();

		// Prompt for port number
		System.out.println("Enter Port: ");

	}	
}