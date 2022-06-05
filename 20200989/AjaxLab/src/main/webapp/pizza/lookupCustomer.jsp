<%@ page import="example.ajax.pizza.*" contentType="text/html; charset=utf-8" %>
<%
Customer[] customers = new Customer[4];

// Set up some addresses to use
customers[0] = new Customer("Doug Henderson",
		new Address("7804 Jumping Hill Lane", "Dallas", "Texas", "75218"),
		"010-111-1111", "no recent order");
customers[1] = new Customer("Mary Jenkins",
		new Address("7081 Teakwood #24C", "Dallas", "Texas", "75182"),
		"010-222-2222", "no recent order");
customers[2] = new Customer("John Jacobs",
		new Address("234 East Rutherford Drive", "Topeka", "Kansas", "66608"),
		"010-333-3333", "no recent order");
customers[3] = new Customer("Happy Traum",
		new Address("876 Links Circle", "Topeka", "Kansas", "66608"),
		"010-444-4444", "no recent order");

String phone = request.getParameter("phone");
System.out.println("phone number: " + phone);   

// Pick a customer
int i = (int)(Math.random()*4);   //전화번호를 무시하고 랜덤으로 보내줌
Customer c = customers[i];
String result = c.getName() + "\n" + c.getAddress();

System.out.println("result: " + result);   
out.print(result);
%>