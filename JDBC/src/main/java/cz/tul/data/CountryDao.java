package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CountryDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Country country) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("CountryName", country.getCountryName());

        return jdbc.update("insert into Country (CountryName) values (:CountryName)", params) == 1;
    }

    public boolean exists(String countryName) {
        return jdbc.queryForObject("select count(*) from Country where CountryName=:CountryName",
                new MapSqlParameterSource("CountryName", countryName), Integer.class) > 0;
    }

    public List<Country> getAllCountries() {
        return jdbc.query("select * from Country", BeanPropertyRowMapper.newInstance(Country.class));
    }

    public boolean delete(String countryName) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("CountryName",countryName);

        return jdbc.update("delete from Country where CountryName=:CountryName", params) == 1;
    }

    public boolean delete(Country country) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("CountryName",country.getCountryName());

        return jdbc.update("delete from Country where CountryName=:CountryName", params) == 1;
    }

    public void deleteCountries() {
        jdbc.getJdbcOperations().execute("DELETE FROM Country");
        jdbc.getJdbcOperations().execute("DELETE FROM Town");
    }
}
