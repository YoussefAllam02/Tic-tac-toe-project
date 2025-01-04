package tictactoe;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

public class WINNERBase extends AnchorPane {

    protected final MediaView videoView;
    protected final Label lbl1;
    protected final Label lbl2;
    protected final Label score;
    protected final Button btnPA;
    protected final Button btnEX;

    public WINNERBase() {

        videoView = new MediaView();
        lbl1 = new Label();
        lbl2 = new Label();
        score = new Label();
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

        lbl1.setLayoutX(324.0);
        lbl1.setLayoutY(42.0);
        lbl1.setPrefHeight(76.0);
        lbl1.setPrefWidth(150.0);
        lbl1.setText("You Are The");
        lbl1.setTextFill(javafx.scene.paint.Color.valueOf("#cc2727"));
        lbl1.setFont(new Font(26.0));

        lbl2.setLayoutX(262.0);
        lbl2.setLayoutY(80.0);
        lbl2.setPrefHeight(152.0);
        lbl2.setPrefWidth(276.0);
        lbl2.setText("WINNER");
        lbl2.setTextFill(javafx.scene.paint.Color.valueOf("#c91c1c"));
        lbl2.setFont(new Font(71.0));

        score.setLayoutX(346.0);
        score.setLayoutY(213.0);
        score.setPrefHeight(26.0);
        score.setPrefWidth(114.0);
        score.setText("New Score = ");
        score.setTextFill(javafx.scene.paint.Color.valueOf("#ec2a2a"));
        score.setFont(new Font(16.0));

        btnPA.setLayoutX(339.0);
        btnPA.setLayoutY(269.0);
        btnPA.setMnemonicParsing(false);
        btnPA.setText("Play Again ?");

        btnEX.setLayoutX(319.0);
        btnEX.setLayoutY(323.0);
        btnEX.setMnemonicParsing(false);
        btnEX.setPrefHeight(26.0);
        btnEX.setPrefWidth(144.0);
        btnEX.setText("Exit");

        getChildren().add(videoView);
        getChildren().add(lbl1);
        getChildren().add(lbl2);
        getChildren().add(score);
        getChildren().add(btnPA);
        getChildren().add(btnEX);
        
         // Set up the video
        Media media = new Media(getClass().getResource("/resources/winner.mp4").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        videoView.setMediaPlayer(mediaPlayer);
        
        // Bind MediaView size to AnchorPane
        videoView.fitWidthProperty().bind(widthProperty());
        videoView.fitHeightProperty().bind(heightProperty());
        videoView.setPreserveRatio(true);

        // Auto-play the video
        mediaPlayer.setAutoPlay(true);


    }
}