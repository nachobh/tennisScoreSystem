import java.util.HashMap;
import java.util.Map;

class TennisScoreSystem {
    public static void main(String[] args) {
        System.out.println(computePoints(args));
    }

    private static String computePoints(String[] wins) {
        String result = "";
        Map<String, Long> playerPointsMap = new HashMap<>();
        for (String player : wins) {
            playerPointsMap.merge(player, 1L, Long::sum);
            if (playerPointsMap.size() > 2) {
                return "ERROR";
            }
            Long winsPlayer1 = (Long) playerPointsMap.values().toArray()[0];
            Long winsPlayer2 = playerPointsMap.values().toArray().length == 1 ? null : (Long) playerPointsMap.values().toArray()[1];
            String player1 = (String) playerPointsMap.keySet().toArray()[0];
            String player2 = playerPointsMap.entrySet().toArray().length == 1 ? null : (String) playerPointsMap.keySet().toArray()[1];
            long winsP1 = winsPlayer1 != null ? winsPlayer1 : 0;
            long winsP2 = winsPlayer2 != null ? winsPlayer2 : 0;
            long difference = (winsP1 - winsP2);
            if (difference >= 2 && winsP1 >= 4) {
                result = player1 + " WINS";
            } else if (difference <= -2 && winsP2 >= 4) {
                result = player2 + " WINS";
            } else if (difference == 0) {
                if (winsP1 == 0) {
                    result = player1 + " 0 - 0 " + player2;
                } else if (winsP1 == 1) {
                    result = player1 + " 15 - 15 " + player2;
                } else if (winsP1 == 2) {
                    result = player1 + " 30 - 30 " + player2;
                } else if (winsP1 > 2) {
                    result = "DEUCE";
                }
            } else if (difference == 1) {
                if (winsP1 == 1) {
                    result = player1 + " 15 - 0 " + player2;
                } else if (winsP1 == 2) {
                    result = player1 + " 30 - 15 " + player2;
                } else if (winsP1 == 3) {
                    result = player1 + " 40 - 30 " + player2;
                } else {
                    result = "ADVANTAGE " + player1;
                }
            } else if (difference == -1) {
                if (winsP2 == 1) {
                    result = player1 + " 0 - 15 " + player2;
                } else if (winsP2 == 2) {
                    result = player1 + " 15 - 30 " + player2;
                } else if (winsP2 == 3) {
                    result = player1 + " 30 - 40 " + player2;
                } else {
                    result = "ADVANTAGE " + player2;
                }
            } else if (difference == 2) {
                if (winsP1 == 2) {
                    result = player1 + " 30 - 0 " + player2;
                } else if (winsP1 == 3) {
                    result = player1 + " 40 - 15 " + player2;
                }
            } else if (difference == -2) {
                if (winsP2 == 2) {
                    result = player1 + " 0 - 30 " + player2;
                } else if (winsP2 == 3) {
                    result = player1 + " 15 - 40 " + player2;
                }
            } else if (difference == 3 && winsP1 == 3) {
                result = player1 + " 40 - 0 " + player2;
            } else if (difference == -3 &&winsP2 == 3) {
                result = player1 + " 0 - 30 " + player2;
            }
            if (wins.length > winsP1 + winsP2) {
                System.out.println(result);
            } else {
                break;
            }
        }
        return result;
    }

}