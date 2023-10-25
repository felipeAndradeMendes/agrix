package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FarmService farmService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param farmService the farm service
   */
  public FertilizerController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Create fertilizer response entity.
   *
   * @param fertilizer the fertilizer
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<FertilizerDto> createFertilizer(@RequestBody Fertilizer fertilizer) {
    Fertilizer newFertilizer = farmService.createFertilizer(fertilizer);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new FertilizerDto(
            newFertilizer.getId(),
            newFertilizer.getName(),
            newFertilizer.getBrand(),
            newFertilizer.getComposition()
        ));
  }
}
