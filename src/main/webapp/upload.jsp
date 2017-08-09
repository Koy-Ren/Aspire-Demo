<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	<div id="uploadfile">
	<h3>1、文件上传</h3>
		<!--用来存放文件信息-->
		<div id="thelist" class="uploader-list"></div>
		<div class="form-group form-inline">
			<div id="picker" style="float: left">选择文件</div>
			&nbsp;
			<button id="ctlBtn" class="btn btn-default"
				style="padding: 8px 15px;">开始上传</button>
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
			chunked : true, //是否要分片处理大文件上传
			chunkSize : 2 * 1024 * 1024, //分片上传，每片2M，默认是5M
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
				console.log('md5 result:', val);
			});
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

		// 文件上传失败，显示上传出错
		uploader.on('uploadError', function(file) {
			$('#' + file.id).find('p.state').text('上传出错');
		});
		// 完成上传完
		//     uploader.on( 'uploadComplete', function( file ) {
		//         $( '#'+file.id ).find('.progress').fadeOut();
		//     });

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

</html>