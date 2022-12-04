package com.dreamix.demo.service;


import com.dreamix.demo.exception.ResourceNotFoundException;
import com.dreamix.demo.jpa.entity.City;
import com.dreamix.demo.jpa.repository.CityRepository;
import com.dreamix.demo.mapper.CityMapper;
import com.dreamix.demo.model.dto.CityRequestDto;
import com.dreamix.demo.model.dto.CityResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityResponseDto findById(Integer id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City not found with id: " + id));
        return cityMapper.mapToCityResponseDto(city);
    }

    public CityResponseDto findByName(String name) {
        City city = cityRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("City not found with name: " + name));
        return cityMapper.mapToCityResponseDto(city);
    }

    public Page<CityResponseDto> findAll(Integer page,
                                         Integer size) {
        return cityRepository.findAll(PageRequest.of(page, size))
                .map(cityMapper::mapToCityResponseDto);
    }

    public CityResponseDto update(Integer id, CityRequestDto cityRequestDto) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City not found with id: " + id));
        city.setName(cityRequestDto.getName());
        city.setPhoto(cityRequestDto.getPhoto());
        cityRepository.save(city);
        return cityMapper.mapToCityResponseDto(city);
    }

}
