package rm;

import java.io.*;
import java.net.URISyntaxException;
import java.util.logging.*;
import rm.lexer.*;
import rm.parser.*;

/**
 *
 * @author Maarten
 */
public class RM {

    /** 
    * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length == 0)
        {
            try {
                //print fancy error using the calling JAR name
                String[] path = RM.class.getProtectionDomain().getCodeSource().getLocation().toURI().toString().split("/");
                System.err.println("Usage: java -jar " + path[path.length-1] + " [-FailOnRedeclare] path");
            }
            catch (URISyntaxException e) 
            {
                //Fall back to default name if that failed
                System.err.println("Usage: java -jar RM.jar [-FailOnRedeclare] path");
            }
            finally
            {
                System.exit(-1);
            }
        }
        String path = null;
        if(args.length == 1)
        {
            path = args[0];
        }
        //Assume parameter is given first, then the path
        else if(args.length == 2 && args[1].equalsIgnoreCase("-failonredeclare"))
        { 
            Interpreter.setFailOnRedeclare();
            path = args[1];
        }
        else
        {
            System.err.println("Too many arguments");
            System.exit(-2);
        }
            
        try {
            //Lexicalize and parse the program text. Throw that at the interpreter
            new Parser(
                new Lexer(
                    new PushbackReader(
                        new FileReader(path), 1024
                    )
                )
            ).parse().apply(
                new Interpreter()
            );
        } catch (ParserException | LexerException | IOException ex) {
            Logger.getLogger(RM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
