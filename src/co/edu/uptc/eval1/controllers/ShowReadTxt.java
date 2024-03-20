package co.edu.uptc.eval1.controllers;

import co.edu.uptc.eval1.services.ReadTxt;
import co.edu.uptc.text.ManagerProperties;

public class ShowReadTxt {
    public void show () {
        ManagerProperties managerProperties = new ManagerProperties();
        managerProperties.setFileName("data.properties");
        ReadTxt readTxt = new ReadTxt();
        readTxt.setPath(managerProperties.getValue("personas"));
        try {
            readTxt.completPersons();
            readTxt.puntoA();
            readTxt.createFileA("PuntoA.txt");
            readTxt.puntoB();
            readTxt.createFileB("PuntoB.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
