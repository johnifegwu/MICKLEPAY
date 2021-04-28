package com.mickleentityltdnigeria.mickle_pay.dalc.events;

import com.mickleentityltdnigeria.mickle_pay.data.model.Document;

import java.util.List;

public interface DocumentEvents {

    void onDocumentAdded(List<Document> documents);

    void onDocumentUpdated(List<Document> documents);

    void onDocumentDeleted(String documentID);

    void onDocumentsFetched(List<Document> documents);

    void onDocumentNotFound(Exception e);

    void onError(Exception e);
}
