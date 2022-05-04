package ddwucom.moblie.week10.customadatrtst;

public class MyData {
    private long _id;
    private String name;
    private String phone;

    //생성자와 get/set 생성하기
    public MyData(long _id, String name, String phone) {    //매개변수 존재한다 == 값이 반드시 존재해야한다
        this._id = _id;    //id이름을 long, _id로 지을 것을 권장함(DB 자동저장)
        this.name = name;
        this.phone = phone;
    }

    public long get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
