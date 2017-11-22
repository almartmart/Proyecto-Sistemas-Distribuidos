package servidor;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import claseManejadorasRobot.javaDuiono;

public class Servidor {
	public static void main(String[] args) {
		System.out.println("Inicio de la actividad en el servidor");
		ServerSocket ss=null;
		Socket s=null;
		
		try {
			//Inicializamos el ServerSocket
			ss=new ServerSocket(6666);
			
			//Inicializamos el robot
			//javaDuiono j = new javaDuiono();
			//j.inicializarConexion();
			ExecutorService pool=Executors.newCachedThreadPool();
			BlockingQueue<String> colaMotor=new ArrayBlockingQueue<>(1000);
			
			//Inicializamos el hilo que manda ordenes al brazo
			
			Thread hi=new Thread(new HiloMandaOrdenes(colaMotor));
			hi.start();
			
			int i = 0;
			try {
				while(true) 
				{
					
					s=ss.accept();
					System.out.println("Cliente "+i+"conectado");
					pool.execute(new HiloCliente(s,colaMotor,i));
					i++;
					
				}
			}
			catch(IOException e) {
				System.out.println("Problema con el accept");
				e.printStackTrace();
			}
		}catch(IOException e1) {
			System.out.println("Problema con el ServerSocket");
			e1.printStackTrace();
		}
		
	}

	public static void cerrar(Closeable o) {
		try {
			if (o != null) {
				o.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
