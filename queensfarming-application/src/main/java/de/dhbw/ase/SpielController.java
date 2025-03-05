package de.dhbw.ase;

import java.util.ArrayList;
import java.util.List;

public class SpielController implements ISubjekt {
    private final List<IObserver> observers = new ArrayList<>();

    private static SpielController instance;

    private SpielController() {}

    public static SpielController getInstance() {
        if (instance == null) {
            instance = new SpielController();
        }
        return instance;
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(IObserver::update);
    }
}
