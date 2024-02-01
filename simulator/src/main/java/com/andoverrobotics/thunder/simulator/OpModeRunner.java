package com.andoverrobotics.thunder.simulator;

import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimTelemetry;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OpModeRunner extends JFrame {
    private SimLinearOpMode opMode;
    private OpmodeState opmodeState;
    private Thread opmodeThread;
    private Class<? extends SimLinearOpMode> opmodeClass;

    JPanel buttonPanel;
    JButton initButton;
    JButton stopButton;
    JButton startButton;
    Thread uiThread;
    JTextArea logsArea;
    enum OpmodeState {
        off, initing, inited, started
    }
    //this is for simulation specific logs, no messages from the opmode will be logged here
    private String nativeLogContents = "";
    //this is a referanec to the opmode's logger
    private SimTelemetry opmodeLogger;

    public OpModeRunner(OpmodeInfo opmodeInfo) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        initializeUI();
        opmodeState = OpmodeState.off;
        opmodeClass = opmodeInfo.opmodeClass;
        //make a new thread to update the ui state every 50 ms
        uiThread = new Thread(() -> {
            while (true) {
                updateUIState();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        uiThread.start();
    }

    public void log(String message) {
        nativeLogContents += message + "\n";
    }

    public void clearLog() {
        nativeLogContents = "";
    }

    private void initializeUI() {
        setTitle("OpMode Runner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(new Dimension(2000, 2000));

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Top right container for buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        initButton = new JButton("Init");
        stopButton = new JButton("Stop");
        startButton = new JButton("Start");

        buttonPanel.add(initButton);

        //listeners for the buttons
        initButton.addActionListener(event -> {
            System.out.println("init button pressed");
            if (opmodeState != OpmodeState.off) {
                System.out.println("shutting down already running opmode");
                //turn off the opmode
                opmodeState = OpmodeState.off;
                opmodeThread.interrupt();
                opmodeThread = null;
                System.out.println("shut down already running opmode");
            }
            //start a new thread with the opmode
            opmodeThread = new Thread(() -> {
                try {
                    System.out.println("making new opmode instance");
                    opMode = opmodeClass.getDeclaredConstructor().newInstance();
                    System.out.println("made new opmode instance");
                } catch (InstantiationException | IllegalAccessException |
                         InvocationTargetException | NoSuchMethodException ex) {
                    System.err.println("Error creating opmode instance");
                    throw new RuntimeException(ex);
                }
                opmodeState = OpmodeState.initing;
                System.out.println("running opmode now");
                opmodeLogger = opMode.telemetry;
                try {
                    opMode.runOpmode();
                } catch (Exception e) {
                    log("Error running opmode: " + e.getMessage());
                    log("Stack trace: ");
                    for (StackTraceElement el : e.getStackTrace()) {
                        log(el.toString());
                    }
                } finally {
                    opmodeLogger = null;
                }
            });
            opmodeThread.start();
        });

        stopButton.addActionListener(e -> {
            System.out.println("stop button pressed");
            if (opmodeState != OpmodeState.off) {
                //turn off the opmode
                opmodeState = OpmodeState.off;
                opmodeThread.interrupt();
                opmodeThread = null;
            }
        });

        startButton.addActionListener(e -> {
            System.out.println("start button pressed");
            if (opmodeState == OpmodeState.inited) {
                opmodeState = OpmodeState.started;
                opMode.waiting = false;
                synchronized (opMode) {
                    opMode.notify();
                }
            }
        });

        // Right container for logs
        logsArea = new JTextArea(1000, 40);
        JScrollPane logsScrollPane = new JScrollPane(logsArea);
        logsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        logsScrollPane.setMaximumSize(new Dimension(300, 2000));
        logsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        logsArea.setEditable(false);
        logsArea.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua\nDui nunc mattis enim ut tellus elementum sagittis vitae\nEnim nulla aliquet porttitor lacus luctus accumsan tortor posuere\nViverra nibh cras pulvinar mattis nunc sed blandit libero\nPurus viverra accumsan in nisl nisi\nNunc aliquet bibendum enim facilisis gravida neque convallis a\nPorttitor lacus luctus accumsan tortor posuere ac ut consequat\nSed blandit libero volutpat sed cras ornare\nMassa id neque aliquam vestibulum morbi blandit cursus risus at\nIn hac habitasse platea dictumst quisque sagittis\nIn hac habitasse platea dictumst quisque sagittis purus\nEget nullam non nisi est sit amet facilisis\nUltrices neque ornare aenean euismod elementum nisi quis eleifend\nDuis tristique sollicitudin nibh sit amet commodo nulla facilisi nullam\nNibh ipsum consequat nisl vel pretium lectus quam id leo\nPlacerat orci nulla pellentesque dignissim enim.");

        // Large JComponent for real content
        JComponent realContent = new JPanel();
        realContent.setSize(new Dimension(1800, 2000));
        // Add your real content components here
        realContent.setBackground(java.awt.Color.RED);

        // Add components to the main panel
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(logsScrollPane, BorderLayout.EAST);
        mainPanel.add(realContent, BorderLayout.CENTER);

        // Add the main panel to the frame
        getContentPane().add(mainPanel);
    }

    public void updateUIState() {
        //check if the opmode is waiting and the state is initing
        //this will mean that the opmode has finished initing and is waiting for start
        if (opmodeState == OpmodeState.initing && opMode.waiting) {
            System.out.println("opmode has finished initing");
            opmodeState = OpmodeState.inited;
        }
        //check if the thread has been interrupted or has exited
        if (opmodeThread != null && (!opmodeThread.isAlive() || opmodeThread.isInterrupted())) {
            System.out.println("opmode has stopped");
            opmodeState = OpmodeState.off;
            opmodeThread = null;
        }

        //update the buttons based on the state
        switch (opmodeState) {
            case off:
                initButton.setEnabled(true);
                stopButton.setEnabled(false);
                startButton.setEnabled(false);
                break;
            case initing:
                initButton.setEnabled(false);
                stopButton.setEnabled(true);
                startButton.setEnabled(false);
                break;
            case inited:
                initButton.setEnabled(false);
                stopButton.setEnabled(true);
                startButton.setEnabled(true);
                break;
            case started:
                initButton.setEnabled(false);
                stopButton.setEnabled(true);
                startButton.setEnabled(false);
                break;
        }

        //update the log contents
        if(opmodeLogger != null){
            synchronized (opmodeLogger) {
                logsArea.setText(nativeLogContents + "\n-----------------\n" + opmodeLogger.finalData);
            }
        }else{
            logsArea.setText(nativeLogContents);
        }
    }
}
