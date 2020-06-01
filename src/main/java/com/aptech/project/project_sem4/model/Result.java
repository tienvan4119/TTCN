package com.aptech.project.project_sem4.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(value = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    ObjectId id;
    ObjectId user_id;
    ObjectId section_id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUser_id() {
        return user_id;
    }

    public void setUser_id(ObjectId user_id) {
        this.user_id = user_id;
    }

    public ObjectId getSection_id() {
        return section_id;
    }

    public void setSection_id(ObjectId section_id) {
        this.section_id = section_id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    int mark;
}
