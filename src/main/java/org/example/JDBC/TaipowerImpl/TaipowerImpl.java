package org.example.JDBC.TaipowerImpl;

import org.example.JDBC.Connection.ConnectionUtil;
import org.example.JDBC.Taipower.Taipower;
import org.example.JDBC.Taipower.TaipowerDAO;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaipowerImpl implements TaipowerDAO {
    @Override
    public void createdata(Taipower taipower) {
        String sql = "insert into Taipower(Year,Month,Station,Capacity,Power,Average) "
                + "values(?,?,?,?,?,?)";
        Connection conn = ConnectionUtil.getConnectionToTaipower();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // 設值進去DB table的方法
            pstmt.setInt(1, taipower.getYear());
            pstmt.setInt(2, taipower.getMonth());
            pstmt.setString(3, taipower.getStation());
            pstmt.setDouble(4, taipower.getCapacity());
            pstmt.setInt(5, taipower.getPower());
            pstmt.setDouble(6, taipower.getAverage());
            // 新增資料進去table
            int updatecount = pstmt.executeUpdate();
            // 確認是否新增成功
            System.out.println("新增" + updatecount + "筆資料");
        } catch (SQLException e) {
            e.printStackTrace();
        } // 確認釋放資源是否成功
        finally {
            boolean free = ConnectionUtil.freetaipower();
            System.out.println(free ? "釋放資源成功" : "釋放資源失敗");
        }

    }

    @Override
    public void retrievedata() throws Exception {

//建立url的物件，下載該物件
        URL url = new URL("https://data.taipower.com.tw/opendata/apply/file/d693003/台灣電力公司_太陽光電發電量及平均單位裝置容量每日發電量統計表.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            try {
                System.out.println("切割資料!");
                System.out.println(line);
                //分割字串列的方法
                String[] arr = line.split(",");
                // 建立Taipower object
                Taipower t = new Taipower();
                //把int和double轉成String的資料型別，再set到陣列相對位置
                t.setYear(Integer.parseInt(arr[0]));
                t.setMonth(Integer.parseInt(arr[1]));
                t.setStation(arr[2]);
                t.setCapacity(Double.parseDouble(arr[3]));
                t.setPower(Integer.parseInt(arr[4]));
                t.setAverage(Double.parseDouble(arr[5]));

                // 存進資料庫
                createdata(t);
                System.out.println("新增成功: " + line);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Success!!");

    }

    //------------------------------------------------------------------------------------
    @Override
    public List<Taipower> findpower() {
        List<Taipower> list = new ArrayList<>();
        String sql = "select Year,Month,Station,Capacity,Power,"
                + "Average from taipower";
        Connection conn = ConnectionUtil.getConnectionToTaipower();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                Taipower t = new Taipower();
                t.setYear(Integer.parseInt(rs.getString("Year")));
                t.setMonth(Integer.parseInt(rs.getString("Month")));
                t.setStation(rs.getString("Station"));
                t.setCapacity(Double.parseDouble(rs.getString("Capacity")));
                t.setPower(Integer.parseInt(rs.getString("Power")));
                t.setAverage(Double.parseDouble(rs.getString("Average")));
                list.add(t);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            boolean free = ConnectionUtil.freetaipower();
            System.out.println(free ? "釋放資源成功" : "釋放資源失敗");
        }
        return list;
    }

    @Override
    public void savecsv(String csvpath) {
        List<Taipower> list = findpower();
        StringBuffer line = new StringBuffer();
        for (Taipower taipower : list) {
            System.out.println(taipower.getYear());
            System.out.println(taipower.getMonth());
            System.out.println(taipower.getStation());
            System.out.println(taipower.getCapacity());
            System.out.println(taipower.getPower());
            System.out.println(taipower.getAverage());
            System.out.println("===========================");
            line.append(taipower.getYear());
            line.append(",");
            line.append(taipower.getMonth());
            line.append(",");
            line.append(taipower.getStation());
            line.append(",");
            line.append(taipower.getCapacity());
            line.append(",");
            line.append(taipower.getPower());
            line.append(",");
            line.append(taipower.getAverage());
            line.append("\r\n");
        }
        try {
            FileWriter csv = new FileWriter(csvpath);

            csv.write("Year,Month,Station,Capacity,Power,Average" + "\r\n" + line.toString());
            csv.flush();
            csv.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


//		-------------------------------------------------------------------------------------------------------

    @Override
    public void updatedata(Taipower taipower) {

        String sql = "update Taipower set Year=?, Month=?, Capacity=?, Power=?, Average=? "
                + "where Station=?";
        Connection conn = ConnectionUtil.getConnectionToTaipower();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, taipower.getYear());
            pstmt.setInt(2, taipower.getMonth());
            pstmt.setDouble(3, taipower.getCapacity());
            pstmt.setInt(4, taipower.getPower());
            pstmt.setDouble(5, taipower.getAverage());
            pstmt.setString(6, taipower.getStation());

            int updateCount = pstmt.executeUpdate();
            System.out.println("一共修改了" + updateCount + "筆");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            boolean free = ConnectionUtil.freetaipower();
            System.out.println(free ? "釋放資源成功" : "釋放資源失敗");
        }

    }

    //	------------------------------------------------------------------------------------------
    @Override
    public void deletedata(Taipower taipower) {

        String sql = "delete from Taipower where Year=?";
        Connection conn = ConnectionUtil.getConnectionToTaipower();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, taipower.getYear());
            int count = pstmt.executeUpdate();
            if (count > 0) System.out.println("刪除成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            boolean freetest = ConnectionUtil.freetaipower();
            System.out.println(freetest ? "釋放資源成功" : "釋放資源失敗");

        }
    }

    //-------------------------------------------------------------------------------------------------
    @Override //找到特定位置的使用者data
    public Taipower findTestByStation(String Station) {
        String sql = "SELECT * FROM jdbc.taipower where Station=?";
        Connection conn = ConnectionUtil.getConnectionToTaipower();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Station);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            Taipower t = new Taipower();
            t.setYear(rs.getInt("Year"));
            t.setMonth(rs.getInt("Month"));
            t.setStation(rs.getString("Station"));
            t.setCapacity(rs.getDouble("Capacity"));
            t.setPower(rs.getInt("Power"));
            t.setAverage(rs.getDouble("Average"));
            return t;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Taipower();

        } finally {
            boolean freetaipower = ConnectionUtil.freetaipower();
            System.out.println(freetaipower ? "釋放資源成功" : "釋放資源失敗");
        }
    }
}
