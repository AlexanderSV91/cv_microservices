package com.faceit.cv_microservices.cv_storage_service.model.mongo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMongo implements Serializable {

    private String firstName;
    private String lastName;
    private String srcImage;
}
