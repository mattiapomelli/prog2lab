// OVERVIEW: Eccezione sollevata nel caso di insieme vuoto
public class FullException extends RuntimeException {
    
    public FullException() { super(); }

    public FullException(String m) { super(m); }
}