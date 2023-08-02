package com.gd.reservationservices.infrastructure.performance.business;

import com.gd.reservationservices.domain.performance.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PerformancePerformanceJdbcRepository implements com.gd.reservationservices.domain.performance.repository.PerformanceJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveAll(List<Seat> seats) {
        String sql = "INSERT INTO reservation.seat (created_at, updated_at, is_reserved, location, number, performance_id)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Seat seat = seats.get(i);

                Date utilDate = new Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                ps.setDate(1, sqlDate);
                ps.setDate(2, sqlDate);
                ps.setBoolean(3, seat.getIsReserved());
                ps.setString(4, seat.getLocation());
                ps.setInt(5, seat.getNumber());
                ps.setLong(6, seat.getPerformanceId());

            }

            @Override
            public int getBatchSize() {
                return seats.size();
            }
        });
    }
}
