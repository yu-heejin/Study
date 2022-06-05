function getCustomerInfo() {
	var phone = $("#phone").val();
	if (phone == "") {
		$("#address").val("");
		$("#order").val("");
	}
	else {
		$.ajax({
	 		type: "GET",
			url: "lookupCustomer_xml.jsp?phone=" + escape(phone),
			// url: "lookupCustomer2.jsp?phone=" + escape(phone),
			dataType: "xml",
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

function updatePage(xmlDoc) {    //callback function
	alert("response: " + xmlDoc);
	var name = xmlDoc.getElementsByTagName("name")[0].textContent;
	var street = xmlDoc.getElementsByTagName("street")[0].textContent;
	var city = xmlDoc.getElementsByTagName("city")[0].textContent;
	var state = xmlDoc.getElementsByTagName("state")[0].textContent;
	var zipCode = xmlDoc.getElementsByTagName("zipCode")[0].textContent;
	var recentOrder = xmlDoc.getElementsByTagName("recentOrder")[0].textContent;
	
//	var node = xmlDoc.getElementByTagName("address")[0].childNodes[0];
//	while(node.nodeName != "street") node = node.nextSibling;   //공백을 건너 뜀
//	var street = node.textContent;
	//city, state를 구해야함
	
	var address = name + "\n"
				+ street + "\n"
				+ city + ", "
				+ state + " "
				+ zipCode;
	
	//var greet = "Hi " + name + "! Type your order in here:";
	//이렇게 하면 기존의 Type your order in here: 문자열 안 없어져서 Type your order in here:가 두번 나오게 됨
	
	/* Update the HTML web form */
	$("#address").val(address);
	$("#order").val(recentOrder);
	$("#greeting").text("Hi " + name + "!");
}

function submitOrder() {
	var phone = $("#phone").val();
	var address = $("#address").val();
	var order = $("#order").val();
	var data = {"phone": phone, "address": address, "order": order};
	$.ajax({
 		type: "POST",
		url: "placeOrder_xml.jsp",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8", // default
		data: data,
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

function showConfirmation(response) {    //callback function
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