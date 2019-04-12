package UDP;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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
			try {
				init();
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
	 * Starts the server to listen to a client sending data over UDP
	 */
	private void init() throws IOException {
		boolean stop = false; // Flag for stopping server
		byte[] buffer = new byte[256]; // Maximum size of message to be received

		while(!stop) {
			// Get timestamp
			String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

			// Setup DatagramPacket to receive request
			DatagramPacket request = new DatagramPacket( buffer, buffer.length);
			serverSocket.receive( request );

			// Get request information
			InetAddress address = request.getAddress();
			int port = request.getPort();

			// Check received data
			String received = new String( request.getData(), 0, request.getLength());
			System.out.printf("%s %s client %s %n", timestamp, received, address);
			if(received.equals("end")) {
				stop = true;
			}

			// Prepare echo with timestamp as response
			String echo = timestamp + " " + received;
			buffer = echo.getBytes();

			// Setup DatagramPacket to send response (echo above)
			DatagramPacket response = new DatagramPacket( buffer, buffer.length, address, port );
			serverSocket.send( response );

			// Clear buffer for next read
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
