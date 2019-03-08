package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.Document;

import java.util.List;

public interface DocumentService {
    /**新建附件记录*/
    void createDocument(Document document);
    /**根据itemId 删除附件记录*/
    void deleteDocumentByItemId(String itemId);
    /**通过itemId  得到附件记录*/
    List<Document> findDocumentByItemId(String itemId);
    /**通过附件did      返回附件记录*/
    Document findDocumentByDid(Integer did);
    /**根据附件 did     删除附件记录*/
    void deleteDocumentByDid(Integer did);
}
