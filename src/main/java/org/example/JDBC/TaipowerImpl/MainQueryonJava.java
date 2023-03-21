package org.example.JDBC.TaipowerImpl;

import org.example.JDBC.Taipower.Taipower;

import java.util.List;


public class MainQueryonJava {

    public static void main(String[] args) {
        TaipowerImpl tDAO = new TaipowerImpl();


        List<Taipower> List = tDAO.findpower();
        for (Taipower taipower : List) {
            System.out.println(taipower.getYear());
            System.out.println(taipower.getMonth());
            System.out.println(taipower.getStation());
            System.out.println(taipower.getCapacity());
            System.out.println(taipower.getPower());
            System.out.println(taipower.getAverage());
            System.out.println("=========================");

        }

    }
}
