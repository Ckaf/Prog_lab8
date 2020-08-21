package Filling;

import GeneralTools.Answer;

import java.util.LinkedList;
import java.util.logging.Level;

import static Filling.MessageHandling.UserList;
import static Filling.SendUpdate.sendUpdate;

public class Synchronization {
    public static void synchronization() {
        Answer answer = new Answer();
        answer.list = new LinkedList<>();
        MessageHandling.StudyGroupPriorityQueue.stream().sorted(XMLReader.countComparator).forEach(student ->
                answer.list.add(student.getId() + "," + student.getName() + "," + student.getStudentsCount() + "," + student.getexp() + "," + student.getFormOfEducation() + "," + student.getSemesterEnum() + "," + student.getAdminName()
                        + "," + student.getHeight() + "," + student.getWeight() + "," + student.getColor() + "," + student.getCoordinatesX() + "," + student.getCoordinatesY()+ "," +student.getUser()+ "," +student.getUserColor()));
        answer.cmd = "show";
        Logger.login(Level.INFO, "Происходит синхронизация");

        UserList.forEach(user -> {
            sendUpdate(user.UpdateAddress, answer);
        });

    }
}
