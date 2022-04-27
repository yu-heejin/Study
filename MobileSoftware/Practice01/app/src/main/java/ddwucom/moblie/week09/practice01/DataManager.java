package ddwucom.moblie.week09.practice01;

import java.util.ArrayList;

public class DataManager {

    ArrayList<String> arrayList;

    public DataManager() {
        arrayList = new ArrayList<String>();

        arrayList.add("믹스견");
        arrayList.add("시츄");
        arrayList.add("말티즈");
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }
}
