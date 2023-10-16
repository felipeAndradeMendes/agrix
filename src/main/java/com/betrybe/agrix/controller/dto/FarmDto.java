package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.models.entities.Farm;

/**
 * The type Farm dto.
 */
public record FarmDto(Integer id, String name, Double size) {

  /**
   * To farm.
   *
   * @return the farm
   */
  public Farm toFarm() {
    return new Farm(id, name, size);
  }
}
