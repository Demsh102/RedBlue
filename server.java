DatagramSocket serverSocket = null;
 int port = 0, port1 = 0, port2 = 0;
 InetAddress IPAddress = null, IPAddress1 = null, IPAddress2 = null;
 String message = "";
 String response = "";
 DatagramPacket receivePacket = null;
 DatagramPacket sendPacket = null;
 int state = 0;
 byte[] receiveData = new byte[1024];
 byte[] sendData = new byte[1024];
 byte[] messageBytes = new byte[1024];
Outline of main loop:
while (state < 3){
 receiveData = new byte[1024];
 sendData = new byte[1024];
 switch (state){

 case 0: // state 0: Wait for first connection
receivePacket = new DatagramPacket(receiveData, receiveData.length);

serverSocket.receive(receivePacket);

message= new String(receivePacket.getData());
If (message.equals("HELLO Red") || message.equals("HELLO Blue")
{}
IPAddress = receivePacket.getAddress();
IPAdress1 = IPAdress;

port = receivePacket.getPort();
port1 = port;

 //reply to first client
sendData = 100;

sendPacket = new DatagramPacket(sendData, sendData.length, IPAdress, port);

serverSocket.send(sendPacket);
 state = 1; //transition to state 1: wait for second client
 break;

 case 1: // state 1: wait for second client to connect
receivePacket = new DatagramPacket(receiveData, receiveData.length);

serverSocket.receive(receivePacket);

message= new String(receivePacket.getData());

IPAddress = receivePacket.getAddress();
IPAdress2 = IPAdress;

port = receivePacket.getPort();
port2 = port;

sendData = 200;
sendPacket = new DatagramPacket(sendData, sendData.length, IPAdress1, port1);
serverSocket.send(sendPacket);

sendPacket = new DatagramPacket(sendData, sendData.length, IPAdress2, port2);
serverSocket.send(sendPacket);

 state = 2; //transition to state 2: chat mode
 break;





 case 2: // state 2: Chat mode
 //receive message from one client

receivePacket = new DatagramPacket(receiveData, receiveData.length);
message = new String(recievePacket.getData());

 // check for Goodbye message
 if (message.length()>=7 && message.substring(0,7).equals("Goodbye")){
 state = 3;
 break;
 }
}
 //if not a Goodbye message, relay message to the other client
 IPAddress = receivePacket.getAddress();
 port = receivePacket.getPort();
 if ((port == port1) && (IPAddress.equals(IPAddress1))){
 IPAddress = IPAddress2;
 port = port2;
 }
 else{
 IPAddress = IPAddress1;
 port = port1;
 }
 …
 //stay in state 2
 break;
 } //end switch
 } //end while
 //send Goodbye messages to both clients
 //close the socket