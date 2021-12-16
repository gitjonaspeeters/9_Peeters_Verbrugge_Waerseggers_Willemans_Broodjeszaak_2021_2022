package model.observer;

import jxl.read.biff.BiffException;
import model.BestellingsEvents;

import java.io.IOException;
import java.util.ArrayList;

public interface Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers(BestellingsEvents bestellingsEvents) throws Exception;
}
