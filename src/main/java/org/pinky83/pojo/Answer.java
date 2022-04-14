package org.pinky83.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Answer extends BaseEntity {

    private String description;

    private Boolean isCorrect;

    private Float score;

}
