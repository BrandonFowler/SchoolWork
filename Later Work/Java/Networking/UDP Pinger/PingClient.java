//Brandon Fowler
//CSCD 330
//HomeWork 4(UDP Pinger)
//PingClient



import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

/*
 PingClient Class
*/
public class PingClient
{

	public static void main(String[] args) throws Exception
	{
		// Get argument.
		if (args.length != 2)
	   {
			System.out.println("Required arguments: host port");
			return;
		}

		String ServerName =args[0];	
		int port = Integer.parseInt(args[1]);


		// Create a datagram socket 
		DatagramSocket socket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName(ServerName);
		

	
		for(int i=0;i<10;i++)
		{

			// Create a datagram packet

			long SendTime = System.currentTimeMillis();
			String Message = "Ping "+ i + " " + SendTime + "\n";
			DatagramPacket request = new DatagramPacket(Message.getBytes(), Message.length(),IPAddress,port );
			socket.send(request);
			DatagramPacket reply = new DatagramPacket(new byte[1024], 1024);

			socket.setSoTimeout(1000);

			try
			{
				socket.receive(reply);
			}
			catch(IOException E)
			{

			}

			Thread.sleep(1000);

		}


	}

	/*
	  Print ping data
	*/
	private static void printData(DatagramPacket request) throws Exception
	{
		// Obtain ref to bytes.
		byte[] buf = request.getData();

		// Wrap the bytes
		ByteArrayInputStream bais = new ByteArrayInputStream(buf);

		InputStreamReader isr = new InputStreamReader(bais);

		BufferedReader br = new BufferedReader(isr);

		String line = br.readLine();

		// Print data
		System.out.println("Received from " + request.getAddress().getHostAddress() + ": " + new String(line) );
	}
}