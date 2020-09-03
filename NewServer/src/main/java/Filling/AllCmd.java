package Filling;

import DB.Command;
import GeneralTools.Answer;
import GeneralTools.Information;
import GeneralTools.UTF8Control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;

import static Filling.MessageHandling.UserList;


/**
 * This class describes how commands work
 **/
public class AllCmd {
    public static String answer;
    static Answer answerr = new Answer();

    public static void help(Information information) {
        ResourceBundle bundle=getBundle(information.locale);
        answer = bundle.getString("info")+"\n" +bundle.getString("show")+"\n" +bundle.getString("add")+"\n" +
                bundle.getString("update")+"\n" +bundle.getString("remove_by_id")+"\n" +bundle.getString("clear")+"\n" +
                bundle.getString("execute")+"\n" +bundle.getString("exit")+"\n" +bundle.getString("head")+"\n" +
                bundle.getString("remove_head")+"\n" +bundle.getString("remove_lower")+"\n" +bundle.getString("remove_any_by_form_of_education")+"\n" +
                bundle.getString("filter_starts_with_name")+"\n" +bundle.getString("filter_greater_than_students_count");
        answerr.cmd = "help";
        answerr.setAnswer(answer);
    }

    public static void info(Queue<StudyGroup> StudyGroupPriorityQueue,Information information) throws NullPointerException {
        ResourceBundle bundle=getBundle(information.locale);
        answer = bundle.getString("info_part1")+ + StudyGroupPriorityQueue.size() + bundle.getString("info_part2") + StudyGroupPriorityQueue.peek().getCreationDate();
        answerr.setAnswer(answer);
        answerr.cmd="info";
        System.out.println(answer);
    }

    public static void show(Information information) {
        answer = "";
        answerr.list = new LinkedList<String>();
        MessageHandling.StudyGroupPriorityQueue.stream().sorted(XMLReader.countComparator).forEach(student ->
                answerr.list.add(student.getId() + "," + student.getName() + "," + student.getStudentsCount() + "," + student.getexp() + "," + student.getFormOfEducation() + "," + student.getSemesterEnum() + "," + student.getAdminName()
                        + "," + student.getHeight() + "," + student.getWeight() + "," + student.getColor() + "," + student.getCoordinatesX() + "," + student.getCoordinatesY() + "," + student.getUser() + "," + student.getUserColor()));
        answerr.cmd = "show";
        answerr.setAnswer(answer);
    }

    public static void getColor(Information information) {
        HashSet<String> ColorList = new HashSet<>();
        UserList.stream().forEach(user -> {
            ColorList.add(user.UserColor);
        });
        UserList.stream().forEach(user -> {
            if (user.login.equals(information.login)) {
                if (user.UserColor != null) {
                    answerr.cmd = "getColor";
                    answerr.UserColor = user.UserColor;
                } else {
                    //генерируем цвет
                    while (true) {
                        try {

                            Random rand = new Random();
                            float r = (float) (rand.nextFloat() / 2f + 0.5);
                            float g = (float) (rand.nextFloat() / 2f + 0.5);
                            float b = (float) (rand.nextFloat() / 2f + 0.5);
                            String hex = String.format("#%02X%02X%02X",
                                    (int) (r * 255),
                                    (int) (g * 255),
                                    (int) (b * 255));
                            if (!ColorList.contains(hex)) {
                                user.UserColor = hex;
                                answerr.cmd = "getColor";
                                answerr.UserColor = user.UserColor;
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        });
    }

    public static void add(String name, String count, String exp, String form, String semestr, String groupAdmin, String height, String weight, String eyeColor, String X, String Y, Queue<StudyGroup> StudyGroupPriorityQueue, Information information) throws Exception {
        Command.add(name, count, exp, form, semestr, groupAdmin, height, weight, eyeColor, X, Y, information);
        ResourceBundle bundle=getBundle(information.locale);
        answer = bundle.getString("add_cmd");
        answerr.cmd = "add";
        answerr.setAnswer(answer);
    }

    public static void update(Information information) throws SQLException {
        ResourceBundle bundle=getBundle(information.locale);
        if (Command.checkLogin(information)) {
            try {
                Command.update(information.name, information.count, information.exp, information.form, information.semestr, information.groupAdmin, information.height, information.weight, information.eyeColor, information.X, information.Y, information.idstr);
                Command.readdb();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            answer = bundle.getString("update_cmd");
        } else {
            answer = bundle.getString("update_error");
            Logger.login(Level.INFO, "Попытка изменения элемента не удалась");
        }
        answerr.cmd = "update";
        answerr.setAnswer(answer);
    }


    public static void remove_by_id(Information information) throws Exception {
        ResourceBundle bundle=getBundle(information.locale);
        if (Command.checkLogin(information)) {
            Command.remove_by_id(information);
            Command.readdb();
            answer = bundle.getString("remove_by_id_cmd");
        } else answer = bundle.getString("remove_by_id_error");
        answerr.cmd = "remove_by_id";
        answerr.setAnswer(answer);
    }

    public static void clear(Information information) throws Exception {
        ResourceBundle bundle=getBundle(information.locale);
        Command.clear(information);
        Command.readdb();
        answer = bundle.getString("clear_cmd");
        answerr.cmd = "clear";
        answerr.setAnswer(answer);
    }

    public static void save(Queue<StudyGroup> StudyGroupPriorityQueue) throws IOException {
        XMLWriter.write(StudyGroupPriorityQueue);
        answerr.file = XMLWriter.file1;
        answerr.wrong = 2;
    }

    public static void head(Queue<StudyGroup> StudyGroupPriorityQueue,Information information) {
        ResourceBundle bundle=getBundle(information.locale);
        try {
            StudyGroupPriorityQueue.stream().limit(1).forEach(studyGroup ->
                    answer = bundle.getString("name") + studyGroup.getName() + bundle.getString("number") + studyGroup.getStudentsCount() + ", " + studyGroup.getexp() + bundle.getString("form") + studyGroup.getFormOfEducation() + ", Id: " + studyGroup.getId()
                            + bundle.getString("height") + studyGroup.getHeight() + bundle.getString("weight") + studyGroup.getWeight() + bundle.getString("eyeColor") + studyGroup.getColor() + bundle.getString("coordinateX") + studyGroup.getCoordinatesX() + bundle.getString("coordinateY") + studyGroup.getCoordinatesY());
            answerr.setAnswer(answer);
        } catch (NullPointerException e) {
            answer = bundle.getString("head_error");
            answerr.setAnswer(answer);
        }
        answerr.cmd = "head";
    }

    public static void remove_head(Information information) throws Exception {
        Command.remove_head(information);
        Command.readdb();
        answerr.setAnswer(answer);
        answerr.cmd = "remove_head";
        // Filling.MessageHandling.StudyGroupPriorityQueue = StudyGroupPriorityQueue.stream().skip(1).collect(Collectors.toCollection(() -> new PriorityQueue<>(Filling.XMLReader.countComparator)));
    }

    public static void remove_lower(Information information) throws Exception {
        ResourceBundle bundle=getBundle(information.locale);
        Command.remove_lower(information);
        Command.readdb();
        answer = bundle.getString("remove_lower_cmd");
        answerr.setAnswer(answer);
        answerr.cmd = "remove_lower";
    }


    public static void remove_any_by_form_of_education(Information information) throws Exception {
        ResourceBundle bundle=getBundle(information.locale);
        Command.remove_any_by_form_of_education(information);
        Command.readdb();
        answer = bundle.getString("remove_lower_cmd");
        answerr.setAnswer(answer);
        answerr.cmd = "remove_any_by_form_of_education";
    }

    public static void filter_starts_with_name(Queue<StudyGroup> StudyGroupPriorityQueue, String name,Information information) {
        answer = "";
        ResourceBundle bundle=getBundle(information.locale);
        StudyGroupPriorityQueue.stream().filter(studyGroup -> studyGroup.getName().trim().startsWith(name)).forEach(studyGroup ->
                answer = answer + "\n" +
                        bundle.getString("name") + studyGroup.getName() + bundle.getString("number") + studyGroup.getStudentsCount() + ", " + studyGroup.getexp() + bundle.getString("form") + studyGroup.getFormOfEducation() + ", Id: " + studyGroup.getId()
                        + bundle.getString("height") + studyGroup.getHeight() + bundle.getString("weight") + studyGroup.getWeight() + bundle.getString("eyeColor") + studyGroup.getColor() + bundle.getString("coordinateX") + studyGroup.getCoordinatesX() +
                        bundle.getString("coordinateY") + studyGroup.getCoordinatesY()+ "\n");
        answerr.setAnswer(answer);
        MessageHandling.StudyGroupPriorityQueue = StudyGroupPriorityQueue;
        answerr.cmd = "filter_starts_with_name";
    }


    public static void filter_greater_than_students_count(Queue<StudyGroup> StudyGroupPriorityQueue, long count,Information information) {
        answer = "";
        ResourceBundle bundle=getBundle(information.locale);
        StudyGroupPriorityQueue.stream().filter(student -> student.getStudentsCount() > count).forEach(studyGroup -> answer =
                answer + "\n" +
                        bundle.getString("name") + studyGroup.getName() + bundle.getString("number") + studyGroup.getStudentsCount() + ", " + studyGroup.getexp() + bundle.getString("form") + studyGroup.getFormOfEducation() + ", Id: " + studyGroup.getId()
                        + bundle.getString("height") + studyGroup.getHeight() + bundle.getString("weight") + studyGroup.getWeight() + bundle.getString("eyeColor") + studyGroup.getColor() + bundle.getString("coordinateX") + studyGroup.getCoordinatesX() +
                        bundle.getString("coordinateY") + studyGroup.getCoordinatesY()+ "\n");
        answerr.setAnswer(answer);
        answerr.cmd = "filter_greater_than_students_count";
        MessageHandling.StudyGroupPriorityQueue = StudyGroupPriorityQueue;
    }


    public static void file() {
        if (XMLReader.flag == 1) {
            answer = "Файл не может быть обработан, программа заканчивает работу";
            answerr.setAnswer(answer);
            answerr.wrong = 1;
        } else {
            answerr.wrong = 0;
            answer = "Файл передан";
            answerr.setAnswer(answer);
        }
    }

    public static void exit() {
        answerr.wrong = 2;
    }

    public static ResourceBundle getBundle(Locale locale){
        ResourceBundle bundle=ResourceBundle.getBundle("locale",Locale.forLanguageTag(locale.getLanguage()), new UTF8Control());
        return bundle;
    }
}




