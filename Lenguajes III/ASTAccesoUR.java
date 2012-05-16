
public class ASTAccesoUR extends ASTAcceso {

    protected String campo;

    public ASTAccesoUR() {
	hijo = null;
	campo = null;
    }

    public ASTAccesoUR(ASTAcceso h, String c) {
	hijo = h;
	campo = c;
    }

    public boolean isNull() {
	return false;
    }

    public Tipo check(Tipo t) {
	int pos;

	if(t instanceof Registro) {
	    Registro r = (Registro) t;
	    pos = r.getCampos().indexOf(campo);
	    if(pos != -1)
		return hijo.check((Tipo)r.getTipos().get(pos));
	    else 
		return null;
	}
	else if(t instanceof Union) {
	    Union u = (Union) t;
	    pos = u.getCampos().indexOf(campo);
	    if(pos != -1) {
		return hijo.check((Tipo)u.getTipos().get(pos));
	    }
	    else 
		return null;
	}
	else
	    return null;
    }
  
    public String printTree() {
	String m = new String("."+ campo);
	if (hijo != null)
	    m = m.concat(hijo.printTree());

	return m;
    }

    public String codigo(int prox_reg)
    {
        String reg=P.nombresReg[(prox_reg +1)% P.N];

        return hijo.codigo(prox_reg)+
               P.salvar(prox_reg+1)+
               P.linea("ori "+reg+","+"$0, "+campo.despl)+
               P.linea("add "+this.reg(prox_reg)+", "+hijo.reg(prox_reg)+", "+reg)+
               P.restaurar(prox_reg+1)+
    }

}
