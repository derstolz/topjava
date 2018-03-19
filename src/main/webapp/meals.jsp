<%@ page import="ru.javawebinar.topjava.web.MealServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<jsp:useBean id="random" class="java.util.Random" scope="application" />

<style type="text/css">
    .tg {
        width: 100%;
        border: 3px double #000; /* Рамка вокруг таблицы */

    }
    TD, TH {
        padding: 5px; /* Поля вокруг текста */
        border: 0.5px solid #000; /* Рамка вокруг ячеек */

    }
</style>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<b>
    <table class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Time</th>
                <th>Description</th>
                <th>Calories</th>
                <th>Exceed?</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="meal" items="${meals}">
                <tr style="${meal.exceed == true ? "color:red" : "color:green"}">
                    <td>
                            ${meal.id}
                    </td>
                    <td>
                        <javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd HH:mm" var="parsedDate" />
                        ${parsedDate}
                    </td>
                    <td>
                            ${meal.description}
                    </td>
                    <td>
                            ${meal.calories}
                    </td>
                    <td>
                            ${meal.exceed}
                    </td>
                    <td>
                        <a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">Edit</a>
                    </td>
                    <td>
                        <a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <form action="meals">
        <input type="hidden" name="action" value="add" />
        Description:<br>
        <input type="text" name="description" value="${meal.id}">
        <br>
        Time (Like 2010-01-31 15:30):<br>
        <input type="text" name="dateTime" value="">
        <br>
        Calories:<br>
        <input type="text" name="calories" value="">
        <br><br>
        <input type="submit" value="Add meal">
    </form>

    <form method="POST" action='edit' name="edit">
        User ID : <input type="text" readonly="readonly" name="userid"
                         value="<c:out value="${meal.id}" />" /> <br />
        First Name : <input
            type="text" name="firstName"
            value="<c:out value="${meal.description}" />" /> <br />
        Last Name : <input
            type="text" name="lastName"
            value="<c:out value="${meal.calories}" />" /> <br />
            type="submit" value="Submit" />
    </form>



</b>
</body>
</html>
