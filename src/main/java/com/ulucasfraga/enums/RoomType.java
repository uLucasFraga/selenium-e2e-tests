package com.ulucasfraga.enums;

import java.security.SecureRandom;
import java.util.function.Supplier;

public enum RoomType implements Supplier<String> {
  SINGLE("Single"),
  FAMILY("Family"),
  BUSINESS("Business");

  private final String value;

  RoomType(String value) {
    this.value = value;
  }

  public static RoomType getRandom() {
    return values()[new SecureRandom().nextInt(values().length)];
  }

  @Override
  public String get() {
    return this.value;
  }
}
