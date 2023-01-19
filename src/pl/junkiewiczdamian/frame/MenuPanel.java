package pl.junkiewiczdamian.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    private final JTextField scorePoints;

    public MenuPanel() {
        this.setPreferredSize(new Dimension(300, 900));
        this.setBackground(new Color(222, 242, 255));
        GridBagLayout l = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(l);

        JButton startButton = new JButton("START");
        startButton.setPreferredSize(new Dimension(300, 150));
        startButton.setMinimumSize(new Dimension(300, 150));
        startButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 36));
        startButton.addActionListener(this);

        JButton pauseButton = new JButton("PAUSE");
        pauseButton.setPreferredSize(new Dimension(300, 150));
        pauseButton.setMinimumSize(new Dimension(300, 150));
        pauseButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 36));
        pauseButton.addActionListener(this);

        JButton restartButton = new JButton("RESTART");
        restartButton.setPreferredSize(new Dimension(300, 150));
        restartButton.setMinimumSize(new Dimension(300, 150));
        restartButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 36));
        restartButton.addActionListener(this);

        JLabel gamePicture = new JLabel();
        gamePicture.setPreferredSize(new Dimension(300, 350));
        gamePicture.setMinimumSize(new Dimension(300, 350));
        gamePicture.setBackground(new Color(232, 255, 236));
        gamePicture.setOpaque(true);
        ImageIcon icon = new ImageIcon("gamePicture.png");
        gamePicture.setIcon(icon);
        gamePicture.setHorizontalAlignment(SwingConstants.CENTER);
        gamePicture.setVerticalAlignment(SwingConstants.CENTER);

        JTextField scoreText = new JTextField();
        scoreText.setPreferredSize(new Dimension(150, 100));
        scoreText.setMinimumSize(new Dimension(150, 100));
        scoreText.setBackground(new Color(180, 231, 255));
        scoreText.setOpaque(true);
        scoreText.setText("Score:");
        scoreText.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 56));
        scoreText.setHorizontalAlignment(SwingConstants.RIGHT);
        scoreText.setEditable(false);

        scorePoints = new JTextField();
        scorePoints.setBackground(new Color(180, 231, 255));
        scorePoints.setOpaque(true);
        scorePoints.setPreferredSize(new Dimension(150, 100));
        scorePoints.setMinimumSize(new Dimension(150, 150));
        scorePoints.setText("0");
        scorePoints.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 56));
        scorePoints.setHorizontalAlignment(SwingConstants.CENTER);
        scorePoints.setEditable(false);

        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        this.add(gamePicture, c);

        c.gridy = 1;
        c.gridwidth = 1;
        this.add(scoreText, c);

        c.gridx = 1;
        this.add(scorePoints, c);

        c.fill = GridBagConstraints.NONE;
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 2;
        this.add(startButton, c);

        c.gridy = 3;
        this.add(pauseButton, c);

        c.gridy = 4;
        this.add(restartButton, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void setPoints(int points){
        scorePoints.setText(String.valueOf(points));
    }

    public int getPoints(){
        return Integer.parseInt(scorePoints.getText());
    }
}
