
public abstract class Tipo {
    
    public Tipo(){}
    
    public abstract Tipo aritmetica();
    public abstract Tipo aritmetica(Tipo t);
    public abstract Tipo compare();
    public abstract Tipo compare(Tipo t);
    public abstract Tipo asign(Tipo t);
    public abstract int tamano();
    public abstract String toString();
    
}
