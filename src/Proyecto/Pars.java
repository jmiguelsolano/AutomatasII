package Proyecto;
import java.util.StringTokenizer;
import java.util.Vector;
public class Pars {
	String mensaje,linea,auxiliar,auxiliar2,estructura,error;
	boolean valida=true;
	boolean dec=true;
	String x,g ;
	Vector<String> table=new Vector<String>();
	Vector<String> token=new Vector<String>();
	Vector<Tok> tokin=new Vector<Tok>();
	StringTokenizer tk ,tk2;
	
	int c,z=-1,ins=0 ,xero;
	Tabla tab;
	ArbolRecur arbol=new ArbolRecur();
	Nodo2 n;
	Nodo2 declar;
	Nodo2 if3;
	//{
	int coPi=0;
	String pila[] ={"iload_","iconstant_","iadd","istore_","ifne","if_acmpne ","if_icmple ","if_icmplt","goto "};
	Vector<Inst> instrucciones =new Vector<Inst>();
	//}
	public Pars(String g) {
		c =0;
		tk=new StringTokenizer(g,"\n");
		while(tk.hasMoreTokens()&&valida){
			linea=tk.nextToken();
			tk2=new StringTokenizer(linea," ");
			n=new Nodo2("programa");
			arbol.raiz=n;
			programa();
		}
	}
	private void programa() {
		next();
		if(x.equals("<EOF>")){
			eof();
			if3=new Nodo2(x);
			arbol.InsertarRecursivo(arbol.raiz,if3.info , arbol.raiz.info);
			valida=true;
		}
		else
		if((x.equals("int")||x.equals("boolean"))&&dec==true){
			if3=new Nodo2(x);
			arbol.InsertarRecursivo(n,if3.info ,n.info );
			String tipo=x;		
			vDec();			
			next();iden();
			String toke=x;
			Tok tokenZ=new Tok (tipo,toke,z++);
			Inst  inst=new Inst(coPi++,pila[1],z,toke);
			instrucciones.add(inst);
			tokin.add(tokenZ);
			tokenZ=new Tok (tipo,toke,z);
			inst=new Inst(coPi++,pila[3],z,toke);
			
			instrucciones.add(inst);
		
			next();punto();
		}else
		
		if(x.equals("if")||x.equals("while")||esVariable()){
			if3=new Nodo2(x);
			arbol.InsertarRecursivo(n,if3.info ,n.info );
			dec=false;
			statement();			
		}
		else valida=false;			
	}	
	//-----------------------------------------------------STATEMENT
	String ayuda;
	void statement(){
		
		////System.out.println("Estructura=========="+estructura);
		if(x.equals("if")){
			xero=coPi;
			estructura=x;
			sIf();next();abre();next();		
			expression();
		}else
			if(x.equals("while")){
				xero=coPi;
				estructura=x;
				whil();next();abre();next();
				error= x;
				expression();	
			}else
		if(token.contains(x)){
			int as =load(x);
			ayuda=x;
			next();
			if(x.equals("=")){
				igual();
				next();
				int aas =load(x);
				String ayudax=x;
				Inst  inst=new Inst(coPi++,pila[0],aas+2,ayudax);
				instrucciones.add(inst);
				
			    inst=new Inst(coPi++,pila[3],as,ayuda);
				instrucciones.add(inst);
				auxiliar2=x;
				
				if(token.contains(x)||esVariable()){
					if(tipo2(auxiliar,auxiliar2)){
					next();
					if(estructura.equals("while")){
						for ( int i= 0; i>=0; i=i-1) { 
							//lo que sea 
							}
					}
					if(x.equals(";")){
						 
						  if(estructura.equals("while")){
							  inst=new Inst(coPi++,pila[8],xero,"");
							  instrucciones.add(inst);
							} 
			
						punto();
						//instrucciones.lastElement().ind;
					}else valida=false;
				
				}else{valida=false; mensaje="Not the same type of variable"/**/;}
				}else {valida=false; mensaje="Variable not in scope";}
				
			}
		}else { valida=false;valida=false; mensaje="Variable not in scope";}
	}
	//------------------------------------------------EXPRESSION--------------------	
	int ayuda2;
	void expression(){
		if(token.contains(x)||esVariable()){
		
			auxiliar="";
			auxiliar=x;	
			
		//	//System.out.println("----------------------"+x);
			ayuda2=(load(auxiliar));
			next();	
			
			if(x.equals(")")){
				
				//System.out.println("auxiliar ------"+auxiliar);
				if(tipo(auxiliar,"boolean")){
//					//System.out.println("-:_:_:_:::_:_:_:__::_:_:");
//					
//					valida=false;
//					mensaje="Expected boolean expression";
//					linea=null;
//					g=null;
//					
//					if(!daval()){
//						next();
//						valida=false;
//						mensaje="Expected boolean expression";
//					}
//				}else{
//					
//				}
////				
				//{ Código intermedio
				Inst  inst=new Inst(coPi++,pila[0],ayuda2+1,auxiliar);
				instrucciones.add(inst);
				//}
				int t;
				if(estructura.equals("while")){
					t=coPi+6;
				}else t=coPi+5;
				inst=new Inst(coPi++,pila[6],t,/**/estructura);
				coPi=coPi+2;
				instrucciones.add(inst);
				
				c();next();
				auxiliar=x;
				if(x.equals("if")||x.equals("while")){
					statement();
				}else{
					if(token.contains(x)||esVariable()){
						
						if(tk.hasMoreTokens()){
							
							
							statement();
						}else{ valida=false;mensaje="Variable not in scope";}
					}else {valida=false;mensaje ="Variable not in scope";}
				}
				
				}
				
			}else if(x.equals("==")){
				//{ Código intermedio
				Inst  inst=new Inst(coPi++,pila[0],ayuda2,auxiliar);
				instrucciones.add(inst);
				//}
				igualIgual();
				next();
				//{ Código intermedio
				inst=new Inst(coPi++,pila[0],load(x),x);
				instrucciones.add(inst);
				//}
				if(tipo2(auxiliar,x)){
					
				}
				int t;
				if(estructura.equals("while")){
					t=coPi+6;
				}else t=coPi+5;
				
				inst=new Inst(coPi++,pila[5],t,/**/estructura);
				coPi=coPi+2;
				instrucciones.add(inst);
				auxiliar2=x;
				if(tipo2(auxiliar,auxiliar2)){
				if(token.contains(x)||esVariable()){
					next();
					c();
					next();
					if(x.equals("if")||x.equals("while")){
						
						statement();
					}else{
						if(token.contains(x)||esVariable()){
							if(tk.hasMoreTokens()){
								statement();
							}else valida=false;
						}else valida=false;
					}
					}else valida=false;
			}else{
				valida=false; mensaje="Not the same type of variable";
			} 
				}else
			if(x.equals("+")){
				mas();
				next();
				auxiliar2=x;
				if(tipo2(auxiliar,auxiliar2)){
					if(token.contains(x)||esVariable()){
						next();
						c();next();
						if(x.equals("if")||x.equals("while")){
							statement();
						}
						else
						if(token.contains(x)||esVariable()){
							if(tk.hasMoreTokens()){
								statement();
							}else valida=false;
						}else valida=false;
					}else{ 
						valida=false;
						mensaje="Operand expected";}
				}else{valida=false; mensaje="Not the same type of variable";}
			}}else{ valida=false; mensaje ="Variable not in scope";}
	}	
	void igual(){
		if(x.equals("=")){
		}
	}
	void mas(){
		if(x.equals("+")){
		}else
			valida=false;
	}
	void igualIgual(){
		if(x.equals("==")){
		}
	}
	void other(){
		if(x.equals("|")){
		}
	}
	void whil(){
		if(x.equals("while")){ 
		}
	}
	void sIf(){
		if (x.equals("if")) {
		}else valida=false;
	}
	void abre(){
		if (x.equals("(")) {
		}else valida=false;
	}
	void c(){
		if (x.equals(")")) {
		}
	}
	void eof(){
		if(x.equals("<EOF>")){
			if(tk.hasMoreTokens()){
				valida=false;
			}else if(daval()){
				//System.out.println("-------------------------------------------------------------------------------------------------------LLena la tabla");
				tab=new Tabla(table,token);
				for (int i = 0; i < instrucciones.size(); i++) {
					//System.out.println(instrucciones.elementAt(i).toString());
				}
				@SuppressWarnings("unused")
				Bytecode b=new Bytecode(instrucciones);
			}
		}  			
	}
	//----------------------------Métodos finales, no modificar--------------------------------------------------------------------------
	void next(){
			c++;
			x=tk2.nextToken();
		 	//System.out.println((c)+"\t-- "+x);
	}
	
	public boolean daval(){
		return valida;
	}
	String getTok(){
		return x;
	}
	String getMensaje(){
		return mensaje;
	}
	//-----------------------------------------------------------------------------------------Declaración de variables
		void vDec(){//TIPO DE DATO 
			if((x.equals("int")||x.equals("boolean"))&&tk2.hasMoreTokens()){		
				table.add("variable");
				token.add(x);
			}else
				valida=false;
		}
		boolean esVariable(){//ES VARIABLE
			int aux=0;
			boolean resp=false;
			for (int i = 0; i < token.size(); i++) {
				if(token.elementAt(i).toString().equals(x)){
					aux=i;
				}
			}
			if(table.elementAt(aux).toString().equals("Identifier")){
				resp=true;
			}
			if(!resp)
				mensaje="Variable not in scope";
			return resp;
		}
		void iden(){//CORROBORAR SI EL IDENTIFICADOR ES VÁLIDO
			if(Character.isLetter(x.charAt(0))&&tk2.hasMoreTokens()){
				for (int i = 0; i < x.length(); i++) {
					if(Character.isDigit(x.charAt(i))||Character.isLetter(x.charAt(i))){
					}
					else{
						i=x.length()+1;
						valida=false;
					}
				}
			}
			table.add("Identifier");
			token.add(x);
			}
		void punto(){//PUNTO Y COMA 
			if(x.equals(";")){		
			}else valida=false;
		}
	boolean tipo(String auxiliar,String tipo){
		boolean boo = false;
		for (int i = 0; i < tokin.size() ; i++) {
			if(auxiliar.equals(tokin.elementAt(i).token)&&(tipo.equals(tokin.elementAt(i).tipo))){
				boo=true;
				i=tokin.size()+1;
			}
		}
		if(!boo){
			mensaje="Expected boolean expression";
			valida=false;
		
		}
		return boo;
	}
	boolean tipo2(String auxiliar,String auxiliar2){
		String a = "",s = "";
		boolean boo = false;
		for (int i = 0; i < tokin.size() ; i++) {
			if(auxiliar.equals(tokin.elementAt(i).token)){
				a=tokin.elementAt(i).tipo;
			}
			if(auxiliar2.equals(tokin.elementAt(i).token)){
				s=tokin.elementAt(i).tipo;
			}	
		}
		if(a.equals(s)){
			boo=true;
		}
		return boo;
	}
	int load(String auxiliar){
		int numero = 0 ;
		for (int i = 0; i < tokin.size(); i++) {
			if(auxiliar.equals(tokin.elementAt(i).token)){
				numero=tokin.elementAt(i).nV;
				i=tokin.size()+1;
			//	//System.out.println("se encontro esta mierda");
			}
		}
		return numero;
	}

}
