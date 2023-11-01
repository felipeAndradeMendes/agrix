package com.betrybe.agrix.controller.dto;

import java.time.LocalDate;

/**
 * The type Crop dto.
 */
public record CropDto(Integer id,
                      String name,
                      Double plantedArea,
                      LocalDate plantedDate,
                      LocalDate harvestDate,
                      Integer farmId) {
}
