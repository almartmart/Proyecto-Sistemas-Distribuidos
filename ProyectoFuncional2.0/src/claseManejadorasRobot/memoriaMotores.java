package claseManejadorasRobot;

import SinBrazo.javaDuionoSinBrazo;

public class memoriaMotores
{
	/*
	 * 
	 * Para que el brazo fucionase, simplemente usariamos la clase
	 * javaDuino en vez de javaDuionoSinBrazo
	 * 
	 * */
	
	private int numMotores = 5;
	private int v [] = null;
	private javaDuiono j = null;
//	private javaDuionoSinBrazo j = null;
	
	
	public memoriaMotores()
	{
		j = new javaDuiono();
//		j = new javaDuionoSinBrazo();
		j.inicializarConexion();
		//v = new int [numMotores];
		v = new int [] {15,25,35,45,55};
	}
	
	public void moverMotor(String orden)
	{
		if(v != null && orden.startsWith("MOVE "))
		{
			String ordenes [] = orden.split(" ");
			
			int motor = Integer.parseInt(ordenes [1])-1;
			String upDown = ordenes[2];
			
			if(motor < numMotores && motor >= 0)
			{
				if(upDown.equals("UP")  && v[motor]%10 !=9)
				{
					v[motor]++;
					j.enviarDatos(v[motor]);
				}
				else if(upDown.equals("DOWN")  && v[motor]%10 !=0)
				{
					v[motor]--;
					j.enviarDatos(v[motor]);
				}
			}
			
		}
	}
}
