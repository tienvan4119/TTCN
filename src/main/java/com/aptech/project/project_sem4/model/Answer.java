package com.aptech.project.project_sem4.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(value = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    ObjectId id;
    ObjectId question_id;
    ObjectId answer_id;

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

    public ObjectId getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(ObjectId answer_id) {
        this.answer_id = answer_id;
    }
}
