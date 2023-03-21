package org.example.JDBC.TaipowerImpl;

import org.example.JDBC.Taipower.Taipower;


public class MainDelete {

	public static void main(String[] args) {
		TaipowerImpl tDAO = new TaipowerImpl();
		Taipower t = tDAO.findTestByStation("大潭電廠光電站/Datang Power Plant Photovoltaics Station");
		tDAO.deletedata(t);
	}

}
