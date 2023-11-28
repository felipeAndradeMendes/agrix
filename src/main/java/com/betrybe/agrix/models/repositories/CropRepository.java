package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Crop repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Integer> {
  // Tentativa de método de consulta derivado.
  //  List<Crop> findByHarvestDateBetweenStartDateAndEndDate(
  //      LocalDate startDate,
  //      LocalDate endDate);

  //  /**
  //   * Metodo de query nativa do sql.
  //   */
  //  @Query(
  //      value = "SELECT * FROM crops WHERE harvest_date BETWEEN :start AND :end",
  //      nativeQuery = true)
  //  List<Crop> findCropByHarvestDateBetween(
  //      @RequestParam("start") LocalDate start,
  //      @RequestParam("end") LocalDate end);

  /**
   * Método de consulta derivado.
   */
  List<Crop> findCropByHarvestDateBetween(LocalDate start, LocalDate end);

  //  /**
  //   * Metodo para trazer do banco lista de fertilizantes de uma crop.
  //   */
  //  @Query("SELECT c.fertilizers FROM Crop c WHERE c.id = :cropId")
  //  List<Fertilizer> findFertilizersByCropId(@Param("cropId") Integer cropId);

}
