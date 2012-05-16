public class ASTConst extends ASTExpresion {
	
    private int caseInt;
    private float caseFloat;
    private String caseChar;
    private boolean caseBool;

    public ASTConst(int ca) {
	super("constante",null,null);
	state = new Basico(1);
	caseInt = ca;
    }

    public ASTConst(float f) {
	super("constante",null,null);
	state = new Basico(2);
	caseFloat = f;
    }

    public ASTConst(boolean b) {
	super("constante",null,null);
	state = new Basico(3);
	caseBool = b;
    }       

    public ASTConst(String ch) {
	super("constante",null,null);
	state = new Basico(4);
	caseChar = ch;
    }

    public void setCaseInt(int i) {
	caseInt = i;
    }

    public void setCaseFloat(float f) {
	caseFloat = f;
    }

    public void setCaseChar(String c) {
	caseChar = c;
    }

    public void setCaseBool(boolean b) {
	caseBool = b;
    }

    public int getCaseInt() {
	return caseInt;
    }

    public float getCaseFloat() {
	return caseFloat;
    }

    public String getCaseChar() {
	return caseChar;
    }

    public boolean getCaseBool() {
	return caseBool;
    }

    public void update() {};

    public String printTree() {
	String m = new String();
      
	switch (((Basico)state).getNBasico()) {
	case 1:	    
	    Integer x = new Integer(caseInt);
	    m = new String("CONST( " + x.toString() + " )");
	    return m;
	case 2:
	    Float y = new Float(caseFloat);
	    m = new String("CONST( " + y.toString() + " )");
	    return m;
	case 3:
	    Boolean z = new Boolean(caseBool);
	    m = new String("CONST( " + z.toString() + " )");
	    return m;
	case 4:	    
	    m = new String("CONST( " + caseChar + " )");
	    return m;
	}

	return m;
    }

    public String codigo(int prox_reg)
    {
       switch (((Basico)state).getNBasico())
       {
          case 1:
              return P.linea("ori "+this.reg(prox_reg)+", $0, "+caseInt);
          case 2:
              return P.linea("ori "+this.reg(prox_reg)+", $0, "+caseFloat);//no estoy seguro
          case 3:
              if(caseBool==true)
              {
                   return P.linea("ori "+this.reg(prox_reg)+ ", $0, 1");
              }
              else
              {
                   return P.linea("ori "+this.reg(prox_reg)+ ", $0, 0");
              }
          default:
              return P.linea("ori "+this.reg(prox_reg)+ ", $0, "+(int)caseChar.charAt(0));
       }
    }

    public String codSaltoEtiq(String si,String no)
    {
       String s="";

       switch (((Basico)state).getNBasico())
       {
          case 1:
              return s;
          case 2:
              return s;
          case 3:
              if(caseBool==true)
              {
                 s+=P.linea("j "+si);
              }
              else
              {
                 s+=P.linea("j "+no);
              }
              return s;
          default:
              return s;
       }
    }
}
