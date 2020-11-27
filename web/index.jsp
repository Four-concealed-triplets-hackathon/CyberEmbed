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
    <link href="https://cdn.bootcss.com/font-awesome/5.8.0/css/all.css" rel="stylesheet" />
    <style>
        body {
            margin: 0;
            padding: 0;
            background: #e84118;
        }
        .search-box {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: #2f3640;
            height: 40px;
            border-radius: 40px;
            padding: 10px;
        }
        .search-btn {
            color: #e84118;
            float: right;
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background: #2f3640;
            display: flex;
            justify-content: center;
            align-items: center;
            transition: 0.4s;
            text-decoration: none;
        }
        .search-txt {
            border: none;
            background: none;
            outline: none;
            float: left;
            padding: 0;
            color: white;
            font-size: 16px;
            transition: 0.4s;
            line-height: 40px;
            width: 0;
        }
        .search-box:hover > .search-txt {
            width: 240px;
            padding: 0 6px;
        }
        .search-box:hover > .search-btn {
            background: white;
        }
    </style>
</head>
</head>
<body>
    <form action="/CyberEmbed/cyber/generatePic" method="post">
        <div class="search-box">
            <input class="search-txt" type="text" placeholder="Type to search" />
            <a class="search-btn" href="#">
                <i class="fas fa-search"></i>
            </a>
        </div>
    </form>
</body>
</html>
