package simplesocket;
import java.net.*;
import java.io.*;


public class Cliente extends Thread {


	final String HOST = "localhost";
	final int PUERTO=9000;
	Socket sc;
	DataOutputStream mensaje;
	DataInputStream entrada;

	public void initClient() /*ejecuta este metodo para correr el cliente */
	{
		try
		{
			sc = new Socket( HOST , PUERTO );
			mensaje = new DataOutputStream(sc.getOutputStream());
			mensaje.writeUTF("Hello world");
			sc.close();
		}catch(Exception e )

		{
			System.out.println("Error: "+e.getMessage());
		}

	}

}