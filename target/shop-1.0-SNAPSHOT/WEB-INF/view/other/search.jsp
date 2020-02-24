<%@page import="java.util.Set"%>
<%@ page import="com.buiminhduc.repository.dao.ThanhVienDAO" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.7.1/modernizr.min.js"></script>
    <script>
        yepnope({
            test : (!Modernizr.input.list || (parseInt($.browser.version) > 400)),
            yep : [
                'https://raw2.github.com/CSS-Tricks/Relevant-Dropdowns/master/js/jquery.relevant-dropdown.js',
                'https://raw2.github.com/CSS-Tricks/Relevant-Dropdowns/master/js/load-fallbacks.js'
            ]
        });
    </script>
</head>
<body>
<form action="XuLyUser" method="get">
    <div class="aa-search-box">
            <input type="text" list="dsHoThanhVien" name="userName" placeholder="Search here ex. 'man' ">
            <button type="submit"><span class="fa fa-search"></span></button>
    </div>
</form>
<%Set<String> dsHoThanhVien = new ThanhVienDAO().getDanhSachTheoHo();%>
<datalist id="dsHoThanhVien">
    <%for(String a:dsHoThanhVien) {%>
    <option value="<%=a%>"><%=a %></option>
    <%} %>
</datalist>
</body>
</html>