public abstract class ASTExpresion {

    //@ invariant value != null;
    protected String value;
    protected Tipo state;
    protected ASTExpresion left;
    protected ASTExpresion right;
    protected boolean canCheck;

    //@ requires v != null;
    public ASTExpresion(String v, ASTExpresion l, ASTExpresion r) {
	value = v;
	state = null;
	left = l;
	right = r;
	canCheck = true;
    }

    public void setState(Tipo t) {
	state = t;
    }

    //@ requires v != null;
    public void setValue(String v) {
	value = v;
    }

    public void setLeft(ASTExpresion l) {
	left = l;
    }

    public void setRight(ASTExpresion r) {
	right = r;
    }

    public void setCanCheck(boolean c) {
	canCheck = c;
    }

    public Tipo getState() {
	return state;
    }

    public String getValue() {
	return value;
    }

    public ASTExpresion getLeft() {
	return left;
    }

    public ASTExpresion getRight() {
	return right;
    }

    public boolean getCanCheck() {
	return canCheck;
    }

    public boolean check() {
	if(canCheck)
	    return state != null;
	else 
	    return true;
    }

    public String reg(int prox_reg)
  
  {
    
    return P.nombresReg[prox_reg % P.N];
  
  }



    public abstract void update();
    public abstract String printTree();
    public abstract String codigo(int prox_reg);
    public abstract String codSaltoEtiq(String si,String no);
	
}
