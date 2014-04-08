<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>渠道管理</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/admin/user/update" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${user.id}"/>
		<fieldset>
			<legend><small>渠道管理</small></legend>
            <div class="control-group">
                <label class="control-label">渠道名:</label>
                <div class="controls">
                    <input type="text" id="name" name="name" value="${user.name}" class="input-large required"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">渠道ID:</label>
                <div class="controls">
                    <input type="text" id="cpId" name="name" value="${user.cpId}" class="input-large required" disabled=""/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">主渠道ID:</label>
                <div class="controls">
                    <select name="parentId" id="parentId">
                        <option value="0" <c:if test="${user.parentId == 0}">selected="true"</c:if>>我是主帐号</option>
                        <c:forEach var="parentUser" items="${parentUsers}" varStatus="parentId">
                            <option value="${parentUser.cpId}" <c:if test="${user.parentId == parentUser.cpId}">selected="true"</c:if>>${parentUser.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
			<div class="control-group">
				<label class="control-label">账号:</label>
				<div class="controls">
					<input type="text" value="${user.loginName}" class="input-large" disabled="" />
				</div>
			</div>
            <div class="control-group">
                <label for="plainPassword" class="control-label">密码:</label>
                <div class="controls">
                    <input type="password" id="plainPassword" name="plainPassword" value="${user.plainPassword}" class="input-large"/>
                </div>
            </div>
            <div class="control-group">
                <label for="plainPassword" class="control-label">扣量比例:</label>
                <div class="controls">
                    <input type="text" id="reduce" name="reduce" value="${user.reduce}" class="input-large"/>%
                </div>
            </div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
	
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
