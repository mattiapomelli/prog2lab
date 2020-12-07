public class Multiply extends ArithmeticInstrucion{

    public Multiply(Memory.Location[] parameters) {
        super(parameters);
    }

    @Override
    public void execute() {
        parameters[2].write(parameters[0].read() * parameters[1].read());
    }
}