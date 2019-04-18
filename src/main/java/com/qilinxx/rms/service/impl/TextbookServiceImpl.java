package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.TextbookMapper;
import com.qilinxx.rms.domain.model.Textbook;
import com.qilinxx.rms.domain.model.TextbookExample;
import com.qilinxx.rms.service.TextbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextbookServiceImpl implements TextbookService {
    @Autowired
    TextbookMapper textbookMapper;
    @Override
    public Integer countTextBookByISBN(String ISBN) {
        TextbookExample example=new TextbookExample();
        example.createCriteria().andIsbnEqualTo(ISBN);
        return textbookMapper.selectCountByExample(example);
    }

    @Override
    public Integer countTextBookByISBNExceptId(String ISBN, String id) {
        TextbookExample example=new TextbookExample();
        example.createCriteria().andIsbnEqualTo(ISBN).andIdNotEqualTo(id);
        return textbookMapper.selectCountByExample(example);
    }

    @Override
    public void createTextbook(Textbook textbook) {
        textbookMapper.insert(textbook);
    }

    @Override
    public Textbook findTextbookById(String id) {
        return textbookMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateTextbook(Textbook textbook) {
        textbookMapper.updateByPrimaryKeySelective(textbook);
    }

    @Override
    public void deleteTextbookById(String id) {
        textbookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Textbook> findTextbookByMid(Integer mid) {
        TextbookExample example=new TextbookExample();
        example.createCriteria().andMidEqualTo(mid);
        example.setOrderByClause("state ASC ,create_time DESC");
        return textbookMapper.selectByExample(example);
    }

    @Override
    public int countTextbookByMidState(Integer mid, String state) {
        TextbookExample example=new TextbookExample();
        example.createCriteria().andMidEqualTo(mid).andStateEqualTo(state);
        return textbookMapper.selectCountByExample(example);
    }
}
