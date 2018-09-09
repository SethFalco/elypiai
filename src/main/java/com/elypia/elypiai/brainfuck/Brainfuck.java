package com.elypia.elypiai.brainfuck;

import java.util.*;

public class Brainfuck {

    /**
     * The default value when a new cell is created.
     */
    private static final byte CELL_INIT = 0;

    /**
     * Converts the String to bytes and overloads {@link #compile(byte[], byte...)}.
     * See {@link #compile(byte[], byte...)}
     *
     * @param brainfuck The brainfuck code to execute.
     * @param input Any input to provide to the code exection on demand.
     * @return A new {@link Brainfuck} instance with the validated code.
     */
    public static Brainfuck compile(final String brainfuck, final byte... input) {
        return Brainfuck.compile(brainfuck.getBytes(), input);
    }

    /**
     * Creates a new Brainfuck object, prepares all variables for
     * interpretting the code and validates the Brainfuck is valid.
     *
     * @param brainfuck The brainfuck code to execute.
     * @param input Any input to provide to the code exection on demand.
     * @return A new {@link Brainfuck} instance with the validated code.
     */
    public static Brainfuck compile(final byte[] brainfuck, final byte... input) {
        return new Brainfuck(brainfuck, input);
    }

    /**
     * The full brainfuck code to execute.
     */
    private final byte[] brainfuck;

    private final Queue<Byte> input;

    /**
     * Set of cells uses to store brainfuck bytes.
     */
    private final List<Byte> cells;

    /**
     * While compiling brainfuck code, all prints (.) are stored.
     */
    private final List<Byte> prints;

    /**
     * The currently selected cell from {@link #cells}.
     */
    private int selectedCell;

    /**
     * Initalise the brainfuck instance with the number of
     * cells provided. This can help speed up interpretting
     * extremely longs code.
     *
     * @param brainfuck The brainfuck code to compile.
     * @param input Input bytes for any required input. (,)
     */
    private Brainfuck(final byte[] brainfuck, final byte... input) {
        this.brainfuck = Objects.requireNonNull(brainfuck);
        this.input = new LinkedList<>();

        for (byte b : input)
            this.input.add(b);

        if (!isValid())
            throw new IllegalArgumentException("Brainfuck should always have an equal number of [ and ].");

        cells = new ArrayList<>();
        prints = new ArrayList<>();

        cells.add(CELL_INIT);
    }

    public List<Byte> interpretBytes() {
        return interpret(brainfuck);
    }

    /**
     * Compile the brainfuck code, then convert the resulting bytes
     * to a String. <br>
     * Possible error: If code requests more input than provided.
     *
     * @return  A {@link String} result of all printed (. command) {@link byte}s.
     */
    public String interpret() {
        List<Byte> list = interpretBytes();
        StringBuilder builder = new StringBuilder();

        list.forEach(o -> builder.append((char)o.byteValue()));

        return builder.toString();
    }

    private List<Byte> interpret(final byte[] brainfuck) {
        int position = 0;

        while (position < brainfuck.length)
            position = intepretCommand(brainfuck, position);

        return prints;
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
                cells.set(selectedCell, (byte)(cells.get(selectedCell) + 1));
                return ++position;

            case '-':
                cells.set(selectedCell, (byte)(cells.get(selectedCell) - 1));
                return ++position;

            case '.':
                prints.add(cells.get(selectedCell));
                return ++position;

            case ',':
                if (input.isEmpty())
                    throw new IndexOutOfBoundsException("Not enough input was provided.");

                cells.set(selectedCell, input.remove());
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
                    interpret(array);

                return matching;

            default:
                return ++position;
        }
    }

    private boolean isValid() {
        int depth = 0;

        for (byte c : brainfuck) {
            if (c == '[') {
                depth++;
            } else if (c == ']') {
                depth--;

                if (depth < 0)
                    return false;
            }
        }

        return depth == 0;
    }
}
