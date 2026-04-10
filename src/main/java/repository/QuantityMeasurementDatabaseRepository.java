package repository;


import entity.QuantityMeasurementEntity;
import exception.DatabaseException;
import util.ConnectionPool;

import java.sql.*;
import java.util.*;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    @Override
    public void save(QuantityMeasurementEntity entity) {

        String sql = "INSERT INTO measurements(value, unit, operation) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, entity.getValue());
            stmt.setString(2, entity.getUnit());
            stmt.setString(3, entity.getOperation());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error saving data", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();

        String sql = "SELECT * FROM measurements";

        try (Connection conn = ConnectionPool.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                list.add(new QuantityMeasurementEntity(
                        rs.getDouble("value"),
                        rs.getString("unit"),
                        rs.getString("operation")
                ));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching data", e);
        }

        return list;
    }

    @Override
    public void deleteAll() {

        String sql = "DELETE FROM measurements";

        try (Connection conn = ConnectionPool.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            throw new DatabaseException("Error deleting data", e);
        }
    }

    @Override
    public int getTotalCount() {

        String sql = "SELECT COUNT(*) FROM measurements";

        try (Connection conn = ConnectionPool.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            throw new DatabaseException("Error counting data", e);
        }

        return 0;
    }
}