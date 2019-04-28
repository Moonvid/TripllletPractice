package com.example.practice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@NoArgsConstructor
public class PageMaker {

    private int totalCount; // 전체 데이터 개수
    private int startPage;  // 시작 페이지
    private int endPage;    // 마지막 페이지
    private boolean prev;   // 이전 페이지
    private boolean next;   // 다음 페이지

    private int displayPageNum = 5; // 페이징 바에서 보여지는 페이지 번호의 개수

    private SearchCriteria searchCri;

    // 10개씩 출력되는 게시판에 대한 전체 데이터 개수 계산
    public int getTotalCount(){

        return totalCount;
    }

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;

        calcData();
    }

    // 데이터 계산을 위한 메서드
    public void calcData(){

        // 게시판 페이징의 마지막 페이지 번호 = Math.ceil(현재 페이지 번호 / 보여지는 페이지 번호의 수) * 보여지는 페이지 번호의 수
        endPage = (int)(Math.ceil(searchCri.getPage() / (double) displayPageNum ) * displayPageNum);

        // 게시판 페이징의 시작 페이지 번호 = (마지막페이지 - 보여지는 페이지 번호의 개수) + 1
        startPage = (endPage - displayPageNum) + 1;

        // 게시판의 실제 마지막 페이지 번호 = Math.ceil(전체 데이터 개수/한 페이지에서 보여줄 데이터 개수)
        int tempEndPage = (int) (Math.ceil(totalCount / (double) searchCri.getPerPageNum()));

        // 게시판 페이징의 마지막 페이지번호가 실제 마지막 페이지 번호보다 높을 경우
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        // 이전 버튼은 시작 페이지가 1일경우에만 비활성화, 그 외에는 활성화
        prev = startPage == 1 ? false : true;

        // 다음 버튼은 마지막 페이지 * 한 페이지당 보여줄 데이터 개수 >= 전체 데이터 개수일 경우 비활성화
        // 그 외에는 활성화
        next = endPage * searchCri.getPerPageNum() >= totalCount ? false : true;

    }

    // 일반 게시글 Parameter 값(page, perPageNum 등)을 유지하기 위한 메서드
    public String makeQuery(int page) throws Exception{

        UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
                .queryParam("perPageNum", searchCri.getPerPageNum()).queryParam("sort", searchCri.getSortDate())
                .build();

        return uriComponents.toUriString();

    }

    // 게시물 검색 시 해당 Parameter 값을 유지하기 위한 메서드
    public String makeSearch(int page) throws Exception{

        UriComponents uriComponents = null;

        if (searchCri != null) {

            uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
                    .queryParam("perPageNum", searchCri.getPerPageNum())
                    .queryParam("keyword", encoding(((SearchCriteria) searchCri).getKeyword())).build();
        }

        return uriComponents.toUriString();

    }

    // 검색 창 검색 시 검색어 관련 encoding
    private String encoding(String keyword){

        if(keyword == null || keyword.trim().length() == 0){
            return "";
        }

        try{
            return URLEncoder.encode(keyword, "utf-8");
        } catch (UnsupportedEncodingException e) {

            return "";

        }


    }

}
