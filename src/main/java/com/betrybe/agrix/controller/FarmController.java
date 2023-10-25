package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Insert farm response entity.
   *
   * @param farmDto the farm dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Farm> insertFarm(@RequestBody FarmDto farmDto) {
    Farm insertedFarm = farmService.insertFarm(farmDto.toFarm());

    return ResponseEntity.status(HttpStatus.CREATED).body(insertedFarm);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping
  public List<FarmDto> getAllFarms() {
    List<Farm> farmsList = farmService.getAllFarms();

    return farmsList.stream()
        .map(farm -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Integer id) {
    Farm foundFarm = farmService.getFarmById(id);
    return ResponseEntity.ok(foundFarm);
  }

  /**
   * CROPS.
   */

  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(
      @PathVariable Integer farmId,
      @RequestBody Crop crop) {
    Crop newCrop = farmService.createCrop(farmId, crop);
    CropDto cropDto = new CropDto(
        newCrop.getId(),
        newCrop.getName(),
        newCrop.getPlantedArea(),
        newCrop.getFarm().getId());

    return ResponseEntity.status(HttpStatus.CREATED).body(cropDto);
  }

  /**
   * Gets all crops.
   *
   * @param farmId the farm id
   * @return the all crops
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getAllCropsFromFarmId(@PathVariable Integer farmId) {
    List<CropDto> allCrops = farmService.getAllCropsFromFarmId(farmId).stream()
        .filter(crop -> Objects.equals(crop.getFarm().getId(), farmId))
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
