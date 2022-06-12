package ddwucom.mobile.test08.adapterclicktest;

import java.util.ArrayList;

public class CatManager {
    private ArrayList<String> catList;

    public CatManager() {
        catList = new ArrayList<String>();

        catList.add("노르웨이숲");
        catList.add("러시안블루");
        catList.add("코리안숏헤어");
        catList.add("먼치킨");
        catList.add("터키시앙고라");
        catList.add("페르시안");
        catList.add("스코티시폴드");
    }

    public ArrayList<String> getCatList() {
        return catList;
    }

    public void addData(String cat) {
        catList.add(cat);
    }

    public void removeData(int index) {
        catList.remove(index);
    }
}
