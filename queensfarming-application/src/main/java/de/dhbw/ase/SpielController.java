package de.dhbw.ase;

import java.util.ArrayList;
import java.util.List;

public class SpielController implements ISubjekt {
    private final List<IObserver> observers = new ArrayList<>();

    public SpielController() {}
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
