<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type='text/javascript' src='/dwr/interface/sayHello.js'></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type="text/javascript">
      function SayHelloTest(){
          var str = document.getElementById("name").value;
          sayHello.sayHello(str,function(value){
              alert(value);
          });
      }
</script>
</head>
<body>
<input type="button" value="无参数" onclick="sayHello.sayHello(function(value){alert(value);});">
<br>
<input type="text" id="name" name="name" />
<input type="button"T value="有参数" onclick="SayHelloTest();"/>
</body>
</html>