package com.aptech.project.project_sem4.repository;

import com.aptech.project.project_sem4.model.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends MongoRepository<Answer, Long> {
    @Query("{ question_id: ObjectId(\"?0\" )}")
    public Answer checkChoice(String question_id);
}
