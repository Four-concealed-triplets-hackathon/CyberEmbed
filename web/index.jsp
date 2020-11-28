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
    <link href="https://cdn.bootcss.com/font-awesome/5.8.0/css/all.css" rel="stylesheet"/>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/animate.css/3.2.0/animate.min.css">
    <link rel="stylesheet" href="css/remodal.css">
    <link rel="stylesheet" href="css/remodal-default-theme.css">
    <link rel="stylesheet" href="css/background.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            /*background: #000;*/
        }

        #output{
            width:40%;
            height:40%;
        }

        .search-box {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: #FFFFFF;
            height: 40px;
            border-radius: 40px;
            padding: 10px;
        }

        .search-btn {
            color: #000;
            float: right;
            width: 40px;
            height: 40px;
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
            font-size: 16px;
            transition: 0.4s;
            line-height: 40px;
            width: 0;
        }

        .search-box:hover > .search-txt {
            width: 240px;
            /*width: 40%;*/
            padding: 0 6px;
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
<%--action="/CyberEmbed/cyber/generatePic"--%>
<form id="form">
    <div class="search-box">
        <input class="search-txt" type="text" placeholder="Type to Create Poster"/>
        <a class="search-btn" onclick="request()">
            <i class="fas fa-search"></i>
        </a>
    </div>
</form>
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
<script src="js/remodal.js"></script>
<script>
    let waiting = "<div class=\"spinner\" id=\"waiting-for-pic\">\n" +
        "<div class=\"rect1\"></div>\n" + "<div class=\"rect2\"></div>\n" + "<div class=\"rect3\"></div>\n" +
        "<div class=\"rect4\"></div>\n" + "<div class=\"rect5\"></div>\n" + "</div>"
    let downloadButton = "<button onclick=\"download()\" class=\"remodal-confirm\">Download</button>"
    let cancelButton = "<button data-remodal-action=\"cancel\" class=\"remodal-cancel\">Cancel</button>"
    window.REMODAL_GLOBALS = {
        NAMESPACE: 'modal',
        DEFAULTS: {
            hashTracking: false
        }
    };
    let inst = $('[data-remodal-id=modal]').remodal();

    function request() {
        let a = document.getElementsByClassName("search-txt")[0].value;
        if (a !== "" && a != null) {
            $('#button-container').html(cancelButton)
            $('#modal-content').html(waiting)
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
                        $('#modal-content').html("<img id=\"output\" src=\""+data["data"][1]["url"]+"\"\\>")
                        $('#button-container').append(downloadButton)
                        //
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
