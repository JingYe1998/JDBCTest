package org.example.JDBC.Taipower;

import java.util.List;

public interface TaipowerDAO  {
    void createdata(Taipower taipower);
    void updatedata(Taipower taipower);
    void deletedata(Taipower taipower);

    //Read
    List<Taipower> findpower();
    Taipower findTestByStation(String Station);

    //從url取出內容的方法:
    void retrievedata() throws Exception;
    //將取出的資料轉成CSV的方法:
    void savecsv(String csvpath);

}
