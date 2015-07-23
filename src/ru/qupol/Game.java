package ru.qupol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
List<Player> players;
    public static void main(String[] args) {
        Game game = new Game();
        game.commandParse();



    }

    public Game() {
        players = new ArrayList<>();
    }

    private  void  play(List<Player> players){
        Player player1 = players.get(0);
        Player player2 = players.get(1);

        Scanner scanner = new Scanner(System.in);
        System.out.print("ставка:");
        int bet =scanner.nextInt();
        scanner.close();
        player1.setBallance(player1.getBallance()-bet);
        player2.setBallance(player2.getBallance()-bet);
        int winner=getWinner(2)+1;
        if (winner==1){
            player1.setBallance(player1.getBallance()+bet*2);
        }else{
            player2.setBallance(player2.getBallance()+bet*2);
        }
        StringBuilder builder;
        builder = new StringBuilder();
        builder.append("игрок ").append(player1.getName()).append(" имеет баланс ").append(player1.getBallance());
        System.out.println(builder.toString());

        builder = new StringBuilder();
        builder.append("игрок ").append(player2.getName()).append(" имеет баланс ").append(player2.getBallance());
        System.out.println(builder.toString());


    }

    private void commandParse(){
        Scanner scanner  = new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).equals("")) {


            String[] strings = line.split(" ");

            switch (strings[0]) {
                case "new":
                    if (strings[1].equals("user") && strings.length > 3) {
                        Player player = new Player();
                        player.setName(strings[2]);
                        player.setBallance(Integer.parseInt(strings[3]));
                        players.add(player);
                        System.out.println("player " + player.getName() + " with ballance " + player.getBallance() + " added");
                    } else {
                        System.out.println("wrong command 'new', try again");
                    }
                    break;
                case "play":play(players);
            }
        }
    }



    private  int getWinner(int count){
        Random random = new Random();
        return random.nextInt(count);
    }
}
