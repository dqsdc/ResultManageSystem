package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.Document;

public interface DocumentService {
    /**新建附件记录*/
    void createDocument(Document document);
}
