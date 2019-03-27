/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orarioprof;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author GIO
 */
public class OrarioProf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<Prof> albo = null;
        Parser dom = new Parser();
        try {
            albo = dom.parseDocument("ricevimento.xml");
        } catch (Exception ex) {
            System.out.println(ex);
        }

        // iterazione della lista e visualizzazione degli oggetti
        
        
        for(int i=0; i<albo.size();i++){
            System.out.println(albo.get(i).Stringa());
        }
        System.out.println("Numero dei docenti: " + albo.size());
        
        /*
        Iterator iterator = albo.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
        */
    }
}
