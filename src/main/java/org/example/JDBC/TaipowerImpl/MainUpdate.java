package org.example.JDBC.TaipowerImpl;

import org.example.JDBC.Taipower.Taipower;

public class MainUpdate {

    public static void main(String[] args) {
        TaipowerImpl tDAO = new TaipowerImpl();
        Taipower t1 = new Taipower();
        t1.setStation("大潭電廠光電站/Datang Power Plant Photovoltaics Station");
//		--------------以下為會被修改的值
        t1.setYear(2017);
        t1.setMonth(10);
        t1.setCapacity(1.1);
        t1.setPower(2022);
        t1.setAverage(1.1);
        tDAO.updatedata(t1);
    }

}
