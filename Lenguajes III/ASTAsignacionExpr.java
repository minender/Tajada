import java.util.LinkedList;

public class ASTAsignacionExpr extends ASTExpresion {

    //@ invariant id != null;
    //@ invariant right != null;
    
    private ASTIdentificador id;

    //@ requires i != null;
    //@ requires r != null;
    public ASTAsignacionExpr(ASTIdentificador i, ASTExpresion r, Tipo s) {
	super("=", null, r);
	id = i;
	state = s;
    }

    //@ requires i != null;
    public void setId(ASTIdentificador i) {
	id = i;
    }

    public ASTIdentificador getId() {
	return id;
    }

    public ASTAsignacion getASTAsignacion() {
	LinkedList l = new LinkedList();
	l.add(id);
	return new ASTAsignacion(l, right, state);
    }

    public void update() {}

    public String printTree() {
	String m = new String(value + "( " + id.printTree() + ", " + right.printTree() + " )");
	return m;
    }
 
    public String codigo(int prox_reg)
    {
        return "";
    }

    public String codSaltoEtiq(String si, String no)
    {
        return "";
    }
}
