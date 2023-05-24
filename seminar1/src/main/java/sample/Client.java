package sample;

import regular.*;

public class Client {

    public static void main(String[] args) {

        Shape circle = new Circle();
        System.out.println("\nJust a circle:");
        circle.draw();

        System.out.println("\nThe circle with red border:");
        Shape redCircle = new RedShapeDecor(circle);
        redCircle.draw();
        System.out.println("\nThe circle with green border:");
        Shape greenCircle = new GreenShapeDecor(circle);
        greenCircle.draw();


        System.out.println("\nThe triangle with red border:");
        new RedShapeDecor(new Triangle()).draw();
        System.out.println("\nThe triangle with green border:");
        new GreenShapeDecor(new Triangle()).draw();
    }
}
