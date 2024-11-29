import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Liam Graham
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        NaturalNumber ans = new NaturalNumber2(0);

        if (exp.label().equals("plus")) {
            ans.copyFrom(evaluate(exp.child(0)));
            ans.add(evaluate(exp.child(1)));
        } else if (exp.label().equals("minus")) {
            assert evaluate(exp.child(0)).compareTo(evaluate(exp.child(
                    1))) > -1 : "ERROR: Negtive number due to subtraction";
            ans.copyFrom(evaluate(exp.child(0)));
            ans.subtract(evaluate(exp.child(1)));
        } else if (exp.label().equals("times")) {
            ans.copyFrom(evaluate(exp.child(0)));
            ans.multiply(evaluate(exp.child(1)));
        } else if (exp.label().equals("divide")) {
            assert !evaluate(exp.child(1)).isZero() : "ERROR: Dividing by Zero";
            ans.copyFrom(evaluate(exp.child(0)));
            ans.divide(evaluate(exp.child(1)));
        } else {
            assert Integer.parseInt(
                    exp.attributeValue("value")) >= 0 : "ERROR: Negtive number";

            ans.setFromString(exp.attributeValue("value"));
        }

        return ans;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
