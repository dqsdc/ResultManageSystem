package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.DocumentMapper;
import com.qilinxx.rms.domain.model.Document;
import com.qilinxx.rms.domain.model.DocumentExample;
import com.qilinxx.rms.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    DocumentMapper documentMapper;
    @Override
    public void createDocument(Document document) {
        documentMapper.insert(document);
    }

    @Override
    public void deleteDocumentByItemId(String itemId) {
        DocumentExample example=new DocumentExample();
        example.createCriteria().andItemIdEqualTo(itemId);
        documentMapper.deleteByExample(example);
    }

    @Override
    public List<Document> findDocumentByItemId(String itemId) {
        DocumentExample example=new DocumentExample();
        example.createCriteria().andItemIdEqualTo(itemId);
        return documentMapper.selectByExample(example);
    }

    @Override
    public Document findDocumentByDid(Integer did) {
        return documentMapper.selectByPrimaryKey(did);
    }

    @Override
    public void deleteDocumentByDid(Integer did) {
        documentMapper.deleteByPrimaryKey(did);
    }
}
