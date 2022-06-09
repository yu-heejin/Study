<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Naver News Search</title>
</head>
<body>
	<h2>Search Naver News by Open API</h2>
	<form id="f" method="GET" action="${pageContext.request.contextPath}/naver/newsSearch">
		Keyword: <input type="text" id="keyword" name="keyword" value="반려동물"/>&nbsp;
		Number of News: 
		<input type="text" id="display" name="display" size="3" value="5"/><br><br>
		<input type="hidden" id="responseType" name="responseType" value="html" /> 
		<input type="submit" value="Search!"/><br>
	</form>
</body>
</html>