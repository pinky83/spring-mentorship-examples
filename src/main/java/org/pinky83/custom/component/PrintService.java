package org.pinky83.custom.component;

import lombok.NoArgsConstructor;
import org.pinky83.custom.CustomComponent;
import org.pinky83.custom.CustomInject;

@CustomComponent
@NoArgsConstructor
public class PrintService {

    @CustomInject
    private Parser parser;

    public void print (String message) {
        String output = parser.parse(message);
        System.out.println(output);
    }
}
