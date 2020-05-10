<%--
  Created by IntelliJ IDEA.
  User: 可乐yue
  Date: 2020/5/7
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
  <head>
    <title>小书童,书籍如此简单~</title>
    <style>
      a{
        text-decoration: none;
        color: powderblue;
        font-size: 18px;
      }

      h3{
        width: 180px;
        height:38px;
        margin: 100px auto;
        text-align: center;
        line-height: 38px;
        background: deepskyblue;
        border-radius: 5px;
      }
    </style>
  </head>
  <body>
      <h3>
          <a href="${pageContext.request.contextPath}/book/allBook">点击进入小书童</a>
      </h3>
  </body>
</html>
