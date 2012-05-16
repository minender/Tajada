import java.util.LinkedList;
import java.util.Iterator;

public class ASTInvocar extends ASTInstruccion {

    private String nombre;
    private LinkedList referencias;
    private LinkedList expresionEntrada;
    private Tipo state;

    public ASTInvocar(String n, LinkedList r, LinkedList e, Tipo s) {
	super("invocar");
	nombre = n;
	referencias = r;
	expresionEntrada = e;
	state = s;
	canCheck = false;
    }

    public void setNombre(String n) {
	nombre = n;
    }

    public void setReferencias(LinkedList r) {
	referencias = r;
    }

    public void  setExpresionEntrada(LinkedList e) {
	expresionEntrada = e;
    }

    public String getNombre() {
	return nombre;
    }

    public LinkedList getReferecias() {
	return referencias;
    }

    public LinkedList getExpresionEntrada() {
	return expresionEntrada;
    }

    public ASTInvocarExpresion toExpresion() {
	ASTInvocarExpresion r = new ASTInvocarExpresion(nombre, referencias, expresionEntrada, state);
	r.setCanCheck(canCheck);
	return r;
    }
  
    //@ requires proc != null;
    public boolean check(SymProc proc) {

	LinkedList nombres = proc.getIn();
	SymTable tabla = proc.getTable();
	Tipo res;
	boolean flag = false;

	if(expresionEntrada == null)
	    return nombres.size() == 0;
	
	if(nombres.size() != expresionEntrada.size())
	    return false;

	Iterator it1 = nombres.iterator();
	Iterator it2 = expresionEntrada.iterator();

	while(it1.hasNext()) {
	    ASTExpresion e = (ASTExpresion)it2.next();
	    if(e.getCanCheck()) {
		res = e.getState().asign(tabla.exist((String)it1.next()));
		if(res == null)
		    return false;
	    }
	    else
		flag = true;
	}

	if(!flag)
	    canCheck = true;

	return true;	
    }

    public String printTree() {
	return nombre;
    }

    public String codigo(String prox)
    {
        return P.codSalvarRegslladr(0)+
               P.codEmpilarParams(nombre,expresionEntrada)+
               P.linea("sub $sp, $sp, 4")+
               P.linea("sw $ra, 0($sp)")+
               P.linea("jal "+nombre+"f")+
               P.linea("lw $ra, 0($sp)")+
               P.linea("add $sp, $sp, 4")+
               P.codDesempilarParams(expresionEntrada)+
               P.codRestaurarRegsLLador(0);
    }
}
