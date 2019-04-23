# Compiling Server
1. Open a terminal 
2. Navigate to the folder containing the .java file using the ‘cd’ command
3. Enter the command `javac ServerMain.java && java ServerMain`, this compiles and 
runs the program
4. Type ‘TCP’ or ‘UDP’ depending on the type of server you want to start and press enter
5. Type the port number that you want the server to use upon startup and press enter
The terminal will print out the connection information for the client to use and 
information about any connections or messages from connections that are received

# Compiling Client
1. Open a terminal 
2. Navigate to the folder containing the .java file using the ‘cd’ command
3. Enter the command `javac ClientMain.java && java ClientMain`, this compiles 
and runs the program
4. Type IP address or host name of the server that you want to connect to and press enter
5. Type ‘TCP’ or ‘UDP’ depending on the type of client you want to use and press enter
6. Type the port number of the server that you intend to connect to and press enter
7. The terminal will print out the connection information if the connection is successful
8. To send a message to the server, just type it into the terminal and press enter. 
The server should echo your message to the terminal window

# Running Server JAR
1. Open a terminal window
2. Navigate to the folder containing the JAR file using the ‘cd’ command
3. Run the command ‘java -jar ServerMain.jar’
4. Type ‘TCP’ or ‘UDP’ depending on the type of server you want to start and press enter
5. Type the port number that you want the server to use upon startup and press enter
6. The terminal will print out the connection information for the client to use and 
information about any connections or messages from connections that are received

# Running Client JAR
1. Open a terminal window
2. Navigate to the folder containing the JAR file using the ‘cd’ command
3. Run the command ‘java -jar ClientMain.jar’
4. Type IP address or host name of the server that you want to connect to and press enter
5. Type ‘TCP’ or ‘UDP’ depending on the type of server you want to connect to and 
press enter
6. Type the port number of the server you want to connect to and press enter
7. The terminal will print out the connection information if the connection is successful
8. To send a message to the server, just type it into the terminal and press enter. 
The server should echo your message to the terminal window
