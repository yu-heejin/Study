package example.ajax.pizza;

public class Customer {
	private String name;
	private Address address;   //address ��ü�� �����ϴ� ���� ���� �߰�
	private String phone;
	private String recentOrder;   //3�� ���� : �ֱ� �ֹ� ����
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
