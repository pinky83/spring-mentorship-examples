package org.pinky83.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InjectedBean {
    private String message = "Some bean is injected";
}
