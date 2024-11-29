import java.io.Serializable;
import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue2;
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
public final class Glossary {

    /**
     * Compare {@code String}s in lexicographic order.
     */

    private static class StringLT implements Comparator<String>, Serializable {
        private static final long serialVersionUID = 1;

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Glossary() {
    }

    /**
     *
     *
     * @param in
     *            the file to be read
     *
     * @requires <pre> The file to be entered needs to start with the word on
     *           the first line, the definition on a following line(s) and a
     *           blank line on the next. This repeats until the file is
     *           completely ran through.
     *
     *
     * @returns All the words from the
     */
    public static Queue<String> textToWord(SimpleReader in) {
        String word = "";
        Queue<String> temp = new Queue2<>();
        while (!in.atEOS()) {
            word = in.nextLine();
            temp.enqueue(word);
            word = in.nextLine();
            while (!word.isBlank()) {
                word = in.nextLine();
            }

        }

        return temp;
    }

    /**
     * @param in
     *
     ** @requires <pre> The file to be entered needs to start with the word on
     *           the first line, the definition on a following line(s) and a
     *           blank line on the next. This repeats until the file is
     *           completely ran through.
     * 
     * 
     * @return definitions of the words in a queue
     */
    public static Queue<String> textToDef(SimpleReader in) {
        String def = "";
        String nextLine = "";

        Queue<String> temp = new Queue2<>();
        while (!in.atEOS()) {
            def = in.nextLine();
            def = "";
            nextLine = in.nextLine();
            def = nextLine;
            nextLine = in.nextLine();

            while (!nextLine.isBlank()) {

                def = def + "\n" + nextLine;
                nextLine = in.nextLine();
            }
            temp.enqueue(def);

        }

        return temp;
    }

    /**
     *
     * @param w
     *            queue of words
     *
     * @param d
     *            queue of words
     *
     * @return the .html pages of the words
     *
     */

    public static void makePages(Queue<String> w, Queue<String> d) {
        String word = "";
        String def = "";
        String temp = "";
        String temp2 = "";
        boolean flip = false;
        int len = w.length();

        // make words have links

        //for every def

        for (int i = 0; i < len; i++) {
            temp = "";
            def = d.dequeue();

            //for every word in the current def
            for (int j = 0; j < def.length(); j++) {
                temp = temp + def.charAt(j);

                if (def.charAt(j) == ' ' || def.charAt(j) == ','
                        || def.charAt(j) == ';' || j + 1 == def.length()) {

                    // for every word on the homepage
                    for (int k = 0; k < len; k++) {
                        word = w.dequeue();

                        if (temp.startsWith(word)) {
                            flip = true;
                            temp = word;
                        }

                        w.enqueue(word);
                    }

                    if (flip) {
                        if (def.charAt(j) == ',') {
                            temp2 = temp2 + "<a href=\"" + temp + ".html\">"
                                    + temp + "</a>, ";
                        } else {
                            temp2 = temp2 + "<a href=\"" + temp + ".html\">"
                                    + temp + "</a> ";
                        }

                        flip = false;

                    } else {

                        temp2 = temp2 + temp;
                    }

                    temp = "";
                }

            }

            temp2 = temp2 + temp;
            d.enqueue(temp2);
            temp2 = " ";
        }

        // write the file 

        for (int i = 0; i < w.length(); i++) {
            word = w.dequeue();

            def = d.dequeue();
            SimpleWriter out = new SimpleWriter1L(word + ".html");
            out.println("<html> \n<head> \n<title> " + word + " </title> ");
            out.println("</head> <body> ");
            out.println(" <h2> \n<b> \n<i> \n<font color=\"red\">" + word
                    + "</font></i></b></h2>");
            out.println("<blockquote> " + def + " </blockquote>");
            out.println("<hr />");
            out.println("<p>Return to <a href=\"index.html\">index</a>.</p>");
            out.println(" </body> \n</html>");

            out.close();
            w.enqueue(word);
            d.enqueue(def);
        }

    }

    /**
     *
     * @param w
     *
     *
     * @return the index.html page
     *
     */

    public static void index(Queue<String> w) {
        int len = w.length();
        SimpleWriter out = new SimpleWriter1L("index.html");

        out.println(
                "<html> \n<head> \n<title> Cy's Glossary </title> \n</head> <body>");
        out.println("<h2> Cy's Glossary </h2> \n <hr />");
        out.println("<h3>Index</h3> \n<ul>");
        String temp = "";

        for (int i = 0; i < len; i++) {
            temp = w.dequeue();
            out.println(
                    "<li><a href=\"" + temp + ".html\">" + temp + "</a></li>");

        }
        out.println("</ul>\r \n</body>\r \n</html>");
        out.close();
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
        Queue<String> wordQ = new Queue2<>();
        Queue<String> defQ = new Queue2<>();

        boolean vaild = true;
        String fileName = "";
        while (vaild) {
            out.print("Name of input file(needs .txt at end): ");

            fileName = in.nextLine();

            if (!fileName.endsWith(".txt")) {
                out.println("name does not end in .txt");
            } else {
                vaild = false;
            }
        }
        SimpleReader fileW = new SimpleReader1L(fileName);
        SimpleReader fileD = new SimpleReader1L(fileName);

        wordQ.transferFrom(textToWord(fileW));
        defQ.transferFrom(textToDef(fileD));

        makePages(wordQ, defQ);

        Comparator<String> cs = new StringLT();

        wordQ.sort(cs);

        index(wordQ);

        /*
         * Close input and output streams
         */
        fileW.close();
        fileD.close();
        in.close();
        out.close();
    }

}
