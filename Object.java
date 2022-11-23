import java.util.ArrayList;

public class Object {
    private final int id;
    private static int internalIdIncr = 0;
    private static final ArrayList<Object> objectList = new ArrayList<>();

    public Object() {
        this.id = internalIdIncr;
        internalIdIncr++;
        objectList.add(this);
    }

    public int getId() {
        return id;
    }

    public static ArrayList<Object> getObjectList() {
        return objectList;
    }

//    public static Object getObjectById()
//    public static void removeObjectById()
}
