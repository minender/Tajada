import java.util.Iterator;
import java.util.LinkedList;

public class ASTSwitch extends ASTInstruccion {

    //@ invariant cases != null;
    //@ invariant bloques != null;
    //@ invariant def != null;
    //@ invariant exp != null;

    private ASTExpresion exp;
    private LinkedList cases;
    private LinkedList bloques;
    private ASTBloque def;

    //@ requires e != null;    
    //@ requires ca != null;    
    //@ requires b != null;    
    //@ requires d != null;    
    public ASTSwitch(ASTExpresion e, LinkedList ca, LinkedList b, ASTBloque d) {
	super("switch");
	exp = e;
	cases = ca;
	bloques = b;
	def = d;
    }

    //@ requires e != null;    
    public void setExp(ASTExpresion e) {
	exp = e;
    }

    //@ requires b != null;    
    public void setCases(LinkedList b) {
	cases = b;
    }

    //@ requires b != null;    
    public void setBloques(LinkedList b) {
	bloques = b;
    }

    //@ requires a != null;    
    public void setDef(ASTBloque a) {
	def = a;
    }

    //@ ensures \result != null;
    public ASTExpresion getExp() {
	return exp;
    }

    //@ ensures \result != null;
    public LinkedList getCases() {
	return cases;
    }

    //@ ensures \result != null;
    public LinkedList getBloques() {
	return bloques;
    }

    //@ ensures \result != null;
    public ASTBloque getDef() {
	return def;
    }

    public void update() {

	Iterator it = bloques.iterator();
	boolean flag = false;
	ASTBloque b;

	while(it.hasNext()) {
	    b = (ASTBloque) it.next();
	    if(!b.getIreturn()) {
		flag = true;
		break;
	    }
	}

	if(!def.getIreturn())
	    flag = true;

	if(!flag)
	    ireturn = true;

	it = bloques.iterator();

	while(it.hasNext()) {
	    b = (ASTBloque) it.next();
	    if(!b.getIbreak())
		return;
	}

	if(!def.getIbreak())
	    return;

	ibreak = true;	
    }
  
    public String printTree() {

	Iterator itcases = cases.iterator();
	Iterator itbloques = bloques.iterator();

	String m = new String("SWITCH( " + exp.printTree() + ",\n");

	while(itcases.hasNext()) {
	    m = m.concat("CASE( " + ((ASTConst)itcases.next()).printTree() + ",\n" + ((ASTBloque)itbloques.next()).printTree() + " ),\n");	    
	}

	if (def != null) {
	    m = m.concat("DEFAULT( " + def.printTree() + " )");
	}
	else {
	    m = m.substring(0, m.length() - 2);
	}

	return m;

    }
}
