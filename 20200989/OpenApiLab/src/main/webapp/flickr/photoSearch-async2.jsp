<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Flickr Search</title>
	<script src="../js/jquery-3.4.1.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	function search() {
		var url = "https://api.flickr.com/services/rest/?method=flickr.photos.search";
		var apiKey = "dfccfd290d9e710d952ba7ad296f4e47";	    
		var tag = $("#tag").val();
		var pp = $("#perPage").val();  
		var requestURL = url 
			+ "&api_key=" + apiKey	
			+ "&tags=" + tag 
			+ "&per_page=" + pp
			+ "&format=json&nojsoncallback=1";
			
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

	function showResult(response){
		alert(response);
		var res = JSON.parse(response);
		var photos = res.photos.photo;
					
		$("#result").empty().html(
			"<h2>Recent " + $("#perPage").val() + " Photos on <em>" 
			+ $("#tag").val() + "</em></h2>"
			+ "<h3>- retrieved from Flickr Web Service</h3>");
		
		for (var i = 0; i < photos.length; i++) {			
			var para = document.createElement("p");
			$(para).text(photos[i].title + " by " + photos[i].owner)
				.append("<br></br>")
				.appendTo("#result");
			
			var pageUrl = "http://www.flickr.com/photos/" 
						+ photos[i].owner + "/" + photos[i].id;
			var a = document.createElement("a");
			$(a).attr("href", pageUrl)
			    .appendTo(para);
			
			var thumbUrl = "http://farm" +  photos[i].farm
						+ ".static.flickr.com/" +  photos[i].server 
						+ "/" + photos[i].id + "_" + photos[i].secret + "_t.jpg";
			var img = document.createElement("img");
			$(img).attr("alt", photos[i].title)
				.attr("src", thumbUrl)
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
 		<input type="button" value="Search Flickr!" 
 				onClick="search()"/><br> 
	</form>
	<br>
	<div id="result"></div>	
</body>
</html>