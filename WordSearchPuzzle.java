import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class WordSearchPuzzle extends JFrame {
    private char[][] grid = new char[10][10];
    private String[] words = {"JAVA", "CODE", "GAME", "PUZZLE", "LOGIC"};
    private Set<String> found = new HashSet<>();
    public WordSearchPuzzle() {
        setTitle("Word Search Puzzle");
        setSize(430, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(10,10));
        fillGrid();
        JButton[][] buttons = new JButton[10][10];
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++) {
                buttons[i][j] = new JButton(grid[i][j]+"");
                gridPanel.add(buttons[i][j]);
            }
        JTextField input = new JTextField();
        JButton submit = new JButton("Check");
        JLabel result = new JLabel("Find: "+String.join(", ", words));
        submit.addActionListener(e->{
            String guess = input.getText().toUpperCase().trim();
            if (Arrays.asList(words).contains(guess)) {
                found.add(guess);
                result.setText("Found: "+String.join(", ", found));
            }
            input.setText("");
        });
        JPanel bottom = new JPanel();
        bottom.add(input); bottom.add(submit);
        add(gridPanel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        add(result, BorderLayout.NORTH);
        setVisible(true);
    }
    void fillGrid() {
        Random r = new Random();
        for(int i=0;i<10;i++)Arrays.fill(grid[i], (char)('A'+r.nextInt(26)));
        for(String w:words) {
            int x = r.nextInt(10), y = r.nextInt(10), dir = r.nextInt(2);
            for(int i=0;i<w.length();i++)
                if(x+i<10 && dir==0) grid[y][x+i]=w.charAt(i);
                else if(y+i<10 && dir==1) grid[y+i][x]=w.charAt(i);
        }
    }
    public static void main(String[] args) { new WordSearchPuzzle(); }
}