import java.util.Iterator;
import java.util.LinkedList;

public class Union extends Tipo {

    //@ invariant tipos != null;
    //@ invariant campos != null;
    private LinkedList tipos;
    private LinkedList campos;

    //@ requires t != null & c != null;	
    public Union(LinkedList t, LinkedList c) {
	super();
	tipos = t;
	campos = c;
    }

    //@ requires c != null;
    public void setCampos(LinkedList c) {
	campos = c;
    }

    //@ requires t != null;
    public void setTipos(LinkedList t) {
	tipos = t;
    }
	
    //@ ensures \result != null;
    public LinkedList getCampos() {
	return campos;
    }
	
    //@ ensures \result != null;
    public LinkedList getTipos() {
	return tipos;
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

	if(!(t instanceof Union))
	    return null;

	Union u2 = (Union) t;
		
	if(u2.getCampos().size() != campos.size())
	    return null;
		
	Iterator ica1 = campos.iterator();
	Iterator iti1 = tipos.iterator();
		
	Iterator ica2 = u2.getCampos().iterator();
	Iterator iti2 = u2.getTipos().iterator();

	String s1,s2;
		
	while(ica1.hasNext()) {
	    s1 = (String)ica1.next();
	    s2 = (String)ica2.next();
	    
	    //@ assume s1 != null;
	    //@ assume s2 != null;
	    
	    if(s1.compareTo(s2)!=0)
		return null;
	    
	    if(((Tipo)iti1.next()).compare(((Tipo)iti2.next()))==null)
		return null;
	}
	
	return this;
	
    }

    public int tamano()
    {
        Iterator it=tipos.iterator();
        int maximo=0;
        Tipo t;
        while(it.hasNext())
        {
            t=(Tipo)it.next();
            if(t.tamano()>maximo)
               maximo=t.tamano();
        }
        return maximo;
    }
    
    public String toString() {
	return "union";
    }	
	
}
