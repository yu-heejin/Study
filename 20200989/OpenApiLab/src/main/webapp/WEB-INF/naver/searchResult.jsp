<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>News Search Result</title>
</head>
<body>
	<h2>Recent ${newsList.size()} news articles on "${keyword}"</h2>
	<h3>- retrieved from Naver News by <em>NaverNewsSearcher</em></h3>
	<hr>
	<c:forEach var="news" items="${newsList}">
		<p>
			News #${news.no}: 
			<em>"${news.title}"</em><br>
			<br>
			${news.description}<br>
			<br>
			- link: <a href="${news.link}">${news.link}</a><br>
			- original link: <a href="${news.originallink}">${news.originallink}</a><br>
			- publish date: ${news.pubDate}<br>
		</p>
		<hr>
	</c:forEach>
</body>
</html>
		