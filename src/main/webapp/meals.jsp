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
                        <javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd" var="parsedDate" />
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
                        <b style="color:black">Edit</b>
                    </td>
                    <td>
                        <b style="color:black">Delete</b>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button type="button" onclick="">Click me </button>
</b>
</body>
</html>
