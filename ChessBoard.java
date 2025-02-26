import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JFrame {
    public static final int SIZE = 8;
    private JPanel[][] squares = new JPanel[SIZE][SIZE];
    private String[][] piecesArray; // Stores piece images and positions

    public ChessBoard() {
        setTitle("Chess Board");
        setSize(750, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        initializeBoard();
        loadPieces();
        populateBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel();
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(new Color(255, 251, 240));
                } else {
                    squares[row][col].setBackground(Color.GRAY);
                }
                add(squares[row][col]);
            }
        }
    }

    private void loadPieces() {
        piecesArray = new String[][]{
            {"image/black_rook.png", "0,0"}, {"image/black_knight.png", "0,1"}, {"image/black_bishop.png", "0,2"},
            {"image/black_queen.png", "0,3"}, {"image/black_king.png", "0,4"}, {"image/black_bishop.png", "0,5"},
            {"image/black_knight.png", "0,6"}, {"image/black_rook.png", "0,7"},
            {"image/black_pawn.png", "1,0"}, {"image/black_pawn.png", "1,1"}, {"image/black_pawn.png", "1,2"},
            {"image/black_pawn.png", "1,3"}, {"image/black_pawn.png", "1,4"}, {"image/black_pawn.png", "1,5"},
            {"image/black_pawn.png", "1,6"}, {"image/black_pawn.png", "1,7"},
            {"image/white_pawn.png", "6,0"}, {"image/white_pawn.png", "6,1"}, {"image/white_pawn.png", "6,2"},
            {"image/white_pawn.png", "6,3"}, {"image/white_pawn.png", "6,4"}, {"image/white_pawn.png", "6,5"},
            {"image/white_pawn.png", "6,6"}, {"image/white_pawn.png", "6,7"},
            {"image/white_rook.png", "7,0"}, {"image/white_knight.png", "7,1"}, {"image/white_bishop.png", "7,2"},
            {"image/white_queen.png", "7,3"}, {"image/white_king.png", "7,4"}, {"image/white_bishop.png", "7,5"},
            {"image/white_knight.png", "7,6"}, {"image/white_rook.png", "7,7"}
        };
    }

    private void populateBoard() {
        for (String[] piece : piecesArray) {
            String imagePath = piece[0];
            String[] position = piece[1].split(",");
            int row = Integer.parseInt(position[0]);
            int col = Integer.parseInt(position[1]);

            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImage = icon.getImage().getScaledInstance(40, 42, Image.SCALE_SMOOTH);

            JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
            squares[row][col].setLayout(new BorderLayout());
            squares[row][col].add(pieceLabel, BorderLayout.CENTER);
        }

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessBoard board = new ChessBoard();
            board.setVisible(true);
        });
    }
}
