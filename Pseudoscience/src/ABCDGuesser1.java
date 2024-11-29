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
public final class ABCDGuesser1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser1() {
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
     * Main method.
     *
     *
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        int runner1 = 0;
        int runner2 = 0;
        int runner3 = 0;
        int runner4 = 0;
        double temp = 0;
        final double err = 0.01;

        boolean go = true;
        final double[] pows = { -5, -4, -3, -2, -1, -1 / 2, -1 / 3, -1 / 4, 0,
                1 / 4, 1 / 3, 1 / 2, 1, 2, 3, 4, 5 };
        int lim = pows.length - 1;
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
        while (go) {

            temp = Math.pow(a, pows[runner1]) * Math.pow(b, pows[runner2])
                    * Math.pow(c, pows[runner3]) * Math.pow(d, pows[runner4]);
            if (((Math.abs(temp - u)) / temp) < err) {
                go = false;
                out.println("FOUND!");
                out.println(temp + "\n" + a + " to the power of "
                        + pows[runner1] + "\n" + b + " to the power of "
                        + pows[runner2] + "\n" + c + " to the power of "
                        + pows[runner3] + "\n" + d + " d to the power of "
                        + pows[runner4]);
            } else {
                runner4++;
            }
            if (runner4 == lim) {
                runner3++;
                runner4 = 0;

            }
            if (runner3 == lim) {
                runner2++;
                runner3 = 0;

            }
            if (runner2 == lim) {
                runner1++;
                runner2 = 0;

            }
            if (runner1 == lim) {
                go = false;
                out.print("failed to find a number");
            }

        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
