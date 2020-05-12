<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 可乐yue
  Date: 2020/5/8
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>展示书籍</title>
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                   <small>书籍列表 ----------- 显示所有书籍</small>
                    <small>${error}</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-info" href="${pageContext.request.contextPath}/book/toAddBook">增加书籍</a>
        </div>
        <div class="col-md-1 column">
            <a class="btn btn-info" href="${pageContext.request.contextPath}/book/allBook">所有书籍</a>
        </div>
        <form action="${pageContext.request.contextPath}/book/selectBookByName">
            <div class="form-group" style="float: left;">
                <input type="text" class="form-control" name="bookName" placeholder="请输入书籍的名字">
            </div>
            <div style="float: right;">
                <input class="btn btn-info" type="submit">
            </div>
        </form>
    </div>
</div>

<div class="row clearfix">
    <div class="col-md-12 column">
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th>书籍编号</th>
                <th>书籍名称</th>
                <th>书籍数量</th>
                <th>书籍详情</th>
                <th>书籍操作</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td>${book.bookId}</td>
                        <td>${book.bookName}</td>
                        <td>${book.bookCounts}</td>
                        <td>${book.detail}</td>
                        <td><a href="${pageContext.request.contextPath}/book/toUpdateBook/${book.bookId}">修改</a>
                             |
                            <a href="${pageContext.request.contextPath}/book/deleteBook/${book.bookId}">删除</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
