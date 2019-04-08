var http = require("http");
var url = require("url");
var path = require("path");

/**
 * 创建服务（request ， response）
 */
var server  = http.createServer(function(req,res){
    // 获取请求路径
    var pathname = url.parse(req.url).pathname;
    res.writeHead(200 ,{'Content-Type' : 'application/json ; charset=utf-8'});
    // 访问 http://localhost:8060/ , 将会返回{"index" : "欢迎来到首页"}
    if(pathname === "/"){
        res.end(JSON.stringify({"index" : "欢迎来到首页"}));
    }
    // 访问 http://localhost:8060/health , 将会返回{"status" : "UP"}
    // 注意：状态值一定要为大写
    else if(pathname === "/health"){
        res.end(JSON.stringify({"status" : "UP"}));
    }
    // 其他情况返回404
    else{
        res.end("404")
    }
});
// 创建监听，并打印日志
server.listen(8060 , function(){
   console.log("listening on localhost:8060");
});