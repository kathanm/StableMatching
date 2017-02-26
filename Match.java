import java.util.List;

/**
 * A matching class which contains two lists of Matchables. Allows for running the stable matching algorithm with both
 * groups as well as print the current match state of each Matchable in each group.
 */
public class Match {
    private final List<Matchable> groupA;
    private final List<Matchable> groupB;

    /**
     * Constructs a match class with two lists of Matchables.
     * @param groupA First list of Matchables.
     * @param groupB Second list of Matchables.
     */
    Match(List<Matchable> groupA, List<Matchable> groupB) {
        this.groupA = groupA;
        this.groupB = groupB;
    }

    /**
     * Ensures that both groups are valid for matching by checking that they are of equal size, that the size of the
     * lists aren't 0 and that every Matchable's preference lists contain everyone in their match-group.
     * @return whether or not the groups are valid for matching
     */
    private boolean checkValidity() {
        // Both groups must be the same size
        if (groupA.size() != groupB.size()) {
            return false;
        }

        // The size of a group cannot be 0. Nothing to match.
        if (groupA.size() == 0) {
            return false;
        }

        // Each person in the first group must have valid preferences.
        for (Matchable m : groupA) {
            if (!m.isPrefsValid(groupB)) {
                return false;
            }
        }

        // Each person in the second group must have valid preferences.
        for (Matchable m : groupB) {
            if (!m.isPrefsValid(groupA)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Runs the matching algorithm on the group and updates the two Matchable lists with the match fields of every
     * Matchable filled. Throws an exception if the groups aren't valid for matching.
     */
    public void match() throws UnsupportedOperationException{
        // Calls the checkValidity method to ensure matching can be done without errors.
        if (!checkValidity()) {
            // throws exception if not
            throw new UnsupportedOperationException("Cannot match these two groups.");
        }

        // Initial call for every person in groupA to attempt to match with their first choice. Runs match helper for this
        for (Matchable m : groupA) {
            matchHelper(m, 0);
        }

        // Match helper will only update groupB's matches for simplicity. This updates group A's match fields as well.
        for (Matchable m : groupB) {
            Matchable match = m.match;
            match.match = m;
        }
    }

    /**
     * Is an additional function use as a helper for matching. Is ran for every person in groupA with 0 as the initial
     * call.
     * @param m The current Matchable being worked on
     * @param prefNumber The preference index to attempt to match to
     */
    private void matchHelper(Matchable m, int prefNumber) {
        // The Matchable to attempt to match to
        Matchable bMatch = m.getPrefByIndex(prefNumber);

        // If they don't have a match yet, then set their match to m.
        if (bMatch.match == null) {
            bMatch.match = m;
        }

        // If they do have a match and they prefer their match over m, then recur the function with m's next preference.
        else if (bMatch.prefs.indexOf(bMatch.match) < bMatch.prefs.indexOf(m)) {
            matchHelper(m, prefNumber + 1);
        }

        // If they do have a match but prefer m to their current match
        else {
            // Run match helper on their old match with the old match's next preference
            matchHelper(bMatch.match,bMatch.match.prefs.indexOf(bMatch) + 1);
            // Set their match to m
            bMatch.match = m;
        }
    }

    /**
     * Prints the state of the matches by printing the match of every Matchable in a new line. If no match exists, it
     * states that.
     * @return
     */
    public String printState() {
        // Create a new StringBuilder
        StringBuilder s = new StringBuilder();

        // Update the string for each match's state
        for (Matchable m : groupA) {
            // If they have no match, print so.
            if (m.match == null) {
                s.append(m.id);
                s.append(" has no match currently.\n");
            }
            // Otherwise, print who they are matched with.
            else {
                s.append(m.id);
                s.append(" is matched with ");
                s.append(m.match.id);
                s.append("\n");
            }
        }

        // Creates an empty line to easier distinguish between the two groups.
        s.append("\n");

        // Do the same exact thing for groupB.
        for (Matchable m : groupB) {
            if (m.match == null) {
                s.append(m.id);
                s.append(" has no match currently.\n");
            }
            else {
                s.append(m.id);
                s.append(" is matched with ");
                s.append(m.match.id);
                s.append("\n");
            }
        }

        // Return as a string
        return s.toString();
    }
}
