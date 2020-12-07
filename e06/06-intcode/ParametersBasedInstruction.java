public abstract class ParametersBasedInstruction implements Instruction{
    protected final Memory.Location[] parameters;

    public ParametersBasedInstruction(Memory.Location[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean isHalting() {
        return false;
    }
}