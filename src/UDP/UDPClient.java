package UDP;

import org.omg.CORBA.UNKNOWN;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
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
	private InetAddress address; // Server address
	
	public UDPClient(String host, int port) {
		try {
			// Create UDP client socket
			clientSocket = new DatagramSocket();

			// Get IP address of server
			address = InetAddress.getByName(host);

			// Get current timestamp
			String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

			// Print client information
			System.out.printf("Connecting to %s with IP address %s using UDP on Port %s at %s %n", host, address, port, timestamp);

			// TODO: Client is started
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