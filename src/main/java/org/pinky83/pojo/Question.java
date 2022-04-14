package org.pinky83.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class Question extends BaseEntity {

    private String description;

    private Set<Answer> answers;

}
