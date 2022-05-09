package com.samsung.drawbattle.activities.maingame;

import android.util.Log;
import com.samsung.drawbattle.classes.LocalPersonalData;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerConnect {
    private Socket socket;
    private PrintWriter pW;
    private Scanner in;
    private boolean readFlad, writeFlag;
    private String[] users;

    public ServerConnect() {
        users = new String[6];
        writeFlag = true;
        WriteThread write = new WriteThread();
        write.start();
        readFlad = true;
        ReadThread read = new ReadThread();
        read.start();
    }

    private class WriteThread extends Thread {
        @Override
        public void run() {
            try {
                Log.d("Server", "Try to start connection...");
                socket = new Socket("10.0.2.2", 5000);
                Log.d("Server", "Successful connected");
                pW = new PrintWriter(socket.getOutputStream());
                pW.println("init/0/" + LocalPersonalData.getPersonName());
                pW.flush();
                in = new Scanner(socket.getInputStream());
                Log.d("Server", "Server start to write here");
                while (true) {
                    if (in.hasNext()) {
                        String message = in.nextLine();
                        String link[] = message.split("/");
                        Log.d("Server", "message = " + message);
                        try {
                            switch (link[0]) {
                                case "init": {
                                    String members[] = link[3].split(";");
                                    for (int i = 0; i < members.length; i++) {
                                        users[i] = members[i];
                                    }
                                    MainGameRoom.room.setUsers(users);
                                    MainGameRoom.room.rebootAvas();
                                    continue;
                                }
                                case "request": {
                                    switch(link[1]) {
                                        case "run": {
                                            String members[] = link[2].split(",");
                                            for (int i = 0; i < members.length; i++) {
                                                users[i] = members[i];
                                            }
                                            MainGameRoom.room.setUsers(users);
                                            MainGameRoom.room.toGame();
                                            break;
                                        }
                                        case "next": {
                                            MainGameActivity.game.onGameStageUpdate();
                                            break;
                                        }
                                        case "text": {
                                            pW.println("text/" + MainGameActivity.game.getText());
                                            pW.flush();
                                            break;
                                        }
                                        case "image": {
                                            break;
                                        }
                                        default: {
                                            Log.d("Server", "404 not found");
                                        }
                                    }
                                    break;
                                }
                                case "text": {
                                    MainGameActivity.game.setEtRes(link[1]);
                                    MainGameActivity.game.onGameStageUpdate();
                                    break;
                                }
                                case "exit": {
                                    if (Integer.parseInt(link[1]) == 200) {
                                        MainGameRoom.room.toMenu();
                                        in.close();
                                        try {
                                            pW.close();
                                        }
                                        catch (NullPointerException e) {
                                            e.printStackTrace();
                                        }
                                        toNull();
                                        return;
                                    }
                                    break;
                                }
                                case "new": {
                                    int i = 0;
                                    while (users[i] != null) {
                                        i++;
                                    }
                                    users[i] = link[1];
                                    MainGameRoom.room.setUsers(users);
                                    MainGameRoom.room.rebootAvas();
                                    break;
                                }
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }
            catch (IOException e) {
                Log.d("Server", "Failed to start connection. IOE");
                e.printStackTrace();
            }
            finally {
                Log.d("Server", "Disconnect");
            }
        }
    }

    private class ReadThread extends Thread {
        @Override
        public void run() {
            while (true) {
                if (MainGameRoom.room.isTryToGo()) {
                    MainGameRoom.room.setTryToGo(false);
                    pW.println("run/try/");
                    pW.flush();
                }
            }
        }
    }

    public void toNull() {
        writeFlag = false;
        readFlad = false;
        socket = null;
        pW = null;
        in = null;
    }
}
