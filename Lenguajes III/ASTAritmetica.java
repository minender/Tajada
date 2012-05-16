public class ASTAritmetica extends ASTExpresion {
	
    //@ invariant left != null;

    //@ requires v != null;
    //@ requires l != null;
    public ASTAritmetica(String v, ASTExpresion l, ASTExpresion r) {
  	super(v, l, r);
    }

    public void update() {

	if(!left.getCanCheck()) {
	    canCheck = false;
	    return;
	}

	Tipo lstate = left.getState();

	if(right!=null) {

	    if(!right.getCanCheck()) {
		canCheck = false;
		return;
	    }
      
	    Tipo rstate = right.getState();
      
	    if(!(lstate instanceof Basico) || !(rstate instanceof Basico)) {
		state = null;
		return;
	    }
      
	    state = lstate.aritmetica(rstate);

	}
	else {
	    if(!(lstate instanceof Basico)) {
		state = null;
		return;
	    }
	    state = lstate.aritmetica();
	}

    }

    public String printTree() {	
	if (right != null) {
	    String m = new String(value + "( " + left.printTree() + ", " + right.printTree() + " )");
	    return m;	    
	}
	else 
	    return "";
    }

    public String codSaltoEtiq(String si,String no)
    {
          return "";
    }

    public String codigo(int prox_reg)
    {
        String reg=left.reg(prox_reg);

	switch(value.charAt(0))
        {
	    case '+':

                return left.codigo(prox_reg)+
                       P.salvar(prox_reg+1) +
                       right.codigo(prox_reg +1)+
                       P.linea("add "+reg+", "+reg+", "+right.reg(prox_reg +1))+
                       P.restaurar(prox_reg+1);
            case '*':

                return left.codigo(prox_reg)+
                       P.salvar(prox_reg+1) +
                       right.codigo(prox_reg +1)+
                       P.linea("mult "+reg+", "+right.reg(prox_reg +1))+
                       P.linea("mflo "+reg)+
                       P.restaurar(prox_reg+1);
            case '/':

                return left.codigo(prox_reg)+
                       P.salvar(prox_reg+1) +
                       right.codigo(prox_reg +1)+
                       P.linea("div "+reg+", "+right.reg(prox_reg +1))+
                       P.linea("mflo "+reg)+
                       P.restaurar(prox_reg+1);
            case '%':

                return left.codigo(prox_reg)+
                       P.salvar(prox_reg+1) +
                       right.codigo(prox_reg +1)+
                       P.linea("div "+reg+","+reg+","+right.reg(prox_reg +1))+
                       P.linea("mfhi "+reg)+
                       P.restaurar(prox_reg+1);
            default:   
                    if(right==null)
                    {
                        return left.codigo(prox_reg)+


                               P.linea("nor "+reg+", "+reg+", $0")+


                               P.linea("addiu "+reg+", "+reg+", 1");
                    }
                    else
                    {
                        return left.codigo(prox_reg)+
                               P.salvar(prox_reg+1) +
                               right.codigo(prox_reg +1)+
                               P.linea("sub "+reg+", "+reg+", "+right.reg(prox_reg +1))+
                               P.restaurar(prox_reg+1);
                    }
        }
    }
}