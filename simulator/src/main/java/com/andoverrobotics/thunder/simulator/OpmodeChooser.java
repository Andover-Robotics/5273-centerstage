package com.andoverrobotics.thunder.simulator;

import static java.lang.System.exit;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class OpmodeChooser {
    public static void main(String[] args) {

        try {
            new RobotConfiguration("./RealRobotConfig.xml");
        } catch (Exception e) {
            System.out.println("Error loading robot configuration: " + e.getMessage());
        }
        exit(0);
        System.out.println("Starting opmode chooser...");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                OpModeSelector opModeSelector = new OpModeSelector();
                opModeSelector.setVisible(true);
            }
        });
    }
}


class OpModeSelector extends JFrame {
    private JList<String> opModeList;
    private JList<String> opModeTestList;
    private JTextArea descriptionTextArea;
    private JButton startButton;
    private enum OpModeType {
        TEST, OPMODE
    };
    private OpModeType opModeType;
    private Map<String, String> opModeDescriptions;
    private Map<String, String> opModeTests;

    public OpModeSelector() {
        opModeDescriptions = new HashMap<>();
        opModeDescriptions.put("OpMode1", "Description of OpMode1.");
        opModeDescriptions.put("OpMode2", "Description of OpMode2.");
        opModeDescriptions.put("OpMode3", "Description of OpMode3.");

        opModeTests = new HashMap<>();
        opModeTests.put("TeleOp forward test", "tests that robot moves forward when joystick is pushed forward");
        opModeTests.put("TeleOp backward test", "tests that robot moves backward when joystick is pushed backward");
        opModeTests.put("TeleOp left test", "tests that robot moves left when joystick is pushed left");
        opModeTests.put("TeleOp right test", "tests that robot moves right when joystick is pushed right");


        initializeUI();
    }

    private void initializeUI() {
        setTitle("OpMode Selector");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(new Dimension(2000, 2000));

        DefaultListModel<String> opModeListModel = new DefaultListModel<>();
        DefaultListModel<String> opModeTestListModel = new DefaultListModel<>();
        for (String opMode : opModeDescriptions.keySet()) {
            opModeListModel.addElement(opMode);
        }
        for (String opModeTest : opModeTests.keySet()) {
            opModeTestListModel.addElement(opModeTest);
        }

        opModeList = new JList<>(opModeListModel);
        opModeTestList = new JList<>(opModeTestListModel);
        opModeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        opModeTestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        opModeList.addListSelectionListener(e -> {
            String selectedOpMode = opModeList.getSelectedValue();
            String description = opModeDescriptions.get(selectedOpMode);
            descriptionTextArea.setText(description);
            startButton.setText("Start opmode");
            opModeTestList.clearSelection();
            opModeType = OpModeType.OPMODE;
        });

        opModeTestList.addListSelectionListener(e -> {
            String selectedOpModeTest = opModeTestList.getSelectedValue();
            String description = opModeTests.get(selectedOpModeTest);
            descriptionTextArea.setText(description);
            startButton.setText("Run test");
            opModeList.clearSelection();
            opModeType = OpModeType.TEST;
        });

        JScrollPane opModeScrollPane = new JScrollPane(opModeList);
        JScrollPane opModeTestScrollPane = new JScrollPane(opModeTestList);


        descriptionTextArea = new JTextArea();
        descriptionTextArea.setEditable(false);

        startButton = new JButton("Start OpMode");
        startButton.addActionListener(e -> {
            String selectedOpMode = opModeType == OpModeType.OPMODE ? opModeList.getSelectedValue() : opModeTestList.getSelectedValue();
            if (selectedOpMode != null) {
                this.setVisible(false);
                JFrame runner = new JFrame();
                runner.setTitle("OpMode Runner");
                runner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                runner.setLayout(new BorderLayout());
                runner.setSize(new Dimension(2000, 2000));
                runner.add(new JLabel("Running " + selectedOpMode), BorderLayout.CENTER);
                runner.setVisible(true);
            }
        });

        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel opModesPanel = new JPanel(new BorderLayout());
        opModesPanel.add(new JLabel("Select OpMode: "), BorderLayout.NORTH);
        opModesPanel.add(opModeScrollPane, BorderLayout.CENTER);
        leftPanel.add(opModesPanel, BorderLayout.WEST);

        JPanel opModeTestsPanel = new JPanel(new BorderLayout());
        opModeTestsPanel.add(new JLabel("Select OpMode Test: "), BorderLayout.NORTH);
        opModeTestsPanel.add(opModeTestScrollPane, BorderLayout.CENTER);
        leftPanel.add(opModeTestsPanel, BorderLayout.EAST);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OpModeSelector opModeSelector = new OpModeSelector();
            opModeSelector.setVisible(true);
        });
    }
}

