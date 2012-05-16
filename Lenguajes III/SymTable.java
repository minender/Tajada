import java.util.*;

public class SymTable {
	
    //@ invariant hash != null;
    private Hashtable hash;
    private SymTable parent;
    private int tamano;
	
    public SymTable(SymTable p) {
	hash = new Hashtable();
	parent = p;
    }

    public SymTable getParent() {
	return parent;
    }

    public boolean isEmpty()
    {
        return hash.isEmpty();
    }

    //@ requires elem != null;
    public boolean insert(Sym elem, int profundidad) {
	if(profundidad >= 0) {
	    if(!existProfundidad(elem.getName(), profundidad)) {
		hash.put(elem.getName(), elem);
		return true;
	    }
	    else return false;
	}
	else {
	    if(!hash.containsKey(elem.getName())) {
		hash.put(elem.getName(), elem);
		return true;
	    }
	    else return false;
	}	
    }
    
    public boolean existProfundidad(String name, int profundidad) {
	
	Sym s = (Sym) hash.get(name);
	
	if(profundidad>=0) {
	    if(s != null)
		return true;
	    else {
		if(parent != null)
		    return parent.existProfundidad(name, profundidad-1);
		else
		    return false;
	    }
	}
	else {
	    return false;
	}  	
    }
    
    public void changeAsigned(String name) {
	
	SymVar s = (SymVar) hash.get(name);
	
	if(s != null)
	    s.setAsigned(true);
	else {
	    if(parent != null)
		parent.changeAsigned(name);
	}	
    }
    
    public Tipo exist(String name) {

	Sym s = (Sym) hash.get(name);

	if(s != null)
	    return s.getState();
	else {
	    if(parent != null)
		return parent.exist(name);
	    else
		return null;
	}	
    }
    
    public Sym getSym(String name) {
	Sym s = (Sym) hash.get(name);

	if(s != null)
	    return s;
	else {
	    if(parent != null)
		return parent.getSym(name);
	    else
		return null;
	}
    }

    public Enumeration keys()
    {
        Enumeration<String> keys=this.hash.keys();

        return keys;
    }

    public String codigo(String nombre)
    {
        String s="";
        int rest=this.tamano % 4;
        if(rest==0)
             s+=P.linea(nombre+": .space "+this.tamano);
        else
             s+=P.linea(nombre+": .space "+(this.tamano+(4-rest)));
        
        return s;
    }

    public void iniDesplazamientos()
    {

    }

    public String codigoFun()
    {
        String s="";
        Enumeration<String> keys=this.hash.keys();
        while(keys.hasMoreElements())
        {
             Sym aux=this.getSym(keys.nextElement());
             if(aux instanceof SymProc) 
                s+=aux.getName()+"f: \n"+
                   ((SymProc)aux).getBloque().codigoFun(0);
        }     //iniciar atributo esVarLocal
        return s;
    }
    
    public String codigoGlobalPrincipal(String nombre)
    {
        String s="";
        Enumeration<String> keys=this.hash.keys();
        while(keys.hasMoreElements())
        {
             Sym aux=this.getSym(keys.nextElement());
             if(!(aux instanceof SymProc) )
                s+=P.linea(aux.getName()+"1"+": .space "+aux.getState().tamano());
        }
        return s;
    }

    public String toString()
    {
        String s="";
        Enumeration<String> keys=this.hash.keys();
        while(keys.hasMoreElements())
        {
            s+=keys.nextElement()+", ";
        }
        return s;
    }
}
