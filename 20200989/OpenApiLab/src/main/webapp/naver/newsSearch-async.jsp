<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Naver News Search</title>
	<script src="../js/jquery-3.4.1.min.js" type="text/javascript"></script>
	<script type="text/javascript">	
	function search() {
		
	}

	function showResult(response) {
		
	}
	</script> 
</head>
<body>
	<h2>Search Naver News by Open API</h2>
	<form id="f" method="GET" action="${pageContext.request.contextPath}/naver/newsSearch">
		Keyword: <input type="text" id="keyword" name="keyword" value="반려동물"/>&nbsp;
		Number of News: 
		<input type="text" id="display" name="display" size="3" value="10"/><br><br>
		<input type="hidden" id="responseType" name="responseType" value="json" /> 
 		<input type="button" value="Search by Ajax!" onClick="search()"/><br> 
	</form>
	<br>
	<div id="result"></div>	
</body>
</html>