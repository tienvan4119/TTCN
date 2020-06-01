package com.aptech.project.project_sem4.repository;

import com.aptech.project.project_sem4.model.Result;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends MongoRepository<Result, Long> {
    @Query("{ user_id: ObjectId(\"?0\" )}")
    public List<Result> getListSection_Result(String User_id);
}
