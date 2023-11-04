package com.ulucasfraga.page.common;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class HelperPage {
  private static final ZoneId THAILAND_ZONE = ZoneId.of("Asia/Bangkok");

  public String getCurrentDateTimeInThailand() {
    ZonedDateTime thailandDateTime = ZonedDateTime.now(THAILAND_ZONE);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", Locale.ENGLISH);
    return thailandDateTime.format(formatter);
  }
}
