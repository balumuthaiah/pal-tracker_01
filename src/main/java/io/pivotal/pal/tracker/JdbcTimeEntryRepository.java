package io.pivotal.pal.tracker;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class JdbcTimeEntryRepository implements TimeEntryRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTimeEntryRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public TimeEntry create(TimeEntry timeEntry){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO time_entries (project_id, user_id, date, hours)" +
                                "VALUES (?, ?, ?, ?)", RETURN_GENERATED_KEYS);
                ps.setLong(1, timeEntry.getProjectId());
                ps.setLong(2, timeEntry.getUserId());
                ps.setDate(3, Date.valueOf(timeEntry.getDate()));
                ps.setLong(4, timeEntry.getHours());

                return ps;
            }
        }, keyHolder);

        return find(keyHolder.getKey().longValue());
    }

    public TimeEntry find(long id){
        return jdbcTemplate.query("SELECT * FROM time_entries WHERE id = ?",
                new Object[]{id},
                rs -> {
                    if (rs.next()) {
                        return new TimeEntry(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getDate(4).toLocalDate(), rs.getInt(5));
                    }
                    return null;
                });
    }

    public List<TimeEntry> list(){
        return jdbcTemplate.query("SELECT * FROM time_entries",
                (rs, rowNum) -> new TimeEntry(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getInt(5)
                ));
    }

    public TimeEntry update(long id, TimeEntry timeEntry){
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE time_entries SET project_id = ?, user_id = ?, date = ?, hours = ? WHERE id = ?");
            ps.setLong(1, timeEntry.getProjectId());
            ps.setLong(2, timeEntry.getUserId());
            ps.setDate(3, Date.valueOf(timeEntry.getDate()));
            ps.setLong(4, timeEntry.getHours());
            ps.setLong(5, id);

            return ps;
        });

        return find(id);
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM time_entries WHERE id = ?", id);
    }

}
