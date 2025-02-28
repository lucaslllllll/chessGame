import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ChessBoard extends JFrame {
    private static final int SIZE = 8;
    private final JPanel[][] squares = new JPanel[SIZE][SIZE];
    private String[][] piecesArray; 
    public ChessBoard() {
        setTitle("ChessBoard");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        initializeBoard();
        generatePieces();
        printUnsortedPieces();
        sortPieces();
        populateBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JPanel panel = new JPanel(new BorderLayout());
                panel.setBackground((row + col) % 2 == 0 ? new Color(240, 240, 240) : new Color(100, 100, 100));
                squares[row][col] = panel;
                add(panel);
            }
        }
    }

    private void generatePieces() {
       
        String[][] standardPieces = {
            
            {"black_rook", "0,0"}, {"black_knight", "0,1"}, {"black_bishop", "0,2"}, {"black_queen", "0,3"},
            {"black_king", "0,4"}, {"black_bishop", "0,5"}, {"black_knight", "0,6"}, {"black_rook", "0,7"},
            {"black_pawn", "1,0"}, {"black_pawn", "1,1"}, {"black_pawn", "1,2"}, {"black_pawn", "1,3"},
            {"black_pawn", "1,4"}, {"black_pawn", "1,5"}, {"black_pawn", "1,6"}, {"black_pawn", "1,7"},
            
            {"white_rook", "7,0"}, {"white_knight", "7,1"}, {"white_bishop", "7,2"}, {"white_queen", "7,3"},
            {"white_king", "7,4"}, {"white_bishop", "7,5"}, {"white_knight", "7,6"}, {"white_rook", "7,7"},
            {"white_pawn", "6,0"}, {"white_pawn", "6,1"}, {"white_pawn", "6,2"}, {"white_pawn", "6,3"},
            {"white_pawn", "6,4"}, {"white_pawn", "6,5"}, {"white_pawn", "6,6"}, {"white_pawn", "6,7"}
        };

        List<String> positions = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                positions.add(i + "," + j);
            }
        }
        Collections.shuffle(positions);

        piecesArray = new String[32][3];
        for (int i = 0; i < 32; i++) {
            piecesArray[i][0] = standardPieces[i][0];
            piecesArray[i][1] = positions.get(i); 
            piecesArray[i][2] = standardPieces[i][1]; 
        }
    }

    private void printUnsortedPieces() {
        System.out.println("unsortedPieces");
        System.out.println("type\t\trandomInitialPosition\t\tsortedPosition");
        for (String[] piece : piecesArray) {
            System.out.printf("%-12s\t%s\t-> %s%n", 
                piece[0], 
                piece[1], 
                piece[2]);
        }
        System.out.println();
    }

    private void sortPieces() {
        Arrays.sort(piecesArray, (a, b) -> {
          
            String[] posA = a[2].split(",");
            String[] posB = b[2].split(",");
            int rowCompare = Integer.compare(Integer.parseInt(posA[0]), Integer.parseInt(posB[0]));
            if (rowCompare != 0) return rowCompare;
            return Integer.compare(Integer.parseInt(posA[1]), Integer.parseInt(posB[1]));
        });
    }

    private void populateBoard() {
    
        for (JPanel[] row : squares) {
            for (JPanel panel : row) {
                panel.removeAll();
            }
        }

        for (String[] piece : piecesArray) {
            String[] targetPos = piece[2].split(",");
            int row = Integer.parseInt(targetPos[0]);
            int col = Integer.parseInt(targetPos[1]);

            ImageIcon icon = new ImageIcon("image/" + piece[0] + ".png");
            Image scaled = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            squares[row][col].add(new JLabel(new ImageIcon(scaled)), BorderLayout.CENTER);
        }

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChessBoard().setVisible(true));
    }
}