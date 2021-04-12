package com.onemount.barcelonateam.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player implements List<Substitute> {
  public Player() {
    }
private String name;
  private int number;
  private Position position;
public void add(Player player) {
}
}
