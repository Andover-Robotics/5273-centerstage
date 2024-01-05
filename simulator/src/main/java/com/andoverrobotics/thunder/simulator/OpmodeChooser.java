package com.andoverrobotics.thunder.simulator;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class OpmodeChooser {
    public static void main(String[] args) {
        System.out.println("Starting opmode chooser...");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                OpModeSelectorFrame opModeSelectorFrame = new OpModeSelectorFrame();
                opModeSelectorFrame.setVisible(true);
            }
        });
    }
}


class OpModeSelectorFrame extends JFrame {
    private JComboBox<String> opModeComboBox;
    private JTextArea descriptionTextArea;
    private JButton startButton;

    private Map<String, String> opModeDescriptions;

    public OpModeSelectorFrame() {
        opModeDescriptions = new HashMap<>();
        opModeDescriptions.put("OpMode1", "Description of OpMode1.");
        opModeDescriptions.put("OpMode2", "Description of OpMode2.");
        opModeDescriptions.put("OpMode3", "Description of OpMode3.");

        initializeUI();
    }

    private void initializeUI() {
        setTitle("OpMode Selector");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        opModeComboBox = new JComboBox<>(opModeDescriptions.keySet().toArray(new String[0]));
        opModeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOpMode = (String) opModeComboBox.getSelectedItem();
                String description = opModeDescriptions.get(selectedOpMode);
                descriptionTextArea.setText(description);
            }
        });

        descriptionTextArea = new JTextArea();
        descriptionTextArea.setEditable(false);

        startButton = new JButton("Start OpMode");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOpMode = (String) opModeComboBox.getSelectedItem();
                // Add your logic to start the selected opmode here
                JOptionPane.showMessageDialog(OpModeSelectorFrame.this, "Starting " + selectedOpMode);
            }
        });

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Select OpMode: "), BorderLayout.NORTH);
        leftPanel.add(opModeComboBox, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel("OpMode Description: "), BorderLayout.NORTH);
        rightPanel.add(descriptionTextArea, BorderLayout.CENTER);
        rightPanel.add(startButton, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
