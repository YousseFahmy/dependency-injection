package com.talent.dependency_injection.repositories;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.talent.dependency_injection.entities.Course;
import com.talent.dependency_injection.rowMappers.CourseRowMapper;

@Repository
public class CourseRepository {

    private JdbcTemplate jdbcTemplate;

    public CourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addCourse(Course course){
        String sql = "INSERT INTO \"Course\" (id, name, description, credit) VALUES (?,?,?,?)";
        jdbcTemplate.update(
            sql, 
            course.getId(), course.getName(), course.getDescription(), course.getCredit()
        );
    }

    public List<Course> getCourses(){
        String sql = "SELECT * FROM \"Course\"";
        return jdbcTemplate.query(sql, new CourseRowMapper());
    }

    public Course getCourseByID(int courseID){
        String sql = "SELECT * FROM \"Course\" WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new CourseRowMapper(), courseID);
    }

    public void deleteCourseById(int courseId){
        String sql = "DELETE FROM \"Course\" WHERE id=?";
        jdbcTemplate.update(sql, courseId);
    }

    public void updateCourse(Course course){
        String sql = "UPDATE \"Course\" SET name=?, description=?, credit=?, assessment=? WHERE id=?";
        jdbcTemplate.update(sql, 
        course.getName(), course.getDescription(), course.getCredit(), course.getAssessmentId(),
        course.getId());
    }
}
