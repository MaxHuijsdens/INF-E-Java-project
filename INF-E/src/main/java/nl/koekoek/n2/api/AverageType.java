package nl.koekoek.n2.api;

/**
 * Enum for average types.
 * @author Tom
 *
 */
public enum AverageType {

    HOUR("hour"), DAY("day");

    String name;

    /**
     * Constructor for AverageType enum.
     * @param name The string equivalent of the enum.
     */
    private AverageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
