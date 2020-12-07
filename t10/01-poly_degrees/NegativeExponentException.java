// OVERVIEW: Eccezione sollevata nel caso di esponente negativo
@SuppressWarnings("serial")
public class NegativeExponentException extends RuntimeException {
  public NegativeExponentException() {
    super();
  }

  public NegativeExponentException(String message) {
    super(message);
  }
}