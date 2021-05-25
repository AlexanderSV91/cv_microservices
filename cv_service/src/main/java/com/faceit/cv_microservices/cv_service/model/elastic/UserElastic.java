package com.faceit.cv_microservices.cv_service.model.elastic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserElastic {

    private String firstName;
    private String lastName;
    private String srcImage;
}
