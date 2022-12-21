package Common;

import java.util.ArrayList;

public class InternalObject {
    private final int id;
    private static int internalIdIncr = 0;
    private static final ArrayList<InternalObject> INTERNAL_OBJECT_LIST = new ArrayList<>();

    public InternalObject() {
        this.id = internalIdIncr;
        internalIdIncr++;
        INTERNAL_OBJECT_LIST.add(this);
    }

    public int getId() {
        return id;
    }

    public static ArrayList<InternalObject> getObjectList() {
        return INTERNAL_OBJECT_LIST;
    }

//    public static Object getObjectById()
//    public static void removeObjectById()
}
