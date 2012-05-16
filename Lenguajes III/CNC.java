import java.io.*;

public class CNC {


  public static void main(String argv[]) 
    throws java.io.IOException, java.lang.Exception
 {
    Lexer scanner = null;
    try {
      scanner = new Lexer( new java.io.FileReader(argv[0]) );
    }
    catch (java.io.FileNotFoundException e) {
      System.out.println("Archvio no encontrado : \""+argv[0]+"\"");
      System.exit(1);
    }
    catch (java.io.IOException e) {
      System.out.println("Error abriendo el archivo : \""+argv[0]+"\"");
      System.exit(1);
    }
    catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Uso: java CNC <Archivo>");
      System.exit(1);
    }

   try {
      Parser p = new Parser(scanner);
      Object result = p.parse().value;
      if(p.error || p.syntaxErrors)
        System.out.println("Revise y vuelva a compilar.");
      else
      {
        System.out.println("Programa sin errores estáticos.");
        System.out.println(result.toString());
        try
        {
          FileWriter fw=new FileWriter(argv[0].substring(0,argv[0].length()-5)+".s");
          BufferedWriter bw=new BufferedWriter(fw);
          PrintWriter pw=new PrintWriter(bw);
          pw.println(((ASTPrograma)result).codigo());

          pw.close();
          bw.close();
          fw.close();

  //      System.out.println(((ASTPrograma)result).cuerpo.getTable().toString());
  //      System.out.println(((ASTPrograma)result).cuerpo.getTable().codigoGlobalPrincipal("principal"));
  //      System.out.println(((ASTPrograma)result).cuerpo.getTable().getParent().codigoGlobalPrincipal("global"));
  //      System.out.println(((ASTPrograma)result).codigo());
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
      }
    }
    catch (Exception e) {
      System.out.println("Revise el error y vuelva a compilar.");
      System.exit(1);
    }
  }
}
