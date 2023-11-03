package com.ulucasfraga.test;

import com.ulucasfraga.BaseWeb;
import com.ulucasfraga.data.dynamic.DeviceProtectionDataFactory;
import com.ulucasfraga.page.device.DeviceProtectionPage;
import com.ulucasfraga.page.device.ExamplePage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRoomWebTest extends BaseWeb {

    @Test(description = "Book a room")
    public void bookARoom() {
        var bookingInformation = DeviceProtectionDataFactory.createDeviceProtectionData();

        var accountPage = new DeviceProtectionPage();
        accountPage.selectDevicePrice(bookingInformation.deviceProtectionPrice());
        accountPage.fillPassword(bookingInformation.password());
        accountPage.selectCountry(bookingInformation.country());
        accountPage.selectBudget(bookingInformation.dailyBudget());
        accountPage.clickNewsletter();
        accountPage.next();

        var roomPage = new ExamplePage();
        roomPage.selectExample(bookingInformation.roomType().get());
        roomPage.next();

        var detailPage = new DetailPage();
        detailPage.fillRoomDescription(bookingInformation.roomDescription());
        detailPage.finish();

        assertThat(detailPage.getAlertMessage())
                .isEqualTo("Your reservation has been made and we will contact you shortly");
    }
}
