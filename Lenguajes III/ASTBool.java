public class ASTBool extends ASTExpresion {

    //@ invariant left != null;

    //@ requires v != null;
    //@ requires l != null;
    public ASTBool(String v, ASTExpresion l, ASTExpresion r) {
	super(v,l,r);
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
      
	    state = lstate.compare(rstate);
        
	}
	else {
	    if(!(lstate instanceof Basico)) {
		state = null;
		return;
	    }
	    state = lstate.compare();
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

    public String codigo(int prox_reg)
    {
        String reg=left.reg(prox_reg);

        if(value=="==")
        {
                String si=P.newLabel("igual");
                String no=P.newLabel("igual");

                return left.codigo(prox_reg)+
                       P.salvar(prox_reg+1) +
                       right.codigo(prox_reg +1)+
                       P.linea("beq "+reg+", "+right.reg(prox_reg +1)+", "+si)+
                       P.linea("ori "+reg+", $0, 0")+
                       P.linea("j "+no)+
                       si+": \n"+
                       P.linea("ori "+reg+", $0, 1")+
                       no+": \n"+
                       P.restaurar(prox_reg+1);
        }
        else if(value=="!=")
        {
                String si=P.newLabel("noIgual");
                String no=P.newLabel("noIgual");

                return left.codigo(prox_reg)+
                       P.salvar(prox_reg+1) +
                       right.codigo(prox_reg +1)+
                       P.linea("bne "+reg+", "+right.reg(prox_reg +1)+", "+si)+
                       P.linea("ori "+reg+", $0, 0")+
                       P.linea("j "+no)+
                       si+": \n"+
                       P.linea("ori "+reg+", $0, 1")+
                       no+": \n"+
                       P.restaurar(prox_reg+1);
        }
        else if(value=="!")
        {
                return left.codigo(prox_reg)+
          
             P.linea("nor "+reg+", "+
reg+", $0");
        }
        else if(value=="||")
        { 
                String fin=P.newLabel("CorCir");

                return left.codigo(prox_reg)+
             
          P.linea("bne "+left.reg(prox_reg)+",$0, "+fin)+


                       P.linea("nop")+
                       right.codigo(prox_reg)+
                       fin+" :\n";
        }
        else if(value=="&&")
        {
                String fin=P.newLabel("CorCir"); 

                return left.codigo(prox_reg)+
                       
P.linea("beq "+left.reg(prox_reg)+", $0, "+fin)+
                       P.linea("nop")+
                       right.codigo(prox_reg)+
                       fin+" :\n";
        }
        else if(value=="<")
        {
                return left.codigo(prox_reg)+
                       P.salvar(prox_reg+1) +
                       right.codigo(prox_reg +1)+
                       P.linea("slt "+reg+", "+reg+", "+right.reg(prox_reg +1))+
                       P.restaurar(prox_reg+1);
        }
        else if(value=="<=")
        {
                String si=P.newLabel("menorI");
                String no=P.newLabel("menorI");

                return left.codigo(prox_reg)+
                       P.salvar(prox_reg+1) +
                       right.codigo(prox_reg +1)+
                       P.linea("sub "+reg+", "+right.reg(prox_reg+1)+", "+reg)+
                       P.linea("bgez "+reg+", "+si)+
                       P.linea("ori "+reg+", $0, 0")+
                       P.linea("j "+no)+
                       si+": \n"+
                       P.linea("ori "+reg+", $0, 1")+
                       no+": \n"+
                       P.restaurar(prox_reg+1);
        }
        else if(value==">")
        {
                return left.codigo(prox_reg)+
                       P.salvar(prox_reg+1) +
                       right.codigo(prox_reg +1)+
                       P.linea("slt "+reg+", "+right.reg(prox_reg +1)+", "+reg)+
                       P.restaurar(prox_reg+1);
        }
        else
        {  //case ">=":
                String si=P.newLabel("mayorI");
                String no=P.newLabel("mayorI");

                return left.codigo(prox_reg)+
                       P.salvar(prox_reg+1) +
                       right.codigo(prox_reg +1)+
                       P.linea("sub "+reg+", "+reg+", "+right.reg(prox_reg+1))+
                       P.linea("bgez "+reg+", "+si)+
                       P.linea("ori "+reg+", $0, 0")+
                       P.linea("j "+no)+
                       si+": \n"+
                       P.linea("ori "+reg+", $0, 1")+
                       no+": \n"+
                       P.restaurar(prox_reg+1);
        }

    }

    public String codSaltoEtiq(String si, String no)
    {

        if(value=="==")
        {
                String reg=left.reg(0);

                return left.codigo(0)+
                       right.codigo(1)+
                       P.linea("beq "+reg+", "+right.reg(1)+", "+si)+
                       P.linea("j "+no);
        }
        else if(value=="!=")
        {
                String reg=left.reg(0);

                return left.codigo(0)+
                       right.codigo(1)+
                       P.linea("bne "+reg+", "+right.reg(1)+", "+si)+
                       P.linea("j "+no);
        }
        else if(value=="!")
        {
                return left.codSaltoEtiq(no,si);
        }
        else if(value=="||")
        { 
                String noCorCir=P.newLabel("noCorCir");
      
                return left.codSaltoEtiq(si,noCorCir)+
                       noCorCir+" :\n"+
                       right.codSaltoEtiq(si,no);
        }
        else if(value=="&&")
        {
                String noCorCir=P.newLabel("noCorCir");

                return left.codSaltoEtiq(noCorCir,no)+
                       noCorCir+" :\n"+
                       right.codSaltoEtiq(si,no);
        }
        else if(value=="<")
        {
                String reg=left.reg(0);

                return left.codigo(0)+
                       right.codigo(1)+
                       P.linea("sub "+reg+", "+reg+", "+right.reg(1))+
                       P.linea("bltz "+reg+", "+si)+
                       P.linea("j "+no);
        }
        else if(value=="<=")
        {
                String reg=left.reg(0);

                return left.codigo(0)+
                       right.codigo(1)+
                       P.linea("sub "+reg+", "+right.reg(1)+", "+reg)+
                       P.linea("bgez "+reg+", "+si)+
                       P.linea("j "+no);
        }
        else if(value==">")
        {
                String reg=left.reg(0);

                return left.codigo(0)+
                       right.codigo(1)+
                       P.linea("sub "+reg+", "+right.reg(1)+", "+reg)+
                       P.linea("bltz "+reg+", "+si)+
                       P.linea("j "+no);
        }
        else
        {  
           //case ">="
           String reg=left.reg(0);

           return left.codigo(0)+
                  right.codigo(1)+
                  P.linea("sub "+reg+", "+reg+", "+right.reg(1))+
                  P.linea("bgez "+reg+", "+si)+
                  P.linea("j "+no);
        }
    }
}
