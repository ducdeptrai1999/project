<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%ServletContext contextChucNang = getServletConfig().getServletContext();
    contextChucNang.setAttribute("chucNang", "Them");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Them tai khoan</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Insert Member</h2>
    <form class="form-horizontal" action="/crudController" method="get">

        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">User Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="pwd" name="username" placeholder="Enter username">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">Password:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="pwd" name="password" placeholder="Enter password" >
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">Phone:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="pwd" name="phone" placeholder="Enter phone" >
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">Email:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="pwd" name="email" placeholder="Enter email" >
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">ID role:</label>
            <div class="col-sm-10">
                    <select name="roleId">
                        <option value="1">Admin</option>
                        <option value="2">User</option>
                    </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Insert</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>