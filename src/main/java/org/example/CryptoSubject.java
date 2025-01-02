package org.example;

import java.util.ArrayList;
import java.util.List;

class CryptoSubject {
    private List<Observer> observers = new ArrayList<>();
    private double price;

    /**
     *
     * @param observer adaug un observator la acest subiect
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /**
     *
     * @param observer scot un observator de la acest subiect
     */
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifica toti observatorii despre schimbarile produse
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(price);
        }
    }

    /**
     * Aceasta clasa modifica starea pretului in criptomoneda, astfel declasand toti observatorii sa intre in actiune
     * @param price elementul care produce schimbarea starii
     */
    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    public double getPrice() {
        return price;
    }
}
