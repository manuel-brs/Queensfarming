package de.dhbw.ase;

public interface ISubjekt {
    public void registerObserver(IObserver observer);
    public void unregisterObserver(IObserver observer);
    public void notifyObservers();
}
