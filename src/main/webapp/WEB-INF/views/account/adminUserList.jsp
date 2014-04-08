<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>用户管理</title>
</head>

<body>
<c:if test="${not empty message}">
    <div id="message" class="alert alert-success">
        <button data-dismiss="alert" class="close">×</button>
            ${message}</div>
</c:if>

<legend><small>渠道列表</small></legend>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>渠道ID</th>
        <th>帐号</th>
        <th>密码</th>
        <th>渠道名</th>
        <th>扣量比例</th>
        <th>主渠道ID</th>
        <th>注册时间</th>
        <th>管理</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.cpId}</td>
            <td>${user.loginName}</td>
            <td>${user.plainPassword}</td>
            <td>${user.name}</td>
            <td>${user.reduce}%</td>
            <td>${user.parentId}</td>
            <td>
                <fmt:formatDate value="${user.registerDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <c:if test="${user.id == 1}">
                    <a href="${ctx}/admin/user/add">添加渠道</a>
                </c:if>
                <c:if test="${user.id > 1}">
                    <a href="${ctx}/admin/user/delete/${user.id}">删除</a>
                    <a href="${ctx}/admin/user/update/${user.id}">修改</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
