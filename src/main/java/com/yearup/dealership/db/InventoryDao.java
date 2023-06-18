package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.*;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        String query = "insert into inventory (dealership_id, VIN) values (?,?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setInt(1, dealershipId);
            preparedStatement.setString(2, vin);

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows affected!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory
        String query = "DELETE FROM inventory WHERE VIN = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, vin);

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows affected!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

