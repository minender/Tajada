public class ASTIdentificador extends ASTExpresion{
    private SymTable table;
    private ASTAcceso acceso;

    //@ requires v != null;
    public ASTIdentificador(String v, SymTable t, ASTAcceso a, Tipo s) {
	super(v, null, null);
	table = t;
	acceso = a;
	state = s;
    }

    public void setTable(SymTable t) {
  	table = t;
    }

    public void setAcceso(ASTAcceso a) {
  	acceso = a;
    }

    public SymTable getTable() {
  	return table;
    }

    public ASTAcceso getAcceso() {
  	return acceso;
    }

    public void update() {
	if(acceso != null && !acceso.isNull()) {
	    state = acceso.check(state);
	}
    }
    
    public String printTree() {
	String m = new String("IDENT( " + value + " )");
	return m;
    }

    public String codigoLVal(int prox_reg)
    {
        if(acceso==null || acceso.getHijo()==null)
        {
           if(this.esGlobal)
           {
              return P.linea("la "+this.reg(prox_reg)+", "+this.getValue()+"1");
           }
           else
           {
              return P.linea("ori "+this.reg(prox_reg)+", $0, "+this.table.getSym(this.getValue()).despl)+
                     P.linea("add "+this.reg(prox_reg)+", "+this.reg(prox_reg)+", "+"$fp");
           }
        }
        else
        {
           return "";
        }
    }

    public String codigo(int prox_reg)
    {
        if(acceso==null || acceso.getHijo()==null)
        {
           if(this.esGlobal)
           {
             return P.linea("lw "+this.reg(prox_reg)+", "+this.getValue()+"1");
           }
           else
           {
             return P.linea("ori "+this.reg(prox_reg)+", $0, "+this.table.getSym(this.getValue()).despl)+
                    P.linea("add "+this.reg(prox_reg)+", "+this.reg(prox_reg)+", "+"$fp")+
                    P.linea("lw "+this.reg(prox_reg)+", "+"0("+this.reg(prox_reg)+")");
           }
        }
        else
        {
           return "";
        }
    }

    //el identificador debe ser del tipo booleano
    public String codSaltoEtiq(String si, String no)
    {
         if(this.esGoal)
         {
            return P.linea("lw "+this.reg(0)+", "+this.getValue()+"1")+
                   P.linea("beq "+"$0, "+this.reg(0)+", "+no)+
                   P.linea("j "+si);
         }
         else
         {
            return P.linea("ori "+this.reg(0)+", $0, "+this.table.getSym(this.getValue()).despl)+
                   P.linea("add "+this.reg(0)+", "+this.reg(0)+", "+"$fp")+
                   P.linea("lw "+this.reg(0)+", "+"0("+this.reg(0)+")")+
                   P.linea("beq "+"$0, "+this.reg(0)+", "+no)+
                   P.linea("j "+si);
         }
    }
}
