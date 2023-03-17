import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LispLogicFunctionTest {
    @Test
    public void testEqualsTrue() {
        LispLogicFuction lf = new LispLogicFuction();
        String result = lf.equals("equals 5 5");
        assertEquals("verdadero", result);
    }

    @Test
    public void testEqualsFalse() {
        LispLogicFuction lf = new LispLogicFuction();
        String result = lf.equals("equals 5 6");
        assertEquals("Falso", result);
    }

    @Test
    public void testMenorCompararTrue() {
        LispLogicFuction lf = new LispLogicFuction();
        String result = lf.menorComparar("< 3 5");
        assertEquals("verdadero", result);
    }

    @Test
    public void testMenorCompararFalse() {
        LispLogicFuction lf = new LispLogicFuction();
        String result = lf.menorComparar("< 5 3");
        assertEquals("Falso", result);
    }

    @Test
    public void testMayorCompararTrue() {
        LispLogicFuction lf = new LispLogicFuction();
        String result = lf.mayorComparar("> 5 3");
        assertEquals("verdadero", result);
    }

    @Test
    public void testMayorCompararFalse() {
        LispLogicFuction lf = new LispLogicFuction();
        String result = lf.mayorComparar("> 3 5");
        assertEquals("Falso", result);
    }

    @Test
    public void testIsAtomTrue() {
        LispLogicFuction lf = new LispLogicFuction();
        String result = lf.isAtom("Atom 5");
        assertEquals("T", result);
    }

    @Test
    public void testIsListFalse() {
        LispLogicFuction lf = new LispLogicFuction();
        String result = lf.isList("5");
        assertEquals("NIL", result);
    }

}