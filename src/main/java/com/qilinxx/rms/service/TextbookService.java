package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.Textbook;

import java.util.List;

public interface TextbookService {
    /**查询重复的个数*/
    Integer countTextBookByISBN(String ISBN);
    /**查询重复的个数      除了指定id*/
    Integer countTextBookByISBNExceptId(String ISBN,String id);
    /**插入一个新的记录*/
    void createTextbook(Textbook textbook);
    /**通过id 查询记录*/
    Textbook findTextbookById(String id);
    /**更新一条记录*/
    void updateTextbook(Textbook textbook);
    /**通过id  删除记录*/
    void deleteTextbookById(String id);
    /**通过mid查找的记录*/
    List<Textbook> findTextbookByMid(Integer mid);
    /**通过 mid  与state  查找记录*/
    int countTextbookByMidState(Integer mid,String state);
}
