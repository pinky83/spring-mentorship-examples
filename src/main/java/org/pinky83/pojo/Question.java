package org.pinky83.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "questions")
public class Question extends BaseEntity {

    @Column
    private String description;

    @Column
    private Set<Answer> answers;

}
