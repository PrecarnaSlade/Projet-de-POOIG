package Common;

public class Sides<E> {
    /*
    content[0] = up
    content[1] = right
    content[2] = down
    content[3] = left
     */
    private E[] content;

    public Sides(E pUp, E pRight, E pDown, E pLeft) {
        content = (E[]) new Object[4];  //  UGLY but works
        content[0] = pUp;
        content[1] = pRight;
        content[2] = pDown;
        content[3] = pLeft;
    }

    public E getUpSide() {
        return content[0];
    }

    public E getRightSide() {
        return content[1];
    }

    public E getDownSide() {
        return content[2];
    }

    public E getLeftSide() {
        return content[3];
    }

    public void setUpSide(E pContent) {
        content[0] = pContent;
    }

    public void setRightSide(E pContent) {
        content[1] = pContent;
    }

    public void setDownSide(E pContent) {
        content[2] = pContent;
    }

    public void setLeftSide(E pContent) {
        content[3] = pContent;
    }

    public void setContent(E[] content) {
        this.content = content;
    }
}
