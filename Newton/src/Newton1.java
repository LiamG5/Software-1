import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Liam Graham
 *
 */
public final class Newton1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double r = x;
        final double n = 0.01;

        while (Math.abs(((r * r - x) / x)) > (n * n)) {

            r = ((r + (x / r)) / 2);

        }
        return r;
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
        boolean notDone = true;

        while (notDone) {
            out.println(
                    "please type the number you wish to take the square root of");
            double num = in.nextDouble();
            if (num <= 0) {
                out.println("That is not a valid number");
            } else {
                out.println(sqrt(num));
            }
            out.println(
                    "Do you wish to keep going? y for yes, anything else for no");
            String fin = in.nextLine();
            if (!(fin.equals("y"))) {
                notDone = false;
            }
        }
        /*
         * Close input and output streams
         */

        in.close();
        out.close();

    }

}
