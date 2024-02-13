package com.andoverrobotics.thunder.simulator;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.*;

import org.reflections.Reflections;

public class OpmodeChooser {
    public static void main(String[] args) {
        System.out.println("Starting opmode chooser...");

        SwingUtilities.invokeLater(
                () -> {
                    OpModeSelector opModeSelector = new OpModeSelector();
                    opModeSelector.setVisible(true);
                });
    }
}

class OpmodeInfo {
    public String name;
    public String description;
    public Class<? extends SimLinearOpMode> opmodeClass;
}

class OpModeSelector extends JFrame {
    private JList<String> opModeList;
    private JList<String> opModeTestList;
    private JTextArea descriptionTextArea;
    private JButton startButton;

    private enum OpModeType {
        TEST,
        OPMODE
    }

    private OpModeType opModeType;
    private final ArrayList<OpmodeInfo> opmodeInfo;
    private final Map<String, String> opModeTests;

    public OpModeSelector() {
        opmodeInfo =
                new Reflections("com.andoverrobotics.thunder.simulator")
                        .getSubTypesOf(SimLinearOpMode.class).stream()
                                .map(
                                        opmodeClass -> {
                                            OpmodeInfo info = new OpmodeInfo();
                                            info.opmodeClass = opmodeClass;
                                            info.name = opmodeClass.getSimpleName();
                                            info.description =
                                                    "Description of "
                                                            + opmodeClass.getSimpleName()
                                                            + ".";
                                            return info;
                                        })
                                .collect(Collectors.toCollection(ArrayList::new));

        opModeTests = new HashMap<>();
        opModeTests.put(
                "TeleOp forward test",
                "tests that robot moves forward when joystick is pushed forward");
        opModeTests.put(
                "TeleOp backward test",
                "tests that robot moves backward when joystick is pushed backward");
        opModeTests.put(
                "TeleOp left test", "tests that robot moves left when joystick is pushed left");
        opModeTests.put(
                "TeleOp right test", "tests that robot moves right when joystick is pushed right");

        initializeUI();
    }

    private void initializeUI() {
        setTitle("OpMode Selector");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(new Dimension(2000, 2000));

        DefaultListModel<String> opModeListModel = new DefaultListModel<>();
        DefaultListModel<String> opModeTestListModel = new DefaultListModel<>();
        for (OpmodeInfo info : opmodeInfo) {
            opModeListModel.addElement(info.name);
        }

        for (String opModeTest : opModeTests.keySet()) {
            opModeTestListModel.addElement(opModeTest);
        }

        opModeList = new JList<>(opModeListModel);
        opModeTestList = new JList<>(opModeTestListModel);
        opModeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        opModeTestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        opModeList.addListSelectionListener(
                e -> {
                    int selectedOpMode = opModeList.getSelectedIndex();
                    String description = opmodeInfo.get(selectedOpMode).description;
                    descriptionTextArea.setText(description);
                    startButton.setText("Start opmode");
                    opModeTestList.clearSelection();
                    opModeType = OpModeType.OPMODE;
                });

        opModeTestList.addListSelectionListener(
                e -> {
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
        startButton.addActionListener(
                e -> {
                    int selectedOpModeIndex = opModeList.getSelectedIndex();
                    OpmodeInfo selectedOpMode = opmodeInfo.get(selectedOpModeIndex);
                    if (selectedOpMode != null) {
                        this.setVisible(false);
                        OpModeRunner opModeRunner = null;
                        try {
                            opModeRunner = new OpModeRunner(selectedOpMode);
                        } catch (NoSuchMethodException
                                | InvocationTargetException
                                | InstantiationException
                                | IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        }
                        opModeRunner.setVisible(true);
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
        SwingUtilities.invokeLater(
                () -> {
                    OpModeSelector opModeSelector = new OpModeSelector();
                    opModeSelector.setVisible(true);
                });
    }
}
