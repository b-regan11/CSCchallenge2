import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static String selectedDirectoryPath;
    private static String repoName;
    private static boolean repoPriv;
    private static String repoDescription;
    public static void main(String[] args) {
        // main window frame
        JFrame frame = new JFrame("GitHub Client GUI");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.gray);
        frame.setContentPane(mainPanel);

        // label to display selected directory
        JLabel directoryLabel = new JLabel("Selected directory will appear here");
        directoryLabel.setBounds(50, 100, 400, 30);
        mainPanel.add(directoryLabel);

        // button to select directory
        JButton selectDirectoryButton = new JButton("Select Directory");
        selectDirectoryButton.setBounds(50, 150, 200, 30);
        mainPanel.add(selectDirectoryButton);

        // label to display GitHub URL
        JLabel urlLabel = new JLabel("GitHub URL will appear here");
        urlLabel.setBounds(50, 200, 400, 30);
        mainPanel.add(urlLabel);
        urlLabel.setVisible(false); // Initially hidden
        
        // button to setup a new repo
        JButton setupButton = new JButton("Setup New Repository");
        setupButton.setBounds(50, 250, 200, 30);
        mainPanel.add(setupButton);
        setupButton.setVisible(false); // Initially hidden

        // button to add to repo
        JButton gitAddButton = new JButton("Git Add");
        gitAddButton.setBounds(50, 300, 200, 30);
        mainPanel.add(gitAddButton);
        gitAddButton.setVisible(false); // Initially hidden

        // button to add README to repo
        JButton gitAddREADMEButton = new JButton("Add README");
        gitAddREADMEButton.setBounds(50, 350, 200, 30);
        mainPanel.add(gitAddREADMEButton);
        gitAddREADMEButton.setVisible(false); // Initially hidden

        // button to add git ignore to repo
        JButton gitAddGitIgnoreButton = new JButton("Add Git Ignore");
        gitAddGitIgnoreButton.setBounds(50, 400, 200, 30);
        mainPanel.add(gitAddGitIgnoreButton);
        gitAddGitIgnoreButton.setVisible(false); // Initially hidden
        
        // button to commit to repo
        JButton gitCommitButton = new JButton("Git Commit");
        gitCommitButton.setBounds(50, 450, 200, 30);
        mainPanel.add(gitCommitButton);
        gitCommitButton.setVisible(false); // Initially hidden

        // button to push to repo
        JButton gitPushButton = new JButton("Git Push");
        gitPushButton.setBounds(50, 500, 200, 30);
        mainPanel.add(gitPushButton);
        gitPushButton.setVisible(false); // Initially hidden
        


        // ActionListener for selecting directory
        selectDirectoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedDirectory = fileChooser.getSelectedFile();
                    selectedDirectoryPath = selectedDirectory.getAbsolutePath();
                    directoryLabel.setText("Selected directory: " + selectedDirectoryPath);
                    // show components
                    urlLabel.setVisible(true); 
                    setupButton.setVisible(true); 
                    gitAddButton.setVisible(true);
                    gitAddREADMEButton.setVisible(true);
                    gitAddGitIgnoreButton.setVisible(true);
                    gitCommitButton.setVisible(true);
                    gitPushButton.setVisible(true);
                    setupButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            repoName = JOptionPane.showInputDialog(frame, "Enter repository name:");
                            repoDescription = JOptionPane.showInputDialog(frame, "Enter repository description:");

                            // Prompt user for repository privacy
                            String[] options = {"Public", "Private"};
                            int privacyOption = JOptionPane.showOptionDialog(frame, "Select repository privacy:", "Repository Privacy",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                            repoPriv = (privacyOption == 1); // Private if option index is 1

                            if (repoName != null && !repoName.isEmpty() && repoDescription != null) {
                                GitOperations setup = new GitOperations(selectedDirectoryPath, repoName);
                                setup.performGitOperations();
                                setup.performGitInit();
                                setup.performNewRepo(repoName, repoDescription, repoPriv);
                                setup.performRemoteAddOrigin(repoName);
                                String url = setup.performGetGitHubURL(repoName);
                                urlLabel.setText("GitHub URL: " + url);
                    
                            } else {
                                JOptionPane.showMessageDialog(frame, "Repository name and description cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(frame, "No directory selected", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener for Git Add button
        gitAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GitOperations setup = new GitOperations(selectedDirectoryPath, repoName);
                setup.performGitAdd();
                System.out.println("Git Add button clicked");
            }
        });

        // ActionListener for Add README button
gitAddREADMEButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        GitOperations setup = new GitOperations(selectedDirectoryPath, repoName);
        // Specify the content of the README.md file
        String readmeContent = "## Challenge 2: Graphic User Interface Application\n" +
                                "This is a README file for the Graphic User Interface Application challenge.\n";

        // Specify the directory where you want to create the README.md file
        String directoryPath = selectedDirectoryPath;

        // Specify the file name
        String fileName = "README.md";

        // Create a File object representing the directory
        File directory = new File(directoryPath);

        // Create the directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs(); // mkdirs() creates parent directories if needed
        }

        // Create the file within the specified directory
        File file = new File(directory, fileName);

        try {
            // Create FileWriter object to write data to the file
            FileWriter writer = new FileWriter(file);

            // Write the content to the file
            writer.write(readmeContent);

            // Close the writer
            writer.close();
            
            setup.performGitAddFile("README.md");
            System.out.println("README.md file created successfully at: " + file.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
});

        // ActionListener for Add Git Ignore button
gitAddGitIgnoreButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        GitOperations setup = new GitOperations(selectedDirectoryPath, repoName);
        // Create the content of the .gitignore file
        String gitIgnoreContent = "##############################\n" +
                                  "## Java\n" +
                                  "##############################\n" +
                                  ".mtj.tmp/\n" +
                                  "*.class\n" +
                                  "*.jar\n" +
                                  "*.war\n" +
                                  "*.ear\n" +
                                  "*.nar\n" +
                                  "hs_err_pid*\n" +
                                  "replay_pid*\n" +
                                  "\n" +
                                  "##############################\n" +
                                  "## Maven\n" +
                                  "##############################\n" +
                                  "target/\n" +
                                  "pom.xml.tag\n" +
                                  "pom.xml.releaseBackup\n" +
                                  "pom.xml.versionsBackup\n" +
                                  "pom.xml.next\n" +
                                  "pom.xml.bak\n" +
                                  "release.properties\n" +
                                  "dependency-reduced-pom.xml\n" +
                                  "buildNumber.properties\n" +
                                  ".mvn/timing.properties\n" +
                                  ".mvn/wrapper/maven-wrapper.jar\n" +
                                  "\n" +
                                  "##############################\n" +
                                  "## Gradle\n" +
                                  "##############################\n" +
                                  "bin/\n" +
                                  "build/\n" +
                                  ".gradle\n" +
                                  ".gradletasknamecache\n" +
                                  "gradle-app.setting\n" +
                                  "!gradle-wrapper.jar\n" +
                                  "\n" +
                                  "##############################\n" +
                                  "## IntelliJ\n" +
                                  "##############################\n" +
                                  "out/\n" +
                                  ".idea/\n" +
                                  ".idea_modules/\n" +
                                  "*.iml\n" +
                                  "*.ipr\n" +
                                  "*.iws\n" +
                                  "\n" +
                                  "##############################\n" +
                                  "## Eclipse\n" +
                                  "##############################\n" +
                                  ".settings/\n" +
                                  "bin/\n" +
                                  "tmp/\n" +
                                  ".metadata\n" +
                                  ".classpath\n" +
                                  ".project\n" +
                                  "*.tmp\n" +
                                  "*.bak\n" +
                                  "*.swp\n" +
                                  "*~.nib\n" +
                                  "local.properties\n" +
                                  ".loadpath\n" +
                                  ".factorypath\n" +
                                  "\n" +
                                  "##############################\n" +
                                  "## NetBeans\n" +
                                  "##############################\n" +
                                  "nbproject/private/\n" +
                                  "build/\n" +
                                  "nbbuild/\n" +
                                  "dist/\n" +
                                  "nbdist/\n" +
                                  "nbactions.xml\n" +
                                  "nb-configuration.xml\n" +
                                  "\n" +
                                  "##############################\n" +
                                  "## Visual Studio Code\n" +
                                  "##############################\n" +
                                  ".vscode/\n" +
                                  ".code-workspace\n" +
                                  "\n" +
                                  "##############################\n" +
                                  "## OS X\n" +
                                  "##############################\n" +
                                  ".DS_Store\n" +
                                  "\n" +
                                  "##############################\n" +
                                  "## Miscellaneous\n" +
                                  "##############################\n" +
                                  "*.log\n";

        // Specify the directory where you want to create the .gitignore file
        String directoryPath = selectedDirectoryPath;

        // Specify the file name
        String fileName = ".gitignore";

        // Create a File object representing the directory
        File directory = new File(directoryPath);

        // Create the directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs(); // mkdirs() creates parent directories if needed
        }

        // Create the file within the specified directory
        File file = new File(directory, fileName);

        try {
            // Create FileWriter object to write data to the file
            FileWriter writer = new FileWriter(file);

            // Write the content to the file
            writer.write(gitIgnoreContent);

            // Close the writer
            writer.close();
            setup.performGitAddFile(".gitignore");
            System.out.println(".gitignore file created successfully at: " + file.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
});

        // ActionListener for Git Commit button
        gitCommitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String commitMessage = JOptionPane.showInputDialog(frame, "Enter commit message:");
                if (commitMessage != null && !commitMessage.isEmpty()) {
                    GitOperations setup = new GitOperations(selectedDirectoryPath, repoName);
                    setup.performGitCommit(commitMessage);
                    System.out.println("Git Commit button clicked with message: " + commitMessage);
                } else {
                    JOptionPane.showMessageDialog(frame, "Commit message cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener for Git Push button
        gitPushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GitOperations setup = new GitOperations(selectedDirectoryPath, repoName);
                setup.performGitPush();
                System.out.println("Git Push button clicked");
            }
        });
        
        frame.setVisible(true);
        frame.setResizable(false);
    }
}