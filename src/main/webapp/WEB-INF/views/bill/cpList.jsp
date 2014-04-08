<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>数据统计</title>
</head>

<body>
<c:if test="${not empty message}">
    <div id="message" class="alert alert-success">
        <button data-dismiss="alert" class="close">×</button>
            ${message}</div>
</c:if>

<legend><small>数据统计</small></legend>

<shiro:user>
    <div class="">
        <div class="">
            <form class="form-search" action="#">
                <c:if test="${!empty trUser}">
                    <label>渠道：</label>
                    <select name="cpId" id="cpId">
                        <option value="0" <c:if test="${billCondition.cpId == 0}">selected="true"</c:if>>全部</option>
                        <c:forEach var="user" items="${trUser}" varStatus="parentId">
                            <option value="${user.cpId}" <c:if test="${billCondition.cpId == user.cpId}">selected="true"</c:if>>${user.name}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <label>时间：</label>
                <input type="text" name="btime" class="input-medium form_datetime" value="${billCondition.btime}">到
                <input type="text" name="etime" class="input-medium form_datetime" value="${billCondition.etime}">
                <button type="submit" class="btn" id="search_btn">查询</button>
            </form>
        </div>
    </div>

    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>日期</th>
            <th>主渠道</th>
            <th>子渠道</th>
            <th>渠道名称</th>
            <th>渠道用户</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bills}" var="bill">
            <tr>
                <td>${bill.date}</td>
                <td>${bill.parentId}</td>
                <td>${bill.cpId}</td>
                <td>${bill.cpName}</td>
                <td>${bill.cpUsers}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</shiro:user>
</body>
</html>
