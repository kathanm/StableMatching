import java.util.ArrayList;
import java.util.List;


/**
 *  A Matchable class. Contains a string which is the name or id of the object, a list of preferences, and a match.
 */
abstract public class Matchable {
    public final String id;
    public List<Matchable> prefs;
    public Matchable match = null;

    /**
     * Constructs a Matchable object with the given String as the id and the given list as the prefs.
     *
     * @param id An identifier or name of the Matchable.
     * @param prefs A list of the Matchable's preferences to be used to match it with another group of Matchables.
     */
    public Matchable(String id, List<Matchable> prefs) {
        this.id = id;
        this.prefs = prefs;
    }

    /**
     * Constructs a Matchable object with the given String as the id. The prefs are set to an empty ArrayList.
     *
     * @param id
     */
    public Matchable(String id) {
        this.id = id;
        this.prefs = new ArrayList<Matchable>();
    }

    public Matchable getPrefByIndex(int index) {
        return prefs.get(index);
    }

    /**
     * Method implement by all classes which extend Matchable. Determines if the list of preferences is valid given the matching list.
     * @param matchList The list of options from which the match will be found.
     * @return
     */
    abstract public boolean isPrefsValid(List<Matchable> matchList);
}
