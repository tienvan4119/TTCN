package com.aptech.project.project_sem4.controller;

import com.aptech.project.project_sem4.model.*;
import com.aptech.project.project_sem4.service.QuizService;
import com.aptech.project.project_sem4.service.UserService;
import org.apache.catalina.SessionIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuizService quizService;

    private Random random = new Random();

    @RequestMapping(value = {"/", "/section"}, method = RequestMethod.GET)
    public String section(Model model) {
        List<User> listUser = userService.listAll();
        List<Section> listSection = userService.getListSectionUnDone();
        model.addAttribute("listUser", listUser);
        model.addAttribute("listSection", listSection);
        return "section";
    }

    @RequestMapping(value = {"/createQuiz"})
    public String createQuiz(Model model, HttpServletRequest request) {
        String params = request.getQueryString();
        String[] section_id = params.split("=");
        System.out.println(section_id[1]);
        List<Topic> listTopic = quizService.listAllTopicBySection_id(section_id[1]);
        // model.addAttribute("listTopic", listTopic);
        System.out.println(section_id[1]);
        List<Question> listQuestion = quizService.listAllQuestion();
        List<Question> listQuestionyByTopic = new ArrayList<Question>();

        UserController obj = new UserController();
        List<Question> newListQuestion = new ArrayList<Question>();

        for (int i = 0; i < listTopic.size(); i++) {
            listQuestionyByTopic.addAll(quizService.listQuestionByTopic(listTopic.get(i).getId().toString()));
        }

        for (int i = 0; i < 10; i++) {
            Question a = getRandomList(listQuestionyByTopic);
            newListQuestion.add(a);
            listQuestionyByTopic.remove(a);
        }
        model.addAttribute("listQuestion", newListQuestion);
        List<Choice> listChoice = new ArrayList<Choice>();
        for (int i = 0; i < 10; i++) {
            String question_id = newListQuestion.get(i).getId().toString();
            listChoice.addAll(quizService.listChoiceByQuestion_id(question_id));
            System.out.println(question_id);
        }
        for (int i = 0; i < 10; i++) {
            Session session_question = new Session();
            session_question.setQuestion_id(newListQuestion.get(i).getId());
            ObjectId choice_Pre = new ObjectId();
            session_question.setChoice_id(choice_Pre);
            ObjectId sectionId = new ObjectId(section_id[1]);
            session_question.setSection_id(sectionId);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            ObjectId user_id = userService.findByEmail(userName).getId();
            session_question.setUser_id(user_id);
            quizService.saveSession(session_question);
        }
        model.addAttribute("listChoice", listChoice);
        model.addAttribute("section", section_id[1]);
        return "quiz";
    }

    public Question getRandomList(List<Question> list) {
        //0-4
        int index = random.nextInt(list.size());
        System.out.println("\nIndex :" + index);
        return list.get(index);
    }

}

