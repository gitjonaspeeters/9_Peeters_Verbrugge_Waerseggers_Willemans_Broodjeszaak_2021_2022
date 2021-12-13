package controller;

import model.BelegSoort;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import view.OrderView;

import java.util.Observable;
import java.util.Observer;

public class OrderViewController implements Observer {

    OrderView view;

    public OrderViewController() throws Exception {

    }

    public void setView(OrderView view){
        this.view = view;
    }




    @Override
    public void update(Observable o, Object arg) {

    }
}
