import java.util.Scanner;

/**
    Creates and runs a client that makes TCP connections
 */
public class TCPClient
{
    //instance variables
    private String IP, hostname;
    private int port;

    /**
     * Constructor using IP address
     * @param IPOrHost - ip address or hostname as a string
     * @param port - port number
     * @param type - the type of identification for the server; acceptable
     *             options are "i" for IP and "h" for host name
     */
    public TCPClient(String IPOrHost, int port, char type) throws IllegalArgumentException
    {
        if(type == 'i')
        {

        }
        else if(type == 'h')
        {

        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String args[])
    {
        //setup
        Scanner s = new Scanner(System.in);
        String IPQuery = "\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}";
        char type = 'h';

        //get name or IP
        System.out.println("Enter the name or IP address of the Server:");
        String IPOrHost = s.nextLine();

        //error checking
        while(IPOrHost.isEmpty())
        {
            System.out.println("Please enter a non-empty name or IP address of the Server:");
            IPOrHost = s.nextLine();
        }

        //change type to an IP if it matches IP pattern
        if(IPOrHost.matches(IPQuery))
        {
            type = 'i';
        }

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
                TCPClient client = new TCPClient(IPOrHost,8080, type);

                //TODO: get IP based on hostname
                String IP = "";

                //TODO: make timestamp
                String timestamp = "";

                System.out.println("Connecting to " + IPOrHost +
                        " with IP address " + IP + " using " + connType.toUpperCase() +
                        " on Port " + port + " at " + timestamp);
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