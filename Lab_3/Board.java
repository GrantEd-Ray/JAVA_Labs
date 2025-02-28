import Figures.*;

import java.util.ArrayList;

public class Board {

    /*
    *   1. Ход фигур с учетом препятствий, и учесть что отаковать короля нельзя
        2. Функции шах и мат.
        Дополнительно:
        1. Превращение пешки в фигуру, с учетом фигур на доске.
        2. Рокеровку
    *
    *
    * */






    private char colorGame;

    public void setColorGame(char colorGame) {
        this.colorGame = colorGame;
    }

    public  char getColorGame(){
        return colorGame;
    }


    private Figure fields[][] = new Figure[8][8];
    private ArrayList<String> takeWhite = new ArrayList(16);
    private ArrayList<String> takeBlack = new ArrayList(16);
    public ArrayList<String> getTakeBlack() {
        return takeBlack;
    }

    public ArrayList<String> getTakeWhite() {
        return takeWhite;
    }

    public void init(){
        this.fields[0] = new Figure[]{
                new Rook("R", 'w'), new Knight("N", 'w'), new Bishop("B", 'w'),
                new Queen("Q", 'w'), new King("K", 'w'), new Bishop("B", 'w'),
                new Knight("N", 'w'), new Rook("R",'w')
        };
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w'),new Pawn("P", 'w'),new Pawn("P", 'w'),new Pawn("P", 'w'),
                new Pawn("P", 'w'),new Pawn("P", 'w'),new Pawn("P", 'w'),new Pawn("P", 'w'),
        };



        this.fields[6] = new Figure[] {
                new Pawn("P", 'b'),new Pawn("P", 'b'),new Pawn("P", 'b'),new Pawn("P", 'b'),
                new Pawn("P", 'b'),new Pawn("P", 'b'),new Pawn("P", 'b'),new Pawn("P", 'b')
        };

        this.fields[7] = new Figure[]{
                new Rook("R", 'b'), new Knight("N", 'b'), new Bishop("B", 'b'),
                new Queen("Q", 'b'), new King("K", 'b'), new Bishop("B", 'b'),
                new Knight("N", 'b'), new Rook("R",'b')
        };
    }

    public String getCell(int row, int col){
        Figure figure = this.fields[row][col];
        if (figure ==null){
            return "    ";
        }
        return  " "+figure.getColor()+figure.getName()+" ";
    }
    public void print_board(){
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for (int row = 7; row > -1 ; row --){
            System.out.print(row);
            for (int col=0; col<8; col++){
                System.out.print("|"+getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for(int col=0; col< 8; col++){
            System.out.print("    "+col);
        }
    }

    public boolean canMoveOnBoard(int row, int col, int row1, int col1){
        // TODO Реализация проверки препятствия на пути фигуры
        Figure figure = this.fields[row][col];
        if (figure == null) return false;

        int dRow = Integer.compare(row1, row);
        int dCol = Integer.compare(col1, col);

        int currRow = row + dRow;
        int currCol = col + dCol;

        // Проверка пути между начальной и конечной точкой
        while (currRow != row1 || currCol != col1) {
            if (this.fields[currRow][currCol] != null) {
                return false; // Обнаружено препятствие
            }
            currRow += dRow;
            currCol += dCol;
        }
        return true;
    }
    public boolean isCheck(){
        // Найти короля текущего игрока
        char currentPlayer = this.colorGame;
        int kingRow = -1, kingCol = -1;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = this.fields[row][col];
                if (figure != null && figure.getName().equals("K") && figure.getColor() == currentPlayer) {
                    kingRow = row;
                    kingCol = col;
                    break;
                }
            }
        }

//        if (kingRow == -1 || kingCol == -1) {
//            throw new IllegalStateException("Король не найден на доске!");
//        }

        // Проверить, атакован ли король
        char opponentColor = (currentPlayer == 'w') ? 'b' : 'w';
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = this.fields[row][col];
                if (figure != null && figure.getColor() == opponentColor) {
                    if (figure.canAttack(row, col, kingRow, kingCol)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    public boolean isMate(){
        if (!isCheck()) {
            return false; // Если шаха нет, мата тоже быть не может
        }

        char currentPlayer = this.colorGame;

        // Проверить все возможные ходы текущего игрока
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = this.fields[row][col];
                if (figure != null && figure.getColor() == currentPlayer) {
                    for (int newRow = 0; newRow < 8; newRow++) {
                        for (int newCol = 0; newCol < 8; newCol++) {
                            if ((figure.canMove(row, col, newRow, newCol) && this.fields[newRow][newCol] == null) || (figure.canAttack(row, col, newRow, newCol) && this.fields[newRow][newCol] != null && this.fields[newRow][newCol].getColor() != this.fields[row][col].getColor())) {
                                // Сделать виртуальный ход
                                Figure originalDestination = this.fields[newRow][newCol];
                                this.fields[newRow][newCol] = figure;
                                this.fields[row][col] = null;

                                boolean stillInCheck = isCheck();

                                // Откатить виртуальный ход
                                this.fields[row][col] = figure;
                                this.fields[newRow][newCol] = originalDestination;

                                if (!stillInCheck) {
                                    return false; // Если нашли ход, который выводит из шаха, то мата нет
                                }
                            }
                        }
                    }
                }
            }
        }

        return true; // Если ни один ход не выводит из шаха, то это мат
    }
    public boolean move_figure(int row, int col, int row1, int col1){
      Figure figure = this.fields[row][col];
      if (figure != null && figure.canMove(row, col, row1, col1) && this.fields[row1][col1] == null && figure.getColor() == this.colorGame && canMoveOnBoard(row, col, row1, col1)){
          this.fields[row1][col1] = figure;
          this.fields[row][col] = null;
          return true;
      }else  if (figure.canAttack(row, col, row1, col1) && this.fields[row1][col1] != null && this.fields[row1][col1].getColor() != this.fields[row][col].getColor()){
          this.fields[row1][col1] = figure;
          this.fields[row][col] = null;

          switch (this.fields[row1][col1].getColor()){
              case 'w': this.takeWhite.add(this.fields[row1][col1].getColor() + this.fields[row1][col1].getName()); break;
              case 'b': this.takeBlack.add(this.fields[row1][col1].getColor() + this.fields[row1][col1].getName()); break;
          }
          return true;
      }
        return false;
    }
}
