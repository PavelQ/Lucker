package ru.qupol;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    List<Player> players;
    Player currentPlayer;
    List<Game> games;

    public static void main(String[] args) {
        Main game = new Main();
        game.commandParse();


    }

    public Main() {
        players = new ArrayList<>();
        games = new ArrayList<>();
    }


    private void commandParse() {
        Scanner scanner = new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).equals("exit")) {


            String[] strings = line.split(" ");

            switch (strings[0]) {
                case "new":
                    if (strings.length > 2) {
                        if (strings.length > 3 && strings[1].equals("user")) {
                            Player player = new Player();
                            player.setName(strings[2]);
                            player.setBallance(Integer.parseInt(strings[3]));
                            players.add(player);
                            System.out.println("player " + player.getName() + " with balance " + player.getBallance() + " added");
                            setCurrentPlayer(player);
                        }
                        if(strings.length > 2 && strings[1].equals("game")){
                            int bet = Integer.parseInt(strings[2]);
                            Game game = new Game(currentPlayer,bet);
                            games.add(game);
                        }
                    }else {
                            System.out.println("wrong command 'new', try again");
                        }
                    break;
//                case "play":
//                    play(players);
//                    break;
                case "help":
                    help();
                    break;
                case "users":
                    users();
                    break;
                case "login":
                    login(strings);
                    break;
                case "join":joinGame(strings); break;
                case "games": games();break;
                default:
                    System.out.println("неверная команда, введите help");
            }
        }
    }

    private void joinGame(String[] strings) {
       // if(strings.length>3){
            int gameId = Integer.parseInt(strings[1]);
            Game game = games.get(gameId);
            game.join(currentPlayer);
            game.play();
        //}
    }

    private void games(){
        for (int i = 0; i < games.size(); i++) {
            System.out.println(i+" "+ games.get(i).bet);
        }
    }

    private void login(String[] strings) {
        if (strings.length > 1) {
            String name = strings[1];
            for (Player player : players) {
                if (player.getName().equals(name)) {
                    setCurrentPlayer(player);
                    System.out.println("вы за шли как " + player.getName() + " ваш баланс= " + player.getBallance());
                    return;
                }
            }
            System.out.println("пользователь не найден");
        } else
            System.out.println("не верное количество параметров длz login");
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private void users() {
        for (Player player : players) {
            System.out.println(player.getName() + " - " + player.getBallance());
        }
    }

    private void help() {
        System.out.println("Помощь:");
        System.out.println("new user [name]  -  добавить пользователя");
        System.out.println("play  -  start game");
        System.out.println("help  -  вывести информацию о помощи");
        System.out.println("exit  -  выйти");
        System.out.println("users - список пользователей");
        System.out.println("login [name] - зайти под существующим пользователем");
    }


}
