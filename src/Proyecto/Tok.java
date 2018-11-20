package Proyecto;

public class Tok {
	String tipo, token;
	int nV;
	public Tok(String tipo ,String token , int  nV) {
		this.tipo=tipo;
		this.token=token;
		this.nV=nV;
	}
	public String toString (){
		return"tipo "+tipo+" token "+token+" numero "+nV;
	}
	
}
