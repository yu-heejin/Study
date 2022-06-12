package ddwucom.mobile.week13.myapplication2;

public class MyData {
    //DTO 향태의 클래스 : 한 항목의 값을 저장할 자료 저장 객체
    private int _id;   //한 항목을 표현할 데이터 저장 변수 선언 -> 반드시 식별 정보 추가
    private String name;
    private String phone;
    
    public MyData(int _id, String name, String phone) {
        this._id = _id;
        this.name = name;
        this.phone = phone;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    //강의자료에는 setter가 있어서 추가했지만, 원래 DTO에는 setter를 만들면 안된다
}
