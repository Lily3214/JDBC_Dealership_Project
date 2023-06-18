package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.*;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int addLeaseContract(LeaseContract leaseContract) {
        // TODO: Implement the logic to add a lease contract
        String query = "insert into lease_contracts (VIN, sale_date, price) values (?,?,?);";
        int keyReturned = 0;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, salesContract.getVin());
            preparedStatement.setDate(2, java.sql.Date.valueOf(salesContract.getSaleDate()));;
            preparedStatement.setDouble(3, salesContract.getPrice());

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows affected!");

            try (ResultSet keys = preparedStatement.getGeneratedKeys();) {
                while (keys.next()) {
                    keyReturned = keys.getInt(1);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return keyReturned;
    }
}
