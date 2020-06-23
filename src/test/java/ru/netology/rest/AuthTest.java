package ru.netology.rest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.cssSelector;

public class AuthTest {
    @Test
    void shouldRegistrationIfUserActive() {
        RegistrationDto user = Generation.generationUserActive();
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=login] input")).sendKeys(user.getLogin());
        form.$(cssSelector("[data-test-id=password] input")).sendKeys(user.getPassword());
        form.$(cssSelector("[data-test-id=action-login] ")).click();
        $(byText("Личный кабинет")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfStatusBlocked() {
        RegistrationDto user = Generation.generationUserBlocked();
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=login] input")).sendKeys(user.getLogin());
        form.$(cssSelector("[data-test-id=password] input")).sendKeys(user.getPassword());
        form.$(cssSelector("[data-test-id=action-login] ")).click();
        $(byText("Ошибка")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfInvalidLogin() {
        RegistrationDto user = Generation.generationUserIfLoginInvalid();
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=login] input")).sendKeys(user.getLogin());
        form.$(cssSelector("[data-test-id=password] input")).sendKeys(user.getPassword());
        form.$(cssSelector("[data-test-id=action-login] ")).click();
        $(byText("Ошибка")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfInvalidPassword() {
        RegistrationDto user = Generation.generationUserIfPasswordInvalid();
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=login] input")).sendKeys(user.getLogin());
        form.$(cssSelector("[data-test-id=password] input")).sendKeys(user.getPassword());
        form.$(cssSelector("[data-test-id=action-login] ")).click();
        $(byText("Ошибка")).waitUntil(Condition.visible, 15000);
    }
}
