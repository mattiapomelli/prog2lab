public enum Opcode {
    ADD(1, 3) {
        @Override
        Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
            return new Add(parameters);
        }
    },
    MULTIPLY(2, 3) {
        @Override
        Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
            return new Multiply(parameters);
        }
    },
    READ(3, 1) {
        @Override
        Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
            return new Read(parameters);
        }
    },
    HALT(99, 0) {
        @Override
        Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
            return new Halt(parameters);
        }
    };

    public final int op;
    public final int params;

    private Opcode(int op, int params) {
        this.op = op;
        this.params = params;
    }

    public static Opcode fromOp(int op) {
        for(Opcode o : values())  if (o.op == op) return o;
        
        throw new IllegalArgumentException("Invalid Opcode : " + op);
    }

    abstract Instruction toInstruction(Memory.Location[] parameters, Registers registers);
}