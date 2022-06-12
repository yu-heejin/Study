package ddwucom.mobile.week13.myapplication3;

public class MyData {
    private int _id;
    private String title;
    private String detail;
    private String state;

    public MyData(int _id, String title, String detail, String state) {
        this._id = _id;
        this.title = title;
        this.detail = detail;
        this.state = state;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
