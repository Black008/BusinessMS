<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Modify</title>
</head>
<body>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>HomePageUI</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/ModifyPagestyles.css" rel="stylesheet">
</head>
<body onload="onDisplay()">

<%--header--%>
<jsp:include page="header.jsp"></jsp:include>
<%--nav导入的时查找页已经登陆状态的nav--%>
<jsp:include page="nav.jsp"></jsp:include>

<!--背景图-->
<div class="img1">
    <div class="boxmaxbg">
        <div class="boxtop">
            <form>
                <!--第一行-->
                <ul class="checkul1 ul11">
                    <li>
                        <span  class="s222">商家ID</span>
                        <input id="id" class="flg" type="text" name="text" disabled>
                    </li>
                    <li>
                        <span>商家名</span>
                        <input id="name" class="flg" type="text" name="text" required>
                    </li>

                </ul>
                <!--第二行-->
                <ul class="checkul2 ul11">
                    <li>
                        <span>电话</span>
                        <input id="phone" class="flg1" type="text" name="text" required>
                    </li>
                    <li class="li2" id="rad">
                        <span  class="s222">商家分类</span>
                        <input class="dian" type="radio" name="radio1" value="中餐" checked><span>中餐</span>
                        <input type="radio" name="radio1" value="韩餐"><span>韩餐</span>
                        <input type="radio" name="radio1" value="日餐"><span>日餐</span>
                       <%-- <input type="radio" name="radio"><span>中餐</span>--%>
                    </li>
                </ul>
                <!--第三行-->
                <ul class="checkul3 ul11">
                    <li class="first">
                        <span class="s222">商家地址</span>
                        <select id="selectaddress">
                            <option selected value="甘井子区">
                                甘井子区
                            </option>
                            <option value="沙河口区">
                                沙河口区
                            </option>
                            <option value="高新园区">
                                高新园区
                            </option>
                        </select>

                    </li>
                    <li class="sen">
                        <span>详细地址</span>
                        <input id="detailaddress" class="inputbox" type="text" name="text" required>
                    </li>
                </ul>
                <!--第四行-->
                <ul class="checkul4 ul11">
                    <li>
                        <span class="s222">人均消费</span>
                        <input id="salary" class="flg" type="text" name="text" required pattern="">
                        <span class="spanli1">元/人</span>
                    </li>
               <%--     <li  class="spanli2">
                        <span class="spanli">~</span>
                        <input id="salaryTo" class="flg" type="text" name="text" required>

                    </li>--%>

                </ul>
                <!--第五行-->
                <ul class="checkul5">
                    <li>
                        <input class="clear" type="submit" value="清空">
                    </li>
                    <li>
                        <input id="addBusiness" class="find" type="button" value="保存">
                    </li>
                </ul>
            </form>

        </div>

    </div>
</div>
<%--footer--%>
<jsp:include page="footer.jsp"></jsp:include>


<script src="js/jquery1.12.4.min.js"></script>
<script src="js/jquery-1.12.4.js"></script>
<script>
    $(function () {
        $('[data-toggle="popover"]').popover()
    })
</script>
<script src="js/bootstrap.js"></script>
<script src="js/Modify.js"></script>
</body>
</html>

</body>
</html>