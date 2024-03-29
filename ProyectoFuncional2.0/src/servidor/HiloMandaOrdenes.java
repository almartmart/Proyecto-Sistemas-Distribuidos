package servidor;


import java.util.concurrent.BlockingQueue;

import claseManejadorasRobot.javaDuiono;
import claseManejadorasRobot.memoriaMotores;

public class HiloMandaOrdenes implements Runnable{
	private BlockingQueue<String> cola;
	private memoriaMotores m = null;
	
	public HiloMandaOrdenes(BlockingQueue<String> cola) {
		this.cola=cola;
		m = new memoriaMotores();
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized (cola) {
				try 
				{
					Thread.sleep(20);//para que le de tiempo al motor a mover la posicion
					m.moverMotor(this.cola.take());
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
	}

}
