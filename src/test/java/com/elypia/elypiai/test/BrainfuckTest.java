package com.elypia.elypiai.test;

import com.elypia.elypiai.brainfuck.Brainfuck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BrainfuckTest {

    @Test
    public void compileChars() {
        assertAll("Intepret Basic Ascii Characters",
            () -> assertEquals("1", Brainfuck.compile("+++++++++++++++++++++++++++++++++++++++++++++++++.").interpret()),
            () -> assertEquals("2", Brainfuck.compile("++++++++++++++++++++++++++++++++++++++++++++++++++.").interpret()),
            () -> assertEquals("3", Brainfuck.compile("+++++++++++++++++++++++++++++++++++++++++++++++++++.").interpret()),
            () -> assertEquals("4", Brainfuck.compile("++++++++++++++++++++++++++++++++++++++++++++++++++++.").interpret()),
            () -> assertEquals("12", Brainfuck.compile("+++++++++++++++++++++++++++++++++++++++++++++++++.>++++++++++++++++++++++++++++++++++++++++++++++++++.").interpret()),
            () -> assertEquals("11111", Brainfuck.compile("+++++++++++++++++++++++++++++++++++++++++++++++++.....").interpret()),
            () -> assertEquals("0123456789", Brainfuck.compile("++++++++++++++++++++++++++++++++++++++++++++++++>++++++++++[<.+>-]").interpret())
        );
    }

    @Test
    public void compileWithInput() {
        assertAll("Interpret Brainfuck with Input",
            () -> assertEquals("1234", Brainfuck.compile(",.,.,.,.", new byte[] {'1', '2', '3', '4'}).interpret())
        );
    }

    @Test
    public void invalidBrainfuck() {
        assertAll("Assert that we are getting the proper errors.",
            () -> assertThrows(IndexOutOfBoundsException.class, () -> Brainfuck.compile(",.,.,.,.,", new byte[] {'1', '2', '3', '4'}).interpret()),
            () -> assertThrows(IllegalArgumentException.class, () -> Brainfuck.compile("++[[]++"))
        );
    }

    @Test
    public void compileText() {
        assertAll("Intepret Brainfuck to Strings",
            () -> assertEquals("This is pretty cool.", Brainfuck.compile("-[--->+<]>-.[---->+++++<]>-.+.++++++++++.+[---->+<]>+++.-[--->++<]>-.++++++++++.+[---->+<]>+++.[-->+++++++<]>.++.-------------.[--->+<]>---..+++++.-[---->+<]>++.+[->+++<]>.++++++++++++..---.[-->+<]>--------.").interpret()),
            () -> assertEquals("Hello world!", Brainfuck.compile("-[------->+<]>-.-[->+++++<]>++.+++++++..+++.[--->+<]>-----.--[->++++<]>-.--------.+++.------.--------.-[--->+<]>.").interpret()),
            () -> assertEquals("copy@copy.sh", Brainfuck.compile("--[----->+<]>---.++++++++++++.+.+++++++++.+[-->+<]>+++.++[-->+++<]>.++++++++++++.+.+++++++++.-[-->+++++<]>++.[--->++<]>-.-----------.").interpret()),
            () -> assertEquals("Potato under the snow. Cold and alone. Waiting for a brighter day.", Brainfuck.compile("++++[>++++++++++>++++++++++++++++++++<<-]>++++++<++++++++++[>>>+++++++++>++++++ +++<<<<-]>>>+++++++>++++++++<<<<++++++++++[>>>>>>++++++++++>++++++++++>+++++++++ +>++++++++++>++++++++++>++++++++++>++++++++++>++++++++++>++++++++++>++++++++++>+ +++++++++>++++++++++>++++++++++>++++++++++>++++++++++>>++++++++++>++++++++++<<<< <<<<<<<<<<<<<<<<<<<-]>>>>>>+++++++++++>++++++++++++++++>+++++++++++++++++>++++++ ++++>>+>++++++++++++++>++++>+++++++++++++++>+++++++++++++++++++>++++++++>+++++>+ ++>++>+++++++++++++++++++++>++++++++++++++++++++++++++++++++>------------->----- ----------------------------<<<<<<<<<<<<<<<<<<<<<.>>>>.>.<<<<.>>>>.<.>>>>>>>>>>> >>>>.<<<<<<<<<<<<<.>.>.>.>.>>>>>>>>>.<<<<<<<<<<<<<<.>>>>>>.<<.>>>>>>>>>>.<<<<<<< .<<<<<.<<<.>>>>>>>>>.<<<<<<<<<<<<<<.>>>>>>>>>>>>>>>>>>>>.>>.<<<<<<<<<<<<<<<<<.>> >>>>>>>>.<<<<<<.>>>>>>>>>>>.<<<<<<<<<<<<<<<<<<.>>>>>>.>.>>>>>>>>>>>.<<<<<<<<<<<< <<<<<<.>>>>>>>>>>>>>.<<<<<<<<<<.>>>.>>.<<<<<<<<<<.>>>>>>>>>>>>>>>>>>>>.>.<<<<<<< <<<<<<<<<<<<.>>>>>>>>>>>>>>.<<<<<<<<<<.>>>>>>>>>>.<<<<<<<<.>>>>>>>>>.>>>.<<.<<<< <<<<<<<<<.>>>>>>.>>>>>>>>>.<<<<<<<<<<<<<<<<<<.>>>>>>>>>>>>>>>>>>.<<<<<<<<<<<<<<< <<.>>>>>>>>.>>>>>.>.<<<<<.<<<<<<.>>>>.>.>>>>>>>>>.<<<<<<<<<<<.<<<<<<<.>>>>>>>>>> >>>>>>>.<<<<<<<<<<<<<<<<<<<.").interpret())
        );
    }

    @Test
    public void compileValidBrainfuck() {
        Brainfuck.compile("++++[>+++++<-]>[<+++++>-]+<+[>[>+>+<<-]++>>[<<+>>-]>>>[-]++>[-]+>>>+[[-]++++++>>>]<<<[[<++++++++<++>>-]+<.<[>----<-]<]<<[>>>>>[>>>[-]+++++++++<[>-<-]+++++++++>[-[<->-]+[<<<]]<[>+<-]>]<<-]<<-]").interpret();
    }

    @Test
    public void shiftingCells() {
        Brainfuck.compile(">").interpret();
        Brainfuck.compile("><><><><").interpret();
        Brainfuck.compile(">>>>>>>>").interpret();
        Brainfuck.compile("<<<<<<<<").interpret();
        Brainfuck.compile("<>>>>><<<<<<<<<<<<<<>>>>>>>>>>>>>><<<<<<<<><><>").interpret();
    }
}
