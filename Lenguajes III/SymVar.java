
public class SymVar extends Sym {

    private boolean isConst;
    private boolean asigned;
    public int despl;
	
    public SymVar(String n, Tipo s, boolean i) {
	super(n,s);
	isConst = i;
	asigned = false;
    }

    public void setIsConst(boolean i) {
	isConst = i;
    }

    public void setAsigned(boolean a) {
	asigned = a;
    }

    public boolean getIsConst() {
	return isConst;
    }

    public boolean getAsigned() {
	return asigned;
    }
}
