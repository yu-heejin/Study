function getCustomerInfo() {
	var phone = $("#phone").val();
	if (phone == "") {
		$("#address").val("");
		$("#order").val("");
	}
	else {
		$.ajax({
	 		type: "GET",
			url: "lookupCustomer.jsp?phone=" + escape(phone),
			// url: "lookupCustomer2.jsp?phone=" + escape(phone),
			dataType: "text",
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

function updatePage(address) {
	alert("response: " + address);
	/* Update the HTML web form */
	$("#address").val(address);
}

function submitOrder() {
	var phone = $("#phone").val();
	var address = $("#address").val();
	var order = $("#order").val();
	var data = {"phone": phone, "address": address, "order": order};
	$.ajax({
 		type: "POST",
		url: "placeOrder.jsp",
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