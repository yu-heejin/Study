<%@ page contentType="text/xml; charset=utf-8" %>
<%@ page import="example.ajax.pizza.*,java.util.*"%>
<%-- <%@ page import="com.fasterxml.jackson.databind.*"%>   --%>
<%! @SuppressWarnings("unchecked") %>
<%
Customer[] customers = new Customer[4];

//Set up some addresses to use
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
// application에서 "custMap" 객체 검색
Map<String, Customer> custMap = (Map<String, Customer>)application.getAttribute("custMap"); 

if (custMap == null) {	// "custMap"이 존재하지 않으면 새로 생성
	//Set up some customers to use 
	custMap = new HashMap<String, Customer>();

	for(Customer c : customers) {
	    custMap.put(c.getPhone(), c);
	}
	
	application.setAttribute("custMap", custMap);	// "custMap"을 application에 저장
	//application : 프로그램 내에 있는 공유메모리
	//custMap이 유지될 수 있도록 도움
}

String phone = request.getParameter("phone");
System.out.println("phone number: " + phone);   //화면이 아니라 콘솔에 출력   

//find a customer having the given phone number
Customer c = custMap.get(phone);	// "custMap"에서 검색
if (c != null) {				// unregistered customer	
%>
	<result>
		<name><%= c.getName() %></name>
		<address>
			<street><%= c.getAddress().getStreet() %></street>
			<city><%= c.getAddress().getCity() %></city>
			<state><%= c.getAddress().getState() %></state>
			<zipCode><%= c.getAddress().getZipCode() %></zipCode>
		</address>
		<recentOrder><%= c.getRecentOrder() %></recentOrder>
	</result>
<%

//	String result = c.getName() + "\n" + c.getAddress();
//	System.out.println("result: " + result);
//	out.clearBuffer();
//	out.print(result);   //response
	/*
	 xml 버전에서는 xml형태로 바꿔서 xml 데이터로 전송
	 xml 태그를 넣으면 html 태그와 유사하게 그대로 응답 메시지로 전송됨
	 */
}
else {
	response.setStatus(400);		// bad request, 못찾으면 에러 전송(헤더값만 리턴함)
	response.addHeader("Status", "Unregistered customer");
}
%>