package com.gd.reservationservices.infrastructure.performance.business;

import com.gd.reservationservices.domain.performance.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PerformancePerformanceJdbcRepository implements com.gd.reservationservices.domain.performance.repository.PerformanceJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveAll(List<Seat> seats) {
        String sql = "INSERT INTO seat (created_at, updated_at, is_reserved, location, number, performance_id)"
                + "VALUES (now(), now(), ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Seat seat = seats.get(i);
                ps.setBoolean(1, seat.getIsReserved());
                ps.setString(2, seat.getLocation());
                ps.setInt(3, seat.getNumber());
                ps.setLong(4, seat.getPerformanceId());

            }

            @Override
            public int getBatchSize() {
                return seats.size();
            }
        });
    }
}
