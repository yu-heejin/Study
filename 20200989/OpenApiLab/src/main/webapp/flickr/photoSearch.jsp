<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Flickr Search</title>
</head>
<body>
	<h2>Search Flickr by Open API</h2>
	<form id="f" method="GET" action="${pageContext.request.contextPath}/flickr/search" >
		Tag: <input type="text" id="tag" name="tag" value="puppy"/>&nbsp;
		Number of Photos: 
		<input type="text" id="perPage" name="perPage" size="3" value="5"/><br><br>
		<input type="hidden" id="responseType" name="responseType" value="html" /> 
		<input type="submit" value="Search recent photos!"/><br>
	</form>
</body>
</html>