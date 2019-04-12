package UDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
/**
 * UDP Server
 * 
 * @author Xin Liu
 */
public class UDPServer {
	private DatagramSocket serverSocket; // Server socket for UDP

	public UDPServer(int port) {
		try {
			// Create UDP server socket
			serverSocket = new DatagramSocket( port );

			// Get IP address of server
			InetAddress ip = InetAddress.getLocalHost();
			String address = ip.getHostAddress();

			// Get the hostname of the server
			String hostname = ip.getHostName();

			// Print server information
			System.out.printf("IP Address: %s %n", address);
			System.out.printf("IP Hostname: %s %n", hostname);
			System.out.printf("Running UDP on Port %s %n", port);

			// Start listening for data sent from client
			init();
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
	 * Starts the server to listen to a client sending data over UDP
	 */
	private void init() {
		boolean stop = false; // Flag for stopping server
		byte[] buffer = new byte[256]; // Maximum size of message to be received

		while(!stop) {
			// Setup DatagramPacket to receive request
			DatagramPacket data = new DatagramPacket( buffer, buffer.length);
			try {
				serverSocket.receive( data );
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
				System.exit(1);
			}

			// TODO: figure out what this means
			String received = new String( data.getData(), 0, data.getLength());

			// Get data information
			InetAddress address = data.getAddress();
			int port = data.getPort();

			// Setup DatagramPacket to send response b
			data = new DatagramPacket( buffer, buffer.length, address, port );

			// Check contents of received

			if(received.equals("end")) {
				stop = true;
				return;
			}

			// Send response
			try {
				serverSocket.send( data );
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
				System.exit(1);
			}

			// Clear buffer
			buffer = new byte[256];
		}

		// Close socket
		serverSocket.close();
		System.exit(1);
	}

	public static void main(String[] args) { 
		// Start scanner to read in user input
		Scanner scn = new Scanner( System.in );

		// Placeholder for user input
		String input = "";

		// Prompt for port for server to start
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
