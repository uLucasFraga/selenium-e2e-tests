package com.ulucasfraga.enums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum Target {
  LOCAL("local"),
  LOCAL_SUITE("local-suite"),
  SELENIUM_GRID("selenium-grid"),
  TESTCONTAINERS("testcontainers");

  private final String value;
  private static final Map<String, Target> ENUM_MAP;

  Target(String value) {
    this.value = value;
  }

  static {
    Map<String, Target> map =
        stream(Target.values())
            .collect(
                toMap(
                    instance -> instance.value.toLowerCase(),
                    instance -> instance,
                    (a, b) -> b,
                    ConcurrentHashMap::new));
    ENUM_MAP = Collections.unmodifiableMap(map);
  }

  public static Target get(String value) {
    return ENUM_MAP.get(value.toLowerCase());
  }
}
