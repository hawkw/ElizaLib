package me.hawkweisman.elizalib;

/**
 * Created by hawk on 6/27/16.
 */
public class MathUtil {

    private MathUtil() {
    }

    /**
     * Linear interpolate between <code>a</code> and <code>b</code> by
     * <code>amount</code>.
     * @method lerp
     * @param   a the first number to lerp between
     * @param   b the second number to lerp between
     * @param   amount the amount to interpolate
     * @return [description]
     */
    public static float lerp(float a, float b, float amount) {
        assert amount >= 0: "Amount to lerp must not be less than 0.";
        assert amount <= 1: "Amount to lerp must not be greater than 1.";
        return a + (b - a) * amount;
    }
}