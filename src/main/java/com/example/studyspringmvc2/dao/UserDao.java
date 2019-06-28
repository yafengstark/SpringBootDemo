package com.example.studyspringmvc2.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public void getResult(){
        String sql = "SELECT st_astext(geom) FROM pgr_dijkstra('\n" +
                "\n" +
                "SELECT gid AS id,                   \n" +
                "\n" +
                "source::integer,                       \n" +
                "\n" +
                "target::integer,                      \n" +
                "\n" +
                "length::double precision AS cost\n" +
                "\n" +
                "FROM gis_osm_roads_free_1',\n" +
                "\n" +
                "30, 60, false, false) as di\n" +
                "\n" +
                "join gis_osm_roads_free_1 pt\n" +
                "\n" +
                "on di.id2 = pt.gid;";
        List<String> stringList = new ArrayList<>();

        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                System.out.println("-------------------");
                stringList.add(resultSet.getString("st_astext"));
            }
        });

        System.out.print(stringList.get(0));
    }


}
