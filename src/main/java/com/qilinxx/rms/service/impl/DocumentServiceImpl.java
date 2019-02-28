package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.DocumentMapper;
import com.qilinxx.rms.domain.model.Document;
import com.qilinxx.rms.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    DocumentMapper documentMapper;
    @Override
    public void createDocument(Document document) {
        documentMapper.insert(document);
    }
}
