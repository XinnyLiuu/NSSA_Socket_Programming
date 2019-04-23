import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ClientMain
{
    public static void main(String args[])
    {
        //setup
        Scanner s = new Scanner(System.in);

        //get name or IP
        System.out.print("Enter the name or IP address of the Server: ");
        String IPOrHostname = s.nextLine().trim();

        //error checking
        while(IPOrHostname.isEmpty())
        {
            System.out.print("Please enter a non-empty name or IP address of the Server: ");
            IPOrHostname = s.nextLine().trim();
        }

        //get type
        System.out.print("Enter TCP or UDP: ");
        String connType = s.nextLine().trim();

        //error checking
        while(!connType.equalsIgnoreCase("TCP") && !connType.equalsIgnoreCase("UDP"))
        {
            System.out.print("Please enter TCP or UDP: ");
            connType = s.nextLine().trim();
        }

        //get port
        System.out.print("Enter Port: ");
        String port = s.nextLine().trim();
        int portNum = Integer.parseInt(port);

        //error checking
        while(portNum <= 0 || portNum > 65535 )
        {
            System.out.print("Please enter a valid Port number between 1 and 65535: ");
            port = s.nextLine().trim();
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
            new UDPClient(IPOrHostname, Integer.parseInt(port));
        }
    }
}
