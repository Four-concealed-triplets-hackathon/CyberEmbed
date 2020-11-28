<%--
  @Author: Ruuku
  @Date: 2020/11/28
  @Time: 1:29
  @Description:
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cyber Embed</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
    <link href="https://cdn.bootcss.com/font-awesome/5.8.0/css/all.css" rel="stylesheet"/>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/animate.css/3.2.0/animate.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="css/remodal.css">
    <link rel="stylesheet" href="css/remodal-default-theme.css">
    <link rel="stylesheet" href="css/background.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            /*background: #000;*/
        }

        /*相应式布局*/

        html {
            font-size: 20px;
        }

        @media (max-width: 540px) {
            html {
                font-size: 18px;
            }
        }

        /*Android常用宽度*/
        @media (max-width: 480px) {
            html {
                font-size: 18px;
            }
        }

        /*Android常用宽度*/
        @media (max-width: 414px) {
            html {
                font-size: 15px;
            }
        }

        /*i6Plus,i7Plus宽度*/
        @media (max-width: 375px) {
            html {
                font-size: 14px;
            }
        }

        /*i6,i7宽度*/
        @media (max-width: 360px) {
            html {
                font-size: 14px;
            }
        }

        /*Android常用宽度*/
        @media (max-width: 320px) {
            html {
                font-size: 12px;
            }
        }

        /*i5宽度*/

        /*广泛布局*/
        .disabled {
            display: none;
        }

        #output {
            width: 70%;
        }

        #title-container {
            color: white;
            position: absolute;
            top: 30%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        #description-container {
            color: white;
            position: absolute;
            top: 70%;
            left: 50%;
            transform: translate(-50%, -50%);
            text-align: center;
        }

        .search-box {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: #FFFFFF;
            /*height: 2rem;*/
            border-radius: 2.5rem;
            padding: 0.5rem;
            font-size: 0.7rem;
            display: flex;
        }

        .search-btn {
            color: #000;
            /*float: right;*/
            width: 2.5rem;
            height: 2.5rem;
            border-radius: 50%;
            background: #FFFFFF;
            display: flex;
            justify-content: center;
            align-items: center;
            transition: 0.4s;
            text-decoration: none;
            cursor: pointer;
        }

        .search-txt {
            border: none;
            background: none;
            outline: none;
            float: left;
            padding: 0;
            color: #000;
            display: flex;
            font-size: 0.8rem;
            transition: 0.4s;
            line-height: 2.5rem;
            width: 0;
        }

        .search-box:hover > .search-txt {
            width: 18rem;
            /*width: 40%;*/
            padding: 0 0.7rem;
        }


        .search-box:hover > .search-btn {
            background: #000;
            color: #FFFFFF;
        }

        .spinner {
            margin: 100px auto;
            width: 50px;
            height: 60px;
            text-align: center;
            font-size: 10px;
        }

        .spinner > div {
            /*background-color: #67CF22;*/
            background-color: #ffa31a;
            height: 100%;
            width: 6px;
            display: inline-block;
            -webkit-animation: stretchdelay 1.2s infinite ease-in-out;
            animation: stretchdelay 1.2s infinite ease-in-out;
        }

        .spinner .rect2 {
            -webkit-animation-delay: -1.1s;
            animation-delay: -1.1s;
        }

        .spinner .rect3 {
            -webkit-animation-delay: -1.0s;
            animation-delay: -1.0s;
        }

        .spinner .rect4 {
            -webkit-animation-delay: -0.9s;
            animation-delay: -0.9s;
        }

        .spinner .rect5 {
            -webkit-animation-delay: -0.8s;
            animation-delay: -0.8s;
        }

        @-webkit-keyframes stretchdelay {
            0%, 40%, 100% {
                -webkit-transform: scaleY(0.4)
            }
            20% {
                -webkit-transform: scaleY(1.0)
            }
        }

        @keyframes stretchdelay {
            0%, 40%, 100% {
                transform: scaleY(0.4);
                -webkit-transform: scaleY(0.4);
            }
            20% {
                transform: scaleY(1.0);
                -webkit-transform: scaleY(1.0);
            }
        }

        input::-webkit-input-placeholder {
            color: black;
        }
    </style>
</head>
<body>
<%--<div id="main">--%>
<%--action="/CyberEmbed/cyber/generatePic"--%>
<%--<form>--%>
<div id="search-box" class="search-box">
    <input class="search-txt" type="text" id="search-txt" placeholder="Type to Embed"/>
    <a class="search-btn" onclick="request()">
        <i class="fa fa-bolt" aria-hidden="true"></i>
    </a>
</div>
<%--</form>--%>

<div class="container" id="main">
    <div id="title-container">
        <h1 id="title" class="animated fadeInDown">CyberEmbed</h1>
    </div>
    <div id="description-container">
        <p id="description" class="disabled">a great poster solution to message contact.</p>
    </div>
</div>
<%--</div>--%>
<%-- 模态窗口 --%>
<%--<a href="#modal">Call the modal with data-remodal-id="modal"</a>--%>
<div class="remodal" data-remodal-id="modal">
    <button data-remodal-action="close" class="remodal-close"></button>
    <h1>Your Poster</h1>
    <div id="modal-content">
    </div>
    <br>
    <br>
    <div id="button-container">
        <button data-remodal-action="cancel" class="remodal-cancel">Cancel</button>
        <button onclick="download()" class="remodal-confirm">Download</button>
    </div>
</div>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
<script src="js/remodal.js"></script>
<script>
    $("#search-box").mouseover(
        function () {
            $("#title").attr("class", "animated fadeOutUp");
            $("#description").attr("class", "animated fadeInUp");
        }
    );

    $("#search-box").mouseleave(
        function () {
            $("#title").attr("class", "animated fadeInDown");
            $("#description").attr("class", "animated fadeOutDown");
        }
    );


    let waiting = "<div class=\"spinner\" id=\"waiting-for-pic\">\n" +
        "<div class=\"rect1\"></div>\n" + "<div class=\"rect2\"></div>\n" + "<div class=\"rect3\"></div>\n" +
        "<div class=\"rect4\"></div>\n" + "<div class=\"rect5\"></div>\n" + "</div>"
    let downloadButton = "<button onclick=\"download()\" class=\"remodal-confirm\">Download</button>"
    let cancelButton = "<button data-remodal-action=\"cancel\" class=\"remodal-cancel\">Cancel</button>"
    let errorcode = "\"<p>❌ url parse failed ❌<br>We haven't supported the website \" +\n" +
        "                \"<br>or something goes wrong with the url</p>\""
    window.REMODAL_GLOBALS = {
        NAMESPACE: 'modal',
        DEFAULTS: {
            hashTracking: false
        }
    };
    let inst = $('[data-remodal-id=modal]').remodal();

    function request() {
        let a = document.getElementsByClassName("search-txt")[0].value;
        $("#search-txt").val("");
        if (a !== "" && a != null) {
            $('#button-container').html(cancelButton)
            $('#modal-content').html(waiting)
            $('#modal-content').html()
            inst.open();
            $.ajax(
                {
                    url: "/CyberEmbed_Web_exploded/cyber/generatePic",
                    data: {
                        url: a
                    },
                    type: "POST",
                    dataType: "JSON",
                    // contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                    traditional: true,
                    success: function (data) {
                        if (data["data"][1]["url"] === "parse url error") {
                            $('#modal-content').html(errorcode);
                        } else {
                            $('#modal-content').html("<img id=\"output\" src=\"" + data["data"][1]["url"] + "\"\\>")
                            $('#button-container').append(downloadButton)
                            //
                        }
                    }
                }
            )
        }
    }

    function download() {
        let imgsrc = document.getElementById("output").src;
        let name = guid();
        let image = new Image();
        // 解决跨域 Canvas 污染问题
        image.setAttribute("crossOrigin", "anonymous");
        image.onload = function () {
            let canvas = document.createElement("canvas");
            canvas.width = image.width;
            canvas.height = image.height;
            let context = canvas.getContext("2d");
            context.drawImage(image, 0, 0, image.width, image.height);
            // 模拟一个虚拟a标签下载图片
            let url = canvas.toDataURL("image/png");
            let a = document.createElement("a");
            let event = new MouseEvent("click");
            a.download = name || "photo";
            a.href = url;
            a.dispatchEvent(event);
        };
        image.src = imgsrc;
    }

    function guid() {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = Math.random() * 16 | 0,
                v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }

</script>
</body>
</html>
