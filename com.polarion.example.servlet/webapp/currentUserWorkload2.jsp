<%@page import="clearcode.ProjectWorkload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Workload Summary</title>
    <style>
        .workload-table {
            border-collapse: collapse;
            width: 100%;
            margin: 20px 0;
        }
        .workload-table th, .workload-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .workload-table th {
            background-color: #f5f5f5;
        }
        .total-time {
            font-weight: bold;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<%!
    // Constants for time conversion
    private static final long MILLISECONDS_PER_MINUTE = 60 * 1000L;
    private static final long MILLISECONDS_PER_HOUR = 3600 * 1000L;
    
    // Helper class to store formatted time
    public static class FormattedTime {
        private final long days;
        private final long hours;
        private final long minutes;
        
        public FormattedTime(long totalMillis, long millisPerDay) {
            this.days = totalMillis / millisPerDay;
            long remainder = totalMillis - (days * millisPerDay);
            this.hours = remainder / MILLISECONDS_PER_HOUR;
            remainder -= (hours * MILLISECONDS_PER_HOUR);
            this.minutes = remainder / MILLISECONDS_PER_MINUTE;
        }
        
        public String format() {
            StringBuilder time = new StringBuilder();
            if (days > 0) {
                time.append(days).append(days == 1 ? " day " : " days ");
            }
            if (hours > 0) {
                time.append(hours).append(hours == 1 ? " hour " : " hours ");
            }
            if (minutes > 0) {
                time.append(minutes).append(minutes == 1 ? " minute " : " minutes ");
            }
            return time.length() > 0 ? time.toString() : "0 minutes";
        }
    }
    
    // Calculate total time from project workloads
    private long calculateTotalTime(List<ProjectWorkload> workloads) {
        long total = 0;
        for (ProjectWorkload workload : workloads) {
            total += workload.getTime();
        }
        return total;
    }
%>

<%
    // Get data from request attributes
    @SuppressWarnings("unchecked")
    List<ProjectWorkload> workloads = (List<ProjectWorkload>) request.getAttribute("pairs");
    long dayLength = ((Long) request.getAttribute("dayLength")).longValue();
    
    // Calculate total time
    long totalTime = calculateTotalTime(workloads);
    
    // Set attributes for JSTL usage
    request.setAttribute("workloads", workloads);
    request.setAttribute("totalTimeFormatted", new FormattedTime(totalTime, dayLength).format());
%>

<h2>Project Workload Summary</h2>
<p>This table shows projects where you have assigned work items.</p>

<table class="workload-table">
    <thead>
        <tr>
            <th>Project Name</th>
            <th>Remaining Time</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="workload" items="${workloads}">
            <tr>
                <td>${workload.projectName}</td>
                <td><%=new FormattedTime(((ProjectWorkload)pageContext.getAttribute("workload")).getTime(), dayLength).format()%></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div class="total-time">
    Total Time: ${totalTimeFormatted}
</div>

</body>
</html>