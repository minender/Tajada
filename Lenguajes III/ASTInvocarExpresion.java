import java.util.LinkedList;

public class ASTInvocarExpresion extends ASTExpresion {

    private String nombre;
    private LinkedList referencias;
    private LinkedList expresionEntrada;

    public ASTInvocarExpresion(String n, LinkedList r, LinkedList e, Tipo s) {
	super("invocar", null, null);
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

    public void setExpresionEntrada(LinkedList e) {
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

    public void update() {}

    public String printTree() {
	return nombre;
    }

    public String codSaltoEtiq(String si, String no)
    {
        return "";
    }

    public String codigo(int prox_reg)
    {
        return "";
    }
}
