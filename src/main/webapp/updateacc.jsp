<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.buiminhduc.util.MySqlConnectionUtil" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%ServletContext contextChucNang = getServletConfig().getServletContext();
    contextChucNang.setAttribute("chucNang", "Sua");
    ServletContext contextID = getServletConfig().getServletContext();
    contextID.setAttribute("id", request.getParameter("id"));
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Sửa thông tin thành viên</h2>
    <%String id = request.getParameter("id");
        ResultSet rs = new MySqlConnectionUtil().chonDuLieuTuDTB("select * from user where id='"+id+"'");
        while(rs.next()){
    %>
    <form class="form-horizontal" action="/crudController" method="get">

        <div class="form-group">
            <label class="control-label col-sm-2" for="email">UserName:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="email" name="username" placeholder="Nhập vào username" value="<%
        	 	 out.print(rs.getString(2));
        %>">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">Phone:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="pwd" name="phone" placeholder="Nhập vào phone" value="<%
        	 	 out.print(rs.getString(4));
        %>">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">Email:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="pwd" name="email" placeholder="Nhập vào email" value="<%
        	 	 out.print(rs.getString(5));
        %>">
            </div>
        </div>
        <%} %>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Sửa</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>