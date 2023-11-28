package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * The type Fertilizer dto.
 */
public record FertilizerDto(
    Integer id,
    String name,
    String brand,
    String composition) {

}
