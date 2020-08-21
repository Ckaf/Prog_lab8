package sample;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.awt.*;

public class AnimationClass {
    public double wight;
    public double height;
    public double x;
    public double y;
    public String color;
    public AnimationClass(double wight,double height,double x,double y,String color){
        this.wight=wight/2;
        this.height=height/4;
        this.x=x * 2 - this.wight / 2;
        this.y=y* 2+ this.height / 2;
        this.color=color;
    }

    public double getHeight() {
        return height;
    }

    public double getWight() {
        return wight;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
