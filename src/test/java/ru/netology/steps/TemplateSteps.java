package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;


public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void loginWithNameAndPassword(String login, String password) {
        String url = "http://localhost:9999";
        loginPage = Selenide.open(url, LoginPage.class);
        verificationPage = loginPage.validLogin(login, password);
        String verificationCode = "12345";
        dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.verifyIsDashboardPage();
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void transferToFirstCardFromSecond(String sum, String numberSecondCard, int countFirstCard) {
        dashboardPage.transferOwnToFirstFromSecondCard(sum, numberSecondCard, countFirstCard);
    }


    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей")
    public void getBalanceFirstCardHappy(int expectedSum) {
        int actualBalance = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
        Assertions.assertEquals(expectedSum, actualBalance);
    }


//    @Тогда("баланс его {int} карты из списка на главной странице не должен стать {int} рублей")
//    public void getBalanceFirstCardNotHappy(int expectedSum) {
//        int actualBalance = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
//        Assertions.assertNotEquals(expectedSum, actualBalance);
//    }


}