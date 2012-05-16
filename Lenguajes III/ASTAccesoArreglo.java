public class ASTAccesoArreglo extends ASTAcceso {
    
    private ASTExpresion expr;
    
    public ASTAccesoArreglo() {
	hijo = null;
	expr = null;
    }

    public ASTAccesoArreglo(ASTAcceso h, ASTExpresion e) {
	hijo = h;
	expr = e;
    }

    public boolean isNull() {
  	return false;
    }

    public Tipo check(Tipo t) {

	if(t instanceof Arreglo) {
	    Arreglo a = (Arreglo) t;
	    return hijo.check(a.getSub());
	} 	
	else
	    return null;

    }

    public String printTree() {

	String m = new String("[]");
	if (hijo != null)
	    m = m.concat(hijo.printTree());

	return m;

    }

    public String codigoLVal(int prox_reg,int tamano)
    {
        String reg=P.nombresReg[(prox_reg +2) % P.N];
        String reg2=P.nombresReg[(prox_reg) % P.N];

        return hijo.codigoLVal(prox_reg, ?)+
               P.salvar(prox_reg+1)+
               expr.codigo(prox_reg+1)+
               P.restaurar(prox_reg+1)+
               P.linea("ori "+reg+", $0, "+tamano)+
               P.linea("mult "+expr.reg(prox_reg+1)+", "+reg)+
               P.linea("mflo "+expr.reg(prox_reg+1))+
               P.salvar(prox_reg+2)+
               P.linea("add "+this.reg(prox_reg)+", "+reg2+", "+expr.reg(prox_reg+1))+
               P.restaurar(prox_reg+2);
    }
}
