package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;

  private final CropRepository cropRepository;

  private final FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository,
      CropRepository cropRepository,
      FertilizerRepository fertilizerRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Insert farm farm.
   *
   * @param farm the farm
   * @return the farm
   */
  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   */
  public Farm getFarmById(Integer id) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    return optionalFarm.get();
  }

  /**
   * CROPS.
   *
   * @param id   the id
   * @param crop the crop
   * @return the crop
   */
  public Crop createCrop(Integer id, Crop crop) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    crop.setFarm(optionalFarm.get());
    return cropRepository.save(crop);
  }

  /**
   * Gets all crops from farm id.
   *
   * @param id the id
   * @return the all crops
   */
  public List<Crop> getAllCropsFromFarmId(Integer id) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    return cropRepository.findAll();
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   */
  public Crop getCropById(Integer id) {
    Optional<Crop> optionalCrop = cropRepository.findById(id);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    return optionalCrop.get();
  }

  /**
   * Gets crop by harvest day.
   *
   * @param startDate the start date
   * @param endDate   the end date
   * @return the crop by harvest day
   */
  public List<Crop> getCropByHarvestDay(LocalDate startDate, LocalDate endDate) {
    return cropRepository.findCropByHarvestDateBetween(startDate, endDate);
  }

  /**
   * Association crop and fertilizer crop.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the crop
   */
  public Crop associationCropAndFertilizer(Integer cropId, Integer fertilizerId) {
    Crop crop = cropRepository.findById(cropId)
        .orElseThrow(CropNotFoundException::new);

    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(FertilizerNotFoundException::new);

    crop.getFertilizers().add(fertilizer);
    return cropRepository.save(crop);
  }

  /**
   * FERTILIZERS.
   */

  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> findAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  public Fertilizer getFertilizerById(Integer id) {
    return fertilizerRepository.findById(id)
        .orElseThrow(FertilizerNotFoundException::new);
  }

}
