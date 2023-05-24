package regular;

public class GreenShapeDecor extends ShapeDecor {

        public GreenShapeDecor(Shape decoratedShape) {
            super(decoratedShape);
        }

        @Override
        public void draw() {
            decoratedShape.draw();
            setGreenBorder(decoratedShape);
        }

    /**
     * Декорирует фигуру, раскрашивая границу в зеленый цвет
     * @param decShape ссылка на декорируемую фигуру
     */
    private void setGreenBorder(Shape decShape) {
            System.out.println("Border has been decorated by green color!");
        }
}
