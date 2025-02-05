package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static server.DataAccessLayer.login;
import sun.misc.Queue;
import templates.Player;

class UserHandler extends Thread {

    DataInputStream input;
    DataOutputStream output;
    String name;
    String oppoName;
    boolean isPlaying = false;
    int score;
    String[] data;
    static Vector<UserHandler> clientsVector = new Vector<>();
    //Queue<String> moves_queu = new Queue<>();

    public UserHandler(Socket socket) {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            addClient();
            //System.out.println(input.readLine());
            start(); // Start the thread for this user
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static UserHandler getUserHandler(String nameFound) {
        UserHandler userFound = null;
        for (UserHandler user : clientsVector) {
            if (user.name.equals(nameFound)) {
                userFound = user;
            }
        }
        return userFound;
    }

    public void run() {

        while (true) {
            try {
                String request = input.readUTF();

                data = request.split("###");

                switch (data[0]) {

                    case "login": {
                        try {
                            Player player = login(data[1], data[2]);
                            name = data[1];
                            System.out.println(player.getPassword());
                            System.out.println(data[2]);
                            if (player.getName() != null) {
                                if (player.getPassword().equals(data[2])) {

                                    score = player.getScore();
                                    this.output.writeUTF(String.valueOf(player.getScore()));

                                    DataAccessLayer.updateStatus(data[1], 1);
                                    try {
                                        Serverscene2Base.updatePiechart(DataAccessLayer.getOnlineMemberCount(), DataAccessLayer.getOfflineMemberCount());

                                    } catch (SQLException ex) {
                                        Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    this.output.writeUTF("incorrect password");
                                }
                            } else {
                                this.output.writeUTF("this name is not exist");
                            }
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());

                        }
                    }
                    break;

                    case "signup": {
                        boolean signedUp = false;
                        Player player = new Player();
                        player.setName(data[1]);
                        name = data[1];
                        player.setPassword(data[2]);
                        player.setScore(0);
                        player.setStatus(1);

                        try {
                            signedUp = DataAccessLayer.signUp(player);
                            try {
                                Serverscene2Base.updatePiechart(DataAccessLayer.getOnlineMemberCount(), DataAccessLayer.getOfflineMemberCount());

                            } catch (SQLException ex) {
                                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (SQLException ex) {
                            this.output.writeUTF("Duplicated name###");
                        }

                        if (signedUp) {

                            this.output.writeUTF("Signed up successfully###" + 0);

                        }
                    }
                    break;

                    case "sendList": {
                        sendList();
                        break;

                    }

                    case "close": {

                        this.output.writeUTF("logout###");
                        break;
                    }

                    case "score": {

                        try {

                            if (data[1].equals("win")) {
                                score = DataAccessLayer.retriveScore(data[2]);
                                score += 4;
                                DataAccessLayer.updateScore(data[2], score);
                                this.output.writeUTF("score###" + score);
                                System.out.println("score:" + score);
                                
                            } else if (data[1].equals("draw")) {
                                UserHandler user = getUserHandler(data[2]);
                                UserHandler user2 = getUserHandler(data[3]);
                                user.score = DataAccessLayer.retriveScore(data[2]);
                                user2.score = DataAccessLayer.retriveScore(data[3]);
                                user.score += 1;
                                user2.score += 1;
                                DataAccessLayer.updateScore(user.name, user.score);
                                DataAccessLayer.updateScore(user2.name, user2.score);
                                user.output.writeUTF("score###" + user.score);
                                user2.output.writeUTF("score###" + user2.score);
                               
                            }
                            this.isPlaying = false;
                            UserHandler oppo = getUserHandler(this.oppoName);
                            oppo.isPlaying = false;
                        } catch (SQLException ex) {
                            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }

                    case "sendRequest": {

                        UserHandler user2 = UserHandler.getUserHandler(data[1]); //reciever
                        System.out.println(user2);
                        user2.output.writeUTF("invitation" + "###" + name + "###" + score);

                        break;

                    }

                    case "History_request": {
                        try {
                            Vector<Vector<String>> history_list = DataAccessLayer.retriveHistory(data[1]);
                            StringBuilder historyResponse = new StringBuilder("History_response");

                            historyResponse.append("###Game");
                            for (String s : history_list.elementAt(0)) {
                                historyResponse.append("###" + s);
                            }
                            historyResponse.append("###PlayerName1");
                            for (String s : history_list.elementAt(1)) {
                                historyResponse.append("###" + s);
                            }
                            historyResponse.append("###PlayerName2");
                            for (String s : history_list.elementAt(2)) {
                                historyResponse.append("###" + s);
                            }
                            historyResponse.append("###winner");
                            for (String s : history_list.elementAt(3)) {
                                historyResponse.append("###" + s);
                            }
                            historyResponse.append("###recording");
                            for (String s : history_list.elementAt(4)) {
                                historyResponse.append("###" + s);
                            }

                            this.output.writeUTF(historyResponse.toString());
                        } catch (SQLException ex) {
                            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                            this.output.writeUTF("Error retrieving history");
                        }
                        break;
                    }

                    case "logout": {
                        try {
                            DataAccessLayer.logout(this.name);
                            output.writeUTF("logout###success");
                            clientsVector.remove(this);
                            input.close();
                            output.close();

                        } catch (SQLException ex) {
                            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }

                    case "winner": {

                        try {
                            String winnerName = data[1]; // winnerName is the second part of the message
                            int gameId = Integer.parseInt(data[2]); // gameId is the third part of the message

                            DataAccessLayer.addWinner(winnerName, gameId);

                            // Notify the client that the winner was recorded successfully
                            output.writeUTF("winner###success");

                        } catch (SQLException ex) {
                            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                            output.writeUTF("winner###failure");
                        }
                        break;

                    }

                    //Connection.sendRequest("GetInvitation" + "###" + "Accepted" + "###" + rec[1]);
                    case "GetInvitation": {
                        getInvitation();

                        break;
                    }
                    case "back": {
                        input.close();
                        output.close();

                        break;

                    }

                    case "Move": {
                        if (this.isPlaying) {
                            UserHandler opponent = getUserHandler(this.oppoName);
                            if (opponent != null) {
                                System.out.println("Move###" + data[1] + " " + data[2] + oppoName);
                                opponent.output.writeUTF("Move###" + data[1] + " " + data[2]/*+" "+data[3]*/);
                                System.out.println(opponent.output);
                            }

                            System.out.println(data[1]);
                        }
                        break;

                    }

                }
            } catch (IOException ex) {
                removeClient();
                try {
                    input.close();
                    output.close();
                } catch (IOException e) {
                    Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

                break; 
            }

        }

    }

    public void sendList() {
        Vector<String> available = new Vector<String>();
        System.out.println(clientsVector.size());
        for (UserHandler client : clientsVector) {
            if (!client.isPlaying) {
                available.add(client.name + " - Score: " + client.score);
            }
        }
        sendListToAll(available);

    }

    public void sendListToAll(Vector<String> Online) {
        for (UserHandler client : clientsVector) {
            Vector<String> temp = new Vector<String>(Online);
            temp.remove(client.name + " - Score: " + client.score);
            try {
                client.output.writeUTF("List" + "###" + temp);
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void removeClient() {
        clientsVector.remove(this);

        sendList();

        try {
            Serverscene2Base.updatePiechart(DataAccessLayer.getOnlineMemberCount(), DataAccessLayer.getOfflineMemberCount());
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addClient() {
        clientsVector.add(this);

    }

    //Connection.sendRequest("GetInvitation" + "###" + "Accepted" + "###" + rec[1])
    public void getInvitation() {

        if (data[1].equals("Accepted")) {

            this.isPlaying = true;
            UserHandler opp = getUserHandler(data[2]);
            opp.isPlaying = true;
            this.oppoName = data[2];
            opp.oppoName = this.name;

            try {

                opp.output.writeUTF("Accepted###" + DataAccessLayer.acceptRequest(this.name, this.oppoName));

                System.out.println(this.name + this.oppoName);
                System.out.println("-----------------");
                System.out.println(DataAccessLayer.acceptRequest(this.name, this.oppoName));
            } catch (SQLException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            sendList();
        } else if (data[1].equals("Refused")) {
            UserHandler opp = getUserHandler(data[2]);
            try {
                opp.output.writeUTF("Refused");
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
