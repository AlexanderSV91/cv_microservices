package com.faceit.cv_microservices.search_google_service.service;

import com.faceit.cv_microservices.search_google_service.dto.request.CvRequest;
import com.faceit.cv_microservices.search_google_service.model.Reference;

import java.util.List;
import java.util.concurrent.Future;

public interface SearchService {

    Future<List<Reference>> search(CvRequest cv);
}
