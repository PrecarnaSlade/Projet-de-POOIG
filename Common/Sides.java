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

    public E getTopSide() {
        return up;
    }

    public E getRightSide() {
        return right;
    }

    public E getBottomSide() {
        return down;
    }

    public E getLeftSide() {
        return left;
    }

    public void setTopSide(E pContent) {
        up = pContent;
    }

    public void setRightSide(E pContent) {
        right = pContent;
    }

    public void setBottomSide(E pContent) {
        down = pContent;
    }

    public void setLeftSide(E pContent) {
        left = pContent;
    }
}
