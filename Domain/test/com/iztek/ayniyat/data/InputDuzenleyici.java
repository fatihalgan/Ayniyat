package com.iztek.ayniyat.data;

import java.util.StringTokenizer;

/**
 * @author Umit Akyol
 *
 */
public class InputDuzenleyici {
    
    public static String kelimelerinBasHarfleriniDuzenle(String orjinal){
        StringTokenizer tokenizer = new StringTokenizer(orjinal);
        String duzenlenmis="";
        
        while(tokenizer.hasMoreTokens()){
            String temp = tokenizer.nextToken();
            temp = temp.toLowerCase();
            temp=temp.replaceFirst(temp.substring(0,1),temp.substring(0,1).toUpperCase());
            duzenlenmis = duzenlenmis+" "+temp;
            
        }
        duzenlenmis = duzenlenmis.trim();
        return duzenlenmis;
    }
}
