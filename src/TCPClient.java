import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 *  Creates and runs a client that makes TCP connections
 */
public class TCPClient
{
    //instance variables
    Socket socket;
    InetAddress IP;
    PrintWriter out;
    BufferedReader in;

    /**
     * Constructor using IP address
     * @param IPOrHost - ip address or hostname as a string
     * @param port - port number
     * @throws IllegalArgumentException
     */
    public TCPClient(String IPOrHost, int port) throws IllegalArgumentException
    {
        try
        {
            this.IP = InetAddress.getByName(IPOrHost);
            this.socket = new Socket(IP, port);
            this.out = new PrintWriter( socket.getOutputStream(), true );
            this.in = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
        }
        catch(UnknownHostException e)
        {
            throw new IllegalArgumentException("Unknown host");
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException("Could not connect with given parameters");
        }
    }

    /**
     * Sends a message across the connection if there is a connection
     * @param message - the message to send
     */
    public void sendMessage(String message, Consumer<String> callback)
    {
        try
        {
            this.out.println(message);
            String msg = this.in.readLine();
            callback.accept(msg);
        }
        catch(IOException e)
        {
            System.out.println("Could not receive response");
        }
    }

    /**
     * Closes client
     */
    public void close()
    {
        try
        {
            out.close();
            in.close();
            socket.close();

        }
        catch(IOException e)
        {
            System.out.println("Error closing");
        }
    }

    public static void main(String args[])
    {
        //setup
        Scanner s = new Scanner(System.in);

        //get name or IP
        System.out.println("Enter the name or IP address of the Server:");
        String IPOrHostname = "192.168.0.8";//s.nextLine();

        //error checking
        while(IPOrHostname.isEmpty())
        {
            System.out.println("Please enter a non-empty name or IP address of the Server:");
            IPOrHostname = s.nextLine();
        }

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
                InetAddress IPAddr = InetAddress.getByName(IPOrHostname);
                TCPClient client = new TCPClient(IPOrHostname, portNum);
                String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

                //TODO: fix IP printing
                //TODO: understand why it doesn't work with JARs?

                System.out.println("Connecting to " + IPOrHostname +
                        " with IP address " + IPAddr.getHostAddress() + " using " +
                        connType.toUpperCase() + " on Port " + port + " at " + timestamp);

                while(true)
                {
                    String msg = s.nextLine();
                    client.sendMessage(msg, (str) -> {
                        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                        System.out.println(time + " " + str);
                    });

                    if(msg.equals("end"))
                    {
                        client.close();
                        break;
                    }
                }

            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Could not connect with the given parameters");
            }
            catch(UnknownHostException e)
            {
                System.out.println("Unknown host provided");
            }
        }
        else
        {
            System.out.println("Under Construction");
        }
    }

}