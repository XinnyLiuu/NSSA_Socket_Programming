import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
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
            InetAddress localhost = InetAddress.getLocalHost();

            //print out server info
            System.out.println("IP Address: " + localhost.getHostAddress());
            System.out.println("IP Hostname: " + localhost.getHostName());
            System.out.println("Running TCP on Port " + serverSocket.getLocalPort());

            while(true)
                new ConnectionThread(serverSocket.accept()).start();
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Thread that handles a new connection to the server
     */
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
        public void run()
        {
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

                    if(message.equals("end"))
                    {
                        break;
                    }
                }

                in.close();
                out.close();
                socket.close();
            }
            catch(IOException e)
            {
                System.out.println("Disconnect");
            }

            System.out.println("Thread done");
        }
    }
}