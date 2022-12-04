package com.dreamix.demo.service;

import com.dreamix.demo.exception.ResourceNotFoundException;
import com.dreamix.demo.jpa.entity.City;
import com.dreamix.demo.jpa.repository.CityRepository;
import com.dreamix.demo.mapper.CityMapper;
import com.dreamix.demo.model.dto.CityRequestDto;
import com.dreamix.demo.model.dto.CityResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CityServiceTest {

    @InjectMocks
    private CityService cityService;

    @Mock
    private CityRepository cityRepository;

    @Spy
    private CityMapper cityMapper;

    @Test
    public void findById() {
        when(cityRepository.findById(1)).thenReturn(Optional.of(new City(1, "Istanbul", "photoUrl")));
        CityResponseDto cityResponseDto = cityService.findById(1);
        assertEquals(Integer.valueOf(1), cityResponseDto.getId());
        assertEquals("Istanbul", cityResponseDto.getName());
        assertEquals("photoUrl", cityResponseDto.getPhoto());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findByIdWithException() {
        when(cityRepository.findById(1)).thenReturn(Optional.empty());
        cityService.findById(1);
    }

    @Test
    public void findByName() {
        when(cityRepository.findByName("Istanbul")).thenReturn(Optional.of(new City(1, "Istanbul", "photoUrl")));
        CityResponseDto cityResponseDto = cityService.findByName("Istanbul");
        assertEquals(Integer.valueOf(1), cityResponseDto.getId());
        assertEquals("Istanbul", cityResponseDto.getName());
        assertEquals("photoUrl", cityResponseDto.getPhoto());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findByNameWithException() {
        when(cityRepository.findByName("Istanbul")).thenReturn(Optional.empty());
        cityService.findByName("Istanbul");
    }

    @Test
    public void update() {
        when(cityRepository.findById(1)).thenReturn(Optional.of(new City(1, "Istanbul", "photoUrl")));
        CityResponseDto cityResponseDto = cityService.update(1, new CityRequestDto("Sofia", "photoUrl"));
        assertEquals(Integer.valueOf(1), cityResponseDto.getId());
        assertEquals("Sofia", cityResponseDto.getName());
        assertEquals("photoUrl", cityResponseDto.getPhoto());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateWithException() {
        when(cityRepository.findById(1)).thenReturn(Optional.empty());
        cityService.update(1, new CityRequestDto("Sofia", "photoUrl"));
    }

    @Test
    public void findAll() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City(1, "Istanbul", "photoUrl"));
        cities.add(new City(2, "Sofia", "photoUrl"));
        when(cityRepository.findAll(PageRequest.of(0, 50))).thenReturn(new PageImpl<>(cities));
        Page<CityResponseDto> citiesPageable = cityService.findAll(0, 50);
        assertEquals(1, citiesPageable.getTotalPages());
    }

}
