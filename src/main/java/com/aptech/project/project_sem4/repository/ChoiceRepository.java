package com.aptech.project.project_sem4.repository;

import com.aptech.project.project_sem4.model.Choice;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ChoiceRepository extends MongoRepository<Choice, Long> {
    @Query("{ question_id: ObjectId(\"?0\" )}")
    List<Choice> findChoiceByQuestionId(String question_id);

    @Query("{ _id : ObjectId(\"?0\" )}")
    public Choice findChoiceById(String choice_id);
}
