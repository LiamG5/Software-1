import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import components.naturalnumber.NaturalNumber;

/**
 * View class.
 *
 * @author Liam G.
 */
public final class NNCalcView1 extends JFrame implements NNCalcView {

    /**
     *
     */

    /**
     * removes warning for serialization :|.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Controller object registered with this view to observe user-interaction
     * events.
     */
    private NNCalcController controller;

    /**
     * State of user interaction: last event "seen".
     */
    private enum State {
        /**
         * Last event was clear, enter, another operator, or digit entry, resp.
         */
        SAW_CLEAR, SAW_ENTER_OR_SWAP, SAW_OTHER_OP, SAW_DIGIT
    }

    /**
     * State variable to keep track of which event happened last; needed to
     * prepare for digit to be added to bottom operand.
     */
    private State currentState;

    /**
     * Text areas.
     */
    private final JTextArea tTop, tBottom;

    /**
     * Operator and related buttons.
     */
    private final JButton bClear, bSwap, bEnter, bAdd, bSubtract, bMultiply,
            bDivide, bPower, bRoot;

    /**
     * Useful constants.
     */
    private static final int TEXT_AREA_HEIGHT = 5, TEXT_AREA_WIDTH = 20,
            DIGIT_BUTTONS = 10, MAIN_BUTTON_PANEL_GRID_ROWS = 4,
            MAIN_BUTTON_PANEL_GRID_COLUMNS = 4, SIDE_BUTTON_PANEL_GRID_ROWS = 3,
            SIDE_BUTTON_PANEL_GRID_COLUMNS = 1, CALC_GRID_ROWS = 3,
            CALC_GRID_COLUMNS = 1, ONE = 1, TWO = 2, THREE = 3, FOUR = 4,
            FIVE = 5, SIX = 6, SEVEN = 7, EIGHT = 8, NINE = 9;

    /**
     * Digit entry buttons.
     */
    private final JButton[] bDigits = new JButton[DIGIT_BUTTONS];

    /**
     * No argument constructor.
     */
    public NNCalcView1() {
        // Create the JFrame being extended

        // getting rid of comments to remove too many line warning
        super("Natural Number Calculator");

        // Set up the GUI widgets --------------------------------------------

        this.currentState = State.SAW_CLEAR;

        /*
         * Create widgets
         */
        for (int i = 0; i < DIGIT_BUTTONS; i++) {
            this.bDigits[i] = new JButton(String.valueOf(i));
        }
        this.tTop = new JTextArea("", TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);
        this.tBottom = new JTextArea("", TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);
        this.bClear = new JButton("CLEAR");
        this.bSwap = new JButton("SWAP");
        this.bEnter = new JButton("ENTER");
        this.bAdd = new JButton("ADD");
        this.bSubtract = new JButton("SUBTRACT");
        this.bMultiply = new JButton("MULTIPLY");
        this.bDivide = new JButton("DIVIDE");
        this.bPower = new JButton("POWER");
        this.bRoot = new JButton("ROOT");

        // Set up the GUI widgets --------------------------------------------

        this.tTop.setEditable(false);
        this.tTop.setLineWrap(true);
        this.tTop.setWrapStyleWord(true);

        this.tBottom.setEditable(false);
        this.tBottom.setLineWrap(true);
        this.tBottom.setWrapStyleWord(true);

        this.bDivide.setEnabled(false);
        this.bRoot.setEnabled(false);

        JScrollPane tTopSP = new JScrollPane(this.tTop);
        JScrollPane tBottomSP = new JScrollPane(this.tBottom);

        JPanel panel1 = new JPanel(new GridLayout(MAIN_BUTTON_PANEL_GRID_ROWS,
                MAIN_BUTTON_PANEL_GRID_COLUMNS));

        panel1.add(this.bDigits[SEVEN]);
        panel1.add(this.bDigits[EIGHT]);
        panel1.add(this.bDigits[NINE]);
        panel1.add(this.bAdd);
        panel1.add(this.bDigits[FOUR]);
        panel1.add(this.bDigits[FIVE]);
        panel1.add(this.bDigits[SIX]);
        panel1.add(this.bSubtract);
        panel1.add(this.bDigits[ONE]);
        panel1.add(this.bDigits[TWO]);
        panel1.add(this.bDigits[THREE]);
        panel1.add(this.bMultiply);
        panel1.add(this.bDigits[0]);
        panel1.add(this.bPower);
        panel1.add(this.bRoot);
        panel1.add(this.bDivide);

        /*
         * Create side button panel
         */

        JPanel panel2 = new JPanel(new GridLayout(SIDE_BUTTON_PANEL_GRID_ROWS,
                SIDE_BUTTON_PANEL_GRID_COLUMNS));

        /*
         * Add the buttons to the side button panel, from left to right and top
         * to bottom
         */
        panel2.add(this.bClear);
        panel2.add(this.bSwap);
        panel2.add(this.bEnter);

        /*
         * Create combined button panel organized using flow layout, which is
         * simple and does the right thing: sizes of nested panels are natural,
         * not necessarily equal as with grid layout
         */
        JPanel panel3 = new JPanel(new FlowLayout());

        /*
         * Add the other two button panels to the combined button panel
         */
        panel3.add(panel1);
        panel3.add(panel2);
        /*
         * Organize main window
         */

        this.setLayout(new GridLayout(CALC_GRID_ROWS, CALC_GRID_COLUMNS));
        /*
         * Add scroll panes and button panel to main window, from left to right
         * and top to bottom
         */

        this.add(tTopSP);
        this.add(tBottomSP);
        this.add(panel3);

        // Set up the observers ----------------------------------------------

        /*
         * Register this object as the observer for all GUI events
         */

        for (int i = 0; i < DIGIT_BUTTONS; i++) {

            this.bDigits[i].addActionListener(this);
        }

        this.bAdd.addActionListener(this);
        this.bClear.addActionListener(this);
        this.bDivide.addActionListener(this);
        this.bEnter.addActionListener(this);
        this.bMultiply.addActionListener(this);
        this.bPower.addActionListener(this);
        this.bRoot.addActionListener(this);
        this.bSubtract.addActionListener(this);
        this.bSwap.addActionListener(this);

        // Set up the main application window --------------------------------

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void registerObserver(NNCalcController controller) {

        this.controller = controller;

    }

    @Override
    public void updateTopDisplay(NaturalNumber n) {

        String dis = n.toString();
        this.tTop.setText(dis);

    }

    @Override
    public void updateBottomDisplay(NaturalNumber n) {

        String dis = n.toString();
        this.tBottom.setText(dis);

    }

    @Override
    public void updateSubtractAllowed(boolean allowed) {

        this.bSubtract.setEnabled(allowed);

    }

    @Override
    public void updateDivideAllowed(boolean allowed) {

        this.bDivide.setEnabled(allowed);

    }

    @Override
    public void updatePowerAllowed(boolean allowed) {

        this.bPower.setEnabled(allowed);

    }

    @Override
    public void updateRootAllowed(boolean allowed) {

        this.bRoot.setEnabled(allowed);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /*
         * Set cursor to indicate computation on-going; this matters only if
         * processing the event might take a noticeable amount of time as seen
         * by the user
         */
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        /*
         * Determine which event has occurred that we are being notified of by
         * this callback; in this case, the source of the event (i.e, the widget
         * calling actionPerformed) is all we need because only buttons are
         * involved here, so the event must be a button press; in each case,
         * tell the controller to do whatever is needed to update the model and
         * to refresh the view
         */
        Object source = event.getSource();
        if (source == this.bClear) {
            this.controller.processClearEvent();
            this.currentState = State.SAW_CLEAR;
        } else if (source == this.bSwap) {
            this.controller.processSwapEvent();
            this.currentState = State.SAW_ENTER_OR_SWAP;
        } else if (source == this.bEnter) {
            this.controller.processEnterEvent();
            this.currentState = State.SAW_ENTER_OR_SWAP;
        } else if (source == this.bAdd) {
            this.controller.processAddEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bSubtract) {
            this.controller.processSubtractEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bMultiply) {
            this.controller.processMultiplyEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bDivide) {
            this.controller.processDivideEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bPower) {
            this.controller.processPowerEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bRoot) {
            this.controller.processRootEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else {
            for (int i = 0; i < DIGIT_BUTTONS; i++) {
                if (source == this.bDigits[i]) {
                    switch (this.currentState) {
                        case SAW_ENTER_OR_SWAP:
                            this.controller.processClearEvent();
                            break;
                        case SAW_OTHER_OP:
                            this.controller.processEnterEvent();
                            this.controller.processClearEvent();
                            break;
                        default:
                            break;
                    }
                    this.controller.processAddNewDigitEvent(i);
                    this.currentState = State.SAW_DIGIT;
                    break;
                }
            }
        }
        /*
         * Set the cursor back to normal (because we changed it at the beginning
         * of the method body)
         */
        this.setCursor(Cursor.getDefaultCursor());
    }

}
