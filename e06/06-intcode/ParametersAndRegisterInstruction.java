public abstract class ParametersAndRegisterInstruction extends ParametersBasedInstruction{
    protected final Registers registers;

    public ParametersAndRegisterInstruction(Memory.Location[] parameters) {
        super(parameters);
        this.registers = registers;
    }
}