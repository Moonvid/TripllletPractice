

<#import "./pagination.ftl" as pagination />

<link href="/assets/plugins/bower_components/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row bg-title">
            <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
                <ol class="breadcrumb" stlye="float:left;">
                    <li>
                        <a href="/main">Home</a>


                    </li>
                    <li>
                        <a href="/notice/list">공지사항 관리</a>
                    </li>
                    <li class="active">공지사항 리스트</li>
                </ol>
            </div>
        </div>

        <!-- row -->
        <div class="row">

            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="white-box">
                    <h3 class="box-title">공지사항 검색</h3>
                    <form id="frmSearch" action="/notice/list" name="frmSearch" method="get">
                        <label for="keyword">검색어</label>
                        <div class="row form-group">
                            <div class="col-sm-2">
                                <select class="form-control" name="searchOpt" id="searchOpt">
                                    <option value="0">제목</option>
                                    <option value="1">내용</option>
                                    <option value="2">제목 + 내용</option>
                                </select>
                            </div>
                            <div class="col-sm-2">
                                <input type="text" class="form-control col-lg-8" name="keyword" id="keyword" value="<#if RequestParameters.keyword?? >${RequestParameters.keyword}<#else></#if>" placeholder="검색어를 입력하세요.">
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4">
                                <label class="input-daterange-datepicker">작성 일자</label>
                                <input class="form-control input-daterange-datepicker" type="text" />
                                <input type="hidden" id="startDate" name="startDate" />
                                <input type="hidden" id="endDate" name="endDate" />
                            </div>
                        </div>
                        <div class="row form-group activate-group">
                            <label class="col-lg-12">노출 상태</label>
                            <div class="radio">
                                <div class="col-lg-2 col-md-2 col-sm-4 col-xs-12">
                                    <input type="radio" name="expose" id="activateRadio1" value="-1" checked>
                                    <label for="activateRadio1">전체</label>
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-4 col-xs-12">
                                    <input type="radio" name="expose" id="activateRadio2" value="1">
                                    <label for="activateRadio2">노출</label>
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-4 col-xs-12">
                                    <input type="radio" name="expose" id="activateRadio3" value="0">
                                    <label for="activateRadio3">비노출</label>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="page" name="page" value="<#if RequestParameters.page?exists>${RequestParameters.page}<#else>0</#if>" />
                        <input type="hidden" id="row" name="row" value="10" />
                        <button type="submit" class="btn waves-effect waves-light m-r-10">검색</button>
                    </form>
                </div>
            </div>
        </div>
        <!-- row end -->
        <div class="row">
            <div class="col-sm-12">
                <div class="white-box">
                    <div class="table-responsive userListTable">
                        <table id="UserListTable" class="table table-striped">
                            <thead>
                                <tr>
                                    <th class="tableCheckBox">
                                        <input id="all_check_btn" type="checkbox" />
                                    </th>
                                    <th>No.</th>
                                    <th class="col-lg-6">Title</th>
                                    <th>작성자</th>
                                    <th>작성일</th>
                                    <th>노출여부</th>
                                </tr>
                            </thead>
                            <tbody>
                                <#list results as row>
                                        <tr style="cursor:pointer;">
                                            <td>
                                                <input type="checkbox" name="tableCheckbox" class="tableCheckbox setCheck" value="${row.id?c}" />
                                            </td>
                                            <td onclick="OpenInNewTabWinBrowser('/notice/view?noticeId=${row.id?c}')">${row.id?c}</td>
                                            <td onclick="OpenInNewTabWinBrowser('/notice/view?noticeId=${row.id?c}')">${row.title}</td>
                                            <td onclick="OpenInNewTabWinBrowser('/notice/view?noticeId=${row.id?c}')">${row.authorName!}</td>
                                            <td onclick="OpenInNewTabWinBrowser('/notice/view?noticeId=${row.id?c}')">${row.createDatetime?string["yyyy-MM-dd"]}</td>
                                            <td onclick="OpenInNewTabWinBrowser('/notice/view?noticeId=${row.id?c}')">
                                                <#if row.expose?number=1>
                                                    노출
                                                <#else>
                                                    비노출
                                                </#if>
                                            </td>
                                        </tr>
                                </#list>
                            </tbody>
                        </table>
                        
                        <div class="paging_box">
                            <ul class="pagination">
                                <@pagination.first />
                                <@pagination.numbers />
                                <@pagination.last />
                            </ul>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->
</div>
<!-- /#page-wrapper -->

<!-- custom 스크립트 추가 영역 -->
<script src="/assets/plugins/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<script src="/assets/plugins/bower_components/datatables/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
<script>

    function paging(pageValue) {
        if(window.location.search != "") {
            var query = window.location.search.replace("?", "");
            query = query.split("&");
            query.forEach(function (one, i) {
                console.log(one.indexOf("page="));
                if (one.indexOf("page=") > -1) {
                    query.splice(i, 1);
                }
            })
            query.push("page=" + pageValue);
            query = query.join("&");
            document.location.href = "?"+query;
        }else{
            document.location.href = "?page="+pageValue;
        }
    }

var startDate = <#if RequestParameters.startDate?? >"${RequestParameters.startDate}"<#else>moment().subtract(30, 'days').format("YYYY-MM-DD")</#if>,
    endDate = <#if RequestParameters.endDate?? >"${RequestParameters.endDate}"<#else>moment().format("YYYY-MM-DD")</#if>,
    searchOpt = <#if RequestParameters.searchOpt?? >"${RequestParameters.searchOpt}"<#else>0</#if>,
    expose = <#if RequestParameters.expose?? >"${RequestParameters.expose}"<#else>-1</#if>,
    row = <#if RequestParameters.row?? >"${RequestParameters.row}"<#else>10</#if>

    var dataTable = undefined;
    var s_total = '${summary?exists?then(summary.total,0)}';
    var s_expose = '${summary?exists?then(summary.expose,0)}';
    var s_unexpose = '${summary?exists?then(summary.unexpose,0)}';
</script>

<script src="/assets/js/page/notice/noticeList.js"></script>
<script>
    if (!dataTable.data().count()) {
    <#if RequestParameters.page?exists>
        modalFunction({"type":"alert","content":"데이터가 존재하지 않습니다. 다시 검색해주세요."});
    </#if>
    }
</script>