package UDP;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
/**
 * UDP Client
 * 
 * @author Xin Liu
 */
public class UDPClient {
	private DatagramSocket clientSocket; // Client socket for UDP
	private static Scanner scn; // Read user inputs
	
	public UDPClient(String host, int port) {
		try {
			// Create UDP client socket
			clientSocket = new DatagramSocket();

			// Get IP address of server
			InetAddress address = InetAddress.getByName(host);

			// Get current timestamp
			String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

			// Print client information
			System.out.printf("Connecting to %s with IP address %s using UDP on Port %s at %s %n", host, address, port, timestamp);

			// Start sending data to server
			try {
				init(address, port);
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
				System.exit(1);
			}
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

	/**
	 * Starts the client to send request to a server over UDP and listens to a response in return
	 */
	private void init(InetAddress address, int port) throws IOException {
		byte[] buffer; // Size of message
		boolean stop = false; // Flag to stop client on "end"

		while(!stop) {
			// Get input
			String input = scn.nextLine();
			if(input.equals("end")) { // On "end", kill the connection
				stop = true;
			}

			// Get timestamp
			String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

			// Convert string input into bytes
			buffer = input.getBytes();

			// Setup DatagramPacket to send input
			DatagramPacket message = new DatagramPacket( buffer, buffer.length, address, port );
			clientSocket.send( message );
			System.out.printf("%s %s %n", timestamp, input);
		}

		// Close socket, scanner and exit
		clientSocket.close();
		scn.close();
		System.exit(1);
	}

	public static void main(String[] args) {
		// Start scanner to read in user input
		scn = new Scanner( System.in );

		// Prompt for name or IP address of server
		System.out.println("Enter the name or IP address of the Server: ");
		String host = scn.nextLine().trim();

		// Check host for errors
		if(host.length() <= 0) {
			System.out.println("Please enter a value!");
			System.exit(1);
		}

		// Prompt for port number
		System.out.println("Enter Port: ");
		String port = scn.nextLine().trim();

		// Check if port is number
		try {
			if(Integer.parseInt(port) > 0) {
				// Create the client
				new UDPClient(host, Integer.parseInt(port));
			}
		}
		catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			System.exit(1);
		}

	}	
}