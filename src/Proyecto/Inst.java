package Proyecto;
public class Inst{
	int var,ind;
	String inst,token;
	Inst(int ind ,String inst,int var,String token ){
		this.ind=ind;
		this.inst=inst;
		this.var=var;
		this.token=token;
	}
	public String toString (){
		return ind+"\t"+inst+""+var+"\t//"+ token;
	}
}
