package de.ait.mp.repositories.impl;

import de.ait.mp.models.Venue;
import de.ait.mp.repositories.VenueMyRepository;
import de.ait.mp.repositories.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class VenueRepositoryImpl implements VenueMyRepository {

    private final JdbcTemplate jdbcTemplate;
    //language=sql
    private static final String SELECT_BY_NAME_SQL = "select * from VENUE where venue_name = ? limit 1";

    private static final RowMapper<Venue> DEFINITION_ROW_MAPPER = (resultSet, rowNum) -> Venue.builder()
            .id(resultSet.getLong("id"))
            .venueName(resultSet.getString("venue_name"))
            .address(resultSet.getString("address"))
            .square(resultSet.getDouble("square"))
            .build();


    @Override
    public Venue existsByName(String venueName) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_NAME_SQL, DEFINITION_ROW_MAPPER, venueName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


}
