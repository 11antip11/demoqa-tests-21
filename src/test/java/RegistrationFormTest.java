import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy="eager";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Antipov");
        $("#userEmail").setValue("ivanantip98@mail.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirth-wrapper").click();
        $(".react-datepicker__month-select").$(byText("August")).click();
        $(".react-datepicker__year-select").$(byText("1998")).click();
        $(".react-datepicker__month").$(byText("20")).click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("task1_Antipov.png");
        $("#currentAddress").setValue("Current address 1");
        $("#stateCity-wrapper").$(byText("Select State")).click();
        $(byText("NCR")).click();
        $("#stateCity-wrapper").$(byText("Select City")).click();
        $(byText("Delhi")).click();

        $("#submit").click();

        //проверка
        $(".table-responsive").shouldHave(text("Ivan"));
        $(".table-responsive").shouldHave(text("Antipov"));
        $(".table-responsive").shouldHave(text("ivanantip98@mail.ru"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("1234567890"));
        $(".table-responsive").shouldHave(text("20 August,1998"));
        $(".table-responsive").shouldHave(text("Maths"));
        $(".table-responsive").shouldHave(text("Sports"));
        $(".table-responsive").shouldHave(text("Current address 1"));
        $(".table-responsive").shouldHave(text("NCR"));
        $(".table-responsive").shouldHave(text("Delhi"));
    }
}