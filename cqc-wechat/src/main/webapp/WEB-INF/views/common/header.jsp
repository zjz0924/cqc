<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/page/taglibs.jsp"%>
<%@include file="/page/NavPageBar.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>header</title>
		
		<script type="text/javascript" src="${ctx}/resources/js/artDialog4.1.7/artDialog.source.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/artDialog4.1.7/jquery.artDialog.source.js?skin=idialog"></script>
		<script type="text/javascript" src="${ctx}/resources/js/artDialog4.1.7/plugins/iframeTools.source.js"></script>
		
		<script type="text/javascript" src="${ctx}/resources/frame/js/ddsmoothmenu.js"></script>
		<script type="text/javascript">
			function login(){
				art.dialog.open('${ctx}/member/loginPage',{
					id: "loginDialog",
			    	padding: 0,
					width: 500,
					height: 300,
					lock: true,
					resize: false,
					close: function(){
						window.location.reload();
					}
				});
			}
			
			
			function register() {
				art.dialog.open('${ctx}/member/registerPage', {
					id : "registerDialog",
					padding : 0,
					width : 600,
					height : 550,
					resize : false,
					close : function() {
						window.location.reload();
					},
					lock: true
				});
			}

			function logout() {
				$.ajax({
					url : "${ctx}/member/logout?time=" + new Date(),
					success : function(data) {
						if (data.success) {
							art.dialog.tips(data.msg, 2, "succeed", function() {
								window.location.reload();
							});
						} else {
							art.dialog.tips(data.msg, 2, "error");
						}
					}
				});
			}
		</script>
	</head>
	
	<body>
		<!-- Wrapper Start -->
		<div id="outer_wrapper">
			<!-- Top Bar Start -->
			<div id="top-bar" class="backcolr">
				<div class="inner">
					<!-- Styleswitcher Start -->
					<div class="styles">
						<ul>
							<li><h6 class="white">主题颜色:</h6></li>
							<li><a href="index.html@style=style1" rel="styles1" class="styleswitch red">&nbsp;</a></li>
							<li><a href="index.html@style=style2" rel="styles2" class="styleswitch blue">&nbsp;</a></li>
							<li><a href="index.html@style=style3" rel="styles3" class="styleswitch gray">&nbsp;</a></li>
							<li><a href="index.html@style=style4" rel="styles4" class="styleswitch orange">&nbsp;</a></li>
							<li><a href="index.html@style=style5" rel="styles5" class="styleswitch green">&nbsp;</a></li>
							<li><a href="index.html@style=style6" rel="styles6" class="styleswitch orange-red">&nbsp;</a></li>
						</ul>
					</div>
					<!-- Styleswitcher End -->
					
					<!-- Top Bar Text Start -->
					<p class="text">欢迎您加入${contact.name}，协会会员已有[ <span style="color:red;font-weight:bold;">${memberNum}</span> ]家企业！
						<c:choose>
							<c:when test="${empty currentMember.userName}">
								<a href="javascript:void()" onclick="login()">[登录]</a>&nbsp;
								<a href="javascript:void()" onclick="register()">[注册]</a>
							</c:when>
							<c:otherwise>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<span>${currentMember.userName}</span>&nbsp;&nbsp;
								<a href="javascript:void()" onclick="logout()">[退出]</a>
							</c:otherwise>
						</c:choose>
					</p>
					<!-- Top Bar Text End -->
				</div>
				<div class="clear"></div>
			</div>
			<!-- Top Bar End -->
			
			<!-- Header Start -->
			<div id="header">
				<div class="rays">
					<div class="inner">
						<!-- Logo Start -->
						<div class="logo">
							<a href="${ctx}/index"><img src="${ctx}/resources/frame/images/logo.png" alt="" /></a>
						</div>
						<!-- Logo End -->
						
						<div class="right">
							<!-- Navigation Start -->
							<div class="navigation">
								<div id="smoothmenu1" class="ddsmoothmenu">
									<ul>
										<li class="current-menu-item"><a href="${ctx}/index">首页</a></li>
										<li><a href="blog.html">走进协会</a></li>
										<li><a href="sermons.html">新闻中心</a></li>
										<li><a href="contact-us.html">政策法规</a></li>
										<li><a href="contact-us.html">教育培训</a></li>
										<li><a href="contact-us.html">会员服务</a></li>
										<li><a href="contact-us.html">会员风采</a></li>
										<li><a href="contact-us.html">协会活动</a></li>
										<li><a href="contact-us.html">联系我们</a></li>
									</ul>
									<div class="clear"></div>
								</div>
							</div>
							<!-- Navigation End -->
							
							<!-- Search Start -->
							<div class="search">
								<a href="#" class="btn">&nbsp;</a>
								<div class="searchinputs backcolr">
									<ul>
										<li class="left">
											<input name="txtSearch" value="输入关键字" onfocus="if(this.value=='Enter Keyword') {this.value='';}"
												onblur="if(this.value=='') {this.value='Enter Keyword';}" type="text" />
										</li>
										<li class="right"><button class="colr">搜索</button></li>
									</ul>
								</div>
							</div>
							<!-- Search End -->
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
			<!-- Header End -->
	</body>
</html>