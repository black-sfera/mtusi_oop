package ru.mtusi;

import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {

    private static final int MAX_ITERATIONS = 2000;

    private final ComplexNumber z0 = new ComplexNumber(0.0d, 0.0d);

    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2.0d;
        range.y = -2.0d;
        range.width = 4.0d;
        range.height = 4.0d;
    }

    @Override
    public int numIterations(double x, double y) {
        int iterator = 0;
        ComplexNumber c = new ComplexNumber(x, y);
        ComplexNumber z = z0;
        while (z.getAbc2() <= 2.0d * 2.0d && iterator < MAX_ITERATIONS) {
            z.complexConjugateNum();
            z = z.pow2();
            z.add(c);
            iterator++;
        }
        if (iterator == MAX_ITERATIONS) {
            iterator = -1;
        }
        return iterator;
    }

    public String toString() {
        return "Tricorn";
    }

}
