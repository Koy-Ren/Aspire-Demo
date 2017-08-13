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

function preview(obj){
	console.log(obj.id);
	var systemFileName = document.getElementById(obj.id+"systemMusicFile").innerHTML;
	console.log(systemFileName);
	$("#preview").html("<audio style='width:100%;' id='myAudio' src='http://music.163.com/#/song?id=186150' autoplay='autoplay' controls='controls'>Your browser does not support the audio tag.</audio>");

	//$("#preview").html("<audio style='width:100%;' id='myAudio' src='${pageContext.request.contextPath }/musicfile/"+systemFileName+"' autoplay='autoplay' controls='controls'>Your browser does not support the audio tag.</audio>");
}

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