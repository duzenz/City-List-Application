package com.dreamix.demo.controller;


import com.dreamix.demo.model.ErrorContainer;
import com.dreamix.demo.model.dto.CityRequestDto;
import com.dreamix.demo.model.dto.CityResponseDto;
import com.dreamix.demo.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityController {

    public static final String DEFAULT_PAGE_SIZE = "50";
    private final CityService cityService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get city by id", description = "Get city by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful response"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorContainer.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorContainer.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorContainer.class)))
    })
    public CityResponseDto getCityById(@PathVariable @NotNull @PositiveOrZero Integer id) {
        log.debug("Request to get city by id: {}", id);
        return cityService.findById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get city by name", description = "Get city by name")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful response"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorContainer.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorContainer.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorContainer.class)))
    })
    public CityResponseDto getCityByName(@PathVariable @NotNull String name) {
        log.debug("Request to get city by name: {}", name);
        return cityService.findByName(name);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all cities with pagination", description = "Get all cities with pagination")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful response"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorContainer.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorContainer.class)))
    })
    public Page<CityResponseDto> getCities(
            @RequestParam(defaultValue = "0", required = false) @PositiveOrZero Integer page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE, required = false) @PositiveOrZero Integer size) {
        return cityService.findAll(page, size);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update city with id", description = "Update city with id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Successful response"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorContainer.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorContainer.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorContainer.class)))
    })
    public CityResponseDto updateCity(@RequestBody @Valid CityRequestDto cityRequestDto,
                                      @PathVariable @NotNull @PositiveOrZero Integer id) {
        log.debug("Request to update city with cityRequestDto: {}, id: {}", cityRequestDto.toString(), id);
        return cityService.update(id, cityRequestDto);
    }

}