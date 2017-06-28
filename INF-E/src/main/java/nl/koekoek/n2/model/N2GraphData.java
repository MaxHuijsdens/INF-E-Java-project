package nl.koekoek.n2.model;

import java.util.Arrays;

/**
 * Class representing a container for an array of data from a n2 host.
 * The data array always contains 320 values.
 * @author Tom
 *
 */
public class N2GraphData {

    private double max;
    private double[] data;

    /**
     * Default constructor.
     */
    public N2GraphData() {

    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] newData) {
        if (newData == null) {
            this.data = new double[0];
        } else {
            this.data = Arrays.copyOf(newData, newData.length);
        }
    }
}
