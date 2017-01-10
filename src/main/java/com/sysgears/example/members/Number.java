package com.sysgears.example.members;

/**
 * An operand of expression.
 *
 * @author Yevgen Goliuk
 */
public class Number implements Member {

    private Double value = 0.0;
    private int position = 0;

    /**
     * Get operand of expression.
     *
     * @return string with a value
     */
    public String getValue() {
        return String.valueOf(value);
    }


    /**
     * Set number position of this operand in the expression.
     *
     * @param position - number position of this operand
     */
    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Get operand of expression.
     *
     * @return double value
     */
    public Double getDoubleValue() {
        return value;
    }

    /**
     * @param value - operand of expression
     */
    public Number(final String value) {
        this.value = Double.valueOf(value);
    }
}
