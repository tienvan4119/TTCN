package com.aptech.project.project_sem4.repository;

import com.aptech.project.project_sem4.model.Session;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends MongoRepository<Session, Long> {
    @Query("{ question_id: ObjectId(\"?0\" )}")
    public Session findAllSessionByQuestionId(String Question_id);
    @Query("{ section_id: ObjectId(\"?0\" )}")
    public List<Session> findAllSessionBySectionId(String section_id);
//    @Query("{ question_id: ObjectId(\"?0\" )}")
//    public Session findChoiceId_inSession(String Question_id);
}
