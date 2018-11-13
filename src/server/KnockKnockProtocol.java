package server;

public class KnockKnockProtocol {

    private static final int WAITING = 0;
    private static final int SENTKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;

    private static final int NUMJOKES = 5;

    private int state = WAITING;
    private int currentJoke = 0;

    private String[] clues = {"Turnip", "Little Old Lady", "Atch", "Who", "Who"};
    private String[] answers = {"Turnip the heat, it's cold here!",
                                "I didn't know you could yodel!",
                                "Bless you!",
                                "Is there an owl in here?",
                                "Is there an echo in here?"};

    /**
     *
     */
    public String processInput (String theInput) {
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "Knock! , Knock!";
            state = SENTKNOCK;
        } else if (state == SENTKNOCK) {

            if (theInput.equalsIgnoreCase("Who's there?")) {
                theOutput = clues[currentJoke];
                state = SENTCLUE;
            } else {
                theOutput = "El programa esta en ingles , se supone que debes decir \"Who's there?\"!" +
                            "Intentalo de nuevo. Knock! Knock!";
            }
        } else if (state == SENTCLUE) {

            if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                theOutput = answers[currentJoke] + " Want another? (y/n)";
                state = ANOTHER;
            } else {
                theOutput = "Se supone que debes decir \"" + clues[currentJoke] + " who?\"" + "! Intentalo de nuevo. Knock! Knock!";
                state = SENTKNOCK;
            }
        } else if (state == ANOTHER) {

            if (theInput.equalsIgnoreCase("y")) {
                theOutput = "Knock! Knock!";

                if (currentJoke == (NUMJOKES - 1)) {
                    currentJoke = 0;
                } else {
                    currentJoke++;
                }
                state = SENTKNOCK;
            } else {
                theOutput = "Bye.";
                state = WAITING;
            }
        }
        return theOutput;
    }

}
