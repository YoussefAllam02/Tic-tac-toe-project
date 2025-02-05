package tictactoe.ui.auth.signup;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class Signup extends AnchorPane {

    protected final TextField nameText;
    protected final AnchorPane nameBackground;
    protected final AnchorPane passwordBackground;
    protected final AnchorPane titleBackground;
    protected final Label title;
    protected final Button signUp;
    protected final Button backButton;
    protected final Label nameLable;
    protected final Label passwordLable;
    protected final PasswordField passwordText;
    protected final Label nameError;
    protected final Label passwordError;

    public Signup(Stage stage) {

        nameText = new TextField();
        nameBackground = new AnchorPane();
        passwordBackground = new AnchorPane();
        titleBackground = new AnchorPane();
        title = new Label();
        signUp = new Button();
        backButton = new Button();
        nameLable = new Label();
        passwordLable = new Label();
        passwordText = new PasswordField();
        nameError = new Label();
        passwordError = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStyleClass().add("coverPage");
        getStylesheets().add("/tictactoe/styles/signUp.css");

        nameText.setLayoutX(264.0);
        nameText.setLayoutY(148.0);
        nameText.getStyleClass().add("nameText");

        nameBackground.setLayoutX(68.0);
        nameBackground.setLayoutY(149.0);

        passwordBackground.setLayoutX(57.0);
        passwordBackground.setLayoutY(216.0);

        titleBackground.setLayoutX(133.0);
        titleBackground.setLayoutY(33.0);

        title.setAlignment(javafx.geometry.Pos.CENTER);
        title.setLayoutY(-7.0);
        title.setPrefHeight(17.0);
        title.setPrefWidth(335.0);
        title.getStyleClass().add("titleBackground");
        title.setText("Shick-Shack-Shock");
        title.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        title.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        title.setFont(new Font("Berlin Sans FB Demi Bold", 30.0));

        signUp.setLayoutX(264.0);
        signUp.setLayoutY(301.0);
        signUp.setMnemonicParsing(false);
        signUp.setPrefHeight(28.0);
        signUp.setPrefWidth(73.0);
        signUp.getStyleClass().add("signUp");
        signUp.setText("Sign Up");
        signUp.setFont(new Font("Centaur", 14.0));

        backButton.setLayoutX(31.0);
        backButton.setLayoutY(27.0);
        backButton.setMnemonicParsing(false);
        backButton.getStyleClass().add("backButton");
        backButton.setText("Back");
        backButton.setFont(new Font("Centaur", 14.0));

        nameLable.setAlignment(javafx.geometry.Pos.CENTER);
        nameLable.setLayoutX(159.0);
        nameLable.setLayoutY(148.0);
        nameLable.setPrefHeight(25.0);
        nameLable.setPrefWidth(62.0);
        nameLable.getStyleClass().add("nameBackground");
        nameLable.setText("Name");
        nameLable.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        nameLable.setFont(new Font("Berlin Sans FB", 18.0));

        passwordLable.setAlignment(javafx.geometry.Pos.CENTER);
        passwordLable.setLayoutX(158.0);
        passwordLable.setLayoutY(211.0);
        passwordLable.setPrefHeight(25.0);
        passwordLable.setPrefWidth(81.0);
        passwordLable.getStyleClass().add("passwordBackground");
        passwordLable.setText("Password");
        passwordLable.setTextFill(javafx.scene.paint.Color.valueOf("#1e9413"));
        passwordLable.setFont(new Font("Berlin Sans FB", 18.0));

        passwordText.setLayoutX(263.0);
        passwordText.setLayoutY(211.0);
        passwordText.getStyleClass().add("passwordText");

        AnchorPane.setLeftAnchor(nameError, 270.0);
        AnchorPane.setTopAnchor(nameError, 173.0);
        nameError.setVisible(false);
        nameError.setLayoutX(264.0);
        nameError.setLayoutY(173.0);
        nameError.setPrefHeight(17.0);
        nameError.setPrefWidth(162.0);
        nameError.setText("Please Enter your name");
        nameError.setTextFill(javafx.scene.paint.Color.valueOf("#c30404"));
        nameError.setFont(new Font("Berlin Sans FB", 15.0));

        AnchorPane.setLeftAnchor(passwordError, 270.0);
        passwordError.setVisible(false);
        passwordError.setLayoutX(270.0);
        passwordError.setLayoutY(236.0);
        passwordError.setPrefHeight(17.0);
        passwordError.setPrefWidth(162.0);
        passwordError.setText("Please Enter Password");
        passwordError.setTextFill(javafx.scene.paint.Color.valueOf("#c30404"));
        passwordError.setFont(new Font("Berlin Sans FB", 15.0));

        getChildren().add(nameText);
        getChildren().add(nameBackground);
        getChildren().add(passwordBackground);
        titleBackground.getChildren().add(title);
        getChildren().add(titleBackground);
        getChildren().add(signUp);
        getChildren().add(backButton);
        getChildren().add(nameLable);
        getChildren().add(passwordLable);
        getChildren().add(passwordText);
        getChildren().add(nameError);
        getChildren().add(passwordError);

    }
}
