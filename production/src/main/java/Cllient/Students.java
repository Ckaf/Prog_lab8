package Cllient;

import jdk.nashorn.internal.objects.annotations.Setter;

public class Students {
     String id;
     String name;
     String count;
     String exp;
     String form;
     String semester;
     String admin_name;
     String height;
     String weight;
     String eyeColor;
     String x;
     String y;
     String User;
     String UserColor;

    public Students(String id, String name, String count, String exp, String form, String semester, String admin_name, String height, String weight, String eyeColor, String x, String y,String User,String UserColor) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.exp = exp;
        this.form = form;
        this.semester = semester;
        this.admin_name = admin_name;
        this.height = height;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.x = x;
        this.y = y;
        this.User=User;
        this.UserColor=UserColor;
    }
    public String getStudents(Students students){
        String str=students.id+","+students.name+","+students.count+","+students.exp+","+students.form+","+students.form+","+students.semester+","+students.admin_name+","+
                students.height+","+students.weight+","+students.eyeColor+","+students.x+","+students.y;
        return str;
    }
    public String getUser(){return User;}

    public String getUserColor(){return UserColor;}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCount() {
        return count;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public String getExp() {
        return exp;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getForm() {
        return form;
    }

    public String getHeight() {
        return height;
    }

    public String getSemester() {
        return semester;
    }

    public String getWeight() {
        return weight;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setUser(String user) {
        User = user;
    }

    public void setUserColor(String userColor) {
        UserColor = userColor;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }
}
