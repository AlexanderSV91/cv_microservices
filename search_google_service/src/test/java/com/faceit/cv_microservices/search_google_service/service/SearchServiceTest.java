package com.faceit.cv_microservices.search_google_service.service;

import com.faceit.cv_microservices.search_google_service.dto.request.CvRequest;
import com.faceit.cv_microservices.search_google_service.dto.request.PreviousWorkRequest;
import com.faceit.cv_microservices.search_google_service.dto.request.SalaryRequest;
import com.faceit.cv_microservices.search_google_service.dto.request.UserRequest;
import com.faceit.cv_microservices.search_google_service.model.Reference;
import com.faceit.cv_microservices.search_google_service.service.impl.SearchServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @InjectMocks
    private SearchServiceImpl searchService;

    @Test
    void search() {
        final CvRequest cv = new CvRequest(
                "1",
                "test",
                new SalaryRequest("test", 1),
                new UserRequest("from", "", "no"),
                Collections.singletonList("test"),
                "test",
                Collections.singletonList(new PreviousWorkRequest("test", "test", "1990")),
                "test");

        try {
            final List<Reference> referenceList = this.searchService.search(cv).get();
            assertTrue(Objects.nonNull(referenceList));

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
