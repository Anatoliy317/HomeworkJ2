package tests;

import com.codeborne.selenide.SelenideElement;
import helpers.Attach;
import org.junit.jupiter.api.*;



import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegistrationForm extends TestBase {

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }


        @Test
        @Tag("smoke")
        @DisplayName("Заполнение всех полей формы регистрации")
        void successfulRegistrationTest() {
            step("Открытие формы регистрации",()-> {
                open("/automation-practice-form");
                $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
                SelenideElement bannerRoot = $(".fc-consent-root");
                if (bannerRoot.isDisplayed()) {
                    bannerRoot.$(byText("Consent")).click();
                }
                executeJavaScript("$('#fixedban').remove()");
                executeJavaScript("$('footer').remove()");
            });
            step("Заполнение полей",()->{
                $("#firstName").setValue("Alex");
                $("#lastName").setValue("Egorov");
                $("#userEmail").setValue("alex@egorov.com");
                $("#genterWrapper").$(byText("Other")).click();
                $("#userNumber").setValue("1234567890");
                $("#dateOfBirthInput").click();
                $(".react-datepicker__month-select").selectOption("July");
                $(".react-datepicker__year-select").selectOption("2008");
                $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
                $("#subjectsInput").setValue("Math").pressEnter();
                $("#hobbiesWrapper").$(byText("Sports")).click();
                $("#uploadPicture").uploadFromClasspath("homework.jpg");
                $("#currentAddress").setValue("Some address 1");
                $("#state").click();
                $("#stateCity-wrapper").$(byText("NCR")).click();
                $("#city").click();
                $("#stateCity-wrapper").$(byText("Delhi")).click();
                $("#submit").click();
            });
            step("Проверка полей регистрации",()->{
                $(".modal-dialog").should(appear);
                $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
                $(".table-responsive").shouldHave(text("Alex"), text("Egorov"),
                        text("alex@egorov.com"), text("1234567890"));
            });
        }
}

