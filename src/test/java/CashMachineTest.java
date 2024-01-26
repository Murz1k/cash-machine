import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.CashMachine;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashMachineTest {
    private CashMachine cashMachine;

    @BeforeEach
    public void setUp() {
        cashMachine = new CashMachine();
    }

    @Test
    public void testAddValidNotes() {
        assertEquals("OK", cashMachine.addNotes("USD", 100, 10));
        assertEquals("OK", cashMachine.addNotes("EUR", 50, 5));
    }

    @Test
    public void testAddInvalidNotes() {
        assertEquals("ERROR", cashMachine.addNotes("USD", 75, 10));
        assertEquals("ERROR", cashMachine.addNotes("US", 100, 10));
        assertEquals("ERROR", cashMachine.addNotes("USD", 100, -5));
    }

    @Test
    public void testGetCashSuccessful() {
        cashMachine.addNotes("USD", 100, 5);
        assertEquals("100 2\nOK", cashMachine.getCash("USD", 200));
    }

    @Test
    public void testGetCashUnsuccessful() {
        cashMachine.addNotes("USD", 100, 1);
        assertEquals("ERROR", cashMachine.getCash("USD", 200));
    }

    @Test
    public void testPrintCash() {
        cashMachine.addNotes("USD", 100, 5);
        cashMachine.addNotes("EUR", 50, 2);
        String expectedOutput = "EUR 50 2\nUSD 100 5\nOK";
        assertEquals(expectedOutput, cashMachine.printCash());
    }
}

