import static org.junit.jupiter.api.Assertions.*;

class BigIntegerTest {

    @org.junit.jupiter.api.Test
    void AddTwoPositives() {
        BigInteger a = new BigInteger(25);
        BigInteger b = new BigInteger(32);
        assertEquals(new BigInteger(57), a.add(b));
    }

    @org.junit.jupiter.api.Test
    void AddTwoNegatives() {
        BigInteger a = new BigInteger(-525);
        BigInteger b = new BigInteger(-1022);
        assertEquals(new BigInteger(-1547), a.add(b));
    }

    @org.junit.jupiter.api.Test
    void AddOneNegativeOnePositive() {
        BigInteger a = new BigInteger(-525);
        BigInteger b = new BigInteger(1024);
        assertEquals(new BigInteger(499), a.add(b));
    }

    @org.junit.jupiter.api.Test
    void AddOnePositiveOneNegative() {
        BigInteger a = new BigInteger(1024);
        BigInteger b = new BigInteger(-525);
        assertEquals(new BigInteger(499), a.add(b));
    }

    @org.junit.jupiter.api.Test
    void SubtractTwoPositives() {
        BigInteger a = new BigInteger(18);
        BigInteger b = new BigInteger(21);
        assertEquals(new BigInteger(-3), a.subtract(b));
    }

    @org.junit.jupiter.api.Test
    void SubtractOneNegativeOnePositive() {
        BigInteger a = new BigInteger(-42);
        BigInteger b = new BigInteger(78);
        assertEquals(new BigInteger(-120), a.subtract(b));
    }

    @org.junit.jupiter.api.Test
    void SubtractOnePositiveOneNegative() {
        BigInteger a = new BigInteger(1000);
        BigInteger b = new BigInteger(-302);
        assertEquals(new BigInteger(1302), a.subtract(b));
    }

    @org.junit.jupiter.api.Test
    void LargerThanNegatives() {
        BigInteger a = new BigInteger(-525);
        BigInteger b = new BigInteger(-1022);
        assertTrue(a.LargerThan(b));
    }

    @org.junit.jupiter.api.Test
    void LargerThanPositives() {
        BigInteger a = new BigInteger(1000);
        BigInteger b = new BigInteger(23);
        assertTrue(a.LargerThan(b));
    }

    @org.junit.jupiter.api.Test
    void LargerThanEqual() {
        BigInteger a = new BigInteger(1000);
        BigInteger b = new BigInteger(1000);
        assertFalse(a.LargerThan(b));
    }

    @org.junit.jupiter.api.Test
    void divide() {
        throw new UnsupportedOperationException();
    }

    @org.junit.jupiter.api.Test
    void multiply() {
        throw new UnsupportedOperationException();
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        throw new UnsupportedOperationException();
    }
}