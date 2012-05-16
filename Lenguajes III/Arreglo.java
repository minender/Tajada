public class Arreglo extends Tipo {

    //@ invariant sub != null;
    private ASTExpresion size;
    private Tipo sub;

    //@ requires s != null;
    public Arreglo(ASTExpresion si, Tipo s) {
	super();
	size = si;
	sub = s;
    }

    public void setSize(ASTExpresion s) {
  	size = s;
    }

    //@ requires s != null;
    public void setSub(Tipo s) {
	sub = s;
    }

    public ASTExpresion getSize() {
	return size;
    }

    public Tipo getSub() {
	return sub;
    }

    public Tipo aritmetica() {
	return null;
    }

    public Tipo aritmetica(Tipo t) {
	return null;
    }

    public Tipo compare() {
	return null;
    }

    public Tipo compare(Tipo t) {
	return null;
    }

    public Tipo asign(Tipo t) {
	
	if(!(t instanceof Arreglo))
	    return null;
	
	Arreglo a2 = (Arreglo) t;
	
	if(sub.compare(a2.getSub()) == null)
	    return null;
	
	return this;
	
    }

    public int tamano()
    {
        if(size instanceof ASTConst)
        {
           return sub.tamano()*((ASTConst)size).getCaseInt();
        }
        else
        {
           return 0;
        }
    }

    public String toString() {
	return sub+"[]";
    }	
}
