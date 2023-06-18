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
        String query = "insert into lease_contracts (VIN, lease_start, lease_end, monthly_payment) values (?,?,?,?);";
        int keyReturned = 0;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, leaseContract.getVin());
            preparedStatement.setDate(2, java.sql.Date.valueOf(leaseContract.getLeaseStart()));
            preparedStatement.setDate(3, java.sql.Date.valueOf(leaseContract.getLeaseEnd()));
            preparedStatement.setDouble(4, leaseContract.getMonthlyPayment());

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
