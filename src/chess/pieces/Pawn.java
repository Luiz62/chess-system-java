package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }


    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position position = new Position(this.position.getRow(), this.position.getColumn());

        if (getColor() == Color.WHITE) {
            position.setValues(this.position.getRow() - 1, this.position.getColumn());
            if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)){
                mat[position.getRow()][position.getColumn()] = true;
            }

            position.setValues(this.position.getRow() - 2, this.position.getColumn());
            Position position1 = new Position(this.position.getRow() - 1, this.position.getColumn());
            if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
                if (getBoard().positionExists(position1) && !getBoard().thereIsAPiece(position1)) {
                    if (getMoveCount() == 0) {
                        mat[position.getRow()][position.getColumn()] = true;
                    }
                }
            }

            position.setValues(this.position.getRow() - 1, this.position.getColumn() - 1);
            if (getBoard().positionExists(position) &&  isThereOpponentPiece(position)){
                mat[position.getRow()][position.getColumn()] = true;
            }

            position.setValues(this.position.getRow() - 1, this.position.getColumn() + 1);
            if (getBoard().positionExists(position) &&  isThereOpponentPiece(position)){
                mat[position.getRow()][position.getColumn()] = true;
            }
        } else {
            if (getColor() == Color.BLACK) {
                position.setValues(this.position.getRow() + 1, this.position.getColumn());
                if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)){
                    mat[position.getRow()][position.getColumn()] = true;
                }

                position.setValues(this.position.getRow() + 2, this.position.getColumn());
                Position position1 = new Position(this.position.getRow() + 1, this.position.getColumn());
                if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
                    if (getBoard().positionExists(position1) && !getBoard().thereIsAPiece(position1)) {
                        if (getMoveCount() == 0) {
                            mat[position.getRow()][position.getColumn()] = true;
                        }
                    }
                }

                position.setValues(this.position.getRow() + 1, this.position.getColumn() - 1);
                if (getBoard().positionExists(position) &&  isThereOpponentPiece(position)){
                    mat[position.getRow()][position.getColumn()] = true;
                }

                position.setValues(this.position.getRow() - 1, this.position.getColumn() + 1);
                if (getBoard().positionExists(position) &&  isThereOpponentPiece(position)){
                    mat[position.getRow()][position.getColumn()] = true;
                }
            }
        }

        return mat;
    }
}
