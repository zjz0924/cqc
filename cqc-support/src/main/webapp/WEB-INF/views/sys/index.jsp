<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/page/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
    	<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>Wow</title>		
		
       <!--[if lt IE 9]>
		<link href="http://fonts.useso.com/css?family=Open+Sans:400" rel="stylesheet" type="text/css" />
		<link href="http://fonts.useso.com/css?family=Open+Sans:700" rel="stylesheet" type="text/css" />
		<link href="http://fonts.useso.com/css?family=Droid+Sans:400" rel="stylesheet" type="text/css" />
		<link href="http://fonts.useso.com/css?family=Droid+Sans:700" rel="stylesheet" type="text/css" />
		<![endif]-->

	    <!-- Css files -->
	    <link href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet">		
		<link href="${ctx}/resources/css/font-awesome.min.css" rel="stylesheet">
		<link href="${ctx}/resources/css/climacons-font.css" rel="stylesheet">
	    <link href="${ctx}/resources/css/style.min.css" rel="stylesheet">
		<link href="${ctx}/resources/css/add-ons.min.css" rel="stylesheet">		

	    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    
	    <!-- start: JavaScript-->
		<script src="${ctx}/resources/js/jquery-2.1.1.min.js"></script>
		<script src="${ctx}/resources/js/core.min.js"></script>
		<script src="${ctx}/resources/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/artDialog4.1.2/artDialog.source.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/artDialog4.1.2/jquery.artDialog.source.js?skin=idialog"></script>
		<script type="text/javascript" src="${ctx}/resources/js/artDialog4.1.2/plugins/iframeTools.source.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/tools.js"></script>
		
		<script type="text/javascript">
			$(function(){
				$("#modular").load(function() {
					$(this).height(0); //用于每次刷新时控制IFRAME高度初始化   
					var height = $(this).contents().height() + 10;
					$(this).height(height < 650 ? 650 : height);
				});
				
				openFrame("main");
			});
		
			function openFrame(src) {
				var frame = document.getElementById("modular");
				frame.src = "${ctx}/" + src;
			}
			
			function adapter(height) {
				$("#modular").height(height < 650 ? 650 : height);
			}
			
			function changepwd(){
				art.dialog.open('${ctx}/accountController/changePwd',{
					id: "changepwdDialog",
			    	padding: 0,
					width: 500,
					height: 300,
					resize: true
				});
			}
		</script>
	</head>
</head>

<body>
	<!-- start: Header -->
	<div class="navbar" role="navigation">
	
		<div class="container-fluid">		
			
			<ul class="nav navbar-nav navbar-actions navbar-left">
				<li class="visible-md visible-lg"><a href="javascript:void(0)" id="main-menu-toggle"><i class="fa fa-th-large"></i></a></li>
				<li class="visible-xs visible-sm"><a href="javascript:void(0)" id="sidebar-menu"><i class="fa fa-navicon"></i></a></li>			
			</ul>
			
	        <ul class="nav navbar-nav navbar-right">
				<li class="dropdown visible-md visible-lg">
	        		<a href="#" class="dropdown-toggle" data-toggle="dropdown">${currentAccount.userName}</a>
	        		<ul class="dropdown-menu">
						<li class="dropdown-menu-header">
							<strong>账号管理</strong>
						</li>						
						<li><a href="javascript:void(0)" onclick="openFrame('accountController/detail?id=${currentAccount.id}&mode=readonly')"><i class="fa fa-user"></i> 个人信息</a></li>
						<li><a href="javascript:void(0)" onclick="changepwd()"><i class="fa fa-wrench"></i> 修改密码</a></li>
	        		</ul>
	      		</li>
				<li><a href="${ctx}/loginout"><i class="fa fa-power-off"></i></a></li>
			</ul>
		</div>
		
	</div>
	<!-- end: Header -->
	
	<div class="container-fluid content">
	
		<div class="row">
				
			<!-- start: Main Menu -->
			<div class="sidebar ">
								
				<div class="sidebar-collapse">
					<div class="sidebar-header t-center">
                        <span style="font-size:20px;font-weight:bold;color:white;">后台管理系统</span>
                    </div>										
					<div class="sidebar-menu">
						<ul class="nav nav-sidebar">
							<li>
								<a href="javascript:void(0)"><i class="fa fa-home"></i><span class="text"> 首页管理</span> <span class="fa fa-angle-down pull-right"></span></a>
								<ul class="nav sub">									
									<li><a href="javascript:void(0)" onclick="openFrame('scrollPicController/list')"><i class="fa fa-picture-o"></i><span class="text">滚动图片管理</span></a></li>
									<li><a href="javascript:void(0)" onclick="openFrame('linkController/list')"><i class="fa fa-link"></i><span class="text">友情链接管理</span></a></li>
								</ul>
							</li>

							<li>
								<a href="javascript:void(0)"><i class="fa fa-user-md"></i><span class="text"> 协会管理</span> <span class="fa fa-angle-down pull-right"></span></a>
								<ul class="nav sub">									
									<li><a href="javascript:void(0)" onclick="openFrame('introduction/detail?type=intro')"><i class="fa fa-book"></i><span class="text">简介管理</span></a></li>
									<li><a href="javascript:void(0)" onclick="openFrame('introduction/detail?type=architecture')"><i class="fa fa-sitemap"></i><span class="text">组织架构管理</span></a></li>
									<li><a href="javascript:void(0)" onclick="openFrame('introduction/detail?type=rule')"><i class="fa fa-list-alt"></i><span class="text">章程管理</span></a></li>
									<li><a href="javascript:void(0)" onclick="openFrame('introduction/detail?type=event')"><i class="fa fa-edit"></i><span class="text">大事记管理</span></a></li>
									<li><a href="javascript:void(0)" onclick="openFrame('soundPic/list')"><i class="fa fa-video-camera"></i><span class="text">声像图片管理</span></a></li>
								</ul>
							</li>
						
							<li>
								<a href="javascript:void(0)"><i class="fa fa-eye"></i><span class="text"> 新闻中心管理</span> <span class="fa fa-angle-down pull-right"></span></a>
								<ul class="nav sub">									
									<li><a href="javascript:void(0)" onclick="openFrame('newsType/list')"><i class="fa fa-list"></i><span class="text">类型管理</span></a></li>
									<li><a href="javascript:void(0)" onclick="openFrame('news/list')"><i class="fa fa-info"></i><span class="text">新闻管理</span></a></li>
								</ul>
							</li>
						
							<li>
								<a href="javascript:void(0)"><i class="fa fa-flask"></i><span class="text"> 政策法规管理</span> <span class="fa fa-angle-down pull-right"></span></a>
								<ul class="nav sub">									
									<li><a href="javascript:void(0)" onclick="openFrame('law/list?type=1')"><i class="fa fa-gavel"></i><span class="text">政策管理</span></a></li>
									<li><a href="javascript:void(0)" onclick="openFrame('law/list?type=2')"><i class="fa fa-lightbulb-o"></i><span class="text">法律法规管理</span></a></li>
								</ul>
							</li>
						
							<li>
								<a href="javascript:void(0)"><i class="fa fa-book"></i><span class="text"> 教育培训管理</span> <span class="fa fa-angle-down pull-right"></span></a>
								<ul class="nav sub">									
									<li><a href="javascript:void(0)" onclick="openFrame('train/list?type=1')"><i class="fa fa-pencil-square"></i><span class="text">培训内容管理</span></a></li>
									<li><a href="javascript:void(0)" onclick="openFrame('train/list?type=2')"><i class="fa fa-check-square-o"></i><span class="text">网络调查表管理</span></a></li>
								</ul>
							</li>
						
							<li>
								<a href="javascript:void(0)"><i class="fa fa-male"></i><span class="text"> 会员服务管理</span> <span class="fa fa-angle-down pull-right"></span></a>
								<ul class="nav sub">
									<li><a href="javascript:void(0)" onclick="openFrame('server/list')"><i class="fa fa-comments"></i><span class="text">服务内容</span></a></li>
								</ul>
							</li>
						
							<li>
								<a href="javascript:void(0)"><i class="fa fa-user"></i><span class="text"> 会员管理</span> <span class="fa fa-angle-down pull-right"></span></a>
								<ul class="nav sub">									
									<li><a href="javascript:void(0)" onclick="openFrame('group/list')"><i class="fa fa-group"></i><span class="text">会员组管理</span></a></li>
									<li><a href="javascript:void(0)" onclick="openFrame('member/list')"><i class="fa fa-user"></i><span class="text">会员管理</span></a></li>
								</ul>
							</li>
						
							<li><a href="javascript:void(0)" onclick="openFrame('activity/list')"><i class="fa fa-bullhorn"></i><span class="text"> 协会活动管理</span></a></li>
						
							<li><a href="javascript:void(0)" onclick="openFrame('contact/detail')"><i class="fa fa-phone-square"></i><span class="text"> 联系方式管理</span></a></li>
											
							<li>
								<a href="javascript:void(0)"><i class="fa fa-cogs"></i><span class="text"> 系统管理</span> <span class="fa fa-angle-down pull-right"></span></a>
								<ul class="nav sub">									
									<li><a href="javascript:void(0)" onclick="openFrame('accountController/list')"><i class="fa fa-user"></i><span class="text">用户管理</span></a></li>
								</ul>
							</li>
						</ul>
					</div>					
				</div>
			</div>
			<!-- end: Main Menu -->
		
		<!-- start: Content -->
		<div class="main">
			<iframe id="modular" name="modular" width="100%" frameborder=0 height="100%" src="" marginheight="0" marginwidth="0"  scrolling="no">
		</div>
		<!-- end: Content -->
		
	</div><!--/container-->
	
	<div class="clearfix"></div>
	
</body>
</html>