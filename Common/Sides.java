package Common;

public class Sides<E> {
    private E up;
    private E right;
    private E down;
    private E left;

    public Sides(E pUp, E pRight, E pDown, E pLeft) {
        up = pUp;
        right = pRight;
        down = pDown;
        left = pLeft;
    }

    public E getUpSide() {
        return up;
    }

    public E getRightSide() {
        return right;
    }

    public E getDownSide() {
        return down;
    }

    public E getLeftSide() {
        return left;
    }

    public void setUpSide(E pContent) {
        up = pContent;
    }

    public void setRightSide(E pContent) {
        right = pContent;
    }

    public void setDownSide(E pContent) {
        down = pContent;
    }

    public void setLeftSide(E pContent) {
        left = pContent;
    }
}
