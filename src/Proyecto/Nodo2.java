package Proyecto;


class Nodo2
{
	String info;
	int nohijos;
	Nodo2 hijos[];
	Nodo2 hijosC[];

	public Nodo2(String dato)
	{
		info=dato;
		this.nohijos=0;
	}	
	public void copiarHijos()
	{
		hijosC=new Nodo2[nohijos+1];
		for(int i=0;i<this.nohijos;i++)
		{
			hijosC[i]=hijos[i];
		}
	}
	public void aumentarHijo(Nodo2 nodo)
	{
		copiarHijos();
		hijosC[this.nohijos]=nodo;
		hijos=hijosC;
		this.nohijos++;
	}
	public String getDato()
	{
		return info;
	}
	public void setDato(String dato)
	{
		info=dato;
	}
	public void verNodo()
	{
		System.out.println("{ "+info+" }");
		
	}
}
