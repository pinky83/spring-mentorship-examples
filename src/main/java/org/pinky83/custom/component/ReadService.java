package org.pinky83.custom.component;

import org.pinky83.custom.CustomComponent;
import org.springframework.context.annotation.Configuration;

@CustomComponent
@Configuration
public class ReadService {
    public String readMessage(String filename) {
        return "";
    }
}
