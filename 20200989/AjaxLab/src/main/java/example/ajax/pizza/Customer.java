package example.ajax.pizza;

public class Customer {
	private String name;
	private Address address;   //address 객체를 참조하는 참조 변수 추가
	private String phone;
	private String recentOrder;   //3번 문제 : 최근 주문 정보
//	private String street;
//	private String city;
//	private String state;
//	private String zipCode;
	
    public Customer(String name, Address address, String phone, String recentOrder) {
        super();
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.recentOrder = recentOrder;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getRecentOrder() {
        return recentOrder;
    }
    public void setRecentOrder(String recentOrder) {
        this.recentOrder = recentOrder;
    }
	
	
}
