package com.sysgears.example.domain.members;

/**
 * An operand of expression.
 *
 * @author Yevgen Goliuk
 */
public class Number implements Member {

    private Double value = 0.0;

    /**
     * Get operand of expression.
     *
     * @return string with a value
     */
    public String getValue() {
        return String.valueOf(value);
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
