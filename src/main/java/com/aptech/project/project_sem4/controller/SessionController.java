package com.aptech.project.project_sem4.controller;

import com.aptech.project.project_sem4.model.*;
import com.aptech.project.project_sem4.service.QuizService;
import com.aptech.project.project_sem4.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/quiz")
public class SessionController {
    @Autowired
    private QuizService quizService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/addChoice", method = RequestMethod.POST)
    public @ResponseBody String addChoice(HttpServletRequest request) {
        String choice_id = request.getParameter("getChoiceId");
        ObjectId question_id = quizService.getChoiceById(choice_id).getQuestion_id();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ObjectId topic_id = quizService.getTopicId(question_id.toString()).getTopic_id();
        ObjectId section_id = quizService.getSectionId(topic_id.toString()).getSectionId();
        //        User user = (User) authentication.getPrincipal();
      //  ObjectId userId = user.getId();
    //    System.out.println(userId);
        ObjectId choice_id_conver = new ObjectId(choice_id);
        System.out.println(authentication.getName());
        String userName = authentication.getName();
        ObjectId user_id = userService.findByEmail(userName).getId();

        Session sessionOfQues_exited = quizService.getSessionExit(question_id.toString());
        if(sessionOfQues_exited == null)
        {
            Session session = new Session();
            session.setUser_id(user_id);
            session.setQuestion_id(question_id);
            session.setChoice_id(choice_id_conver);
            session.setSection_id(section_id);
            quizService.saveSession(session);
            System.out.println("choice id: "+choice_id +"   question id:   " + question_id + "     user id :   " + user_id);
        }
        else {
            sessionOfQues_exited.setChoice_id(choice_id_conver);
            quizService.saveSession(sessionOfQues_exited);
        }
        String ajaxResponse = "";
        return ajaxResponse;
    }

    @RequestMapping(value = "/finishTest", method = RequestMethod.POST)
    public @ResponseBody void finishTest(HttpServletRequest request)
    {
        String section_id = request.getParameter("getSectionId");

        ObjectId section_id_conver = new ObjectId(section_id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        ObjectId user_id = userService.findByEmail(userName).getId();
        List<Session> listChoice = quizService.getListChoice(section_id);
        int mark = 0;
        try {
            for(int i = 0; i< 10; i++)
            {
                String choice_can_check = listChoice.get(i).getChoice_id().toString();
                Answer correct_answer = quizService.checkChoice(listChoice.get(i).getQuestion_id().toString());

                String correct_answer_id = correct_answer.getAnswer_id().toString();
                if (choice_can_check.equals(correct_answer_id))
                {
                    mark++;
                }
            }
            Result result = new Result();
            result.setUser_id(user_id);
            result.setSection_id(section_id_conver);
            result.setMark(mark);
            quizService.saveResult(result);
            Section changeDone = quizService.isDone(section_id);
            changeDone.setDone(true);
            quizService.saveSection(changeDone);
        }catch (Exception e) {
            e.getMessage();
            System.out.println("nullllllllllll");
        }
    }

    @RequestMapping(value = {"/Result"}, method = RequestMethod.GET)
    public ModelAndView afterTest(Model model) {
        List<User> listUser = userService.listAll();
        List<Section> listSection = userService.getListSectionUnDone();
        model.addAttribute("listUser", listUser);
        model.addAttribute("listSection", listSection);
        return new ModelAndView("redirect:/section");
    }
}
