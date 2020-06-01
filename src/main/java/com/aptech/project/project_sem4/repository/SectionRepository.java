package com.aptech.project.project_sem4.repository;

import com.aptech.project.project_sem4.model.Section;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends MongoRepository<Section, Long> {
    @Query("{ _id: ObjectId(\"?0\" )}")
    public Section isDone(String section_id);
    @Query("{isDone: false}")
    public List<Section> getAllSectionUnDone();
}
