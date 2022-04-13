package org.pinky83.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "questions")
public class Question extends BaseEntity {

    @Column
    private String description;

    @Column
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "id")
    private Set<Answer> answers;

}
