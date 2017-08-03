<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/page/taglibs.jsp"%>
<%@include file="/page/NavPageBar.jsp"%>

<html>
<head>
	<meta charset="utf-8">
	<link href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="${ctx}/resources/css/font-awesome.min.css" rel="stylesheet">
	<link href="${ctx}/resources/css/style.min.css" rel="stylesheet">
	<link href="${ctx}/resources/css/add-ons.min.css" rel="stylesheet">
</head>

<body>
	
	<div class="row">
		<div class="col-lg-12">
			<ol class="breadcrumb">
				<li><i class="fa fa-home"></i>首页</li>
			</ol>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<h2>欢迎使用</h2>
					
					当前用户：${currentAccount.userName}</br>
					登录时间： ${loginTime}</br>
					
					会员总数：${memberNum}
					
				</div>
			</div>
		</div>
	</div>

</body>


</html>