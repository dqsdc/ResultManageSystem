package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.TextbookMapper;
import com.qilinxx.rms.domain.model.TextbookExample;
import com.qilinxx.rms.service.TextbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextbookServiceImpl implements TextbookService {
    @Autowired
    TextbookMapper textbookMapper;
    @Override
    public Integer findTextBookByISBN(String ISBN) {
        TextbookExample example=new TextbookExample();
        example.createCriteria().andIsbnEqualTo(ISBN);
        return textbookMapper.selectCountByExample(example);
    }
}
