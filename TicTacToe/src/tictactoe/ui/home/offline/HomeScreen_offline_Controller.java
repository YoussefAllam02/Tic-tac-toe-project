
package tictactoe.ui.home.offline;

import connection.Connection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.TicTacToe;
import tictactoe.ui.auth.login.LoginController;
import tictactoe.ui.player.multiplayer.Multi_player_SceneController;
import tictactoe.ui.player.singleplayer.Single_player_sceneController;

public class HomeScreen_offline_Controller extends HomeScreen_offline {

    public static boolean pc;
    public HomeScreen_offline_Controller(Stage stage) {
        super(stage);

        pc = false;
        OnlineBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                TicTacToe.online = true;

                boolean connected = Connection.setConnection();
                if (connected) {
                    LoginController account = new LoginController(stage);
                    Scene scene = new Scene(account);
                    stage.setScene(scene);
                }
               
            }
        });

        MultiPBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Multi_player_SceneController multi = new Multi_player_SceneController(stage);
                Scene scene = new Scene(multi);
                stage.setScene(scene);
            }
        });

        singlePBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pc = true;
                Single_player_sceneController single = new Single_player_sceneController(stage);
                Scene scene = new Scene(single);
                stage.setScene(scene);
            }
        });
    }

}
