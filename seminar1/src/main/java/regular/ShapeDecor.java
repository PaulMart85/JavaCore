package regular;

public abstract class ShapeDecor implements Shape {

    protected Shape decoratedShape;

    public ShapeDecor(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    /**
     * Рисует декорированную фигуру
     */
    public void draw() {
        decoratedShape.draw();
    }
}
