import java.util.Arrays;

/**
 * Overview: le istanze di questa classe rappresentano delle parole
 *           Gli oggetti di questo tipo sono immutabili
 * 
 */
public class Word implements Comparable<Word> {
    
    final private String value;

    /**
     * RI: value non null
     */

    /**
     * Inizializza this affinche rappresenti la parola s
     * @param s una stringa che rappresenta il valore con cui inizializzare this
     * @throws NullPointerException se s e' null
     */
    public Word(String s) {
        if(s == null) throw new NullPointerException();
        value = s;
        assert repOk();
    }

    /**
     * 
     * @return una stringa che rappresenta la firma di this, ovvero la stringa composta dai caratteri di this
     *         in ordine alfabetico
     */
    public String getFirm() {
        char a[] = value.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
    
    public String toString() {
        return value;
    }

    @Override
    public int compareTo(Word o) {
        return value.compareTo(o.value);
    }

    public boolean repOk() {
        return value != null;
    }
}
