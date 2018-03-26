<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books Shop Online</title>
<link href="<c:url value="/resources/css/styles.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/menu.css" />"
    rel="stylesheet">
<link
	href="<c:url value="/resources/css/footer-distributed-with-contact-form.css" />"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />
	<div class="page-title">Shopping Cart Demo</div>
	<div class="demo-container">
		<h3>Demo content</h3>
		<ul>
			<li>Buy online</li>
			<li>Admin pages</li>
			<li>Reports</li>
		</ul>
	</div>
	<jsp:include page="_footer.jsp" />
</body>
</html>