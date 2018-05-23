/**
 * Created by Administrator on 18-3-27.
 */
function f(id) {
    return document.getElementById(id);
}
function fname(name) {
    return document.getElementsByName(name);
}


$(function () {
    /*全局变量*/
    var totalnumber=0;
    var rows = 0;
    /*点击查询按钮查询数据并且验证金额前面的不能大于后面的*/
    $("#search").click(function () {
            var id = $("#id").val();
            var name = $("#name").val();
            var phone = $("#phone").val();
            var kinds = $('input:radio[name="radio1"]:checked').val();
            var address = $("select").val();
            var detailaddress = $("#detailaddress").val();
            var salaryFrom = $("#salaryFrom").val();
            var salaryTo = $("#salaryTo").val();
            $("#nowPage").text(1)
            /*判断人均消费前面的不能大于后面的数字*/
            if(parseInt($("#salaryFrom").val())>parseInt($("#salaryTo").val())){
                $("#checkisnumTo").text("格式错误！");
            }else {
                $("#checkisnumTo").text("");
                $.post("SelectByBusiness", {
                    id: id,
                    name: name,
                    phone: phone,
                    kinds: kinds,
                    address: address,
                    detailaddress: detailaddress,
                    salaryFrom: salaryFrom,
                    salaryTo: salaryTo
                }, function (data) {
                    var num=0;
                    for (i in data.result){
                        var a = a +
                            "                 <tr>" +
                            "                    <td><input class=\"selected\" name=\"headcheckbox1\" type=\"checkbox\"></td>" +
                            "                    <td><span>" + data.result[i].businessId + "</span></td>" +
                            "                <td><span>" + data.result[i].name + "</span></td>" +
                            "                <td><span>" + data.result[i].phone + "</span></td>" +
                            "                <td><span>" + data.result[i].Kinds + "</span></td>" +
                            "                <td><span>" + data.result[i].address + "</span></td>" +
                            "                <td><span>" + data.result[i].detailaddress + "</span></td>" +
                            "                <td><span>" + data.result[i].salary + "</span></td>" +
                            "                <td><a href='ModifyPage.jsp\?businessId="+data.result[i].businessId+"&businessNAME="+data.result[i].name+"&phone="+data.result[i].phone+"&Kinds="+data.result[i].Kinds+"&address="+data.result[i].address+"&detailaddress="+data.result[i].detailaddress+
                            "&salary="+data.result[i].salary+"'>" +
                            "<input type='button' style= 'width:60px;background-color: rgb(92,184,92);border-radius:4px; color:white; border:none;box-shadow: 2px 2px 2px black;' value='修改'></a></td>" +
                            "                </tr>"
                        num++;
                    }
                    rows=num;
                    totalnumber=data.total;
                    $("#showTable").html(a);
                    $("#countres").text(data.total);/*显示总条数*/

                    /*计算一共有多少页，总条数除以显示的条数  总条数和单页显示的条数存放在一个类中，用就去取值*/
                    var page = parseInt(data.total/num);/*不可以 分母不能为零*/
                    if(data.total!=0){
                        if(data.total%num!=0){
                            page++;
                        }
                        $("#countPage").text(page);
                    }else{
                        $("#countPage").text(1);
                    }

                }, "json")
            }


        });
    /*当点击首页的时候，去数据库查询显示默认5条信息的数据*/
    $("#FristPage").click(function () {
        $("#nowPage").text(1);
        totalNow = $("#nowPage").text();
        totalPage = $("#countPage").text();
        ShowPages = $("#ShowPages option:selected").val();

        $.post("OtherBut",{
            totalNow:totalNow,
            totalPage:totalPage,
            ShowPages:ShowPages
        },function (data) {

            for (i in data) {
                var a = a +
                    "                 <tr>" +
                    "                    <td><input class=\"selected\" name=\"headcheckbox1\" type=\"checkbox\"></td>" +
                    "                    <td><span>" + data[i].businessId + "</span></td>" +
                    "                <td><span>" + data[i].name + "</span></td>" +
                    "                <td><span>" + data[i].phone + "</span></td>" +
                    "                <td><span>" + data[i].Kinds + "</span></td>" +
                    "                <td><span>" + data[i].address + "</span></td>" +
                    "                <td><span>" + data[i].detailaddress + "</span></td>" +
                    "                <td><span>" + data[i].salary + "</span></td>" +
                    "                <td><a href='ModifyPage.jsp\?businessId=" + data[i].businessId + "&businessNAME=" + data[i].name + "&phone=" + data[i].phone + "&Kinds=" + data[i].Kinds + "&address=" + data[i].address + "&detailaddress=" + data[i].detailaddress +
                    "&salary=" + data[i].salary + "'>" +
                    "<input type='button' style= 'width:60px;background-color: rgb(92,184,92);border-radius:4px; color:white; border:none;box-shadow: 2px 2px 2px black;' value='修改'></a></td>" +
                    "                </tr>"

            }

            $("#showTable").html(a);
            /*计算一共有多少页，总条数除以显示的条数  总条数和单页显示的条数存放在一个类中，用就去取值*/
            // var page = parseInt(data.total/numfive1);
            // if(data.total%numfive1!=0){
            //     page++;
            // }
            // $("#countPage").text(page);
        },"json")
    })
    /*当点击下一页的时候，去数据库查询显示默认5条信息的数据*/
    $("#NextPage").click(function () {
        totalNow = $("#nowPage").text();
        totalPage = $("#countPage").text();
        ShowPages = $("#ShowPages option:selected").val();
        if(totalNow<totalPage){
            totalNow=parseInt(totalNow)+1;
            $("#nowPage").text(totalNow)
            $.post("OtherBut",{
                totalNow:totalNow,
                totalPage:totalPage,
                ShowPages:ShowPages
            },function (data) {

                for (i in data) {
                    var a = a +
                        "                 <tr>" +
                        "                    <td><input class=\"selected\" name=\"headcheckbox1\" type=\"checkbox\"></td>" +
                        "                    <td><span>" + data[i].businessId + "</span></td>" +
                        "                <td><span>" + data[i].name + "</span></td>" +
                        "                <td><span>" + data[i].phone + "</span></td>" +
                        "                <td><span>" + data[i].Kinds + "</span></td>" +
                        "                <td><span>" + data[i].address + "</span></td>" +
                        "                <td><span>" + data[i].detailaddress + "</span></td>" +
                        "                <td><span>" + data[i].salary + "</span></td>" +
                        "                <td><a href='ModifyPage.jsp\?businessId=" + data[i].businessId + "&businessNAME=" + data[i].name + "&phone=" + data[i].phone + "&Kinds=" + data[i].Kinds + "&address=" + data[i].address + "&detailaddress=" + data[i].detailaddress +
                        "&salary=" + data[i].salary + "'>" +
                        "<input type='button' style= 'width:60px;background-color: rgb(92,184,92);border-radius:4px; color:white; border:none;box-shadow: 2px 2px 2px black;' value='修改'></a></td>" +
                        "                </tr>"

                }

                $("#showTable").html(a);
                /*计算一共有多少页，总条数除以显示的条数  总条数和单页显示的条数存放在一个类中，用就去取值*/
                // var page = parseInt(data.total/numfive1);
                // if(data.total%numfive1!=0){
                //     page++;
                // }
                // $("#countPage").text(page);
            },"json")
        }

    })
    /*当点击上一页的时候，去数据库查询显示默认5条信息的数据*/
    $("#PrePage").click(function () {
        totalNow = parseInt($("#nowPage").text());
        totalPage = $("#countPage").text();
        ShowPages = $("#ShowPages option:selected").val();
        if(totalNow>1){
            totalNow=parseInt(totalNow)-1;
            $("#nowPage").text(totalNow)
            $.post("OtherBut",{
                totalNow:totalNow,
                totalPage:totalPage,
                ShowPages:ShowPages
            },function (data) {
                var numfive1=0;
                for (i in data) {
                    var a = a +
                        "                 <tr>" +
                        "                    <td><input class=\"selected\" name=\"headcheckbox1\" type=\"checkbox\"></td>" +
                        "                    <td><span>" + data[i].businessId + "</span></td>" +
                        "                <td><span>" + data[i].name + "</span></td>" +
                        "                <td><span>" + data[i].phone + "</span></td>" +
                        "                <td><span>" + data[i].Kinds + "</span></td>" +
                        "                <td><span>" + data[i].address + "</span></td>" +
                        "                <td><span>" + data[i].detailaddress + "</span></td>" +
                        "                <td><span>" + data[i].salary + "</span></td>" +
                        "                <td><a href='ModifyPage.jsp\?businessId=" + data[i].businessId + "&businessNAME=" + data[i].name + "&phone=" + data[i].phone + "&Kinds=" + data[i].Kinds + "&address=" + data[i].address + "&detailaddress=" + data[i].detailaddress +
                        "&salary=" + data[i].salary + "'>" +
                        "<input type='button' style= 'width:60px;background-color: rgb(92,184,92);border-radius:4px; color:white; border:none;box-shadow: 2px 2px 2px black;' value='修改'></a></td>" +
                        "                </tr>"
                    numfive1++;
                }

                $("#showTable").html(a);
                /*计算一共有多少页，总条数除以显示的条数  总条数和单页显示的条数存放在一个类中，用就去取值*/
                // var page = parseInt(data.total/numfive1);
                // if(data.total%numfive1!=0){
                //     page++;
                // }
                // $("#countPage").text(page);
            },"json")
        }

    })
    /*当点击末页的时候，去数据库查询显示默认5条信息的数据*/
    $("#LastPage").click(function () {
        totalNow = $("#nowPage").text();
        totalPage = $("#countPage").text();
        ShowPages = $("#ShowPages option:selected").val();
        totalNow = totalPage

            $("#nowPage").text(totalNow)
            $.post("OtherBut",{
                totalNow:totalNow,
                totalPage:totalPage,
                ShowPages:ShowPages
            },function (data) {
                var numfive1=0;
                for (i in data) {
                    var a = a +
                        "                 <tr>" +
                        "                    <td><input class=\"selected\" name=\"headcheckbox1\" type=\"checkbox\"></td>" +
                        "                    <td><span>" + data[i].businessId + "</span></td>" +
                        "                <td><span>" + data[i].name + "</span></td>" +
                        "                <td><span>" + data[i].phone + "</span></td>" +
                        "                <td><span>" + data[i].Kinds + "</span></td>" +
                        "                <td><span>" + data[i].address + "</span></td>" +
                        "                <td><span>" + data[i].detailaddress + "</span></td>" +
                        "                <td><span>" + data[i].salary + "</span></td>" +
                        "                <td><a href='ModifyPage.jsp\?businessId=" + data[i].businessId + "&businessNAME=" + data[i].name + "&phone=" + data[i].phone + "&Kinds=" + data[i].Kinds + "&address=" + data[i].address + "&detailaddress=" + data[i].detailaddress +
                        "&salary=" + data[i].salary + "'>" +
                        "<input type='button' style= 'width:60px;background-color: rgb(92,184,92);border-radius:4px; color:white; border:none;box-shadow: 2px 2px 2px black;' value='修改'></a></td>" +
                        "                </tr>"
                    numfive1++;
                }

                $("#showTable").html(a);
                /*计算一共有多少页，总条数除以显示的条数  总条数和单页显示的条数存放在一个类中，用就去取值*/
                // var page = parseInt(data.total/numfive1);
                // if(data.total%numfive1!=0){
                //     page++;
                // }
                // $("#countPage").text(page);
            },"json")


    })
    /*当点击跳转到第几页的时候，去数据库查询显示默认5条信息的数据*/
    $("#jumpPage").click(function () {

       /* totalNow = $("#Pagebyjump").val();*/
        totalNow = $("#Pagebyjump").val();

        totalPage = $("#countPage").text();

        ShowPages = $("#ShowPages option:selected").val();

        if(totalPage>=totalNow){
            totalNow = $("#nowPage").text(totalNow)
            $.post("OtherBut",{
                totalNow:totalNow,
                totalPage:totalPage,
                ShowPages:ShowPages
            },function (data) {
                for (i in data) {
                    var a = a +
                        "                 <tr>" +
                        "                    <td><input class=\"selected\" name=\"headcheckbox1\" type=\"checkbox\"></td>" +
                        "                    <td><span>" + data[i].businessId + "</span></td>" +
                        "                <td><span>" + data[i].name + "</span></td>" +
                        "                <td><span>" + data[i].phone + "</span></td>" +
                        "                <td><span>" + data[i].Kinds + "</span></td>" +
                        "                <td><span>" + data[i].address + "</span></td>" +
                        "                <td><span>" + data[i].detailaddress + "</span></td>" +
                        "                <td><span>" + data[i].salary + "</span></td>" +
                        "                <td><a href='ModifyPage.jsp\?businessId=" + data[i].businessId + "&businessNAME=" + data[i].name + "&phone=" + data[i].phone + "&Kinds=" + data[i].Kinds + "&address=" + data[i].address + "&detailaddress=" + data[i].detailaddress +
                        "&salary=" + data[i].salary + "'>" +
                        "<input type='button' style= 'width:60px;background-color: rgb(92,184,92);border-radius:4px; color:white; border:none;box-shadow: 2px 2px 2px black;' value='修改'></a></td>" +
                        "                </tr>"
                }
                $("#showTable").html(a);
            },"json")
        }





    })
    /*当点击下拉列表的时候以几行显示*/
    $("#ShowPages").change(function () {
        totalNow = $("#nowPage").text();
        totalPage = $("#countPage").text();
        ShowPages = $("#ShowPages option:selected").val();
        $("#countPage").text(Math.ceil(totalnumber/ShowPages));
        $.post("OtherBut",{
            totalNow:totalNow,
            totalPage:totalPage,
            ShowPages:ShowPages
        },function (data) {
            for (i in data) {
                var a = a +
                    "                 <tr>" +
                    "                    <td><input class=\"selected\" name=\"headcheckbox1\" type=\"checkbox\"></td>" +
                    "                    <td><span>" + data[i].businessId + "</span></td>" +
                    "                <td><span>" + data[i].name + "</span></td>" +
                    "                <td><span>" + data[i].phone + "</span></td>" +
                    "                <td><span>" + data[i].Kinds + "</span></td>" +
                    "                <td><span>" + data[i].address + "</span></td>" +
                    "                <td><span>" + data[i].detailaddress + "</span></td>" +
                    "                <td><span>" + data[i].salary + "</span></td>" +
                    "                <td><a href='ModifyPage.jsp\?businessId=" + data[i].businessId + "&businessNAME=" + data[i].name + "&phone=" + data[i].phone + "&Kinds=" + data[i].Kinds + "&address=" + data[i].address + "&detailaddress=" + data[i].detailaddress +
                    "&salary=" + data[i].salary + "'>" +
                    "<input type='button' style= 'width:60px;background-color: rgb(92,184,92);border-radius:4px; color:white; border:none;box-shadow: 2px 2px 2px black;' value='修改'></a></td>" +
                    "                </tr>"
            }
            $("#showTable").html(a);
        },"json")


    })

    /*当点击修改按钮的时候跳转到修改页 根据传过来的id再去数据库查一遍值赋值给页面*/
    $("#modifybut").click(function () {
    /*该步骤在前段完成的，a标签的href跳转传值的*/
    })
    /*点击删除按钮删除一条信息，并且把数据库的标志位变成1*/
    $("#deletebut").click(function () {
        var date=[];
        $("#deletable").find(":checkbox[name='headcheckbox1']").each(function () {
            if($(this).is(":checked")){
                var val =$(this).parent().next().text();
                $(this).parent().parent().remove();
                $("#countres").text($("#countres").text()-1);
                totalnumber=parseInt($("#countres").text());
                $("#countPage").text(Math.ceil(totalnumber/rows));
                date.push(val);
            }
        })
        $.post("DeleteBusiness",{
                businessid:date
            },
            function (data) {
            }, "json");


    })
    /*当点击退出登陆的时候*/
    $("#quitLogin").click(function () {
        $.post("quitLogin",{},function () {
            confirm("确认要退出吗？");
            /*prompt("确认要退出吗？","123");*/
            window.location.href="centerhomepage.jsp";
        },"text")
    })

})
/*checkbox*/
function print() {
    if(f("headcheckbox").hasAttribute('checked')){
        f("headcheckbox").removeAttribute('checked');
        for(var i=0;i<fname("headcheckbox1").length;i++){
            fname("headcheckbox1")[i].checked=false;
        }
    }else{
        f("headcheckbox").setAttribute('checked',"true");
        for(var i=0;i<fname("headcheckbox1").length;i++){
            fname("headcheckbox1")[i].checked=true;
        }

    }


}
/*当输入金额的时候 失去焦点 提示金额必须是数字并且金额不能过万*/
$("#salaryFrom").blur(function () {
    var num = $("#salaryFrom").val();
    var n = /^[0-9]*$/;
    if(!n.test(num)){
        alert("必须是数字");
    }else if(num.length>5){
        alert("不能过万");
    }
    else{
        $("#checkisnumFrom").text("");
    }
})
$("#salaryTo").blur(function () {
    var num = $("#salaryTo").val();
    var n = /^[0-9]*$/;
    if(!n.test(num)){
        alert("必须是数字");
    }else if(num.length>5){
        alert("不能过万");
    }
    else{
        $("#checkisnumTo").text("");
    }
})
/*当点击添加按钮的时候跳转到添加页*/



