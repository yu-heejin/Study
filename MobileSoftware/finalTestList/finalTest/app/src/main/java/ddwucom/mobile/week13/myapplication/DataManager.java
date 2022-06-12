package ddwucom.mobile.week13.myapplication;

import android.provider.ContactsContract;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataManager {

    ArrayList<String> subjectList;
    //데이터의 원본 준비
//    subjectList.add("모바일소프트웨어");
//    subjectList.add("네트워크");
//    subjectList.add("웹서비스");
//    subjectList.add("운영체제");
//    subjectList.add("웹프로그래밍2");
    //이 위치에서 add 에러가 나는 이유 : 메소드 안에서 실행되지 않고 클래스 내부에서 실행되고 있기 때문

    public DataManager() {
        subjectList = new ArrayList<String>();
        subjectList.add("모바일소프트웨어");
        subjectList.add("네트워크");
        subjectList.add("웹서비스");
        subjectList.add("운영체제");
        subjectList.add("웹프로그래밍2");
    }

    public ArrayList<String> getSubjectList() {
        return subjectList;
    }

    public void addData(String newSubject) {
        subjectList.add(newSubject);
    }

    public void removeData(int index) {
        subjectList.remove(index);
    }

}
