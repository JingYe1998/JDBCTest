package org.example.JDBC.TaipowerImpl;


import org.example.JDBC.Taipower.Taipower;

public class MainFind {

	public static void main(String[] args) {
		TaipowerImpl tDAO = new TaipowerImpl();
		Taipower t = tDAO.findTestByStation("大潭電廠光電站/Datang Power Plant Photovoltaics Station");
		System.out.println(t.getYear());
		System.out.println(t.getMonth());
		System.out.println(t.getStation());
		System.out.println(t.getCapacity());
		System.out.println(t.getPower());
		System.out.println(t.getAverage());
//		t.setStation("中大D/S光電站/Zhongda D/S Photovoltaics Station");
//		tDAO.savecsv("C://Test//test1.csv");
		

	}

}
