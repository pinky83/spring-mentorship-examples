package org.pinky83.dao;

import org.pinky83.pojo.Question;
import org.springframework.data.repository.CrudRepository;

public interface CrudQuestionDao extends CrudRepository<Question, Integer> {
}
