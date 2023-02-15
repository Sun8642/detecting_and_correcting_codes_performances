package model;

public class InternetChecksumParameter extends CodeParameter {

    public InternetChecksumParameter() {
        messageBitSize = 16;
    }

    @Override
    public void setMessageBitSize(int messageBitSize) {
        if (messageBitSize % 16 != 0) {
            throw new IllegalArgumentException("The number of bits per message must be a multiple of 16.");
        }
        this.messageBitSize = messageBitSize;
    }
}
