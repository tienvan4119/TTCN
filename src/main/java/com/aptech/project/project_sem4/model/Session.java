package com.aptech.project.project_sem4.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

@Document(collection = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    ObjectId id;
    ObjectId question_id;
    ObjectId choice_id;
    ObjectId section_id;

    public ObjectId getSection_id() {
        return section_id;
    }

    public void setSection_id(ObjectId section_id) {
        this.section_id = section_id;
    }

    public Session(){

    }

    public Session(String done, List<Session> listSession) {

    }

    public ObjectId getUser_id() {
        return user_id;
    }

    public void setUser_id(ObjectId user_id) {
        this.user_id = user_id;
    }

    ObjectId user_id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(ObjectId question_id) {
        this.question_id = question_id;
    }

    public ObjectId getChoice_id() {
        return choice_id;
    }

    public void setChoice_id(ObjectId choice_id) {
        this.choice_id = choice_id;
    }
}
