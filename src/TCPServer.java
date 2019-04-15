import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

/**
    Creates and runs a server that accepts TCP connections
 */
public class TCPServer {
    //instance variables
    private int port;
    private ServerSocket serverSocket;
    
    /**
        Constructor
        @param port - port number
     */
    public TCPServer(int port) throws IllegalArgumentException
    {
        try
        {
            serverSocket = new ServerSocket(port);
            this.port = port;
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException();
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String args[])
    {
        //setup
        Scanner s = new Scanner(System.in);

        //get type
        System.out.println("Enter TCP or UDP:");
        String connType = s.nextLine();

        //error checking
        while(!connType.equalsIgnoreCase("TCP") && !connType.equalsIgnoreCase("UDP"))
        {
            System.out.println("Please enter TCP or UDP:");
            connType = s.nextLine();
        }

        //get port
        System.out.println("Enter Port:");
        String port = s.nextLine();
        int portNum = Integer.parseInt(port);

        //error checking
        while(portNum <= 0 || portNum > 65535 )
        {
            System.out.println("Please enter a valid Port number between 1 and 65535:");
            port = s.nextLine();
            portNum = Integer.parseInt(port);
        }

        //decide the type that needs to be created
        if(connType.equalsIgnoreCase("TCP"))
        {
            try
            {
                TCPServer server = new TCPServer(8080);

                //TODO: get IP and hostname
                String IP = "";
                String hostname = "";

                //TODO: make timestamp
                String timestamp = "";

                System.out.println("IP Address: " + IP);
                System.out.println("IP Hostname: " + hostname);
                System.out.println("Running " + connType + " on Port " + portNum);
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Could not connect with the given parameters");
            }
        }
        else
        {
            System.out.println("Under Construction");
        }
    }
}