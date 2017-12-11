package com.school.controllers.WebControllers;

import com.school.controllers.WebControllers.admin.*;
import com.school.controllers.WebControllers.mentor.*;
import com.school.controllers.WebControllers.mentor.artifacts_controllers.*;
import com.school.controllers.WebControllers.mentor.quest_controllers.*;
import com.school.controllers.WebControllers.mentor.student_controllers.*;
import com.school.controllers.WebControllers.student.*;
import com.school.controllers.WebControllers.utilController.Static;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;


public class App {

        public static void main(String[] args) throws Exception {
            // create a server on port 8000
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

            // set routes

            //log in
            server.createContext("/loginForm", new LoginWebController());
            server.createContext("/signUp", new SignUpWebController());

            server.createContext("/logOut", new LogOutController());

            //students
            server.createContext("/shop", new ShopController());
            server.createContext("/students", new StudentWebController());
            server.createContext("/myartifacts", new ArtifactWebController());
            server.createContext("/quests", new StudentQuestWebController());
            server.createContext("/availablequests", new QuestWebController());
            server.createContext("/basket", new BasketController());
            server.createContext("/payment", new PaymentController());
            server.createContext("/transactionSuccessful", new TransactionDoneController());
//            server.createContext("/transactionUnsuccessful", new TransactionDoneController());



            //admins
            server.createContext("/admins", new AdminWebController());
            server.createContext("/add_mentor", new AddMentorController());
            server.createContext("/edit_mentor", new EditMentorController());
            server.createContext("/edit_mentor_submit", new SubmitMentorEdition());
            server.createContext("/add_course", new AddCourseController());
            server.createContext("/show_mentor", new ShowMentorController());
            server.createContext("/show_course", new ShowCourseController());

            //mentors
            server.createContext("/mentors", new MentorWebController());
            server.createContext("/managestudents", new ManageStudentController());
            server.createContext("/addstudent", new AddStudentController());
            server.createContext("/editstudent", new EditStudentController());
            server.createContext("/edit_student_submit", new EditStudentSubmit());
            server.createContext("/deletestudent", new DeleteStudentController());
            server.createContext("/delete_student_submit", new DeleteStudentSubmit());
            server.createContext("/showstudents", new ShowStudentsController());
            server.createContext("/managequests", new ManageQuestController());
            server.createContext("/addquest", new AddQuestController());
            server.createContext("/markstudentquest", new MarkStudentQuestController());
            server.createContext("/mark_student_submit", new MarkStudentSubmit());
            server.createContext("/manageartifacts", new ManageArtifactsController());
            server.createContext("/markstudentartifact", new MarkStudentArtifactController());
            server.createContext("/markstudentartifactsubmit", new SubmitMarkStudentArtifact());
            server.createContext("/addartifact", new AddArtifactController());
            server.createContext("/editartifact", new EditArtifactController());
            server.createContext("/editartifactsubmit", new SubmitEditArtifact());
            server.createContext("/deleteartifacts", new DeleteArtifactsController());
            server.createContext("/deletequest", new DeleteQuestController());
            server.createContext("/deletesubmission", new SubmitToDeleteArtifact());
            server.createContext("/showcourses", new ShowCoursesController());
            server.createContext("/deletesubmissionquest", new SubmitToDeleteQuest());




            //static
            server.createContext("/static", new Static());
            server.setExecutor(null); // creates a default executor


            // start listening
            server.start();
        }
    }

