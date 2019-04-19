import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
    Creates and runs a server that accepts TCP connections
 */
public class TCPServer {
    //instance variables
    private ServerSocket serverSocket;
    
    /**
        Constructor
        @param port - port number
     */
    public TCPServer(int port) throws IllegalArgumentException
    {
        try
        {
            this.serverSocket = new ServerSocket(port);

            //print out server info
            System.out.println("IP Address: " + serverSocket.getInetAddress().getHostAddress());
            System.out.println("IP Hostname: " + serverSocket.getInetAddress().getHostName());
            System.out.println("Running TCP on Port " + serverSocket.getLocalPort());

            while(true)
                new ConnectionThread(serverSocket.accept()).start();
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException();
        }
    }

    private static class ConnectionThread extends Thread
    {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ConnectionThread(Socket socket) {
            try
            {
                this.socket = socket;
                this.in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
                this.out = new PrintWriter(socket.getOutputStream());
            }
            catch(IOException e)
            {
                System.out.println("Could not connect with client on" + socket.getInetAddress().getHostAddress());
            }
        }

        @Override
        public void run() {
            try
            {
                //print out connection info
                String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                System.out.println(timestamp + " New connection from: " + this.socket.getInetAddress().getHostAddress());

                String message;
                while ((message = in.readLine()) != null)
                {
                    //echo message to console
                    timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                    System.out.println("Sending to client: " + this.socket.getInetAddress().getHostAddress() +
                            " " + timestamp + " " + message);

                    //send message to client
                    out.println(message);
                    out.flush();
                }
            }
            catch(IOException e)
            {
                System.out.println("Error receiving message");
            }
        }
    }

    public static void main(String args[])
    {
        //setup
        Scanner s = new Scanner(System.in);

        //get type
        System.out.println("Enter TCP or UDP:");
        String connType = "TCP";//s.nextLine();

        //error checking
        while(!connType.equalsIgnoreCase("TCP") && !connType.equalsIgnoreCase("UDP"))
        {
            System.out.println("Please enter TCP or UDP:");
            connType = s.nextLine();
        }

        //get port
        System.out.println("Enter Port:");
        String port = "1000";//s.nextLine();
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
                TCPServer server = new TCPServer(portNum);
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