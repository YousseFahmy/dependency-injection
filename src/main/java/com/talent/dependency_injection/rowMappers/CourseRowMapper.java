package com.talent.dependency_injection.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.talent.dependency_injection.entities.Course;

public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet resSet, int rowNum) throws SQLException {
        Course course = new Course(
            resSet.getInt("id"),
            resSet.getString("name"),
            resSet.getString("description"),
            resSet.getInt("credit")
        );
        
        return course;
    }
}
