package Filling;

/**
 * It seems that this is where we store and set the student's coordinates
 */
public class Coordinates {
    private float x;
    private Double y;//Поле не может быть null
    public Coordinates(String x, String y) {
        try {


            try {
                this.x = Float.parseFloat(x);
            } catch (NullPointerException e) {
                System.out.println("X не может быть null");
            }
            this.y = Double.valueOf(y);
            if(this.y==null){
                System.out.println("Ошибка в заполнении данных, программа прерывает работу");
                System.exit(0);
            }
        }
        catch (Exception e){
            System.out.println("Данные введены неравильно");
        }

    }

    public void setX(String x) {
        this.x = Float.parseFloat(x);
    }

    public Float getX() {
        return x;
    }

    public void setY(String y) {
        this.y = Double.valueOf(y);
    }

    public Double getY() {
        return y;
    }
}
