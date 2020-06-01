package com.aptech.project.project_sem4.service;

import com.aptech.project.project_sem4.model.*;
import com.aptech.project.project_sem4.repository.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    MongoTemplate mongoTemplate = null;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ChoiceRepository choiceRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private SectionRepository sectionRepository;

    public List<Topic> listAllTopicBySection_id(String section_id) {
        return topicRepository.findTopicsBySectionId(section_id);
    }

    public List<Question> listAllQuestion() {
        return questionRepository.findAll();
    }

    public List<Choice> listChoiceByQuestion_id(String question_id) {
        return choiceRepository.findChoiceByQuestionId(question_id);
    }

    public List<Question> listQuestionByTopic(String topic_id) {
        return questionRepository.findQuestionByTopicId(topic_id);
    }

    public Choice getChoiceById(String choice_id) {
        return choiceRepository.findChoiceById(choice_id);
    }

    public void saveSession(Session session) {
        sessionRepository.save(session);
    }

    public Session getSessionExit(String question_id) {
        return sessionRepository.findAllSessionByQuestionId(question_id);
    }

    public Answer checkChoice(String question_id) {
        return answerRepository.checkChoice(question_id);
    }

    public Question getTopicId(String question_id) {
        return questionRepository.findTopicByQuestionId(question_id);
    }

    public Topic getSectionId(String topic_id) {
        return topicRepository.findSectionByTopicId(topic_id);
    }

    public List<Session> getListChoice(String section_id) {
        return sessionRepository.findAllSessionBySectionId(section_id);
    }

    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

//    public Session getChoice_inSession(String Question_id) {
//        return sessionRepository.findChoiceId_inSession(Question_id);
//    }

    public Section isDone(String section_id) {
        return sectionRepository.isDone(section_id);
    }

    public Section saveSection(Section section)
    {
        return sectionRepository.save(section);
    }
}
