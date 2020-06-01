package com.aptech.project.project_sem4.repository;

import com.aptech.project.project_sem4.model.Section;
import com.aptech.project.project_sem4.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
    @Query("{ id: ObjectId(\"?0\" )}")
    User findUserById(String id);

}
