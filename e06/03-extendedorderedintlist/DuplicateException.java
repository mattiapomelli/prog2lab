// OVERVIEW: Eccezione sollevata nel caso di insieme vuoto
public class DuplicateException extends RuntimeException {
    
    public DuplicateException() { super(); }

    public DuplicateException(String m) { super(m); }
}