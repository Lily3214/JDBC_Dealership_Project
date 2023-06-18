package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        // TODO: Implement the logic to add a vehicle
        String query = "insert into vehicles (VIN, make, model, year, SOLD, color, vehicleType, odometer, price) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setInt(4, vehicle.getYear());
            preparedStatement.setBoolean(5, vehicle.isSold());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setString(7, vehicle.getVehicleType());
            preparedStatement.setInt(8, vehicle.getOdometer());
            preparedStatement.set
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range
        String query = "SELECT * FROM Vehicles WHERE price BETWEEN ? AND ?;";
        Vehicle vehicle = null;

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);

            try (ResultSet results = preparedStatement.executeQuery()) {
                List<Vehicle> vehicles = new ArrayList<>();
                while (results.next()) {
                    vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);
                }
                return vehicles;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model
        String query = "SELECT * FROM Vehicles WHERE make = ? AND model = ?;";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicles.add(createVehicleFromResultSet(results));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range
        String query = "SELECT * FROM Vehicles WHERE year BETWEEN ? AND ?;";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, minYear);
            preparedStatement.setInt(2, maxYear);

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicles.add(createVehicleFromResultSet(results));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        String query = "SELECT * FROM Vehicles WHERE color = ?;";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, color);

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicles.add(createVehicleFromResultSet(results));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        String query = "SELECT * FROM Vehicles WHERE odometer BETWEEN ? AND ?;";
        Vehicle vehicle = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, minMileage);
            preparedStatement.setInt(2, maxMileage);

            try (ResultSet results = preparedStatement.executeQuery()) {
                List<Vehicle> vehicles = new ArrayList<>();
                while (results.next()) {
                    vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);
                }
                return vehicles;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
        String query = "SELECT * FROM Vehicles WHERE vehicleType = ?;";
        Vehicle vehicle = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, type);

            try (ResultSet results = preparedStatement.executeQuery()) {
                List<Vehicle> vehicles = new ArrayList<>();
                while (results.next()) {
                    vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);
                }
                return vehicles;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
