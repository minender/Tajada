
//clase de Procedimiento y funciones
import java.util.LinkedList;
import java.util.Iterator;

public class P
{

   public static String [] nombresReg ={"$s0","$s1","$s2","$s3","$s4","$s5",
     "$s6","$s7","$t0","$t1","$t2","$t3","$t4","$t5","$t6","$t7","$t8","$t9"};
   public static int N=15;
   public static int newLabel=0;

   public static String newLabel(String l)

   {
     l+="_"+newLabel;
     newLabel++;
     return l;
   }

   public static String linea(String s)
   {
      return "\t"+s+"\n";
   }
   public static String salvar(int prox_reg)
   {
      String s="";
      if(prox_reg >= N)
      {
         s=linea("sw "+nombresReg[prox_reg % N]+ ", 0(sp)");
         s+=linea("addi $sp, $sp, -4");
      }
      return s;
   }
   public static String restaurar(int prox_reg)
   {
      String s="";
      if(prox_reg >= N)
      {
         s=linea("addi $sp, $sp, 4");
         s+=linea("lw "+nombresReg[prox_reg]+", 0($sp)");
      }
      return s;
   }
   public static String codSalvarRegslladr(int prox_reg)
   {
      String c="";
   
      for(int k=9; k<prox_reg; k++)
      {
         c=c+P.linea("sub $sp, $sp, 4")+
             P.linea("sw "+P.nombresReg[k]+", 0($sp)");
      }
      return c;
   }
   public static String codEmpilarParams(String nombre,LinkedList expresionEntrada)
   {
      String c="";
      Iterator it=expresionEntrada.iterator();
      
      while(it.hasNext())
      {
          c=c+P.EPVal((ASTExpresion)it.next());
      }
      return c;
   }
   public static String EPVal(ASTExpresion ex)
   {
      return ex.codigo(0)+
             P.linea("sub $sp, $sp, "+ex.getState().tamano())+
             P.linea("sw "+P.nombresReg[0]+", 0($sp)");
   }
   public static String codDesempilarParams(LinkedList expresionEntrada)
   {
      String c="";
      Iterator it=expresionEntrada.iterator();
      LinkedList list=new LinkedList();
      
      while(it.hasNext())
      {
         list.addFirst(it.next());
      }
      
      Iterator it2=list.iterador();
      while(it.hasNext())
      {
         c=c+P.DPVal((ASTExpresion)it2.next());
      }
      return c;
   }
   public static String DPVal(ASTExpresion ex)
   {
       return P.linea("sub $sp, $sp, "+ex.getState().tamano());  
   }
   public static String codRestaurarRegsLLador(int prox_reg)
   {
      String c="";

      for(int k=prox_reg-1;8<k;k--)
      {
         c=c+P.linea("lw "+P.nombresReg[k]+", 0($sp)")+
             P.linea("add $sp, $sp, 4");
      }

      return c;
   }
}