import java.util.LinkedList;
import java.util.Iterator;

public class ASTAsignacion extends ASTInstruccion {

    private LinkedList ids;
    private ASTExpresion expr;
    private Tipo state;

    public ASTAsignacion(LinkedList l, ASTExpresion e, Tipo s) {
	super("asig");
	ids = l;
	expr = e;
	state = s;
    }

    public void setIds(LinkedList l) {
	ids = l;
    }   

    public void setExpr(ASTExpresion e) {
	expr = e;
    }

    public void setState(Tipo s) {
	state = s;
    }

    public LinkedList getIds() {
	return ids;
    }

    public ASTExpresion getExpr() {
	return expr;
    }

    public Tipo getState() {
	return state;
    }

    public void update() {}

    public String printTree() {

	Iterator it = ids.iterator();
	String idents = new String("[ ");
	while(it.hasNext()) {
	    idents = idents.concat(((ASTIdentificador)it.next()).printTree() + " ");
	}
	idents = idents.concat("]");

	if (expr != null) {
	    String m = new String("ASIG( " + idents + ", " + expr.printTree() + " )");
	    return m;
	}
	else {
	    String m = new String("ASIG( " + idents + " )");
	    return m;
	}
    }

    public String codigo(String prox)
    {
        Iterator it=ids.iterator();

        String s="";
        while( it.hasNext() )
        {
            ASTIdentificador id=(ASTIdentificador)it.next();
            //si es un boolean se tiene que guardar en un byte
            s+= id.codigoLVal(0)+
                P.salvar(1)+
                expr.codigo(1)+
                P.linea("sw "+expr.reg(1)+",0("+id.reg(0)+")")+
                P.restaurar(1);
        }
        return s;
    }
}


