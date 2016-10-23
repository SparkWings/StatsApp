package org.jbltd.mcl.stats;

import org.jbltd.mcl.stats.util.PlayerData;
import org.jbltd.mcl.stats.util.PlayerManager;
import org.jbltd.mcl.stats.util.SpringUtilities;
import org.jbltd.mcl.stats.util.TeamData;
import org.jbltd.mcl.stats.util.TeamManager;
import org.jbltd.mcl.stats.util.UtilFileReader;
import org.jbltd.mcl.stats.util.UtilSystem;
 
public class Main extends JFrame {

    private static final long serialVersionUID = - 9094851614997657772L;

    private HashMap<JLabel, JTextField> _data = new HashMap<>();

    private PlayerManager _pmanager;
    private TeamManager _tmanager;

    private String _teamOneName, _teamTwoName, _teamOneColor, _teamTwoColor;
    private String _filePath;

    private Main main = this;

    private static final String[] TEXT_LABELS = new String[] { "Team 1 Name", "Team 2 Name", "Team 1 Game Color (Red/Blue)", "Team 2 Game Color (Red/Blue)", "Team 1 Players (Separate by commas)", "Team 2 Players (Separate by commas)" };

    public Main() {

        // Initialization
        _pmanager = new PlayerManager();
        _tmanager = new TeamManager();

        JPanel p = new JPanel(new SpringLayout());

        for (int i = 0; i < TEXT_LABELS.length; i++) {
            JLabel j = new JLabel(TEXT_LABELS[i], JLabel.TRAILING);
            p.add(j);
            JTextField t = new JTextField(10);
            j.setLabelFor(t);
            p.add(t);

            _data.put(j, t);
        }

        SpringUtilities.makeCompactGrid(p, TEXT_LABELS.length, 2, 6, 6, 6, 6);

        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Minecraft Log Files", "txt", "log");
        jf.setFileFilter(filter);
        jf.setDialogTitle("MSCL Stat Tracker - Step 1: Choose Game Log File");

        // Save some time by attempting to set the user to
        // the default log directory - OutdatedVersion
        jf.setCurrentDirectory(UtilSystem.logDirectory());

        int returnVal = jf.showOpenDialog(p);

        if (returnVal == JFileChooser.APPROVE_OPTION)
            _filePath = jf.getSelectedFile().getAbsolutePath();

        if (returnVal == JFileChooser.CANCEL_OPTION)
            System.exit(1);

        JButton b = new JButton();
        b.setText("Enter Selected Info");
        b.setSize(new Dimension(20, 20));
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                for (JLabel jt : _data.keySet()) {

                    if (jt.getText().contains("Team 1 Name")) {
                        JTextField jtf = _data.get(jt);
                        _teamOneName = jtf.getText();
                    }

                    if (jt.getText().contains("Team 2 Name")) {
                        JTextField jtf = _data.get(jt);
                        _teamTwoName = jtf.getText();

                    }

                    if (jt.getText().contains("Team 1 Game Color")) {
                        JTextField jtf = _data.get(jt);
                        _teamOneColor = jtf.getText().trim();

                    }

                    if (jt.getText().contains("Team 2 Game Color")) {
                        JTextField jtf = _data.get(jt);
                        _teamTwoColor = jtf.getText().trim();

                    }

                    if (jt.getText().contains("Team 1 Players")) {
                        JTextField jtf = _data.get(jt);
                        String[] players = jtf.getText().split(",");

                        for (final String player : players) {
                            PlayerData pd = new PlayerData(player.trim(), _teamOneName, 0, 0, 0);
                            _tmanager.TEAM_ONE.add(pd);
                            _pmanager.MasterData.add(pd);
                        }
                    }

                    if (jt.getText().contains("Team 2 Players")) {
                        JTextField jtf = _data.get(jt);
                        String[] players = jtf.getText().split(",");

                        for (final String player : players) {
                            PlayerData pd = new PlayerData(player.trim(), _teamTwoName, 0, 0, 0);
                            _tmanager.TEAM_TWO.add(pd);
                            _pmanager.MasterData.add(pd);
                        }

                    }

                }

                TeamData team1 = new TeamData(_teamOneName, _teamOneColor, 0);
                _tmanager.MasterTeamData.add(team1);

                TeamData team2 = new TeamData(_teamTwoName, _teamTwoColor, 0);
                _tmanager.MasterTeamData.add(team2);

                new UtilFileReader(_pmanager, _tmanager, _filePath, main);

                main.dispose();

            }

        });

        this.add(p);
        this.add(b, SpringLayout.SOUTH);
        this.setTitle("MSCL Stat Tracker - Step 2: Enter Team Data");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 300);
        this.setEnabled(true);

        try {
            String path = "/logo.png";
            InputStream imgStream = Main.class.getResourceAsStream(path);
            BufferedImage img = ImageIO.read(imgStream);
            this.setIconImage(img);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

}
