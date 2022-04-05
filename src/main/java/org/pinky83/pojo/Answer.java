package org.pinky83.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "answers", uniqueConstraints = {@UniqueConstraint(columnNames = {"description"}, name = "answers_unique_description_score_idx")})
public class Answer extends BaseEntity {

    @Column
    private String description;

    @Column(nullable = false)
    private Boolean isCorrect;

    @Column
    private Float score;

}
