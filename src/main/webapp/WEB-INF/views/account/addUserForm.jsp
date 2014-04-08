<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>渠道管理</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/admin/user/add" method="post" class="form-horizontal">
		<fieldset>
			<legend><small>添加渠道</small></legend>
            <div class="control-group">
                <label class="control-label">渠道名:</label>
                <div class="controls">
                    <input type="text" id="name" name="name" value="" class="input-large required"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">渠道ID:</label>
                <div class="controls">
                    <input type="text" id="cpId" name="cpId" value="" class="input-large required"/>
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
					<input type="text" name="loginName" value="" class="input-large required" />
				</div>
			</div>
            <div class="control-group">
                <label for="plainPassword" class="control-label">密码:</label>
                <div class="controls">
                    <input type="password" id="plainPassword" name="plainPassword" value="" class="input-large required"/>
                </div>
            </div>
            <div class="control-group">
                <label for="plainPassword" class="control-label">扣量比例:</label>
                <div class="controls">
                    <input type="text" id="reduce" name="reduce" value="0" class="input-large"/>%
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
