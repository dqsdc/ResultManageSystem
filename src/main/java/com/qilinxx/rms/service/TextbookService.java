package com.qilinxx.rms.service;

public interface TextbookService {
    /**查询重复的个数*/
    Integer findTextBookByISBN(String ISBN);
}
