import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AritmethicOperationTest {

    private AritmethicOperation calculator = new AritmethicOperation();

    @Test
    void Suma() {
        assertEquals(16, calculator.Evaluate("+ 8 8"));
    }

    @Test
    void SumaNegativa() {
        assertEquals(2, calculator.Evaluate("+ 10 -8"));
    }

    @Test
    void Resta() {
        assertEquals(30, calculator.Evaluate("- 35 5"));
    }

    @Test
    void RestaNegativa() {
        assertEquals(-30, calculator.Evaluate("- 5 35"));
    }

    @Test
    void Division() {
        assertEquals(36, calculator.Evaluate("/ 360 10"));
    }

    @Test
    void Multiplicacion() {
        assertEquals(10, calculator.Evaluate("* 5 2"));
    }


}