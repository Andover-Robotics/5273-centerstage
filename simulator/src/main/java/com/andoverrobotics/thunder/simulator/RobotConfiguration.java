package com.andoverrobotics.thunder.simulator;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RobotConfiguration {
    public RobotConfiguration(String filename)
            throws IOException, SAXException, ParserConfigurationException {
        File configFile = new File(filename);
        if (!configFile.exists()) {
            throw new IllegalArgumentException("Config file does not exist: " + filename);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(configFile);

        // https://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();

        NodeList components = doc.getDocumentElement().getChildNodes();
        for (int i = 0; i < components.getLength(); i++) {
            switch (components.item(i).getNodeName()) {
                case "name":
                    System.out.println("Name: " + components.item(i).getTextContent());
                    break;
                case "motor5202":
                    System.out.println("Motor: " + components.item(i).getTextContent());
                    break;
                case "wheel96mm":
                    System.out.println("Wheel: " + components.item(i).getTextContent());
                    break;
                default:
                    System.out.println("Unknown component: " + components.item(i).getNodeName());
            }
        }
    }
}
