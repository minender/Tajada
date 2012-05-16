import java.util.Iterator;
import java.util.LinkedList;

public class ASTIf extends ASTInstruccion {

    //@ invariant cond != null;
    //@ invariant bloques != null;
    //@ invariant els != null;

    private LinkedList cond;
    private LinkedList bloques;
    private ASTBloque els;

    //@ requires co != null;
    //@ requires b != null;
    //@ requires e != null;
    public ASTIf(LinkedList co, LinkedList b, ASTBloque e) {
	super("if");
	cond = co;
	bloques = b;
	els =e;
    }

    //@ requires c != null;    
    public void setCond(LinkedList c) {
	cond =c;
    }

    //@ requires b != null;    
    public void setBloques(LinkedList b) {
	bloques =b;
    }

    //@ requires a != null;    
    public void setEls(ASTBloque a) {
	els = a;
    }

    public LinkedList getCond() {
	return cond;
    }

    public LinkedList getBloques() {
  	return bloques;
    }

    public ASTBloque getEls() {
  	return els;
    }

    public void update() {
	Iterator it = bloques.iterator();
	boolean flag = false;
	ASTBloque b;

	if(els == null)
	    return;

	while(it.hasNext()) {
	    b = (ASTBloque) it.next();
	    if(!b.getIreturn()) {
		flag = true;
		break;
	    }
	}

	if(!flag)
	    ireturn = true;

	it = bloques.iterator();

	while(it.hasNext()) {
	    b = (ASTBloque) it.next();
	    if(!b.getIbreak())
	        return;
	}
	ibreak = true;	
    }
  
    public String printTree() {

	Iterator itcond = cond.iterator();
	Iterator itbloques = bloques.iterator();

	String m = new String("IF( " + ((ASTExpresion)cond.getFirst()).printTree() + ",\n" + ((ASTBloque)bloques.getFirst()).printTree() + ",\n");
	itcond.next();
	itbloques.next();

	while(itcond.hasNext()) {
	    m = m.concat("ELIF( " + ((ASTExpresion)itcond.next()).printTree() + ",\n" + ((ASTBloque)itbloques.next()).printTree() + " ),\n");	    
	}

	if (els != null) {
	    m = m.concat("ELSE( " + els.printTree() + " )");
	}
	else {
	    m = m.substring(0, m.length() - 2);
            m = m.concat(" )");
	}

	return m;

    }

    public String codigo(String prox)
    {
        Iterator itcond = cond.iterator();
	Iterator itbloques = bloques.iterator();
        String s="";
        String s1="";
        String si=P.newLabel("si");

        ASTExpresion exp=(ASTExpresion)cond.getFirst();
        ASTBloque bloq=(ASTBloque)bloques.getFirst();
        itcond.next();
        itbloques.next();

        if(els==null && !(itcond.hasNext()))
        {
            s=exp.codSaltoEtiq(si,prox)+
               si+": \n"+bloq.codigo(prox);
        }
        else
        {
            String no=P.newLabel("sino");
            s=exp.codSaltoEtiq(si,no)+
               si+": \n"+bloq.codigo(prox)+
               P.linea("j "+prox)+
               no+": \n";
        }

        while(itcond.hasNext())
        {
            exp=(ASTExpresion)itcond.next();
            bloq=(ASTBloque)itbloques.next();
            si=P.newLabel("si");
            if(!(itcond.hasNext()) || els==null)
            {
                s+=exp.codSaltoEtiq(si,prox)+
                   si+": \n"+bloq.codigo(prox);
            }
            else
            {
                String no=P.newLabel("sino");
                s+=exp.codSaltoEtiq(si,no)+
                   si+": \n"+bloq.codigo(prox)+
                   P.linea("j "+prox)+
                   no+": \n";
            }
        }

        if(els != null)
        {
           s+=els.codigo(prox);
        }

        return s;
    }
}
