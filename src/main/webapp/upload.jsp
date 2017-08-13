<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Audio</title>

<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/js/fileUpload.js"></script>

<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="assets/css/main.css">
<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
<link rel="stylesheet" href="assets/css/demo.css">
<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../../css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/webuploader.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/webuploader.css"
	rel="external nofollow">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/webuploader.js"></script>

</head>
<body>


	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<form action="searchMusicFile.do" method="get"
				class="navbar-form navbar-left">
				<table>
					<tr>
						<th>
							<input type="hidden" name="pageNow" value="1">
						</th>
						<th>
							文件名称：
							<input type="text" name="name" value="">
						</th>
						<th>
							持续时长：
							<select name="durationStatus">
								<option value="over">大于</option>
								<option value="under">小于</option>
								<option value="equal">等于</option>
							</select>

							<input type="text" name="duration" value="">
						</th>
						<th>
							文件大小：
							<select name="fileStatus">
								<option value="over">大于</option>
								<option value="under">小于</option>
								<option value="equal">等于</option>
							</select>

							<input type="text" name="fileSize" value="">
						</th>
						<th>
							上传时间：
							<input type="text" name="timeBegin" value="2000-01-01 00:00:00"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
								class="Wdate" />
						</th>
						<th>
							至
							<input type="text" name="timeEnd" value="2020-01-01 00:00:00"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
								class="Wdate" />
						</th>
						<th>
							<input type="submit" value="搜索文件" class="btn btn-primary">
						</th>
					</tr>
				</table>
			</form>
		</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
				<ul class="nav">
					<li>
						<a href="welcome.jsp" class="active">
							<i class="lnr lnr-dice"></i>
							<span>主页</span>
						</a>
						<a href="selectAllDevice.do" class="active">
							<i class="lnr lnr-dice"></i>
							<span>临时设备</span>
						</a>
						<a href="devicePage.do?pageNow=1" class="active">
							<i class="lnr lnr-dice"></i>
							<span>正式设备</span>
						</a>
						<a href="searchAllMusicFile.do?pageNow=1" class="active">
							<i class="lnr lnr-dice"></i>
							<span>文件</span>
						</a>
					</li>
				</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">文件</h3>
					<div class="row">
						<div class="col-md-12">
							<div class="panel">
								<div class="panel-heading">
									<h5>每页显示条数</h5>
									<select name="pageSize" onclick="search(this)">
										<option value="none">选择</option>
										<option value="4">4</option>
										<option value="8">8</option>
										<option value="12">12</option>
									</select>
								</div>
								<div class="panel-body">
									<table class="table">
										<thead>
											<tr>
												<th>文件名称</th>
												<th>持续时长</th>
												<th>上传时间</th>
												<th>文件大小</th>
												<th>操作</th>
											</tr>
										</thead>
										<c:forEach items="${requestScope.allMusicFile}"
											var="musicFile">
											<tbody>
												<tr>
													<td id="${musicFile.systemFileName}systemMusicFile"
														style="display: none">${musicFile.systemFileName}</td>
													<td onclick="preview()">${musicFile.name}</td>
													<td>
														00:0
														<fmt:formatNumber
															value="${(musicFile.duration-musicFile.duration%60)/60}"
															pattern="0" type="number" />
														:${musicFile.duration%60}
													</td>
													<td>
														<fmt:formatDate value="${musicFile.recordDateTime}"
															type="both" />
													</td>
													<td>
														<fmt:formatNumber value="${musicFile.size/1024/1024}"
															pattern="0.00" type="number" />
														MB
													</td>
													<td>
														<button type="button" onclick="preview(this)"
															id="${musicFile.systemFileName}">详情</button>
													</td>
													<td>
														<a
															href="deleteFile.do?recordDateTime=<fmt:formatDate value="${musicFile.recordDateTime}"
															type="both" />&pageNow=1">
															<input type="submit" value="删除">
														</a>
													</td>
												</tr>
											</tbody>
										</c:forEach>
									</table>

									<!-- 分页功能 start -->
									<div class="panel-body">
										<font size="2">共 ${requestScope.page.totalPageCount} 页</font>
										<font size="2">第 ${requestScope.page.pageNow} 页</font>
										<a
											href="searchMusicFile.do?name=${requestScope.name}&
											durationStatus=${requestScope.durationStatus}&
											duration=${requestScope.duration}&
											fileStatus=${requestScope.fileStatus}&
											fileSize=${requestScope.fileSize}&
											timeBegin=${requestScope.timeBegin}&
											timeEnd=${requestScope.timeEnd}&
											pageNow=1&
											pageSize=${requestScope.page.pageSize}">首页</a>
										<c:choose>
											<c:when test="${requestScope.page.pageNow - 1 > 0}">
												<a
													href="searchMusicFile.do?name=${requestScope.name}&
													durationStatus=${requestScope.durationStatus}&
													duration=${requestScope.duration}&
													fileStatus=${requestScope.fileStatus}&
													fileSize=${requestScope.fileSize}&
													timeBegin=${requestScope.timeBegin}&
													timeEnd=${requestScope.timeEnd}&
													pageNow=${requestScope.page.pageNow - 1}&
													pageSize=${requestScope.page.pageSize}">上一页</a>
											</c:when>
											<c:when test="${requestScope.page.pageNow - 1 <= 0}">
												<a
													href="searchMusicFile.do?name=${requestScope.name}&
													durationStatus=${requestScope.durationStatus}&
													duration=${requestScope.duration}&
													fileStatus=${requestScope.fileStatus}&
													fileSize=${requestScope.fileSize}&
													timeBegin=${requestScope.timeBegin}&
													timeEnd=${requestScope.timeEnd}&
													pageNow=1&
													pageSize=${requestScope.page.pageSize}">上一页</a>
											</c:when>
										</c:choose>
										<c:choose>
											<c:when test="${requestScope.page.totalPageCount==0}">
												<a
													href="searchMusicFile.do?name=${requestScope.name}&
													durationStatus=${requestScope.durationStatus}&
													duration=${requestScope.duration}&
													fileStatus=${requestScope.fileStatus}&
													fileSize=${requestScope.fileSize}&
													timeBegin=${requestScope.timeBegin}&
													timeEnd=${requestScope.timeEnd}&
													pageNow=${requestScope.page.pageNow}&
													pageSize=${requestScope.page.pageSize}">下一页</a>
											</c:when>
											<c:when
												test="${requestScope.page.pageNow + 1 < requestScope.page.totalPageCount}">
												<a
													href="searchMusicFile.do?name=${requestScope.name}&
													durationStatus=${requestScope.durationStatus}&
													duration=${requestScope.duration}&
													fileStatus=${requestScope.fileStatus}&
													fileSize=${requestScope.fileSize}&
													timeBegin=${requestScope.timeBegin}&
													timeEnd=${requestScope.timeEnd}&
													pageNow=${requestScope.page.pageNow + 1}&
													pageSize=${requestScope.page.pageSize}">下一页</a>
											</c:when>
											<c:when
												test="${requestScope.page.pageNow + 1 >= requestScope.page.totalPageCount}">
												<a
													href="searchMusicFile.do?name=${requestScope.name}&
													durationStatus=${requestScope.durationStatus}&
													duration=${requestScope.duration}&
													fileStatus=${requestScope.fileStatus}&
													fileSize=${requestScope.fileSize}&
													timeBegin=${requestScope.timeBegin}&
													timeEnd=${requestScope.timeEnd}&
													pageNow=${requestScope.page.totalPageCount}&
													pageSize=${requestScope.page.pageSize}">下一页</a>
											</c:when>
										</c:choose>
										<c:choose>
											<c:when test="${requestScope.page.totalPageCount==0}">
												<a
													href="searchMusicFile.do?name=${requestScope.name}&
													durationStatus=${requestScope.durationStatus}&
													duration=${requestScope.duration}&
													fileStatus=${requestScope.fileStatus}&
													fileSize=${requestScope.fileSize}&
													timeBegin=${requestScope.timeBegin}&
													timeEnd=${requestScope.timeEnd}&
													pageNow=${requestScope.page.pageNow}&
													pageSize=${requestScope.page.pageSize}">尾页</a>
											</c:when>
											<c:otherwise>
												<a
													href="searchMusicFile.do?name=${requestScope.name}&
													durationStatus=${requestScope.durationStatus}&
													duration=${requestScope.duration}&
													fileStatus=${requestScope.fileStatus}&
													fileSize=${requestScope.fileSize}&
													timeBegin=${requestScope.timeBegin}&
													timeEnd=${requestScope.timeEnd}&
													pageNow=${requestScope.page.totalPageCount}&
													pageSize=${requestScope.page.pageSize}">尾页</a>
											</c:otherwise>
										</c:choose>
									</div>
									<!-- 分页功能 End -->
									<!-- <div id="demo-line-chart" class="ct-chart"></div> -->

								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">文件上传</h3>
								</div>
								<div class="panel-body">
									<div id="uploadfile">
										<!--用来存放文件信息-->
										<div id="thelist" class="uploader-list"></div>
										<div class="form-group form-inline">
											<div id="picker" style="float: left">选择文件</div>
											&nbsp;
											<button id="ctlBtn" class="btn btn-default"
												style="padding: 8px 15px; display: none;">开始上传</button>
										</div>

										<audio id="audio" style="display: none;"></audio>
										<br />
										文件Md5：
										<p id="showMD5"></p>
									</div>
								</div>
								<div id="preview"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
	</div>
</body>
<!-- 3.添加js代码 -->
<script type="text/javascript">
	$(function() {
		var $list = $('#thelist'), $btn = $('#ctlBtn');

		var uploader = WebUploader.create({
			resize : false, // 不压缩image     
			swf : 'js/uploader.swf', // swf文件路径
			server : 'fileUpload.do', // 文件接收服务端。
			pick : '#picker', // 选择文件的按钮。可选
			chunked : false, //是否要分片处理大文件上传
			//method : post,
			//chunkSize : 2 * 1024 * 1024, //分片上传，每片2M，默认是5M
			//auto: false //选择文件后是否自动上传
			// chunkRetry : 2, //如果某个分片由于网络问题出错，允许自动重传次数
			//runtimeOrder: 'html5,flash',
			accept : {
				title : 'Audios',
				extensions : 'mp3',
				mimeTypes : 'Audio/mp3'
			}
		});
		// 当有文件被添加进队列的时候
		uploader.on('fileQueued', function(file) {
			//var url = null;
			//url = URL.createObjectURL(file);
			//$("#preview").html("<audio style='width:100%;' id='myAudio' src='url' autoplay='autoplay' controls='controls'>Your browser does not support the audio tag.</audio>");
			$list.append('<div id="' + file.id + '" class="item">'
					+ '<h4 class="info">' + file.name + '</h4>'
					+ '<p class="state">等待上传...</p>' + '</div>');

			//计算MD5
			uploader.md5File(file)

			// 及时显示进度
			.progress(function(percentage) {
				console.log('Percentage:', percentage);
			})

			// 完成
			.then(function(val) {
				document.getElementById("showMD5").innerHTML = val;
				document.getElementById("showMD5").value = val;
				$btn.show();
			});
			
			var objUrl = getObjectURL(file);
            $("#audio").attr("src", objUrl);
            $("#audio")[0].play();
            $("#audio").show();
            
            function getObjectURL(file) {
                var url = null;
                if (window.createObjectURL != undefined) { // basic
                    url = window.createObjectURL(file);
                } else if (window.URL != undefined) { // mozilla(firefox)
                    url = window.URL.createObjectURL(file);
                } else if (window.webkitURL != undefined) { // webkit or chrome
                    url = window.webkitURL.createObjectURL(file);
                }
                return url;
            }
		});
		
		
		uploader.on( 'uploadBeforeSend', function( file, data ) {
		    // block为分块数据。

		    // file为分块对应的file对象。
		    // var file = block.file;


		    // 修改data可以控制发送哪些携带数据。
		    data.uid = 123;

		    // 将存在file对象中的md5数据携带发送过去。
		    data.fileMd5 = document.getElementById("showMD5").value;

		    // 删除其他数据
		    // delete data.key;
		});
		// 文件上传过程中创建进度条实时显示。
		uploader
				.on(
						'uploadProgress',
						function(file, percentage) {
							var $li = $('#' + file.id), $percent = $li
									.find('.progress .progress-bar');

							// 避免重复创建
							if (!$percent.length) {
								$percent = $(
										'<div class="progress progress-striped active">'
												+ '<div class="progress-bar" role="progressbar" style="width: 0%">'
												+ '</div>' + '</div>')
										.appendTo($li).find('.progress-bar');
							}

							$li.find('p.state').text('上传中');

							$percent.css('width', percentage * 100 + '%');
						});
		// 文件上传成功
		uploader.on('uploadSuccess', function(file) {
			$('#' + file.id).find('p.state').text('已上传');
		});

		
		uploader.on('uploadSuccess', function (file, response) {
		    var imgurl = response._raw; //上传文件的路径
		    //alert(${requestScope.alert});
		});
		
		// 文件上传失败，显示上传出错
		uploader.on('uploadError', function(file) {
			$('#' + file.id).find('p.state').text('上传出错');
		});
		// 完成上传
		uploader.on('uploadComplete', function(file) {
			$('#' + file.id).find('.progress').fadeOut();
			//window.location.href = "alert.jsp";
		});

		$btn.on('click', function() {
			if ($(this).hasClass('disabled')) {
				return false;
			}
			uploader.upload();
			// if (state === 'ready') {
			//     uploader.upload();
			// } else if (state === 'paused') {
			//     uploader.upload();
			// } else if (state === 'uploading') {
			//     uploader.stop();
			// }
		});

	});
</script>

<script>
	function search(value) {
		var selectedOption = value.options[value.selectedIndex];
		//alert(selectedOption.value);  
		if (selectedOption.value == "4") {
			window.location.href = "searchMusicFile.do?\
			name=${requestScope.name}&\
			durationStatus=${requestScope.durationStatus}&\
			duration=${requestScope.duration}&\
			fileStatus=${requestScope.fileStatus}&\
			fileSize=${requestScope.fileSize}&\
			timeBegin=${requestScope.timeBegin}&\
			timeEnd=${requestScope.timeEnd}&\
			pageNow=1&\
			pageSize=4";
		} else if (selectedOption.value == "8") {
			window.location.href = "searchMusicFile.do?\
				name=${requestScope.name}&\
				durationStatus=${requestScope.durationStatus}&\
				duration=${requestScope.duration}&\
				fileStatus=${requestScope.fileStatus}&\
				fileSize=${requestScope.fileSize}&\
				timeBegin=${requestScope.timeBegin}&\
				timeEnd=${requestScope.timeEnd}&\
				pageNow=1&\
				pageSize=8";
		} else if (selectedOption.value == "12") {
			window.location.href = "searchMusicFile.do?\
			name=${requestScope.name}&\
			durationStatus=${requestScope.durationStatus}&\
			duration=${requestScope.duration}&\
			fileStatus=${requestScope.fileStatus}&\
			fileSize=${requestScope.fileSize}&\
			timeBegin=${requestScope.timeBegin}&\
			timeEnd=${requestScope.timeEnd}&\
			pageNow=1&\
			pageSize=12";
		}
	};
</script>

<!-- 音频预览 -->
<script type="text/javascript">
function preview(obj){
	console.log(obj.id);
	var systemFileName = document.getElementById(obj.id+"systemMusicFile").innerHTML;
	console.log(systemFileName);
	$("#preview").html("<audio style='width:100%;' id='myAudio' src='http://music.163.com/#/song?id=186150' autoplay='autoplay' controls='controls'>Your browser does not support the audio tag.</audio>");

	//$("#preview").html("<audio style='width:100%;' id='myAudio' src='${pageContext.request.contextPath }/musicfile/"+systemFileName+"' autoplay='autoplay' controls='controls'>Your browser does not support the audio tag.</audio>");
}
</script>

<script>
        $(function () {
            $("#picker").change(function () {
                var objUrl = getObjectURL(this.files[0]);
                $("#audio").attr("src", objUrl);
                $("#audio")[0].play();
                $("#audio").show();
                getTime();
            });
        });
        //获取mp3文件的时间 兼容浏览器
        function getTime() {
            setTimeout(function () {
                var duration = $("#audio")[0].duration;
                if(isNaN(duration)){
                    getTime();
                }
                else{
                    console.info("该歌曲的总时间为："+$("#audio")[0].duration+"秒")
                }
            }, 10);
        }
        //把文件转换成可读URL
        function getObjectURL(file) {
            var url = null;
            if (window.createObjectURL != undefined) { // basic
                url = window.createObjectURL(file);
            } else if (window.URL != undefined) { // mozilla(firefox)
                url = window.URL.createObjectURL(file);
            } else if (window.webkitURL != undefined) { // webkit or chrome
                url = window.webkitURL.createObjectURL(file);
            }
            return url;
        }
    </script>


<!-- Javascript -->
<script src="assets/vendor/jquery/jquery.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/scripts/klorofil-common.js"></script>

</html>