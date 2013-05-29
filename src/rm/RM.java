/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rm;

import java.io.*;
import java.util.logging.*;
import rm.lexer.*;
import rm.node.*;
import rm.parser.*;
import rm.analysis.*;

/**
 *
 * @author Maarten
 */
public class RM {

    /** 
    * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new Parser(
                new Lexer(
                    new PushbackReader(
                        new FileReader(args[0]), 1024
                    )
                )
            )
            .parse()
            .apply(
                new Interpreter()
            );
        } catch (ParserException | LexerException | IOException ex) {
            Logger.getLogger(RM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
