package com.aptech.project.project_sem4.repository;

import com.aptech.project.project_sem4.model.Topic;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {
    @Query("{section_id : ObjectId(\"?0\")}")
    public List<Topic> findTopicsBySectionId(String section_id);
    @Query("{_id : ObjectId(\"?0\")}")
    public Topic findSectionByTopicId(String topic_id);
}
