import java.io.*;
import java.util.*;
import java.lang.*;

class ASTPrograma
{
	private ASTBloque cuerpo;
        private SymTable global;
	
        public ASTPrograma(ASTBloque c,SymTable t)
        {
		cuerpo=c;
                global=t;
        }

        public String toString()
        {
		return cuerpo.printTree();
        }

        public String codigo()
        {
              String halt=P.newLabel("fin");

              if(!(global.isEmpty()))
              {
                   return P.linea(".data")+
                          global.codigoGlobalPrincipal("global")+
                          P.linea("mensaje0: .asciiz \"error: indice fuera de rango en arreglo\"")+
                          P.linea(".text")+
                          global.codigoFun()+
                          "main:\n"+
                          cuerpo.codigo(halt)+
                          halt+": \n"+
                          P.linea("li $v0, 10")+
                          P.linea("syscall");
              }
              else
              {
                  return P.linea(".text")+
                          "main :\n"+
                          cuerpo.codigo(halt)+
                          halt+": \n"+
                          P.linea("li $v0, 10")+
                          P.linea("syscall");
              }
        }
}