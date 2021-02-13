package ru.mtusi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {

    private static final int MAX_ITERATIONS = 2000;

    private final ComplexNumber z0 = new ComplexNumber(0.0d, 0.0d);

    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2.0d;
        range.y = -1.5d;
        range.width = 3.0d;
        range.height = 3.0d;
    }

    @Override
    public int numIterations(double x, double y) {
        int iterator = 0;
        ComplexNumber c = new ComplexNumber(x, y);
        ComplexNumber z = z0;
        while (z.getAbc2() <= 2.0d * 2.0d && iterator < MAX_ITERATIONS) {
            z = z.pow2();
            z.add(c);
            iterator++;
        }
        if (iterator == MAX_ITERATIONS) {
            iterator = -1;
        }
        return iterator;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class ComplexNumber {

        private Double Re;
        private Double Im;

        public double getAbc2() {
            return Re*Re + Im*Im;
        }

        public void add(ComplexNumber complexNumber) {
            this.Im += complexNumber.getIm();
            this.Re += complexNumber.getRe();
        }

        public ComplexNumber pow2() {
            return new ComplexNumber(this.Re * this.Re - this.Im * this.Im,
                    2d * this.Re * this.Im);
        }

    }

}