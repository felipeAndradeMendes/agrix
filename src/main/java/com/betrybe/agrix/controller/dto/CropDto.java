package com.betrybe.agrix.controller.dto;

/**
 * The type Crop dto.
 */
public record CropDto(Integer id, String name, Double plantedArea, Integer farmId) {
}
