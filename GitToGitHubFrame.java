import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GitToGitHubFrame extends JFrame {
   private JPanel mainPanel;
   private JButton convertButton; 
   private JTextField projectNameField, repoNameField, repoDescriptionField;
   private JCheckBox privateRepoCheckBox;
   private JLabel statusLabel;


   public GitToGitHubFrame(String title) {
       super(title);
       initializeComponents();
   }


   private void initializeComponents() {
       mainPanel = new JPanel();
       mainPanel.setLayout(null);
       mainPanel.setBackground(Color.BLACK);


       JLabel projectNameLabel = new JLabel("Project Name:");
       projectNameLabel.setBounds(50, 50, 100, 25);
       mainPanel.add(projectNameLabel);


       projectNameField = new JTextField();
       projectNameField.setBounds(160, 50, 200, 25);
       mainPanel.add(projectNameField);


       JLabel repoNameLabel = new JLabel("Repository Name:");
       repoNameLabel.setBounds(50, 100, 100, 25);
       mainPanel.add(repoNameLabel);


       repoNameField = new JTextField(); 
       repoNameField.setBounds(160, 100, 200, 25);
       mainPanel.add(repoNameField);


       JLabel repoDescriptionLabel = new JLabel("Repo Description:");
       repoDescriptionLabel.setBounds(50, 150, 120, 25);
       mainPanel.add(repoDescriptionLabel);


       repoDescriptionField = new JTextField();
       repoDescriptionField.setBounds(180, 150, 200, 25);
       mainPanel.add(repoDescriptionField);


       privateRepoCheckBox = new JCheckBox("Private Repository");
       privateRepoCheckBox.setBounds(50, 200, 150, 25);
       mainPanel.add(privateRepoCheckBox);


       convertButton = new JButton("Convert and Push");
       convertButton.setBounds(50, 250, 150, 25);
       convertButton.addActionListener(new ConvertActionListener());
       mainPanel.add(convertButton);


       statusLabel = new JLabel("Status: Waiting for input...");
       statusLabel.setBounds(50, 300, 300, 25);
       mainPanel.add(statusLabel);


       this.setContentPane(mainPanel);
   }


   private class ConvertActionListener implements ActionListener {
       @Override
       public void actionPerformed(ActionEvent e) {
           // Placeholder for conversion logic
           statusLabel.setText("Status: Conversion started...");
           // Implement the conversion logic here
       }
   }
}
