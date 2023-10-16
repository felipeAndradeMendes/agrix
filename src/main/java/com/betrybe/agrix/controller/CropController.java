package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final FarmService farmService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public CropController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<CropDto> allCrops = farmService.getAllCrops().stream()
        .map(crop -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getFarm().getId()
        ))
        .collect(Collectors.toList());

    return ResponseEntity.ok(allCrops);
  }
}