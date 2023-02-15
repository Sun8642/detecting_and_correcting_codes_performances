package model;

import code.HammingCode;

public class HammingCodeParameter extends CodeParameter {

    public HammingCodeParameter() {
        canCorrectError = true;
        messageBitSize = 4;
    }

    @Override
    public void setMessageBitSize(int messageBitSize) {
        if (!HammingCode.isKValid(messageBitSize)) {
            throw new IllegalArgumentException("The number of bits per message plus the number of redundancy bits for " +
                    "this number minus one must be equals to a power of 2 (e.g. code(7, 4), code(15,11), ...).");
        }
        this.messageBitSize = messageBitSize;
    }
}
