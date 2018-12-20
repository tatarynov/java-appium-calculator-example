package pageobjects.screens.base;

import enums.Operation;

public interface MainCalculatorScreen {

    String getResult();
    MainCalculatorScreen tapDigit(int digit);
    MainCalculatorScreen equals();
    MainCalculatorScreen openAdvancedPad();
    MainCalculatorScreen makeOperation(Operation operation);
    HistoryCalculatorScreen openHistory();
    void goBack();

}
