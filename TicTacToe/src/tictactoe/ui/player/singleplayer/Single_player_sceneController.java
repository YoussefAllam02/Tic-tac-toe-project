/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui.player.singleplayer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.ui.game.screen.game_screenBase;
import tictactoe.ui.home.offline.HomeScreen_offline;

/**
 * FXML Controller class
 *
 * @author A.Atia
 */

public class Single_player_sceneController extends Single_Player_Scene{
    private Stage stage;

    public Single_player_sceneController(Stage stage) {
        this.stage=stage;
        listen();
        
    }
    private void listen()
    {
         back_btn.setOnAction((ActionEvent event) -> {
             goToHome();
         });
         
         start_btn.setOnAction((ActionEvent event) -> {
             goToGame();
         });
    }
    private void goToGame() {
        game_screenBase game = new game_screenBase(stage);
        Scene scene = new Scene(game);
        stage.setScene(scene);

    }

    private void goToHome() {

        HomeScreen_offline home = new HomeScreen_offline(stage);
        Scene scene = new Scene(home);
        stage.setScene(scene);

    }
    

   
}
