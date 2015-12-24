//Brandon Fowler
//CSCD330
//Assignmnet 3 Web Server


import java.io.* ;
import java.net.* ;
import java.util.* ;

public final class WebServer
{
	//Main
	public static void main(String argv[]) throws Exception
	{
		int port = 6789;//Initialize port
		
		//Initialize Sockets
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		
		try 
		{
          serverSocket = new ServerSocket(port);//Listen
      } 
		catch (IOException e)
		{
          System.err.println("Could not listen on port: "+port);
          System.exit(1);
      }

		
		while (true) 
		{
						
         try 
			{
         	clientSocket = serverSocket.accept();//Accept Client
         } 
		   catch (IOException e) 
			{
            System.err.println("Accept failed.");
            System.exit(1);
         }
			
			//Proccess request
			HttpRequest request = new HttpRequest(clientSocket);
			Thread thread = new Thread(request);
			thread.start();
		}
	}//End Main
}

//Process Request
final class HttpRequest implements Runnable
{
	final static String CRLF = "\r\n";
	Socket socket;

	//Construct request
	public HttpRequest(Socket socket) throws Exception 
	{
		this.socket = socket;
	}

	public void run()
	{
		try 
		{
			processRequest();//Send for acctual processing
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

	private void processRequest() throws Exception
	{
		InputStream is = socket.getInputStream();//Get input data
		DataOutputStream os = new DataOutputStream(socket.getOutputStream());//Get output data
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));//Wrap imput data
		
		String requestLine = new String(br.readLine());
		
		System.out.println();
		System.out.println(requestLine);
		
		String headerLine = null;
		while ((headerLine = br.readLine()).length() != 0)//Go through headers 
		{
			System.out.println(headerLine);//Print headers
		}
		
		//File name processing
		StringTokenizer tokens = new StringTokenizer(requestLine);
		tokens.nextToken();  
		String fileName = tokens.nextToken();
		fileName = "." + fileName;
		
		FileInputStream fis = null;
		boolean fileExists = true;
		try 
		{
			fis = new FileInputStream(fileName);
		} 
		catch (FileNotFoundException e) 
		{
			fileExists = false;
		}
		
		//File checking and returning
		String statusLine = null;
		String contentTypeLine = null;
		String entityBody = null;
		if (fileExists)
	   {
			statusLine = "200 OK" + CRLF;
			contentTypeLine = "Content-type: " + contentType( fileName ) + CRLF;
		} 
		else 
		{
			statusLine = "404 NOT FOUND" + CRLF;
			contentTypeLine = "Content Not Found!" + CRLF;
			entityBody = "<HTML>" + "<HEAD><TITLE>Not Found</TITLE></HEAD>" +"<BODY>Not Found</BODY></HTML>";
		}
		
		os.writeBytes(statusLine);

		os.writeBytes(contentTypeLine);

		os.writeBytes(CRLF);
		
		if (fileExists) 
		{
   		 sendBytes(fis, os);
    		 fis.close();
    	} 
		else 
		{
    		os.writeBytes("File DNE: File Not Found!");
		}
		
		os.close();
		br.close();
		socket.close();
	}
	
	private static void sendBytes(FileInputStream fis, OutputStream os) throws Exception
	{
  	   byte[] buffer = new byte[1024];
  	   int bytes = 0;

   	while((bytes = fis.read(buffer)) != -1 ) 
		{
      	os.write(buffer, 0, bytes);
   	}
	}
	
	//File type checker
   private static String contentType(String fileName) 
	{
    	if(fileName.endsWith("htm") || fileName.endsWith("html")) 
		{
        return "text/html";
    	}
    	if(fileName.endsWith("jpeg") || fileName.endsWith("jpg")) 
		{
    		return "image/jpeg";
    	}
    	if(fileName.endsWith("gif")) 
		{
    		return "image/gif";
    	}
    	return "application/octet-stream";
   }
}