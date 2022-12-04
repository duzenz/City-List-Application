package com.dreamix.demo.model.dto;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CityResponseDto {

    private Integer id;
    private String name;
    private String photo;
}
