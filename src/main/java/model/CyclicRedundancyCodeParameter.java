package model;

import lombok.Getter;

@Getter
public class CyclicRedundancyCodeParameter extends CodeParameter {

    private String generatorPolynomial = "1011";

    public void setGeneratorPolynomial(String generatorPolynomial) {
        if (generatorPolynomial == null || generatorPolynomial.length() == 0) {
            throw new IllegalArgumentException("The polynomial generator must be greater or equals to x (>= 10 in binary representation).");
        }
        generatorPolynomial = generatorPolynomial.trim();
        int oneCount = 0;
        for (int i = 0; i < generatorPolynomial.length(); i++) {
            if (generatorPolynomial.charAt(i) != '0' && generatorPolynomial.charAt(i) != '1') {
                throw new IllegalArgumentException("The polynomial generator can only contain 1s and 0s as characters.");
            }
            if (generatorPolynomial.charAt(i) == '1') {
                oneCount++;
            }
        }
        if (oneCount == 0 || (oneCount == 1 && generatorPolynomial.charAt(generatorPolynomial.length() - 1) == '1')) {
            throw new IllegalArgumentException("The polynomial generator must be greater or equals to x (>= 10 in binary representation).");
        }
        this.generatorPolynomial = generatorPolynomial;
    }
}
