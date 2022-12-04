package com.dreamix.demo.mapper;

import com.dreamix.demo.jpa.entity.City;
import com.dreamix.demo.model.dto.CityResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public CityResponseDto mapToCityResponseDto(City city) {
        CityResponseDto cityResponseDto = new CityResponseDto();
        cityResponseDto.setId(city.getId());
        cityResponseDto.setName(city.getName());
        cityResponseDto.setPhoto(city.getPhoto());
        return cityResponseDto;
    }
}
