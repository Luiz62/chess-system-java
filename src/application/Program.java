package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        while (true) {
            try {
                if (System.getProperty("os.name").contains("Windows")) {
                    try {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                UI.printMatch(chessMatch, captured);

                System.out.print("\nSource: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                if (System.getProperty("os.name").contains("Windows")) {
                    try {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.print("\nTarget: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if (!(capturedPiece == null)) {
                    captured.add(capturedPiece);
                }

            } catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}
