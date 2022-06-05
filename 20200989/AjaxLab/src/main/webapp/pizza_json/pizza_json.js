function getCustomerInfo() {
	var phone = $("#phone").val();
	if (phone == "") {
		$("#address").val("");
		$("#order").val("");
	}
	else {
		$.ajax({
	 		type: "GET",
			url: "lookupCustomer_json.jsp?phone=" + escape(phone),
			// url: "lookupCustomer2.jsp?phone=" + escape(phone),
			dataType: "text",    //alert 출력을 위해 text
			success: updatePage,
			error: function(jqXHR, textStatus, errorThrown) {
				var message = jqXHR.getResponseHeader("Status");
				if ((message == null) || (message.length <= 0)) {
					alert("Error! Request status is " + jqXHR.status);
				} else {
					alert(message);	
				}
			}
		});
	}
}

function updatePage(jsonText) {
	alert("response: " + jsonText);
	/*
	var name = xmlDoc.getElementsByTagName("name")[0].textContent;
	var street = xmlDoc.getElementsByTagName("street")[0].textContent;
	var city = xmlDoc.getElementsByTagName("city")[0].textContent;
	var state = xmlDoc.getElementsByTagName("state")[0].textContent;
	var zipCode = xmlDoc.getElementsByTagName("zipCode")[0].textContent;
	var recentOrder = xmlDoc.getElementsByTagName("recentOrder")[0].textContent;
	*/
//	var node = xmlDoc.getElementByTagName("address")[0].childNodes[0];
//	while(node.nodeName != "street") node = node.nextSibling;   //공백을 건너 뜀
//	var street = node.textContent;
	//city, state를 구해야함
	
	var cust = JSON.parse(jsonText);
	
	var address = cust.name + "\n"
				+ cust.address.street + "\n"
				+ cust.address.city + ", "
				+ cust.address.state + " "
				+ cust.address.zipCode;
	
	//var greet = "Hi " + name + "! Type your order in here:";
	//이렇게 하면 기존의 Type your order in here: 문자열 안 없어져서 Type your order in here:가 두번 나오게 됨
	
	/* Update the HTML web form */
	$("#address").val(address);
	$("#order").val(cust.recentOrder);
	$("#greeting").text("Hi " + cust.name + "!");
}

function submitOrder() {
	var phone = $("#phone").val();
	var address = $("#address").val();
	var order = $("#order").val();
	var data = {"phone": phone, "address": address, "order": order};
	//json 데이터가 아니라 일반적인 자바스크립트 객체임
	var jsonText = JSON.stringify(data);   //객체를 string으로 만듦
	
	alert("submit: " + data);
	$.ajax({
 		type: "POST",
		url: "placeOrder_json.jsp",
		contentType: "application/json; charset=UTF-8", // default
		data: jsonText,
		dataType: "text",
		success: showConfirmation,
		error: function(jqXHR, textStatus, errorThrown) {
			var message = jqXHR.getResponseHeader("Status");
			if ((message == null) || (message.length <= 0)) {
				alert("Error! Request status is " + jqXHR.status);
			} else {
				alert(message);	
			}
		}
	});
}

function showConfirmation(response) {
	// Create some confirmation text
	var p = document.createElement("p");
	p.innerHTML = "Your order should arrive within " +
			response + " minutes. Enjoy your pizza!";
	var span = document.createElement("span");
	$(span).append(p);
	$("#main-page > span").remove();
	$("#main-page").append(span);
	
	// Or you can replace the form with the confirmation as below:
	// $("#order-form").replaceWith(p);	
}