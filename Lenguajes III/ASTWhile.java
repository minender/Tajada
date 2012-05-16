public class ASTWhile extends ASTInstruccion {

    //@ invariant cond != null;
    //@ invariant bloque != null;

    private ASTExpresion cond;
    private ASTBloque bloque;

    //@ requires co != null;
    //@ requires b != null;
    public ASTWhile(ASTExpresion co, ASTBloque b) {
	super("while");
	cond = co;
	bloque = b;
    }

    //@ requires c != null;
    public void setCond(ASTExpresion c) {
  	cond = c;
    }

    //@ requires b != null;
    public void setBloque(ASTBloque b) {
	bloque = b;
    }

    public ASTExpresion getCond() {
	return cond;
    }

    public ASTBloque getBloque() {
	return bloque;
    }

    public void update() {

	if(bloque.getIreturn())
	    ireturn = true;

	if(bloque.getIreturn())
	    ibreak = true;	
    }

    public String printTree() {
	String m = new String("WHILE( " + cond.printTree() + ",\n" + bloque.printTree() + " )");
	return m;
    }

    public String codigo(String prox)
    {
        String si=P.newLabel("bloque");
        String con=P.newLabel("cond");

        return con+": \n"+cond.codSaltoEtiq(si,prox)+si+": \n"+bloque.codigo(con)+
               P.linea("j "+con);
    }

}
