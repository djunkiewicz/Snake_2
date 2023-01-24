package pl.junkiewiczdamian.frame;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private final JTextField scorePoints;
    private final JButton startButton;
    private final JButton pauseButton;
    private final JButton restartButton;

    public MenuPanel() {
        this.setPreferredSize(new Dimension(240, 720));
        this.setBackground(new Color(222, 242, 255));
        GridBagLayout l = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(l);

        startButton = new JButton("START");
        startButton.setPreferredSize(new Dimension(240, 120));
        startButton.setMinimumSize(new Dimension(240, 120));
        startButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));

        pauseButton = new JButton("PAUSE");
        pauseButton.setPreferredSize(new Dimension(240, 120));
        pauseButton.setMinimumSize(new Dimension(240, 120));
        pauseButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));

        restartButton = new JButton("RESTART");
        restartButton.setPreferredSize(new Dimension(240, 120));
        restartButton.setMinimumSize(new Dimension(240, 120));
        restartButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));

        JLabel gamePicture = new JLabel();
        gamePicture.setPreferredSize(new Dimension(240, 280));
        gamePicture.setMinimumSize(new Dimension(240, 280));
        gamePicture.setBackground(new Color(232, 255, 236));
        gamePicture.setOpaque(true);
        ImageIcon icon = new ImageIcon("icons\\gamePicture.png");
        gamePicture.setIcon(icon);
        gamePicture.setHorizontalAlignment(SwingConstants.CENTER);
        gamePicture.setVerticalAlignment(SwingConstants.CENTER);

        JTextField scoreText = new JTextField();
        scoreText.setPreferredSize(new Dimension(120, 80));
        scoreText.setMinimumSize(new Dimension(120, 80));
        scoreText.setBackground(new Color(180, 231, 255));
        scoreText.setOpaque(true);
        scoreText.setText("Score:");
        scoreText.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 40));
        scoreText.setHorizontalAlignment(SwingConstants.RIGHT);
        scoreText.setEditable(false);

        scorePoints = new JTextField();
        scorePoints.setBackground(new Color(180, 231, 255));
        scorePoints.setOpaque(true);
        scorePoints.setPreferredSize(new Dimension(120, 80));
        scorePoints.setMinimumSize(new Dimension(120, 80));
        scorePoints.setText("0");
        scorePoints.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 40));
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

    public void setPoints(int points){
        scorePoints.setText(String.valueOf(points));
    }
    public JButton getStartButton() {
        return startButton;
    }
    public JButton getPauseButton() {
        return pauseButton;
    }
    public JButton getRestartButton() {
        return restartButton;
    }
}
