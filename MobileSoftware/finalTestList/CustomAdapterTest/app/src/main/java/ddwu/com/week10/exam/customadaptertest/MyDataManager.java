package ddwu.com.week10.exam.customadaptertest;

import java.util.ArrayList;

public class MyDataManager {

    ArrayList<MyData> myDataList;

    public MyDataManager() {
        myDataList = new ArrayList<MyData>();
        myDataList.add ( new MyData(1, "홍길동", "012345") );
        myDataList.add ( new MyData(2, "전우치", "123456") );
        myDataList.add ( new MyData(3, "일지매", "234567") );
    }

    public ArrayList<MyData> getMyDataList() {
        return myDataList;
    }

    public void addData(MyData newData) {
        myDataList.add(newData);
    }

    public void removeData(int idx) {
        myDataList.remove(idx);
    }

    public MyData getData(int idx) {
        return myDataList.get(idx);
    }

    public void updateData(int idx, MyData updateData) {
        myDataList.set(idx, updateData);
    }

}
