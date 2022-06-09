<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Flickr Search</title>
	<script src="../js/jquery-3.4.1.min.js" type="text/javascript"></script>
	<script type="text/javascript">
/*
	var xhttp = new XMLHttpRequest();
		
	function search() {
	  var form = document.getElementById("f");
	  var tag = form.tag.value;
	  var pp = form.perPage.value;  
	  var url = "${pageContext.request.contextPath}/flickr/search"
	  			+ "?responseType=json&tag=" + tag + "&perPage=" + pp;
	  xhttp.open("GET", url, true);
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	showResult(this.responseText); 
	    }
	  };
	  xhttp.send();   
	}

	function showResult(response) {
           alert(response);
	    var photos = JSON.parse(response);
	   
		var result = document.getElementById("result");
           var tag = document.getElementById("f").tag.value;
		result.innerHTML = "<h2>Recent Photos on <em>" + tag + "</em></h2>"
						+ "<h3>- retrieved from Flickr</h3>";
		
		for (var i = 0; i < photos.length; i++) {
			var para = document.createElement("p");
			para.textContent = photos[i].title + " by " + photos[i].owner
			para.appendChild(document.createElement("br"));
			result.appendChild(para);
			
			var a = document.createElement("a");
			a.setAttribute("href", photos[i].pageUrl);
			para.appendChild(a);
			
			var img = document.createElement("img");
			img.setAttribute("alt", photos[i].title);
			img.setAttribute("src", photos[i].thumbUrl);
			a.appendChild(img);		
		}	
	}
*/
	function search() {
		var tag = $("#tag").val();
		var pp = $("#perPage").val();  
		var requestURL = "${pageContext.request.contextPath}/flickr/search"
						+ "?responseType=json"
						+ "&tag=" + tag 
						+ "&perPage=" + pp;
				
		$.ajax({
	 		type: "GET",
			url: requestURL,
			dataType: "text",
			success: showResult,
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

	function showResult(response) { 
		alert(response);
		var photos = JSON.parse(response);
				
		$("#result").empty().html(
			"<h2>Recent " + $("#perPage").val() + " Photos on <em>" 
			+ $("#tag").val() + "</em></h2>"
			+ "<h3>- retrieved from Flickr by <em>FlickrSearcher</em></h3>");
		
		for (var i = 0; i < photos.length; i++) {
			var para = document.createElement("p");
			$(para).text(photos[i].title + " by " + photos[i].owner)
				.append("<br></br>")
				.appendTo("#result");
			
			var a = document.createElement("a");
			$(a).attr("href", photos[i].pageUrl)
			    .appendTo(para);
			
			var img = document.createElement("img");
			$(img).attr("alt", photos[i].title)
				.attr("src", photos[i].thumbUrl)
				.appendTo(a);		
		}	
	}
	</script> 
</head>
<body>
	<h2>Search Flickr by Open API</h2>
	<form id="f" method="GET" action="">
		Tag: <input type="text" id="tag" name="tag" value="puppy"/>&nbsp;
		Number of Photos: 
		<input type="text" id="perPage" name="perPage" size="3" value="5"/><br><br>
 		<input type="button" value="Search recent photos by Ajax!" 
 				onClick="search()"/><br> 
	</form>
	<br>
	<div id="result"></div>	
</body>
</html>