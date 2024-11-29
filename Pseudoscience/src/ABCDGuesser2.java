import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 *
 * @author Liam G.
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     *
     * @param out
     *            the output stream
     *
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        boolean isPos = true;
        String str = "";
        double n = 0;
        while (isPos) {
            str = in.nextLine();
            if (FormatChecker.canParseDouble(str)) {
                n = Double.parseDouble(str);
                if (n > 0) {
                    isPos = false;
                } else {
                    out.print("not > 0");
                }
            } else {
                out.print("not a Double");
            }

        }

        return n;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        boolean isPos = true;
        String str = "";
        double n = 0;
        while (isPos) {
            str = in.nextLine();
            if (FormatChecker.canParseDouble(str)) {
                n = Double.parseDouble(str);
                if (n > 1) {
                    isPos = false;
                } else {
                    out.print("not > 1");
                }
            } else {
                out.print("not a Double");
            }
        }

        return n;
    }

    /**
     * computes the number that is within 1% of u
     *
     * @param in
     *            the input stream
     *
     * @param out
     *            the output stream
     *
     * @param u
     *            number u
     *
     * @param a
     *            number a
     *
     * @param b
     *            number b
     *
     * @param c
     *            number c
     *
     * @param d
     *            number d
     *
     */
    private static void theAlg(SimpleReader in, SimpleWriter out, double u,
            double a, double b, double c, double d) {
        double temp = 0;
        boolean fail = false;
        final double err = 0.01;
        final double[] pows = { -5, -4, -3, -2, -1, -1 / 2, -1 / 3, -1 / 4, 0,
                1 / 4, 1 / 3, 1 / 2, 1, 2, 3, 4, 5 };
        int lim = pows.length - 1;

        for (int i = 0; i < lim; i++) {
            for (int j = 0; j < lim; j++) {
                for (int k = 0; k < lim; k++) {
                    for (int l = 0; l < lim; l++) {
                        temp = Math.pow(a, pows[i]) * Math.pow(b, pows[j])
                                * Math.pow(c, pows[k]) * Math.pow(d, pows[l]);
                        if (((Math.abs(temp - u)) / temp) < err) {
                            i = lim;
                            out.println("FOUND!");
                            out.println(temp + "\n" + a + " to the power of "
                                    + pows[i] + "\n" + b + " to the power of "
                                    + pows[j] + "\n" + c + " to the power of "
                                    + pows[k] + "\n" + d + " d to the power of "
                                    + pows[l]);
                            i = lim;
                            j = lim;
                            k = lim;
                            l = lim;
                            fail = true;
                        }
                    }
                }
            }
        }

        if (!fail) {
            out.print("failed to find a number!");
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Gets the values from user
         */
        out.println("u?");
        double u = getPositiveDoubleNotOne(in, out);
        out.println("a?");
        double a = getPositiveDouble(in, out);
        out.println("b?");
        double b = getPositiveDouble(in, out);
        out.println("c?");
        double c = getPositiveDouble(in, out);
        out.println("d?");
        double d = getPositiveDouble(in, out);

        /*
         * the alg
         */
        theAlg(in, out, u, a, b, c, d);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
