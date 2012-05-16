import java.util.LinkedList;

public class SymProc extends Sym {

    //@ invariant in != null;
    //@ invariant bloque != null;    

    private LinkedList in;
    private ASTBloque bloque;
  
    //@ requires i != null;
    //@ requires b != null;    
    public SymProc(String n, Tipo s, LinkedList i, ASTBloque b) {
	super(n,s);
	in = i;
	bloque = b;
    }

    //@ requires i != null;
    public void setIn(LinkedList i) {
  	in = i;
    }
  
    //@ requires b != null;    
    public void setBloque(ASTBloque b) {
	bloque = b;
    }
      
    //@ ensures \result != null;    
    public SymTable getTable() {
	return bloque.getTable();
    }
    
    //@ ensures \result != null;
    public LinkedList getIn() {
	return in;
    }
  
    //@ ensures \result != null;
    public ASTBloque getBloque() {
	return bloque;
    }    
}
