package com.faceit.cv_microservices.search_google_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reference {

    String url;
    String title;
    String description;
}
