package regular;

public class RedShapeDecor extends ShapeDecor {

    public RedShapeDecor(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    /**
     * Декорирует фигуру, раскрашивая границу в красный цвет
     * @param decShape ссылка на декорируемую фигуру
     */
    private void setRedBorder(Shape decShape) {
        System.out.println("Border has been decorated by red color!");
    }
}
