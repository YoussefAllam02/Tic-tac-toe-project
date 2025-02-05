package tictactoe.ui.game.winner;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class WINNERBase extends AnchorPane {

    protected final MediaView videoView;
    protected final Label lbl1;
    protected final Label lbl2;
    protected final Button btnPA;
    protected final Button btnEX;

    public WINNERBase() {
        videoView = new MediaView();
        lbl1 = new Label();
        lbl2 = new Label();
        btnPA = new Button();
        btnEX = new Button();

            setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(450.0);
        setPrefWidth(800.0);

        AnchorPane.setBottomAnchor(videoView, 0.0);
        AnchorPane.setLeftAnchor(videoView, 0.0);
        AnchorPane.setRightAnchor(videoView, 0.0);
        AnchorPane.setTopAnchor(videoView, 0.0);
        videoView.setLayoutX(200.0);
        videoView.setLayoutY(100.0);
        // Label 1: "You Are The"
        lbl1.setLayoutX(300.0);
        lbl1.setLayoutY(50.0);
        lbl1.setText("You Are The");
        lbl1.setTextFill(Color.web("#28a745"));
        lbl1.setFont(new Font("Arial", 28.0));

        // Label 2: "WINNER"
        lbl2.setLayoutX(270.0);
        lbl2.setLayoutY(110.0);
        lbl2.setText("WINNER");
        lbl2.setTextFill(Color.web("#28a745"));
        lbl2.setFont(new Font("Arial", 70.0));

        // Play Again Button
        btnPA.setLayoutX(320.0);
        btnPA.setLayoutY(270.0);
        btnPA.setText("Play Again?");
        btnPA.setStyle("-fx-background-color: linear-gradient(to bottom, #28a745, #66c767); " +
                       "-fx-text-fill: white; " +
                       "-fx-font-family: 'Segoe UI'; " +
                       "-fx-font-size: 16px; " +
                       "-fx-padding: 10px; " +
                       "-fx-background-radius: 20;");
        btnPA.setPrefWidth(200.0);
        btnPA.setPrefHeight(40.0);
        btnPA.setEffect(new DropShadow(10, Color.BLACK));

        // Exit Button
        btnEX.setLayoutX(320.0);
        btnEX.setLayoutY(320.0);
        btnEX.setText("Exit");
        btnEX.setStyle("-fx-background-color: linear-gradient(to bottom, #e74c3c, #c0392b); " +
                       "-fx-text-fill: white; " +
                       "-fx-font-family: 'Segoe UI'; " +
                       "-fx-font-size: 16px; " +
                       "-fx-padding: 10px; " +
                       "-fx-background-radius: 20;");
        btnEX.setPrefWidth(200.0);
        btnEX.setPrefHeight(40.0);
        btnEX.setEffect(new DropShadow(10, Color.BLACK));

        // Add all components to the scene
        getChildren().add(videoView);
        getChildren().add(lbl1);
        getChildren().add(lbl2);
        getChildren().add(btnPA);
        getChildren().add(btnEX);

        // Bind MediaView size to AnchorPane
        videoView.fitWidthProperty().bind(widthProperty());
        videoView.fitHeightProperty().bind(heightProperty());
        videoView.setPreserveRatio(true);
    }
}
