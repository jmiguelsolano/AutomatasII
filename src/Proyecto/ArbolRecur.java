package Proyecto;


class ArbolRecur
{
	Nodo2 raiz;
	static ArbolRecur arbol=new ArbolRecur();
	static Nodo2 nodo=new Nodo2(null);
	public Nodo2 insertarRaiz(String dato)
	{
		raiz=new Nodo2(dato);
		return raiz;
	}
	public void verHijosRecursivo(Nodo2 nodo)
	{
		for(int i=0;i<nodo.nohijos;i++)
		{
			nodo.hijos[i].verNodo();
			verHijosRecursivo(nodo.hijos[i]);
		}
	}
	public void InsertarRecursivo(Nodo2 nodo,String dato,String padre)
	{
		Nodo2 nuevo=new Nodo2(dato);
		if(nodo.getDato().equals(padre))
		{
			nodo.aumentarHijo(nuevo);
		}
		else
		{
			for(int i =0;i<nodo.nohijos;i++)
			{
				if(nodo.hijos[i].getDato().equals(padre))
				{
					nodo.hijos[i].aumentarHijo(nuevo);
				}
				else
				{
					InsertarRecursivo(nodo.hijos[i],dato,padre);
				}
			}
		}
	}
	public static void Atrae(String valor,String padre)
	{
		arbol.InsertarRecursivo(nodo,valor,padre);
	}
	public static void Inicial(String valor)
	{
		nodo=arbol.insertarRaiz(valor);
	}
	public static Nodo2 VeNodo()
	{
		return nodo;
	}
	public static ArbolRecur VeArbol()
	{
		return arbol;
	}
}