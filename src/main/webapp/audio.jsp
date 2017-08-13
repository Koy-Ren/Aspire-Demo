<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>

<script src="./audiojs/audio.min.js"></script>
<link rel="stylesheet" href="./includes/index.css" media="screen">
<script>
      audiojs.events.ready(function() {
        audiojs.createAll();
      });
    </script>
    
    
    <style type="text/css"> 
#progress{ 
border: 1px solid blue; 
width:500px; 
height:20px; 
} 
 
#bar { 
background:green; 
height:20px; 
width:0%; 
} 
 
</style> 
</head>

<body>

<button type="button" onclick="preview()">test</button>
<div id="preview"></div>


<form> 
            <input type="file" name="pic" /><br /> 
        </form> 
        <div id="progress"><div id="bar"></div></div> 





	<form action="fileUpload.do" method="post"
		enctype="multipart/form-data">
		<input name="fileMd5" type="hidden"
			value="bac3e9d5fedd45e2792453b99f1f3263" />
		<input id="audioFile" name="file" type="file" accept="audio/mpeg" />
		<input id="upload" type="submit" value="上传文件" style="display: none" />
	</form>
	<audio id="audio" autoplay></audio>

	<div
		style="width: 200px; height: 10px; border: solid 1px #ccc; padding: 1px;">
		<div id="processbar"
			style="background-color: #90EE90; width: 0px; height: 10px;"></div>
	</div>
	<input type="button" value="开始" onclick="startbar();" />
	<input type="button" value="暂停" onclick="pausebar();" />
</body>

<script>
        $(function () {
            $("#audioFile").change(function () {
                var objUrl = getObjectURL(this.files[0]);
                $("#audio").attr("src", objUrl);
                //$("#audio")[0].play();
                $("#audio").show();
                $("#upload").show();
                getTime();
            });
        });
        <!-- 获取mp3文件的时间 兼容浏览器 -->
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
        <!--把文件转换成可读URL-->
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

<script type="text/javascript">
var mytimer = null;  var bar = 0;
function processbar() {
    if (bar < 200) {
bar++;
document.getElementById("processbar").style.width = bar + "px";
mytimer = window.setTimeout("processbar()", 50);
    }
}
function startbar() {
    mytimer = window.setTimeout("processbar()", 50);
}
function pausebar() {
    window.clearTimeout(mytimer);
}
</script>

<script> 
        // 负责ajax发送数据 
        function up(fd) { 
            var xhr = new XMLHttpRequest(); 
            xhr.open('POST','fileUpload.do',true); // 异步传输 
 
            // xhr.upload 这是html5新增的api,储存了上传过程中的信息 
            xhr.upload.onprogress = function (ev) { 
                var percent = 0; 
                if(ev.lengthComputable) { 
                    percent = 100 * ev.loaded/ev.total; 
                    //document.getElementById('progress').innerHTML = percent; 
                    document.getElementById('bar').style.width = percent + '%'; 
                } 
            } 
 
            xhr.send(fd); 
        } 
 
 
        document.getElementsByTagName('input')[0].onchange = function() { 
            //alert('你选择文件了'); 
            //alert(this.files[0]); // 文件对象,html5新增的api 
 
            var fd = new FormData(); // html5新增的对象,可以包装字符,二进制信息 
            fd.append(this.name,this.files[0]); 
 
            up(fd); 
 
        } 
    </script>


<script type="text/javascript">
function preview(){
	//console.log(obj.id);
	//var systemFileName = document.getElementById(obj.id+"systemMusicFile").innerHTML;
	//console.log(systemFileName);
	//$("#preview").html("<audio style='width:100%;' id='myAudio' src='http://music.163.com/#/song?id=186150' autoplay='autoplay' controls='controls'>Your browser does not support the audio tag.</audio>");

	$("#preview").html("<audio style='width:100%;' id='myAudio' src='${pageContext.request.contextPath }/musicfile/20170813214406935.mp3' autoplay='autoplay' controls='controls'>Your browser does not support the audio tag.</audio>");
}
</script>
</html>