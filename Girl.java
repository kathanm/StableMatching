import java.util.List;

/**
 * A girl class used to model an example of a Matcahble.
 */
public class Girl extends Matchable{

    Girl(String id, List prefs) {
        super(id, prefs);
    }

    Girl(String id) {
        super(id);
    }

    /**
     * Implementation of the isPrefsValid method required by the abstract class. Prefs cannot be empty as this method
     * is used to check whether or not matching can occur. The girl class can only be matched with a boy class for
     * simplicity purposes.
     * @param matchList The list of options from which the match will be found.
     * @return
     */
    @Override
    public boolean isPrefsValid(List matchList) {
        if (prefs.size() == 0) {
            return false;
        }

        if (!Boy.class.isInstance(prefs.get(0))) {
            return false;
        }

        if (matchList.size() != prefs.size()) {
            return false;
        }

        if (!prefs.containsAll(matchList)) {
            return false;
        }

        return true;
    }
}