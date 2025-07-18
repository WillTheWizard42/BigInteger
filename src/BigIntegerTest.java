import static org.junit.jupiter.api.Assertions.*;

class BigIntegerTest {

    @org.junit.jupiter.api.Test
    void addTwoPositives() {
        BigInteger a = new BigInteger(25);
        BigInteger b = new BigInteger(32);
        assertEquals(new BigInteger(57), a.add(b));
    }

    @org.junit.jupiter.api.Test
    void addTwoNegatives() {
        BigInteger a = new BigInteger(-525);
        BigInteger b = new BigInteger(-1022);
        assertEquals(new BigInteger(-1547), a.add(b));
    }

    @org.junit.jupiter.api.Test
    void addOneNegativeOnePositive() {
        BigInteger a = new BigInteger(-525);
        BigInteger b = new BigInteger(1024);
        assertEquals(new BigInteger(499), a.add(b));
    }

    @org.junit.jupiter.api.Test
    void addOnePositiveOneNegative() {
        BigInteger a = new BigInteger(1024);
        BigInteger b = new BigInteger(-525);
        assertEquals(new BigInteger(499), a.add(b));
    }

    @org.junit.jupiter.api.Test
    void subtractTwoPositives() {
        BigInteger a = new BigInteger(18);
        BigInteger b = new BigInteger(21);
        assertEquals(new BigInteger(-3), a.subtract(b));
    }

    @org.junit.jupiter.api.Test
    void subtractOneNegativeOnePositive() {
        BigInteger a = new BigInteger(-42);
        BigInteger b = new BigInteger(78);
        assertEquals(new BigInteger(-120), a.subtract(b));
    }

    @org.junit.jupiter.api.Test
    void subtractOnePositiveOneNegative() {
        BigInteger a = new BigInteger(1000);
        BigInteger b = new BigInteger(-302);
        assertEquals(new BigInteger(1302), a.subtract(b));
    }

    @org.junit.jupiter.api.Test
    void largerThanNegatives() {
        BigInteger a = new BigInteger(-525);
        BigInteger b = new BigInteger(-1022);
        assertTrue(a.LargerThan(b));
    }

    @org.junit.jupiter.api.Test
    void largerThanPositives() {
        BigInteger a = new BigInteger(1000);
        BigInteger b = new BigInteger(23);
        assertTrue(a.LargerThan(b));
    }

    @org.junit.jupiter.api.Test
    void largerThanEqual() {
        BigInteger a = new BigInteger(1000);
        BigInteger b = new BigInteger(1000);
        assertFalse(a.LargerThan(b));
    }

    @org.junit.jupiter.api.Test
    void sumToLongerThan32bits() {
        BigInteger a = new BigInteger(2147483647);
        BigInteger b = new BigInteger(2147483646);
        BigInteger sum4a = a.add(a); // 4294967294
        sum4a = sum4a.add(sum4a); // 8589934588 which is 33 bits
        BigInteger sum4bplus4 = b.add(b); // 4294967294
        sum4bplus4 = sum4bplus4.add(sum4bplus4).add(new BigInteger(4)); // 4294967294

        assertEquals(sum4a, sum4bplus4);
    }
}