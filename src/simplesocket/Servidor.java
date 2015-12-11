package simplesocket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	private final int puerto = 9000;
	private ServerSocket sc;
	private Socket so;

	public void initServer(){
		try{
			sc = new ServerSocket(puerto);
			while(true){
				System.out.println("Waiting for conecction:");
				so = sc.accept();
				new Socket2(so).start();
			}

		}catch(Exception e){
			System.out.println("Error: "+e.getMessage());
		}finally{
			try {
				sc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated catch block
		}

	}


	public class Socket2 extends Thread{
		private DataOutputStream salida;
		private String mensajeRecibido;
		private Socket so2;

		public Socket2(Socket so){
			this.so2 = so;
		}

		public void run(){
			try{
				int numRandom = (int) (Math.random()*10);
				BufferedReader entrada;
				System.out.println("New client");
				entrada = new BufferedReader(new InputStreamReader(so2.getInputStream()));
				salida = new DataOutputStream(so2.getOutputStream());
				salida.writeUTF("Conecction");

				do{
					salida.writeUTF("Text number");
					mensajeRecibido = entrada.readLine();
					salida.writeUTF(mensajeRecibido);
					if(mensajeRecibido.equals(""+numRandom)){
						salida.writeUTF("Success");
					}else{
						salida.writeUTF("Wrong");
					}
					if(mensajeRecibido.equals("e")){
						so2.close();
					}
				}while((!mensajeRecibido.equals(""+numRandom)));
				System.out.println("Exit room");
				System.out.println("Conecction close");

				salida.close();
				entrada.close();
				so2.close();

			}catch(Exception e){

			}
		}

	}

}
