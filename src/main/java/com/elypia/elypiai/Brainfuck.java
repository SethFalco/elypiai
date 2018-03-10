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

    private boolean valid;

    /**
     * Initalise the brainfuck instance with the default number of cells. (10)
     * Do note this is a dynamic session, all code will still compile however
     * see {@link Brainfuck(int)} to specify inital number of cells which may
     * result in better speed.
     */

    public Brainfuck() {
        this(10);
    }

    /**
     * Initalise the brainfuck instance with the number of
     * cells provided. This can help speed up interpretting
     * extremely longs code.
     *
     * @param initialCapacity   Default array length before having to expand for more cells.
     */

    public Brainfuck(int initialCapacity) {
        cells = new ArrayList<>(initialCapacity);
        prints = new ArrayList<>();
        valid = false;

        cells.add(CELL_INIT);
    }

    public List<Byte> compile(final String brainfuck, final byte... input) {
        Objects.requireNonNull(brainfuck);
        return compile(brainfuck.getBytes(), input);
    }

    public List<Byte> compile(final byte[] brainfuck, final byte... input) {
        Objects.requireNonNull(brainfuck);

        if (!valid) {
            this.input = input;

            if (!isValid(brainfuck))
                throw new IllegalArgumentException("Brainfuck should always have equal number of open ([) and close (]) brackets.");

            valid = true;
        }

        int position = 0;

        while (position < brainfuck.length)
            position = intepretCommand(brainfuck, position);

        return prints;
    }

    /**
     * Compile the brainfuck code, then convert the resulting bytes
     * to a String. <br>
     * Possible error: If code requests more input than provided.
     *
     * @param brainfuck The brainfuck code to interpret.
     * @param input An array of input bytes for any of input (, command).
     * @return  A {@link String} result of all printed (. command) {@link byte}s.
     */

    public String compileToString(final String brainfuck, final byte... input) {
        List<Byte> list = compile(brainfuck, input);
        StringBuilder buffer = new StringBuilder();

        list.forEach(o -> buffer.append((char)o.byteValue()));

        return buffer.toString();
    }

    /**
     * Intepret a single command from brainfuck code.
     *
     * @param brainfuck The brainfuck we're intepretting.
     * @param position  The possition of the command.
     * @return  The possition to go to once complete.
     */

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
