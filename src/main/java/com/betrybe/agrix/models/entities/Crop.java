package com.betrybe.agrix.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Crop.
 */
@Entity
@Table(name = "crops")
public class Crop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Column(name = "planted_area")
  private Double plantedArea;

  @Column(name = "planted_date")
  private LocalDate plantedDate;

  @Column(name = "harvest_date")
  private LocalDate harvestDate;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farm;

  /**
   * The Fertilizers.
   */
  @ManyToMany
  @JoinTable(
      name = "crop_fertilizer",
      joinColumns = @JoinColumn(name = "fertilizer_id"),
      inverseJoinColumns = @JoinColumn(name = "crop_id")
  )
  List<Fertilizer> fertilizers;

  /**
   * Instantiates a new Crop.
   */
  public Crop() {
  }

  /**
   * Instantiates a new Crop.
   *
   * @param id          the id
   * @param name        the name
   * @param plantedArea the planted area
   * @param farm        the farm
   * @param plantedDate the planted date
   * @param harvestDate the harvest date
   */
  public Crop(Integer id,
      String name,
      Double plantedArea,
      Farm farm,
      LocalDate plantedDate,
      LocalDate harvestDate) {
    this.id = id;
    this.name = name;
    this.plantedArea = plantedArea;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
    this.farm = farm;
  }

  /**
   * Gets fertilizers.
   *
   * @return the fertilizers
   */
  public List<Fertilizer> getFertilizers() {
    return fertilizers;
  }

  /**
   * Sets fertilizers.
   *
   * @param fertilizers the fertilizers
   */
  public void setFertilizers(List<Fertilizer> fertilizers) {
    this.fertilizers = fertilizers;
  }

  /**
   * Gets planted date.
   *
   * @return the planted date
   */
  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  /**
   * Sets planted date.
   *
   * @param plantedDate the planted date
   */
  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  /**
   * Gets harvest date.
   *
   * @return the harvest date
   */
  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  /**
   * Sets harvest date.
   *
   * @param harvestDate the harvest date
   */
  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets planted area.
   *
   * @return the planted area
   */
  public Double getPlantedArea() {
    return plantedArea;
  }

  /**
   * Sets planted area.
   *
   * @param plantedArea the planted area
   */
  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  /**
   * Gets farm.
   *
   * @return the farm
   */
  public Farm getFarm() {
    return farm;
  }

  /**
   * Sets farm.
   *
   * @param farm the farm
   */
  public void setFarm(Farm farm) {
    this.farm = farm;
  }
}
