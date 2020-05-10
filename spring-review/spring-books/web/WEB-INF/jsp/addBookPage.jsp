<%--
  Created by IntelliJ IDEA.
  User: 可乐yue
  Date: 2020/5/10
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加书籍的页面</title>
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 clumn">
            <div class="page-header">
                <h1>
                    <small>书籍信息 ----------- 添加一本书籍</small>
                </h1>
            </div>
        </div>
    </div>
</div>

<form action="${pageContext.request.contentPath}/book/addBook" method="post">
    <div class="form-group">
        <label>书籍名称: </label>
        <input type="text" name="bookName" class="form-control">
    </div>
    <div class="form-group">
        <label>书籍数量: </label>
        <input type="text" name="bookCounts" class="form-control">
    </div>
    <div class="form-group">
        <label>书籍描述: </label>
        <input type="text" name="detail" class="form-control">
    </div>
    <div class="form-group">
        <input type="submit" class="form-control" value="添加">
     </div>
</form>

</body>
</html>
