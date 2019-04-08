
/*$('.bh-button').click(function(){
  $('.pop-up').addClass('open');
});
*/
$('.blog-view-file').click(function(){
	// 获取图片id
	var url= "/view/" + $(this).attr("fileId");
	//ajax 请求二进制流文件 XMLHttpRequest 请求并处理二进制流数据
	readStream (url)
});
$('.pop-up .close').click(function(){
  $('.pop-up').removeClass('open');
});

//ajax 请求二进制流 图片 文件 XMLHttpRequest 请求并处理二进制流数据
function readStream (url){
	var xhr = new XMLHttpRequest();    
    xhr.open("get", url, true);
    xhr.responseType = "blob";
    xhr.onload = function() {
        if (this.status == 200) {
            var blob = this.response;
        	var type = blob.type.substring(0 , blob.type.indexOf("/"));
        	var $em = null;
        	$("#my-modal-content").html("");
        	var $content = document.getElementById("my-modal-content");
        	// 视频文件
        	if(type == "video"){
        		$em = setBinaryStream(blob , type);
        		$content.appendChild($em);
        		
        	}// 音频文件
        	else if(type == "audio"){
        		$em = setBinaryStream(blob , type);
        		$content.appendChild($em);
        
        	}// 图片文件
        	else if(type == "image"){
        		$em = setBinaryStream(blob , type);
        		$("#my-modal-content").append('<img  src="'+$em.src+'"  alt="Car">');
        	}
			$('.pop-up').addClass('open');
        }
    }
    xhr.send();
}
// 把二进制流设置到元素src中
function setBinaryStream(blob , type){
	 var $em = document.createElement(type);
	 $em.style.width = '100%';
	  $em.onload = function(e) {
         window.URL.revokeObjectURL($em.src); 
       };
       $em.src = window.URL.createObjectURL(blob);
       $em.controls = 'controls';
       return $em;
}

