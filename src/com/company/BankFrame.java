package com.company;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.util.ArrayList;
import java.io.File;


public class BankFrame extends Application {
    private Bank bank;
    private final String FILENAME = "bank.dat";

    private Scene mainScene = new Scene(initLoginPage());
    private TabPane tabPaneForAcc = new TabPane();

    @Override
    public void start(Stage primaryStage) {
        if (new File(FILENAME).exists()) {
            try {
                bank = new Bank(FILENAME);
                showInfo("Информация загружена");
            } catch (Exception e) {
                showError("Ошибка при открытии файла");
                System.exit(1);     //Выход из программы с ошибкой
            }
        } else {
            bank = new Bank();
        }

        primaryStage.setTitle("Банк");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.setScene(mainScene);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.show();
    }

    private VBox initAccountPage(Owner checkedOwner) {

        // регистрация клиента
        Label ownerLabel = new Label("Добро пожаловать, ");
        Label ownerValue = new Label(checkedOwner.getName());
        HBox ownerBox = new HBox(5, ownerLabel, ownerValue);
        ownerBox.setStyle("-fx-background-color : #CBCCFF");
        Button allAccounts = new Button("Получить сводную информацию по всем счетам");
        allAccounts.setOnAction(event -> showInfo(checkedOwner.printInfo()));


        Label createAccLabel = new Label("Создать новый аккунт в валюте");
        ObservableList<CoinType> currency = FXCollections.observableArrayList(CoinType.Dollar, CoinType.Euro, CoinType.Ruble);
        ComboBox<CoinType> currencyComboBox = new ComboBox<CoinType>(currency);
        currencyComboBox.setValue(CoinType.Dollar);

        Button createAccButton = new Button("Создать");
        createAccButton.setOnAction(e -> {
            BankAccount bankAccount = checkedOwner.openAccount(currencyComboBox.getValue());
            Tab tabAcc = new Tab(bankAccount.getId().toString());
            tabAcc.setOnClosed(event -> checkedOwner.closeAccount(bankAccount));
            tabPaneForAcc.getTabs().add(tabAcc);
            tabAcc.setContent(initTabContent(checkedOwner,bankAccount));
        });
        HBox createAccBox = new HBox(10, createAccLabel, currencyComboBox, createAccButton);

        // информация для владельца о его счетах
        Label fromTransferLabel = new Label("Перевести со счета:");
        TextField fromTransferTextField = new TextField();
        Label toTransferLabel = new Label("На счёт:");
        TextField toTransferTextField = new TextField();
        Label moneyForTransferLabel = new Label("сумму:");
        TextField moneyForTransfer = new TextField();
        Button transferButton = new Button("Перевести");
        transferButton.setOnAction(event -> {
            double sum = Double.parseDouble(moneyForTransfer.getText());
            BankAccount fromAcc = checkedOwner.getBankAccountById(Integer.parseInt(fromTransferTextField.getText()));
            BankAccount toAcc = checkedOwner.getBankAccountById(Integer.parseInt(toTransferTextField.getText()));
            boolean f = checkedOwner.transfer(sum, fromAcc, toAcc);
            if (f) {
                for (Tab tab : tabPaneForAcc.getTabs()) {
                    BankAccount bankAccount = checkedOwner.getBankAccountById(Integer.parseInt(tab.getText()));
                    tab.setContent(initTabContent(checkedOwner,bankAccount));
                }
            } else {
                showInfo("Во время операции возникла ошибка. Проверьте правильность ввода номеров счетов или баланс на карте. На данный момент перевод между счетами разных валют не поддерживается.");
            }
        });

        HBox transferMoneyBox = new HBox(10, fromTransferLabel, fromTransferTextField, toTransferLabel, toTransferTextField, moneyForTransferLabel, moneyForTransfer, transferButton);

        Label operationFieldsName = new Label("Тип операции        "+"Номер счета       "+"Дата операции                               "+"Сумма   "+"Валюта");

        Label operationLabel = new Label();
        Button operationHistoryButton = new Button("Обновить историю операций");
        operationHistoryButton.setOnAction(event -> {
            operationLabel.setText(initHistoryLabel(checkedOwner.getOperationHistory()));
        });
        Label historyLabel = new Label("История операций:");
        HBox operationHistoryBox = new HBox(10, operationLabel);

        ScrollPane historyScrollPane = new ScrollPane();
        historyScrollPane.setContent(operationHistoryBox);
        historyScrollPane.setMaxHeight(300);


        return new VBox(20, initMenuBar(), ownerBox,allAccounts, tabPaneForAcc, createAccBox, transferMoneyBox,historyLabel,operationHistoryButton,operationFieldsName,historyScrollPane);

    }
    private String initHistoryLabel(ArrayList<OperationRecord> history){
        String text="";
        for (OperationRecord i : history) {
            text = text + i.getOperationType() + "\t" + i.getBankAccountID() + "\t" + i.getDate() +"\t" + i.getSum() + "\t" + i.getCoinType() + "\n";
        }
        return text;
    }

    private VBox initTabContent(Owner owner, BankAccount bankAccount) {
        Label idLabel = new Label("Номер счёта: " + bankAccount.getId());
        Label dateLabel = new Label("Дата создания: " + bankAccount.getDate());
        Label balanceLabel = new Label("Баланс: " + bankAccount.getBalance() + " " + bankAccount.getCoinType());

        Label addMoneyLabel = new Label("Добавить на счет/Снять со счёта:");
        TextField moneyTextField = new TextField();
        //запретить ввод символов отличных от цифр
        Label addMoneyCurrency = new Label(bankAccount.getCoinType().toString());
        Button addMoneyButton = new Button("Пополнить");
        addMoneyButton.setOnAction(event -> {
            try {
                if (Double.parseDouble(moneyTextField.getText()) <= 0) {
                    showInfo("Недопустимый ввод");
                    moneyTextField.setText("");
                } else {
                    owner.deposit(Double.parseDouble(moneyTextField.getText()),bankAccount);
                    balanceLabel.setText("Баланс: " + bankAccount.getBalance() + " " + bankAccount.getCoinType());
                    moneyTextField.setText("");
                }
            } catch (NumberFormatException e) {
                showInfo("Недопустимый ввод");
                moneyTextField.setText("");
            }
        });
        Button subtractMoneyButton = new Button("Снять");
        subtractMoneyButton.setOnAction(event -> {
            try {
                if (Double.parseDouble(moneyTextField.getText()) <= 0) {
                    showInfo("Недопустимый ввод");
                    moneyTextField.setText("");
                } else {
                    boolean f = owner.withdraw(Double.parseDouble(moneyTextField.getText()),bankAccount);
                    if (f) {
                        balanceLabel.setText("Баланс: " + bankAccount.getBalance() + " " + bankAccount.getCoinType());
                        moneyTextField.setText("");
                    } else {
                        showInfo("Недостаточно средств");
                        moneyTextField.setText("");
                    }
                }
            } catch (NumberFormatException e) {
                showInfo("Недопустимый ввод");
                moneyTextField.setText("");
            }
        });
        HBox addMoneyBox = new HBox(5, addMoneyLabel, moneyTextField, addMoneyCurrency, addMoneyButton, subtractMoneyButton);

        VBox root = new VBox(10, idLabel, dateLabel, balanceLabel, addMoneyBox);
        root.setStyle("-fx-background-color : lavender");


        return root;
    }

    private VBox initLoginPage() {
        Label loginLabel = new Label("Введите логин:");
        TextField loginTextField = new TextField();
        HBox loginBox = new HBox(5, loginLabel, loginTextField);

        Button loginButton = new Button("Войти");
        loginButton.setOnAction(e -> {
            if (!loginTextField.getText().equals("")) {
                loginOwner(loginTextField.getText());
            } else {
                showInfo("Поле ввода логина пусто");
            }
        });

        return new VBox(10, initMenuBar(), loginBox, loginButton);
    }

    private MenuBar initMenuBar() {
        MenuBar bar = new MenuBar();
        bar.setMinHeight(25);
        Menu item = new Menu("Программа");
        Menu saveAndContinueOption = new Menu("Сохранить и продолжить");
        Menu saveAndExitOption = new Menu("Сохранить и выйти");
        Menu exitWithoutSavingOption = new Menu("Выйти без сохранения");
        item.getItems().addAll(saveAndContinueOption, saveAndExitOption, exitWithoutSavingOption);
        bar.getMenus().add(item);
        try {
            saveAndContinueOption.setOnAction(e -> save(FILENAME));
            saveAndExitOption.setOnAction(e -> {
                save(FILENAME);
                Platform.exit();
            });
            exitWithoutSavingOption.setOnAction(e -> exitWithoutSaving());
        } catch (Exception e) {
            showError("Некорректная операция");
        }

        return bar;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Ошибка в системе");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Информация о системе");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private String showAlert(String msg, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING, msg,
                ButtonType.YES, ButtonType.CANCEL);
        alert.setTitle(title);
        alert.setHeaderText("Предупреждение");
        String response = alert.showAndWait().get().getText();

        return response;
    }

    private void save(String fileName) {
        try {
            bank.save(fileName);
            showInfo("Данные сохранены");
        } catch (Exception e) {
            showError("Ошибка при сохранении файла");
        }
    }

    private void exitWithoutSaving() {
        String response = showAlert("Вы уверены? Все данные будут потеряны", "Требуется подтверждение");
        if (response.equals("Yes"))
            Platform.exit();
    }

    private void loginOwner(String name) {
        Owner checkedOwner = bank.checkOwner(name);
        if (checkedOwner != null) {
            mainScene.setRoot(initAccountPage(checkedOwner));
            for (BankAccount acc : checkedOwner.getAccounts()) {
                Tab tabAcc = new Tab(acc.getId().toString());
                tabAcc.setOnClosed(event -> checkedOwner.closeAccount(acc));
                tabPaneForAcc.getTabs().add(tabAcc);
                tabAcc.setContent(initTabContent(checkedOwner,acc));
            }
        } else {
            String response = showAlert("Пользователя не существует, создать пользователя с таким именем?", "Пользователя не существует");
            if (response.equals("Yes")) {
                Owner newOwner = new Owner(name);
                bank.addOwner(newOwner);
                mainScene.setRoot(initAccountPage(newOwner));
            }
        }
    }
}
