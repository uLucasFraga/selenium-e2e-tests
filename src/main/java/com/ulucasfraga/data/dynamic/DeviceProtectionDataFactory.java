package com.ulucasfraga.data.dynamic;

import com.ulucasfraga.enums.RoomType;
import com.ulucasfraga.model.DeviceProtection;
import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

import static com.ulucasfraga.config.ConfigurationManager.configuration;

public final class DeviceProtectionDataFactory {

  private static final Faker faker = new Faker(new Locale(configuration().faker()));
  private static final Logger logger = LogManager.getLogger(DeviceProtectionDataFactory.class);

  private DeviceProtectionDataFactory() {}

  public static DeviceProtection createDeviceProtectionData() {
    var deviceProtection =
        new DeviceProtection.DeviceProtectionBuilder()
            .devicePrice(returnRandomDevicePurchasePrice())
            .build();

    logger.info(deviceProtection);
    return deviceProtection;
  }

  private static String returnRandomDevicePurchasePrice() {
    return faker
        .options()
        .option(
            "THB 2,000 - 6,000",
            "THB 6,001 - 10,000",
            "THB 10,001 - 15,000",
            "THB 15,001 - 22,000",
            "THB 22,001 - 26,000",
            "THB 26,001 - 36,000",
            "THB 36,001 - 65,000");
  }
}
