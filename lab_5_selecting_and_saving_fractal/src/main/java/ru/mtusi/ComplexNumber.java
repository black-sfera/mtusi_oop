package ru.mtusi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplexNumber {

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

    public void complexConjugateNum() {
        this.Im = this.Im * (-1.0d);
    }

}