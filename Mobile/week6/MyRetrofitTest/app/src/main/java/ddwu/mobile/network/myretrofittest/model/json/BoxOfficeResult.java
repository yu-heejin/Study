package ddwu.mobile.network.myretrofittest.model.json;

import java.util.List;

public class BoxOfficeResult {
    //필요한 멤버변수 선언
    private List<DailyBoxOffice> dailyBoxOfficeList;    //이거만 쓰고싶은데, 이것은 여러개의 항목을 담는 리스트

    public List<DailyBoxOffice> getDailyBoxOfficeList() {
        return dailyBoxOfficeList;
    }

    public void setDailyBoxOfficeList(List<DailyBoxOffice> dailyBoxOfficeList) {
        this.dailyBoxOfficeList = dailyBoxOfficeList;
    }
}
