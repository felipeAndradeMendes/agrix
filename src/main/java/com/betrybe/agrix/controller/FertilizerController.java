package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  /**
   * Find all fertilizers response entity.
   *
   * @return the response entity
   */
  @GetMapping
  public ResponseEntity<List<FertilizerDto>> findAllFertilizers() {
    List<Fertilizer> fertilizers = farmService.findAllFertilizers();
    return ResponseEntity
        .ok(
            fertilizers.stream()
                .map(fertilizer -> new FertilizerDto(
                    fertilizer.getId(),
                    fertilizer.getName(),
                    fertilizer.getBrand(),
                    fertilizer.getComposition()
                )).toList()
        );
  }

  /**
   * Gets fertilizer by id.
   *
   * @param id the id
   * @return the fertilizer by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Integer id) {
    Fertilizer fertilizer = farmService.getFertilizerById(id);

    return ResponseEntity.ok(
        new FertilizerDto(
            fertilizer.getId(),
            fertilizer.getName(),
            fertilizer.getBrand(),
            fertilizer.getComposition()
        )
    );
  }
}
