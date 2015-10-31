package Core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSymbolName {

    static final int testSymbolCode1001 = 1001;
    static final int testSymbolCode2001 = 2001;
    static final int testSymbolCode3001 = 3001;
    static final int testSymbolCode4001 = 4001;
    static final int testSymbolCode5001 = 5001;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void Should_Compare_Different_Symbols_AsEqual() {
        SymbolName testSymbolOne = new SymbolName(testSymbolCode1001, "Begining Symbol");

        SymbolName testSymbolTwo = new SymbolName(testSymbolCode1001, "Terminal Symbol");

        assertEquals(testSymbolOne, testSymbolTwo);
    }

    @Test
    public void Should_Compare_Different_Symbols_AsGreater() {
        SymbolName testSymbolOne = new SymbolName(testSymbolCode1001, "Begining Symbol");

        SymbolName testSymbolTwo = new SymbolName(testSymbolCode5001, "Terminal Symbol");

        assertNotEquals(testSymbolTwo, testSymbolOne);

        assertTrue(testSymbolTwo.compareTo(testSymbolOne) > 0);
    }

    @Test
    public void Should_Compare_Different_Symbols_AsLess() {
        SymbolName testSymbolOne = new SymbolName(testSymbolCode1001, "Begining Symbol");

        SymbolName testSymbolTwo = new SymbolName(testSymbolCode5001, "Terminal Symbol");

        assertNotEquals(testSymbolTwo, testSymbolOne);

        assertTrue(testSymbolOne.compareTo(testSymbolTwo) < 0);
    }
}
