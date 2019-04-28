package com.example.practice.domain;

import lombok.Data;

@Data
public class SearchCriteria {

    private String searchType;  // 검색 type
    private String keyword;     // 검색 창 검색 시 사용되는 keyword Value
    private String sortDate;          // 정렬 용 날짜
    private int page;            // 페이지 번호
    private int perPageNum;     // 페이지 당 보여지는 데이터 개수

    public SearchCriteria(){
        this.page = 1;  // 페이지 번호는 1
        this.perPageNum = 10;   // 페이지 당 보여지는 데이터 개수는 10으로 초기화
        this.sortDate = "createdDate";
    }

    public void setPage(int page){
        if(page<=0){
            this.page = 1;      // 페이지 번호가 0보다 작을경우 1로 초기화
        }

        this.page = page;
    }

    public int getPageStart(){

        return (this.page -1) * perPageNum; // 시작 데이터 번호 = (페이지 번호 -1) * 페이지 당 보여지는 데이터 개수

    }

    public void setPerPageNum(int perPageNum){ // 페이지 당 보여지는 데이터 개수가 0보다 작거나 100개 이상일 경우 10개로 초기화

        if(perPageNum <= 0 || perPageNum > 100){
            this.perPageNum = 10;
            return;
        }
        this.perPageNum = perPageNum;

    }





}
