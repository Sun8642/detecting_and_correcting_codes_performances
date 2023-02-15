package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HammingResponse {

    private boolean isErrorDetected;
    private String decodedMessage;
}
