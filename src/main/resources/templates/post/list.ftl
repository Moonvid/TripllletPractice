<!DOCTYPE HTML>

<#import "../etc/pagination.ftl" as pagination />

<html>
<head>
    <title>Spring Boot Board Service</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <!-- 부트스트랩 js, jquery 추가-->
    <link rel="stylesheet" href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/lib/common.css">

    <style>
        .t-title{
            text-align: center;
            font-weight: bold;
            background-color: lightgrey;
            width: 150px;
        }

        .t-number{
            width: 200px;
        }

        .all{
            text-align: center;
        }
    </style>

</head>

<body>

    <hr/>
    <div>
        <ul class="navi">
            <li class="navi-menu">
                <a href="/">Home</a>
            </li>
            <li class="navi-menu">
                <a href="/post/list">Board</a>
            </li>
        </ul>
    </div>
    <hr/>

    <br/><br/>

<div class="container">
    <div align="center"><h1>게시물 리스트</h1></div>
    <br/>

    <div class="col-md-12">
        <button type="button" class="btn btn-primary" data-toggle="modal" onclick="location.href='/post/insertForm'" >글 등록</button>
    </div>

    <br/>
    <br/>

    <!-- 목록 출력 영역 -->
    <#if postsList?has_content>
        <table class="table table-horizontal table-bordered">
            <thead class="thead-strong">
            <tr>
                <th class="t-title t-number">게시글번호</th>
                <th class="t-title">제목</th>
                <th class="t-title">작성자</th>
                <th class="t-title">최근수정일</th>
            </tr>
            </thead>
            <tbody id="tbody">
                <#list postsList as item>
                    <tr onclick="location.href='/post/view/${item.id}'" class="all">
                        <td>${item.id}</td>
                        <td>${item.title}</td>
                        <td>${item.writer}</td>
                        <td>${item.modifiedDate?string['YYYY-MM-dd']}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
        <div class="row">
            <div class="paging_box">
                <ul class="pagination">
                    <@pagination.first />
                    <@pagination.numbers />
                    <@pagination.last />
                </ul>
            </div>
        </div>
        <br>
    <#else>
        <div>
            <h4 class="all"> 등록된 게시글이 없습니다.</h4>
        </div>
    </#if>
    <form action="/post/list" id="frmSearch">
        <div class="row">
            <div class="search-align">
                <div class="input">
                    <input type="text" name="keyword" id="keyword" value="<#if RequestParameters.keyword?exists>${RequestParameters.keyword}</#if>"/>
                </div>&nbsp;
                <input type="hidden" id="page" name="page" value="<#if RequestParameters.page?exists>${RequestParameters.page}<#else>0</#if>" />
                <input type="hidden" id="row" name="row" value="10" />
                <div class="button">
                    <button type="submit" class="btn btn-primary btn-success">검색</button>
                </div>
            </div>
        </div>
    </form>

</div>



<!--
 Modal 영역
<div class="modal fade" id="savePostsModal" tabindex="-1" role="dialog" aria-labelledby="savePostsLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="savePostsLabel">게시글 등록</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="title">제목</label>
                        <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요" required>
                    </div>
                    <div class="form-group">
                        <label for="writer"> 작성자 </label>
                        <input type="text" class="form-control" id="writer" placeholder="작성자를 입력하세요" required>
                    </div>
                    <div class="form-group">
                        <label for="content"> 내용 </label>
                        <textarea class="form-control" id="content" placeholder="내용을 입력하세요"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                <button type="button" class="btn btn-primary" id="btn-save">등록</button>
            </div>
        </div>
    </div>
</div>
-->

<!-- 부트스트랩 js, jquery, popper 추가-->
<script src='//unpkg.com/jquery@3/dist/jquery.min.js'></script>
<script src='//unpkg.com/popper.js@1/dist/umd/popper.min.js'></script>
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>


<script>

    $(document).ready(function () {

        $("#frmSearch").submit(function () {

            $("#page").val(0);

        });

    });


    function paging(pageValue){
        if(window.location.search !=""){                            // 해당 페이지의 파라미터 값이 있을 때, 즉 검색으로 인한 페이지 이동이 있을 때
            var query = window.location.search.replace("?", "");    // ? 이후의 값을 query에 담는데 ?를 공백으로 대체해서 저장
                                                                    // ex) 검색창에 2를 검색했을 때 keyword=2&page=0&row=10
            query = query.split("&");
            query.forEach(function (one, i) {                      // 매개변수를 받아서 for문을 돌려주는 forEach


                console.log(one.indexOf("page="));                // indexOf로 문자열 검색 시 문자열이 발견되지 않을 경우에는 -1 반환
                //alert(one.indexOf("page="));
                if(one.indexOf("page=") > -1){                      // query[i] 배열에서 page= 가 검색 됐을 경우
                    query.splice(i, 1);                             // query 배열(i)의 1번째 인덱스값을 ""로 바꿔준다 = 삭제
                                                                    // splice(배열, 인덱스값, 바꿀값), 바꿀값이 공백이라면 삭제 기능
                }
            })
            query.push("page=" + pageValue);                        // 배열의 맨 마지막 요소에 "page="+pageValue를 추가
            //console.log(query);
            query = query.join("&");                                // 각 배열 요소를 &으로 연결하여 하나의 문자열 생성
            document.location.href = "?"+query;                     // 해당 문자열로 만들어진 주소로 페이지 이동
        }else{
            document.location.href = "?page="+pageValue;            // 파라미터 값이 없다면 다음 페이지값 주소로 이동
        }
    }
</script>


</body>




</html>