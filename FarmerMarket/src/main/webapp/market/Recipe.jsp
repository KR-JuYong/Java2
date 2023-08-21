<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Market.Recipe"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 제공</title>
</head>
<body>
	<%
	Recipe recipe = new Recipe();
	String customUrl = "https://www.10000recipe.com/recipe/list.html?q=";
	List<String> resultList = new ArrayList<>();
	String itemName = recipe.getCookInfo(resultList);
	String newUrl = customUrl + itemName;
	if (newUrl != null) {
		resultList = recipe.getSearchCook(newUrl);
	}
	%>
</body>
</html>

