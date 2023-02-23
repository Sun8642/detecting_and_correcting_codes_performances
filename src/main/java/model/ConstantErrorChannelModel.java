package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConstantErrorChannelModel implements ErrorChannelModel {

    private double errorRate;

    @Override
    public String corrupt(String message) {
        StringBuilder stringBuilder = new StringBuilder(message);
        for (int i = 0; i < message.length(); i++) {
            if (Math.random() <= errorRate) {
                stringBuilder.replace(i, i + 1, message.charAt(i) == '0' ? "1" : "0");
            }
        }
        return stringBuilder.toString();
    }
}
