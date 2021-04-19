package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TownDao {
    @Autowired
    private NamedParameterJdbcOperations jdbc;
    public List<Town> getTowns() {

        return jdbc
                .query("select * from Town, Country where Town.CountryName=Country.CountryName",
                        (ResultSet rs, int rowNum) -> {
                            Country country = new Country();
                            country.setCountryName(rs.getString("CountryName"));

                            Town town = new Town();
                            town.setName(rs.getString("TownName"));
                            town.setCountry(country);

                            return town;
                        }
                );
    }
    public boolean create(Town town) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                town);

        return jdbc
                .update("insert into Town (CountryName, TownName) values (:CountryName, :Name)",
                        params) == 1;
    }

    public boolean delete(String townName, String countryName) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("TownName",townName);
        params.addValue("CountryName",countryName);

        return jdbc.update("delete from Town where TownName=:TownName and CountryName=:CountryName", params) == 1;
    }

    public Town getTown(String townName, String countryName) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("TownName",townName);
        params.addValue("CountryName",countryName);

        return jdbc.queryForObject("select * from Town where Town.TownName=:TownName and Town.CountryName=:CountryName", params,
                new RowMapper<Town>() {

                    public Town mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        Country country = new Country();
                        country.setCountryName(rs.getString("CountryName"));

                        Town town = new Town();
                        town.setName(rs.getString("TownName"));
                        town.setCountry(country);

                        return town;
                    }
                });
    }
}
