<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
    <meta charset="UTF-8">
    <title>Polarion Work Items</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .message {
            padding: 10px;
            margin: 10px 0;
            border-radius: 4px;
        }
        .error {
            background-color: #ffe6e6;
            color: #cc0000;
        }
        .success {
            background-color: #e6ffe6;
            color: #006600;
        }
    </style>
</head>
<body>
    <h1>Polarion Work Items</h1>
    
    <c:if test="${not empty error}">
        <div class="message error">${error}</div>
    </c:if>
    
    <c:if test="${not empty message}">
        <div class="message success">${message}</div>
    </c:if>
    
    <c:if test="${not empty workItems}">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Project</th>
                    <th>Type</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${workItems}" var="item">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.title}</td>
                        <td>${item.projectId}</td>
                        <td>${item.type}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
