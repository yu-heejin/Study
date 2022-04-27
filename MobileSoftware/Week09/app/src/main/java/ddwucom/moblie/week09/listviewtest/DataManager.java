package ddwucom.moblie.week09.listviewtest;

import android.provider.ContactsContract;

import java.util.ArrayList;

public class DataManager {
    private ArrayList<String> subjectList;

    public DataManager() {  //생성자 안에 원본 데이터 생성
        subjectList = new ArrayList<String>();
        subjectList.add("모바일소프트웨어");
        subjectList.add("네트워크");
        subjectList.add("시스템프로그래밍");
        subjectList.add("웹서비스");
        subjectList.add("시스템/네트워크보안");
    }

    public ArrayList<String> getSubjectList() {
        return subjectList;
    }

    //리스트 뷰에 새로운 항목 추가 및 삭제?  -> 화면과 원본 데이터 바뀌어야함
    public void addData(String newSubject) {
        subjectList.add(newSubject);
    }
    
    public void removeData(int idx) {
        subjectList.remove(idx);    //해당 위치에 있는 데이터를 삭제함
    }

    //몇번째 있는 요소인지 알아냄
    public String getSubject(int idx) {
        String msg = subjectList.get(idx) + " 과목입니다.";   //해당 인덱스 항목의 값을 받아온다.
        return msg;
    }
}
