<%--
  Created by IntelliJ IDEA.
  User: 可乐yue
  Date: 2020/5/10
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<html>
<head>
    <title>修改书籍的页面</title>
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>书籍信息 ----------- 修改指定书籍</small>
                </h1>
            </div>
        </div>
    </div>
</div>
<%--RestFul风格url--%>
<form action="${pageContext.request.contextPath}/book/updateBook" method="post">
    <%--这里设置一个隐藏域将来提交修改后方便依据该id进行修改数据--%>
    <input type="hidden" name="bookId" value="${book.bookId}"/>
    <div class="form-group">
        <label>书籍名称: </label>
        <input type="text" name="bookName" value="${book.bookName}" class="form-control" required/>
    </div>
    <div class="form-group">
        <label>书籍数量: </label>
        <input type="text" name="bookCounts" value="${book.bookCounts}" class="form-control" required/>
    </div>
    <div class="form-group">
        <label>书籍描述: </label>
        <input type="text" name="detail" value="${book.detail}" class="form-control" required/>
    </div>
    <div class="form-group">
        <input type="submit" class="form-control" value="修改" required/>
    </div>
</form>

</body>
</html>
