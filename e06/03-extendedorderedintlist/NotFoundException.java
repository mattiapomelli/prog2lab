// OVERVIEW: Eccezione sollevata nel caso di insieme vuoto
public class NotFoundException extends RuntimeException {
    
    public NotFoundException() { super(); }

    public NotFoundException(String m) { super(m); }
}