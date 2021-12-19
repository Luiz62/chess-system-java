package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.*;

public class Program {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        while (!chessMatch.isCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);

                System.out.print("\nSource: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.print("\nTarget: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if (!(capturedPiece == null)) {
                    captured.add(capturedPiece);
                }

                if (!(chessMatch.getPromoted() == null)) {
                    String type = "";
                    do  {
                        System.out.print("Enter piece for promotion (B/N/R/Q): ");
                        type = sc.nextLine().toUpperCase();
                    } while(!type.equals("B") && !type.equals("N")  && !type.equals("R") && !type.equals("Q"));

                    chessMatch.replacePromotedPiece(type);
                }
            } catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}
