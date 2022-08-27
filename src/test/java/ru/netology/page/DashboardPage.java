package ru.netology.page;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public void verifyIsDashboardPage(){
        heading.shouldBe(visible);
    }

    public int getCardBalance(String id) {
        String text = $("[data-test-id =" +"'" + id + "']").getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
    public void transferOwnToFirstFromSecondCard(String sum, String numberSecondCard, int countFirstCard) {
        $$("[data-test-id='action-deposit'").get(countFirstCard-1).click();
        $("[data-test-id='amount'] .input__control").sendKeys(sum);
        $("[data-test-id='from'] .input__control").sendKeys(numberSecondCard);
        $("[data-test-id='action-transfer'").click();
        $("[data-test-id='dashboard'").should(visible);
    }

    public String errorWithInsufficientSum() {
        return $("[data-test-id='error-notification'] .notification__content").getText();
    }
}


