package com.faceit.cv_microservices.cv_service.controller;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.mapper.CvElasticMapper;
import com.faceit.cv_microservices.cv_service.service.CvStorageElasticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1/elastic")
@Tag(name = "CvElastic", description = "cv elastic")
public class CvElasticControllerRest {

    private final CvStorageElasticService cvStorageElasticService;
    private final CvElasticMapper cvElasticMapper;

    public CvElasticControllerRest(CvStorageElasticService cvStorageElasticService,
                                   CvElasticMapper cvElasticMapper) {
        this.cvStorageElasticService = cvStorageElasticService;
        this.cvElasticMapper = cvElasticMapper;
    }

    @Operation(summary = "find all cv elastic", tags = "CV")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get all cv elastic", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CvResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(hidden = true))})})
    @GetMapping
    public ResponseEntity<Page<CvResponse>> findAllCvElastic(Pageable pageable) {
        return ResponseEntity.ok(this.cvStorageElasticService.findAllCv(pageable));
    }

    @Operation(summary = "find cv elastic", tags = "CV")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get cv elastic", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CvResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(hidden = true))})})
    @GetMapping("/{id}")
    public ResponseEntity<CvResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.cvElasticMapper.toCvResponse(this.cvStorageElasticService.findById(id)));
    }
}
