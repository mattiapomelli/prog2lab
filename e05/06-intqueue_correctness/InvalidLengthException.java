// OVERVIEW: Eccezione sollevata nel caso di insieme vuoto
public class InvalidLengthException extends RuntimeException {
    
    public InvalidLengthException() { super(); }

    public InvalidLengthException(String m) { super(m); }
}