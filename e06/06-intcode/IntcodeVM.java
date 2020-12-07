public class IntcodeVM {
    private final Memory memory;
    private final Registers registers;
    private boolean running;

    public IntcodeVM(List<Integer> program) {
        memory = new Memory(program);
        registers = new Registers();
    }

    public void run() {
        do {
            int fetched = memory.get(registers.instructionPointer);         // fetch
            Instruction i = decode();
            execute(i);
        } while(running);
    }

    private Instruction decode(int fetched) {
        Opcode opcode = Opcode.fromCode(fetched % 100);
        Memory.Location[] parameters = memory.prepareLocation(opcode.params, fetched / 100, registers );
        registers.instructionPointer += opcode.params + 1;
        return opcode.toInstruction(parameters, registers);
    }

    private void execute(Instruction i) {
        i.execute();
        running = !i.isHalting();
    }
}