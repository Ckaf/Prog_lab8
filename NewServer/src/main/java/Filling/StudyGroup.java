package Filling;

import java.time.ZonedDateTime;
import java.util.Queue;

/**
 * Stored and set the parameters of students
 */

public class StudyGroup {
    Integer id=0; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name;//Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates;//Поле не может быть null
    private ZonedDateTime creationDate;//Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long studentsCount;//Значение поля должно быть больше 0
    private Long expelledStudents;//Значение поля должно быть больше 0, Поле не может быть null
    private Enum.FormOfEducation formOfEducation;//Поле не может быть null
    private Enum.Semester semesterEnum;//Поле не может быть null
    private Person groupAdmin;//Поле не может быть null
    private String exp;
    private String Color;
    private String User;
    public StudyGroup(Queue<StudyGroup> StudyGroupPriorityQueue, String name, String count, String exp, String form, String semestr, String groupAdmin, String height, String weight, String eyeColor, String X, String Y,String User,String Color) throws Exception {
        //creationDate=creationDate.minusDays(0);
        creationDate=ZonedDateTime.now();
//        Iterator<Filling.StudyGroup> it = StudyGroupPriorityQueue.iterator();
        this.name = name.trim();
        if (this.name == null | this.name.equalsIgnoreCase("") ) {
            System.out.println("Ошибка в заполнении данных, программа прерывает работу");
            System.exit(0);
        }

        this.studentsCount = Long.parseLong(count);
        if (this.studentsCount <= 0) {
            System.out.println("Ошибка в заполнении данных, программа прерывает работу");
            System.exit(0);
        }

        this.exp = exp;
        if (this.exp == null ) {
            System.out.println("Ошибка в заполнении данных, программа прерывает работу");
            System.exit(0);
        }
        if (exp.equals("yes") == true) {
            this.exp = "отчислен";
            expelledStudents=1L;
        } else {
            this.exp = "не отчислен";
            expelledStudents=2L;
        }
        form=form.trim();
        if (form.equalsIgnoreCase("full time") == true || form.equals("FULL_TIME_EDUCATION") == true) this.formOfEducation = Enum.FormOfEducation.FULL_TIME_EDUCATION;
        else
        if (form.equalsIgnoreCase("distance") == true|| form.equals("DISTANCE_EDUCATION") == true) this.formOfEducation = Enum.FormOfEducation.DISTANCE_EDUCATION;
        else
        if (form.equalsIgnoreCase("evening") == true|| form.equals("EVENING_CLASSES") == true) this.formOfEducation = Enum.FormOfEducation.EVENING_CLASSES;
        else  {
            System.out.println("Ошибка в заполнении данных, программа прерывает работу");
            System.exit(0);
        }
        semestr=semestr.trim();
        if (semestr.equals("FIFTH") == true || semestr.equals("5") == true ) this.semesterEnum = Enum.Semester.FIFTH;
        else
        if (semestr.equals("6") == true||semestr.equals("SIXTH") == true) this.semesterEnum = Enum.Semester.SIXTH;
        else
        if (semestr.equals("7") == true||semestr.equals("SEVENTH") == true) this.semesterEnum = Enum.Semester.SEVENTH;
        else
        if (semestr.equals("8") == true||semestr.equals("EIGHTH") == true) this.semesterEnum = Enum.Semester.EIGHTH;
        else  {
            System.out.println("Ошибка в заполнении данных, программа прерывает работу");
            System.exit(0);
        }
        this.User=User;
        this.Color=Color;
        this.groupAdmin = new Person(groupAdmin, height, weight, eyeColor);
        this.coordinates = new Coordinates(X, Y);
    }

public Person getGroupAdmin(){return groupAdmin;}
public Coordinates getCoordinates(){return coordinates;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(long studentsCount) {
        this.studentsCount = studentsCount;
    }

    public String getexp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public Enum.FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public String getAdminName() {
        return groupAdmin.getAdminName();
    }

    public void setFormOfEducation(String form) {
        if (form.equals("full time") == true) this.formOfEducation = Enum.FormOfEducation.FULL_TIME_EDUCATION;
        if (form.equals("distance") == true) this.formOfEducation = Enum.FormOfEducation.DISTANCE_EDUCATION;
        if (form.equals("evening") == true) this.formOfEducation = Enum.FormOfEducation.EVENING_CLASSES;
    }

    public Double getHeight() {
        return groupAdmin.getHeight();
    }

    public Enum.Color getColor() {
        return groupAdmin.getColor();
    }

    public Integer getWeight() {
        return groupAdmin.getWeight();
    }

    public Float getCoordinatesX() {
        return coordinates.getX();
    }

    public Double getCoordinatesY() {
        return coordinates.getY();
    }

    public String getUser(){return User;}

    public String getUserColor(){return Color;}

    public void setSemesterEnum(String semestr) {
        if (semestr.equals("5") == true) this.semesterEnum = Enum.Semester.FIFTH;
        if (semestr.equals("6") == true) this.semesterEnum = Enum.Semester.SIXTH;
        if (semestr.equals("7") == true) this.semesterEnum = Enum.Semester.SEVENTH;
        if (semestr.equals("8") == true) this.semesterEnum = Enum.Semester.EIGHTH;
    }

    public Enum.Semester getSemesterEnum() {
        return semesterEnum;
    }
    public ZonedDateTime getCreationDate(){
        return creationDate;
    }
    public Long getExpelledStudents(){
        return expelledStudents;
    }


}

