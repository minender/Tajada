import java.util.*;
	
public class ASTBloque extends ASTInstruccion {

    private SymTable table;
    private LinkedList insts;
	    
    public ASTBloque( LinkedList i, SymTable t) {
	super("bloque");
	insts = i;
	table = t;
    }

    public void setTable(SymTable t) {
	table = t;
    }

    public void setInsts(LinkedList i) {
	insts = i;
    }

    //@ ensures \result != null;    
    public SymTable getTable() {
  	return table;
    }

    public LinkedList getInsts() {
  	return insts;
    }

    public void update(){}
    
    public String printTree() {

	Iterator it = insts.iterator();
	String ainst = new String("");
	while(it.hasNext()) {
	    ainst = ainst.concat(((ASTInstruccion)it.next()).printTree() + ",\n");
	}

	ainst = ainst.substring(0, ainst.length() - 2);
	String m = new String("BLOQUE( " + ainst + " )");
	return m;

    }

    public String codigo(String prox)
    {
        table.iniDesplazamientos();
        //empilar las variables locales
        Iterator it=insts.iterator();
        String s="";
        while(it.hasNext())
        {
            ASTInstruccion inst=(ASTInstruccion)it.next();
            if(it.hasNext())
            {
               String label=P.newLabel("instr");
               s+=inst.codigo(label)+label+": \n";
            }
            else
            {
               s+=inst.codigo(prox);
            }
        }
        return s;
    }

    public String codigoFun(int prox_reg)
    {
        table.iniDesplazamientos();
        String s=P.linea("sub $sp, $sp, 4")+
                 P.linea("sw $fp, 0($sp)")+
                 P.linea("or $fp, $0, $sp");

        Enumeration keys=table.keys();
        LinkedList list=new LinkedList();
        while(keys.hasMoreElements())
        {
            // if(aux no es parametro)
             //{
                Sym aux=table.getSym((String)keys.nextElement());
                s+=P.linea("sub $sp, $sp, "+aux.getState().tamano());
                list.addFirst(aux);
             //}
        }

        for(int k=0;k<prox_reg;k++)
        {
           s+=P.linea("sub $sp, $sp, 4")+
              P.linea("sw "+P.nombresReg[k]+", 0($sp)");
        }

        Iterator it=insts.iterator();
        while(it.hasNext())
        {
            ASTInstruccion inst=(ASTInstruccion)it.next();
            if(it.hasNext())
            {
               String label=P.newLabel("instr");
               s+=inst.codigo(label)+label+": \n";
            }
            else
            {
               String label2=P.newLabel("finfun");
               s+=inst.codigo(label2)+
                  label2+": \n";
            }
        }
        for(int k=prox_reg;0<k;k--)
        {
            s+=P.linea("lw "+P.nombresReg[k-1]+", 0($sp)")+
               P.linea("add $sp, $sp, 4");
        }

        Iterator it2=list.iterator();
        while(it2.hasNext())
        {
            s+=P.linea("add $sp, $sp, "+((Sym)it2.next()).getState().tamano());
        }
        s+=P.linea("lw $fp, 0($sp)")+
           P.linea("add $sp, $sp, 4")+
           P.linea("jr $ra");
        return s;
    }
}
