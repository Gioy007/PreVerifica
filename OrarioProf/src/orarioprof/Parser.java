/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orarioprof;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author GIO
 */
public class Parser {

    public Parser() {

    }

    public List parseDocument(String filename) {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element;
        NodeList nodelist;
        Prof p;
        List albo = new ArrayList();
        // creazione dell’albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        root = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(filename);
            root = document.getDocumentElement();
        } catch (Exception ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }

        nodelist = root.getElementsByTagName("tr");
        int count = 0;
        if (nodelist != null && nodelist.getLength() > 0) {

            boolean trovato = false;

            while (!trovato) {
                if (nodelist.item(count).getFirstChild().getTextContent().contains("DOCENTE")) {
                    trovato = true;
                }
                count++;
            }

            for (int i = count; i < nodelist.getLength(); i++) {
                element = (Element) nodelist.item(i);
                p = getProf(element);
                if (p != null) {
                    albo.add(p);
                }

            }
        }
        return albo;
    }

    private Prof getProf(Element el) {
        Prof p = null;

        // cerco il primo elemento nel primo tr
        //Element elementParent = (Element) el.getParentNode();
        ArrayList a = new ArrayList();
        for (int i = 0; i < el.getChildNodes().getLength(); i++) {
            String bambino = el.getChildNodes().item(i).getTextContent(); //getTextValue(elementParent, "td", 0);
            if (!bambino.equals("")) {
                a.add(bambino);
            }
        }
        String ID = (String) a.get(0);
        String nome = (String) a.get(1);
        String giorno = "";
        String ora = "";
        String lotto = "";
        //creo l'oggetto del prof 
        if (a.size() > 2) {
            giorno = (String) a.get(2);
        }
        if (a.size() > 3) {
            ora = (String) a.get(3);
        }
        if (a.size() > 4) {
            lotto = (String) a.get(4);
        }

        p = new Prof(ID, ora, nome, giorno, lotto);
        return p;
    }

    // restituisce il valore testuale dell’elemento figlio specificato
    private String getTextValue(Element element, String tag, int child) {
        String value = null;
        NodeList nodelist;
        nodelist = element.getElementsByTagName(tag);
        if (nodelist != null && nodelist.getLength() > child) {
            Node nodeChild = nodelist.item(child).getFirstChild();
            if ((nodeChild != null) && nodeChild.hasChildNodes()) {
                value = nodeChild.getFirstChild().getNodeValue();
            }
        }
        return value;
    }

}
