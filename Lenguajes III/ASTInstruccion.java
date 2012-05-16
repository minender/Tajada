public class ASTInstruccion {

    protected boolean ireturn;
    protected boolean ibreak;
    protected boolean canCheck;
    protected String name;

    public ASTInstruccion(String n) {
	name = n;
	ireturn = false;
	ibreak = false;
	canCheck = true;
    }

    public void setIreturn(boolean i) {
  	ireturn = i;
    }
    public void setIbreak(boolean i) {
	ibreak = i;
    }

    public void setCanCheck(boolean c) {
  	canCheck = c;
    }

    public boolean getIreturn() {
	return ireturn;
    }

    public boolean getIbreak() {
	return ibreak;
    }

    public boolean getCanCheck() {
  	return canCheck;
    }


    public String getName() {
	return name;
    }

    public String printTree() {
  	return name;
    }

    public String codigo(String prox)
    {
        return P.linea("lw $fp, 0($sp)")+
               P.linea("add $sp, $sp, 4")+
               P.linea("jr $ra");
    }

    public void update() {}

}
