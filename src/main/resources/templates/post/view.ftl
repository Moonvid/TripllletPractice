<!DOCTYPE HTML>

<html>
<head>
    <title>스프링부트 게시판 서비스</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <!-- 부트스트랩 js, jquery 추가-->
    <link rel="stylesheet" href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/lib/common.css">

    <style>
        .left{
            text-align: center;
            font-weight: bold;
            background-color: lightgrey;
            width: 150px;
        }

        .button-align{
            text-align: center;
        }
        
        .button{
            display: inline-block;
        }

        .content-title{
            vertical-align: middle;
        }


    </style>

</head>

<body>

    <hr/>
    <div>
        <ul class="navi">
            <li>
                <a href="/">Home</a>
            </li>
            <li>
                <a href="/post/list">Board</a>
            </li>
        </ul>
    </div>
    <hr/>

    <div class="container">
        <!-- 게시물 확인 -->
        <div align="center"><h1>게시물 확인</h1></div>
        <br/>

        <div class="row">
        <table class="table table-horizontal table-bordered">
            <tbody id="tbody">
                <tr>
                    <td class="left">게시글번호</td><td>${posts.id}</td>
                    <td class="left">작성자</td><td>${posts.writer}</td>
                </tr>
                <tr>
                    <td class="left">최근수정일</td><td colspan="3">${posts.modifiedDate}</td>
                </tr>
                <tr>
                    <td class="left">제목</td><td colspan="3">${posts.title}</td>
                </tr>
                <tr>
                    <td class="left content-title">글내용</td><td colspan="3">${posts.content}</td>
                </tr>
            </tbody>
        </table>
            <div class="row">
            <div class="button-align">
                <div class="button">
                    <button type="button" class="btn btn-primary" data-toggle="modal" onclick="location.href='/post/updateForm/${posts.id}'">수정하기</button>
                </div>&nbsp;
                <div class="button">
                    <button type="button" class="btn btn-primary" data-toggle="modal" onclick="location.href='/post/delete/${posts.id}'">삭제하기</button>
                </div>&nbsp;
                <div class="button">
                    <button type="button" class="btn btn-primary" onclick="location.href='/post/list'">목록보기</button>
                </div>
            </div>
            </div>
        </div>
    </div>





<!-- 부트스트랩 js, jquery, popper 추가-->
<script src='//unpkg.com/jquery@3/dist/jquery.min.js'></script>
<script src='//unpkg.com/popper.js@1/dist/umd/popper.min.js'></script>
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>

<!--custom js 추가-->
<script src="/js/app/main.js"></script>


</body>


</html>