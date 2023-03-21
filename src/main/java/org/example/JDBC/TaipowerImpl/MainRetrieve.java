package org.example.JDBC.TaipowerImpl;

public class MainRetrieve {
//此方法是把資料匯入資料庫
	public static void main(String[] args) {
		TaipowerImpl tDAO = new TaipowerImpl();
		try {
			tDAO.retrievedata();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
