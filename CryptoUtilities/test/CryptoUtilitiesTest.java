import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Liam Graham
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_15_5() {
        NaturalNumber n = new NaturalNumber2(15);
        NaturalNumber nExpected = new NaturalNumber2(5);
        NaturalNumber m = new NaturalNumber2(10);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }
    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_99999999() {
        NaturalNumber n = new NaturalNumber2(99999999);
        NaturalNumber nExpected = new NaturalNumber2(99999999);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_10_2_10() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(2);
        NaturalNumber pExpected = new NaturalNumber2(2);
        NaturalNumber m = new NaturalNumber2(10);
        NaturalNumber mExpected = new NaturalNumber2(10);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void isWitnessToCompositeness6_25() {
        NaturalNumber n = new NaturalNumber2(6);
        NaturalNumber nExpected = new NaturalNumber2(6);
        NaturalNumber w = new NaturalNumber2(25);
        NaturalNumber wExpected = new NaturalNumber2(25);
        assertEquals(true, CryptoUtilities.isWitnessToCompositeness(n, w));
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);

    }

    @Test
    public void isWitnessToCompositeness6_90() {
        NaturalNumber n = new NaturalNumber2(6);
        NaturalNumber nExpected = new NaturalNumber2(6);
        NaturalNumber w = new NaturalNumber2(90);
        NaturalNumber wExpected = new NaturalNumber2(90);
        assertEquals(true, CryptoUtilities.isWitnessToCompositeness(n, w));
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);

    }

    @Test
    public void isWitnessToCompositeness13_293() {
        NaturalNumber n = new NaturalNumber2(13);
        NaturalNumber nExpected = new NaturalNumber2(13);
        NaturalNumber w = new NaturalNumber2(293);
        NaturalNumber wExpected = new NaturalNumber2(293);
        assertEquals(false, CryptoUtilities.isWitnessToCompositeness(n, w));
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);

    }

    @Test
    public void isWitnessToCompositeness41_61() {
        NaturalNumber n = new NaturalNumber2(41);
        NaturalNumber nExpected = new NaturalNumber2(41);
        NaturalNumber w = new NaturalNumber2(61);
        NaturalNumber wExpected = new NaturalNumber2(61);
        assertEquals(false, CryptoUtilities.isWitnessToCompositeness(n, w));
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);

    }

    @Test
    public void isPrime2_13() {
        NaturalNumber n = new NaturalNumber2(13);
        NaturalNumber nExpected = new NaturalNumber2(13);

        assertEquals(true, CryptoUtilities.isPrime2(n));
        assertEquals(nExpected, n);
    }

    @Test
    public void isPrime2_17() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(17);

        assertEquals(true, CryptoUtilities.isPrime2(n));
        assertEquals(nExpected, n);

    }

    @Test
    public void isPrime2_24() {
        NaturalNumber n = new NaturalNumber2(24);
        NaturalNumber nExpected = new NaturalNumber2(24);

        assertEquals(false, CryptoUtilities.isPrime2(n));
        assertEquals(nExpected, n);

    }

    @Test
    public void generateNextLikelyPrime13() {
        NaturalNumber n = new NaturalNumber2(13);
        NaturalNumber nExpected = new NaturalNumber2(13);

        assertEquals(nExpected, n);

    }

    @Test
    public void generateNextLikelyPrime41() {
        NaturalNumber n = new NaturalNumber2(41);
        NaturalNumber nExpected = new NaturalNumber2(41);

        assertEquals(nExpected, n);

    }

    public void generateNextLikelyPrime64() {
        NaturalNumber n = new NaturalNumber2(64);
        NaturalNumber nExpected = new NaturalNumber2(67);

        assertEquals(nExpected, n);

    }

}
