package Filling;

/**
 * It seems that this is where we store and set the group admin data
 */
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Double height; //Поле может быть null, Значение поля должно быть больше 0
    private Integer weight; //Поле может быть null, Значение поля должно быть больше 0
    private Enum.Color eyeColor; //Поле может быть null

    public Person(String name, String height, String weight, String eyeColor) throws Exception {


        this.name = name.trim();
        if (this.name == null | this.name.equalsIgnoreCase("")) {
            System.out.println("Ошибка в заполнении данных, программа прерывает работу");
            System.exit(0);
        }
        this.height = Double.parseDouble(height);
        if (this.height == null | this.height <= 0) {
            System.out.println("Ошибка в заполнении данных, программа прерывает работу");
            System.exit(0);
        }
        this.weight = Integer.parseInt(weight);
        if (this.weight == null | this.weight <= 0) {
            System.out.println("Ошибка в заполнении данных, программа прерывает работу");
            System.exit(0);
        }
        if (eyeColor.equalsIgnoreCase("black")) this.eyeColor = Enum.Color.BLACK;
        else {
            if (eyeColor.equalsIgnoreCase("red")) this.eyeColor = Enum.Color.RED;
            else {
                if (eyeColor.equalsIgnoreCase("orange")) this.eyeColor = Enum.Color.ORANGE;
                else if (eyeColor.equalsIgnoreCase("brown")) this.eyeColor = Enum.Color.BROWN;
                else {
                    System.out.println("Ошибка в заполнении данных, программа прерывает работу");
                    System.exit(0);
                }
            }
        }
    }


    public Double getHeight() {
        return height;
    }

    public Enum.Color getColor() {
        return eyeColor;

    }

    public void setEyeColor(String color) {
        if (color.equalsIgnoreCase("black")) this.eyeColor = Enum.Color.BLACK;
        if (color.equalsIgnoreCase("red")) this.eyeColor = Enum.Color.RED;
        if (color.equalsIgnoreCase("orange")) this.eyeColor = Enum.Color.ORANGE;
        if (color.equalsIgnoreCase("brown")) this.eyeColor = Enum.Color.BROWN;
    }

    public String getAdminName() {
        return name;
    }

    public void setAdminName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = Integer.parseInt(weight);
    }

    public void setHeight(String height) {
        this.height = Double.parseDouble(height);
    }
}

