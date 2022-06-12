package ddwucom.mobile.week13.myapplication3;

import java.util.ArrayList;

public class DataManager {
    private ArrayList<MyData> myDataArrayList;

    public DataManager() {
        myDataArrayList = new ArrayList<>();
        myDataArrayList.add(new MyData(1, "하월곡동", "서울시 성북구", "좋음"));
        myDataArrayList.add(new MyData(2, "은행2동", "성남시 중원구", "맑음"));
    }

    public ArrayList<MyData> getMyDataArrayList() {
        return myDataArrayList;
    }

    public void addData(MyData newData) {
        myDataArrayList.add(newData);
    }

    public void removeData(int idx) {
        myDataArrayList.remove(idx);
    }
}
