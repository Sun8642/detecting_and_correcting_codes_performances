package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BurstErrorChannelModel implements ErrorChannelModel {

    private double errorRate;
    private int burstLength;

    @Override
    public String corrupt(String message) {
        StringBuilder stringBuilder = new StringBuilder(message);
        for (int i = 0; i < message.length(); i++) {
            if (Math.random() <= errorRate) {
                stringBuilder.replace(i, Math.min(i + burstLength, message.length()), message.charAt(i) == '0' ? "1" : "0");
                i += (burstLength - 1);
            }
        }
        return stringBuilder.toString();
    }
}
