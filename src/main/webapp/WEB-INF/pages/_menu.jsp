<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>  

	<ul class="nav navbar-nav">
		<li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
		<li><a href="${pageContext.request.contextPath}/productList">Product
				List</a></li>
		<li><a href="${pageContext.request.contextPath}/shoppingCart">My
				Cart</a></li>
		<security:authorize
			access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
			<li>     <a href="${pageContext.request.contextPath}/orderList">
					         Order List      </a>      
			</li>
   </security:authorize>
		<security:authorize access="hasRole('ROLE_MANAGER')">
			<li><a href="${pageContext.request.contextPath}/product">Create
					Product</a></li>     
   </security:authorize>
	</ul>
