public class EmptyException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EmptyException() {
        super();
    }

    public EmptyException(String m) { super(m);}
}