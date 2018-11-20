package Proyecto;

public class Ar {
	
//	        static Scanner miscaner = new Scanner (System.in);
//	        static int cantidad;
//	        static double precio, total;
//	        
//	        public static void main(String[] args)throws FileNotFoundException{
//	        		
//	                PrintStream DDescritor = new PrintStream("Resultados.java");
//	                
//	                System.out.print("Ahora introduce la cantidad del producto? ");
//	                cantidad = miscaner.nextInt();
//	                System.out.print("Ahora introduce el precio del producto? ");
//	                precio = miscaner.nextDouble();
//	                System.out.print("Ahora los resultados seran utgenerados en un archivo .txt");
//	                
//	                total = cantidad * precio;
//	                
//	                DDescritor.println("Cantidad\tPrecio");
//	                DDescritor.print(cantidad);
//	                DDescritor.print("\t");
//	                DDescritor.println(precio);
//	                DDescritor.println("En total tienes: "+total); 
//	        }
//	
//}
	public Ar() {
	}
	public static void main(String[] args) {
		ArbolRecur arbol=new ArbolRecur();
		Nodo2 n=new Nodo2("programa");
		arbol.raiz=n;
		Nodo2 n1=new Nodo2("hijo");
		arbol.InsertarRecursivo(n,n1.info , n.info);
		Nodo2 n2=new Nodo2("hijo1");
		arbol.InsertarRecursivo(n1,n2.info , n1.info);
		
		Nodo2 n3=new Nodo2("hijo1");
		arbol.InsertarRecursivo(n2,n3.info , n2.info);
		
		
		System.out.println("cc");
		
		//---------------------------------------------------------------------------------------	if(x=y)
		
		Nodo2 if1=new Nodo2("if");
		arbol.InsertarRecursivo(n,if1.info , n.info);
		
		
		Nodo2 v1=new Nodo2("x");
		arbol.InsertarRecursivo(if1,v1.info , if1.info);
		
		Nodo2 igual=new Nodo2("+");
		arbol.InsertarRecursivo(if1,igual.info , if1.info);
		
		Nodo2 v2=new Nodo2("y");
		arbol.InsertarRecursivo(if1,v2.info , if1.info);
		System.out.println(".................................");
		System.out.println(if1.getDato());
		arbol.verHijosRecursivo(if1);
	
		//----------------------------------------------------------------------------------------if(x)
		
		Nodo2 if2=new Nodo2("if");
		arbol.InsertarRecursivo(n,if2.info , n.info);
		
		
		Nodo2 vU=new Nodo2("x");
		arbol.InsertarRecursivo(if2,vU.info , if2.info);
		
		
		System.out.println("--------------------------------");
		System.out.println(if2.getDato());
		arbol.verHijosRecursivo(if2);
		
		//-----------------------------------------------------------------------------------------if(x==y)
		Nodo2 if3=new Nodo2("if");
		arbol.InsertarRecursivo(n,if3.info , n.info);
		
		
		Nodo2 vv1=new Nodo2("x");
		arbol.InsertarRecursivo(if3,vv1.info , if3.info);
		
		Nodo2 igualigual=new Nodo2("==");
		arbol.InsertarRecursivo(if3,igualigual.info , if3.info);
		
		Nodo2 vv2=new Nodo2("y");
		arbol.InsertarRecursivo(if3,vv2.info , if3.info);
		System.out.println("------------------------------");
		System.out.println(if3.getDato());
		arbol.verHijosRecursivo(if3);
		
		
		System.out.println("------------------------------");
		System.out.println(n.getDato());
		arbol.verHijosRecursivo(n);
		
		
	//	Nodo2 h=new Nodo2("if");
	//	arbol.InsertarRecursivo(n, h.info, n.getDato());
	//	Nodo2 h1=new Nodo2("int");
	//	arbol.InsertarRecursivo(n, h1.info, n.getDato());
	//	Nodo2 h2=new Nodo2("while");
	//	arbol.InsertarRecursivo(n, h2.info, n.getDato());
	//
	//	
	//	Nodo2 dec=new Nodo2("x");
	//	arbol.InsertarRecursivo(h1, dec.info, n.getDato());
	//	Nodo2 ounto=new Nodo2(";");
	//	arbol.InsertarRecursivo(dec, ounto.info, dec.getDato());
	//	
	//	System.out.println(arbol.raiz.info);
	//	//arbol.verHijosRecursivo(n);
	//	arbol.verHijosRecursivo(n);
	}

}
