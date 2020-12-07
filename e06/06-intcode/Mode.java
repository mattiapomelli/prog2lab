public enum Mode {
    POSITION(0),
    IMMEDIATE(1),
    RELATIVE(2);

    public final int code;

    private Mode(int code) {
        this.code = code;
    }

    public static Mode fromCode(int code) {
        for(Mode m : values())  if (m.code == code) return m;
        
        throw new IllegalArgumentException("Invalid mode : " + code);
    }
}