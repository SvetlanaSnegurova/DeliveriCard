package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

    public String generateMinDate() {
        PlanningData data = new PlanningData();
        return data.generateDate(Long.parseLong("3"), "dd.MM.yyyy");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }
    
    @Test
    void shouldDeliveryCardOrder() {
        String generateMinDate = this.generateMinDate();

        $("[data-test-id=city] input").setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(generateMinDate());
        $("[name='name']").setValue("Снегурова Светлана");
        $("[name='phone']").setValue("+79208651085");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=notification]").should(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=notification]").shouldHave(exactText("Успешно! Встреча успешно забронирована на " + generateMinDate));
    }
}
