package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FarmService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            crop.getFarm().getId()
        ))
        .collect(Collectors.toList());

    return ResponseEntity.ok(allCrops);
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Integer id) {
    Crop foundCrop = farmService.getCropById(id);
    CropDto foundCropDto = new CropDto(
        foundCrop.getId(),
        foundCrop.getName(),
        foundCrop.getPlantedArea(),
        foundCrop.getPlantedDate(),
        foundCrop.getHarvestDate(),
        foundCrop.getFarm().getId()
    );
    return ResponseEntity.ok(foundCropDto);
  }

  /**
   * Gets crop by harvest date.
   *
   * @param startDate the start date
   * @param endDate   the end date
   * @return the crop by harvest date
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getCropByHarvestDate(
      @RequestParam(name = "start")LocalDate startDate,
      @RequestParam(name = "end") LocalDate endDate
  ) {
    List<CropDto> crops = farmService.getCropByHarvestDay(startDate, endDate).stream()
        .map(crop -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            crop.getFarm().getId()
        ))
        .toList();

    return ResponseEntity.ok(crops);
  }

  /**
   * Associante crop and fertilizer response entity.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the response entity
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associanteCropAndFertilizer(
      @PathVariable Integer cropId, @PathVariable Integer fertilizerId
  ) {
    farmService.associationCropAndFertilizer(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Gets fertilizers by crop id.
   *
   * @param cropId the crop id
   * @return the fertilizers by crop id
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getFertilizersByCropId(@PathVariable Integer cropId) {
    List<Fertilizer> fertilizers = farmService.getFertilizerByCropId(cropId);

    return ResponseEntity.ok(fertilizers.stream()
        .map(fertilizer -> new FertilizerDto(
            fertilizer.getId(),
            fertilizer.getName(),
            fertilizer.getBrand(),
            fertilizer.getComposition()
        )).toList());
  }
}
