package com.elypia.elypiai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Brainfuck {

    /**
     * The default value when a new cell is created.
     */

    private static final byte CELL_INIT = 0;

    /**
     * Set of cells uses to store brainfuck bytes.
     */

    private List<Byte> cells;

    /**
     * While compiling brainfuck code, all prints (.) are stored.
     */

    private List<Byte> prints;

    /**
     * The currently selected cell from {@link #cells}.
     */

    private int selectedCell;

    /**
     * Input parameters for any input required.
     * Number of input bytes must match number of input commands (,).
     */

    private byte[] input;

    /**
     * The number of times input has been required.
     */

    private int params;

    public Brainfuck() {
        this(10);
    }

    public Brainfuck(int initialCapacity) {
        cells = new ArrayList<>(initialCapacity);
        prints = new ArrayList<>(initialCapacity);

        cells.add(CELL_INIT);
    }

    public List<Byte> compile(final String brainfuck, final byte... input) {
        Objects.requireNonNull(brainfuck);
        return compile(brainfuck.getBytes(), input);
    }

    public List<Byte> compile(final byte[] brainfuck, final byte... input) {
        Objects.requireNonNull(brainfuck);
        this.input = input;

        int position = 0;

        while (position < brainfuck.length)
            position = intepretCommand(brainfuck, position);

        return prints;
    }

    public String compileToString(final String brainfuck, final byte... input) {
        List<Byte> list = compile(brainfuck, input);
        StringBuffer buffer = new StringBuffer();

        list.forEach(o -> buffer.append((char)o.byteValue()));

        return buffer.toString();
    }

    private int intepretCommand(final byte[] brainfuck, int position) {
        byte command = brainfuck[position];

        switch (command) {
            case '>':
                if (cells.size() == selectedCell + 1)
                    cells.add(CELL_INIT);

                selectedCell++;
                return ++position;

            case '<':
                if (selectedCell == 0)
                    cells.add(0, CELL_INIT);

                else
                    selectedCell--;
                return ++position;

            case '+':
                cells.set(selectedCell, (byte) (cells.get(selectedCell) + 1));
                return ++position;

            case '-':
                cells.set(selectedCell, (byte)(cells.get(selectedCell) - 1));
                return ++position;

            case '.':
                prints.add(cells.get(selectedCell));
                return ++position;

            case ',':
                if (input.length < params + 1)
                    throw new IllegalArgumentException("Not enough input was provided.");

                cells.set(selectedCell, input[params++]);
                return ++position;

            case '[':
                int matching = ++position;
                int depth = 1;

                while (depth != 0) {
                    byte b = brainfuck[matching++];

                    if (b == '[')
                        depth++;

                    else if (b == ']')
                        depth--;
                }

                byte[] array = Arrays.copyOfRange(brainfuck, position, matching - 1);

                while (cells.get(selectedCell) != 0)
                    compile(array, input);

                return matching;

            default:
                return ++position;
        }
    }

    private boolean isValid(byte[] brainfuck) {
        int depth = 0;

        for (byte c : brainfuck) {
            if (c == '[')
                depth++;

            else if (c == ']')
                depth--;
        }

        return depth == 0;
    }
}
