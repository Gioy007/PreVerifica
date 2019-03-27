/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orarioprof;

/**
 *
 * @author GIO
 */
public class Prof {
    public String nome, giorno,lotto, ora, ID;
    
    public Prof(){
        
    }
    
    public Prof(String ID, String ora,String nome,String giorno,String lotto){
        this.ID = ID;
        this.ora = ora;
        this.nome = nome;
        this.giorno = giorno;
        this.lotto = lotto;
    }
    
    public String Stringa(){
        String s="";
        
        s+=ID+"  "+nome+"  "+giorno+"  " +ora+"   "+lotto;
        
        return s;
    }
    
}
