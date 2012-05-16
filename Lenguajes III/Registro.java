import java.util.LinkedList;
import java.util.Iterator;

public class Registro extends Tipo {

    //@ invariant tipos != null;
    //@ invariant campos != null;
    private LinkedList tipos;
    private LinkedList campos;

    //@ requires t != null & c != null;
    public Registro(LinkedList t, LinkedList c) {
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

	if(!(t instanceof Registro))
	    return null;
	
	Registro r2 = (Registro) t;
	
	if(r2.getCampos().size() != campos.size())
	    return null;

	Iterator ica1 = campos.iterator();
	Iterator iti1 = tipos.iterator();
	
	Iterator ica2 = r2.getCampos().iterator();
	Iterator iti2 = r2.getTipos().iterator();

	String s1,s2;
	
	while(ica1.hasNext()) {
	    s1 = (String)ica1.next();
	    s2 = (String)ica2.next();

	    //@ assume s1 != null;
	    //@ assume s2 != null;
	    if(s1.compareTo(s2)!=0)
		return null;
      
	    if(((Tipo)iti1.next()).compare(((Tipo)iti2.next())) == null)
		return null;
	}

	return this;
    }

    public int tamano()
    {
        int entero=0;
        Tipo aux;
        Iterator it=tipos.iterator();
        while(it.hasNext())
        {
            aux=((Tipo)it.next()); 
            int modulo=aux.tamano()%4;  
            if(modulo==0)
                entero+=aux.tamano();
            else
                entero+=aux.tamano()+(4-modulo);
        }
        return entero;
    }
    
    public String toString() {
	return "struct";
    }	

}
