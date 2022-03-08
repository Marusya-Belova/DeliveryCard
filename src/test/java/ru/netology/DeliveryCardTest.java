package ru.netology;

import org.junit.jupiter.api.Test;

import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

    @Test
    void shouldReturnOK() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(LocalDate.now().plusDays(3));
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Римский-Корсаков Николай");
        $("[data-test-id='phone'] input").setValue("+77777777777");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id='notification']").waitUntil(visible, 15000).shouldHave(text("Успешно! " +
                "Встреча успешно забронирована на " + date));
    }

    @Test
    void shouldTestFailNameinEnglish() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(LocalDate.now().plusDays(3));
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Marusya");
        $("[data-test-id='phone'] input").setValue("+77777777777");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub")
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestFailNumberinName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(LocalDate.now().plusDays(3));
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("123");
        $("[data-test-id='phone'] input").setValue("+77777777777");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub")
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestFailNameEmpty() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(LocalDate.now().plusDays(3));
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+77777777777");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestFailPhoneEmpty() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(LocalDate.now().plusDays(3));
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Мария Белова");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestFailCityEmpty() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(LocalDate.now().plusDays(3));
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Мария Белова");
        $("[data-test-id='phone'] input").setValue("+79177777777");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

}