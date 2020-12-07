import java.util.List;
import java.util.ArrayList;

public class Memory {
    private List<Integer> memoryCells;

    public Memory(List<Integer> program) {
        memoryCells = new ArrayList<>(program);
    }

    private void set(int index, int value) {
        memoryCells.set(index, value);
    }

    protected int get(int index) {
        return memoryCells.get(index);
    }

    public Location[] prepareLocation(int params, int fetchedModes, Registers registers) {
        final Location[] parameters = new Location[params];
        
        for(int i = 0; i < params; i++, fetchedModes /= 10) {
            parameters[i] = new Location(Mode.fromCode(fetchedModes % 10), registers.instructionPointer + 1 + i, registers.relativeBasePointer);
        }

        return parameters;
    }

    class Location {
        private final Mode mode;
        private final int memoryIndex;
        private final int relativeBasePointer;

        public Location(Mode mode, int memoryIndex, int relativeBasePointer) {
            this.mode = mode;
            this.memoryIndex = memoryIndex;
            this.relativeBasePointer = relativeBasePointer;
        }

        void write() {
            if(mode == Mode.IMMEDIATE) throw new IllegalStateException("Cannot write");
            int address = (mode == Mode.RELATIVE) ? memoryIndex + relativeBasePointer : memoryIndex;
            set(address, v);
        };
        
        int read() {
            int address = memoryIndex;
            if(mode == Mode.IMMEDIATE) return get(address);
            if(mode == Mode.RELATIVE) address += relativeBasePointer;
            return get(get(address));
        };
    }
}