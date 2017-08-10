<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/page/taglibs.jsp"%>
<%@include file="/page/NavPageBar.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<%@include file="../common/source.jsp"%>
	
	<style type="text/css">
		.role-content{
			width: 250px;
			border:1px solid #A3A3A3;
			height: 450px;
		}
		
		.role-content-query{
			margin:8px 0px 0px 10px;
		}
	
		.role-content-item{
			border-bottom:1px solid #d0d0d0;
			padding-left:10px;
			line-height:30px;
			color: #303030;
		}
		
		.role-content-item:hover{
			background: #E0EEEE;
		}
		
		.role-content-item-selected{
			background: #d1e6fd
		}
		
		.role-content-item:first-child{
			border-top:1px solid #d0d0d0;
		}
		
		.role-content-query-input{
		    background: white;
		    border-width: 1px;
		    border-style: solid;
		    border-color: #d7d7d7;
		    font-family: Verdana,Geneva,Helvetica,Arial,sans-serif;
		    width: 70%;
		}
		
		.role-content-title{
			font-weight: bold;
			margin-left: 10px;
			margin-top: 10px;
		}
		
		.role-content-query-tn{
			padding: 2px 10px;
    		margin-left: 10px;
		}
		
		.role-content-list{
			margin-top:5px;
			overflow-y: auto;
			height: 400px;
		}
		
		.role-content-item-error{
			padding: 0px 10px;
			color: red;
		}
		
		.table{
			display: table;
			table-layout: fixed;
		}
	
		.column{
			display: table-cell;
		    vertical-align: top;
		    margin: 0;
		    padding: 0;
		}
		
		.row{
			display: table-row;
		}
		
		.permission{
			border:1px solid #A3A3A3;
			border-left:0px;
			height: 450px;
			padding: 10px 15px
		}
		
		.permisson-content-separate{
			width: 100%;
			border: 1px solid #eaeaea;
			margin-top: 20px;
		}
		
		.permisson-content-list{
			margin-top: 10px;
			margin-left: 10px;
		}
		
		.permisson-content-item{
			padding: 8px 0px;
			background: #f2f8ff;
			width: 33.33%;
		}
		
		.permisson-content-select{
			width: 120px;
    		display: inline-block;
		}
		
		.permisson-content-span{
			color: black;
   			margin-right: 10px;
   			display: inline-block;
    		width: 90px;
		}
		
		.permisson-content-input{
			display: inline-block;
		    width: 200px;
		    font-weight: bold;
		}
	</style>
	
	<script type="text/javascript">
		var currentId;
	
		$(function(){
			loadRolelist();
		});
		
		function loadRolelist(){
			$.ajax({
				url: "${ctx}/role/data?data=" + new Date(),
				data: {
					roleName: $("#roleName").val()
				},
				success: function(data){
					$("#role-content-list").empty();
					
					if(data.success){
						data.data.forEach(function(d){
							$("#role-content-list").append("<div class='role-content-item' id='"+ d.id + "' name='"+ d.name +"'>"+ d.name + "</div>");
						});
						addClickEven();
					}else{
						$("#role-content-list").append("<div class='role-content-item-error'>"+ data.msg + "</div>");
						$("#name").val('');
						// 全部选中第一个值
						$('select').prop('selectedIndex', 0);
					}
				}
			});
		}
		
		// 添加点击事件 
		function addClickEven(){
			$(".role-content-item").click(function(){
				$(".role-content-item").removeClass("role-content-item-selected");
				$(this).addClass("role-content-item-selected");
				
				var id = $(this).attr("id");
				var name = $(this).attr("name");
				$("#name").val(name);
				
				currentId = id;
				
				$.ajax({
					url: "${ctx}/role/rolePermission?date=" + new Date(),
					data: {
						id: id
					},
					success: function(d){
						if(d.success){
							if(d.data.permission != null && d.data.permission != ""){
								var vals = d.data.permission.split(",");
								
								vals.forEach(function(rp){
									// 设置值
									var kv = rp.split("-");
									$("#" + kv[0]).val(kv[1]);
								});
							}
						}else{
							errorMsg(data.msg);
						}
					}
				});
				
			});
			
			// 默认点击第一个
			$(".role-content-item").eq(0).trigger("click");
		}
		
		function addRole(){
			currentId = "";
			$("#addBtn").hide();
			$("#name").val('');
			// 全部选中第一个值
			$('select').prop('selectedIndex', 0);
		}
		
		function cancelAdd(){
			$("#addBtn").show();
			// 默认点击第一个
			$(".role-content-item").eq(0).trigger("click");
		}
		
		function saveAdd(){
			var name = $("#name").val();
			
			if(isNull(name)){
				errorMsg("请输入角色名称");
				return;
			}
			
			var permission = "";
			$("select").each(function() {
				console.info($(this).attr("id") + "-" + $(this).val());
				permission += $(this).attr("id") + "-" + $(this).val() + ",";
			});
			
			$.ajax({
				url: "${ctx}/role/addRole?date=" + new Date(),
				data: {
					id: currentId,
					permission: permission,
					name: name
				},
				success: function(data){
					if(data.success){
						
					}else{
						
					}
				}
			});
			
		}
	</script>
</head>

<body>
    <div class="row">
        <div class="col-lg-12">
            <ol class="breadcrumb">
                <li><i class="fa fa-user"></i>角色管理</li>
            </ol>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h2><i class="fa fa-user red"></i><span class="break"></span><strong>角色管理</strong></h2>

                    <span style="float:right;padding-top:5px;">
                        <button type="button" class="btn btn-primary btn-xs" onclick="addRole()" id="addBtn">添加</button>
                    </span>
                </div>

                <div class="panel-body">
                
                	<div class="table">
	                	<div class="role-content column">
	                		<div class="role-content-query">
	                			<input type="text" id="roleName" name="roleName" class="role-content-query-input">
	                			<button class="btn btn-primary role-content-query-tn" onclick="loadRolelist()">查询</button>
	                		</div>
	                		
	                		<div class="role-content-title">角色</div>
	                		<div class="role-content-list" id="role-content-list"></div>
	                	</div>
	                
	                    <div class="permission column">
	                    	<div>
	                    		<span class="role-content-title" style="margin-right: 15px;">角色</span>
	                    		<input type="text" id="name" name="role" class="form-control permisson-content-input">
	                    	</div>
	                    	
	                    	<div class="permisson-content-separate"></div>
	                    	<div class="role-content-title">用户权限</div>
	                    	
	                    	<c:forEach items="${menuList}" var="vo">
	                    		<c:choose>
									<c:when test="${fn:length(vo.subList) > 0}">
	                    				<div class="permisson-content-list">
	                    					<div>${vo.name}</div>
	                    					<div style="border-top: 1px solid #eaeaea; border-bottom: 1px solid #eaeaea" class="table">
                    							<c:forEach items="${vo.subList}" var="subVo" varStatus="vst">
                    							
                    								<c:if test="${vst.index % 3 == 0}">
                    									<div class="row">
                    								</c:if>
                    								
													<div class="permisson-content-item column">
						                    			<span class="permisson-content-span">${subVo.name}</span>
						                    			<select class="form-control permisson-content-select" id="${subVo.alias}">
						                    				<option value="all">可读-可写</option>
						                    				<option value="read">只读</option>
						                    				<option value="not">无权限</option>
						                    			</select>
						                    		</div>
						                    		
						                    		<c:if test="${vst.index != 0 && (vst.index +1) % 3 == 0}">
                    									</div>
                    								</c:if>
												</c:forEach>
	                    					</div>
	                    				</div>
	                    			</c:when>
									<c:otherwise>
										<div class="permisson-content-list">
	                    					<div>${vo.name}</div>
	                    					<div style="border-top: 1px solid #eaeaea; border-bottom: 1px solid #eaeaea" class="table">
												<div class="permisson-content-item column">
					                    			<span class="permisson-content-span">${vo.name}</span>
					                    			<select class="form-control permisson-content-select" id="${vo.alias}">
					                    				<option value="all">可读-可写</option>
					                    				<option value="read">只读</option>
					                    				<option value="not">无权限</option>
					                    			</select>
					                    		</div>
	                    					</div>
	                    				</div>
	                    			</c:otherwise>
								</c:choose>
							</c:forEach>
							
							<div style="margin-top:50px;">
								<button type="button" class="btn btn-primary" onclick="saveAdd()">保存</button>
								<button type="button" class="btn btn-danger" onclick="cancelAdd()">取消</button>									
							</div>
	                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
