<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Recent Photos</title>
</head>
<body>
	<h2>Recent ${photoList.size()} Photos on <em>${tag}</em></h2>
	<h3>- retrieved from Flickr by <em>FlickrSearcher</em></h3>
	
	<c:forEach var="photo" items="${photoList}">
		<p>"${photo.title}" by ${photo.owner}<br>
			<a href='${photo.pageUrl}'>
				<img alt='${photo.title}' src='${photo.thumbUrl}'/>
			</a>
		</p>
	</c:forEach>

</body>
</html>