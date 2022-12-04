package com.dreamix.demo.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CityRequestDto {

    @NotNull(message = "Required field name is missing.")
    @NotBlank(message = "Name must not be blank.")
    private String name;

    @NotNull(message = "Required field photo is missing.")
    @NotBlank(message = "Photo must not be blank.")
    private String photo;
}
