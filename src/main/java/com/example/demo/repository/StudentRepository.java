package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE " +
            "(:studentName IS NULL OR s.studentName LIKE %:studentName%) AND " +
            "(:courseId IS NULL OR s.course.id = :courseId) AND " +
            "(:studentEmail IS NULL OR s.studentEmail LIKE %:studentEmail%) AND " +
            "(:markStart IS NULL OR s.mark >= :markStart) AND " +
            "(:markEnd IS NULL OR s.mark <= :markEnd)")
    List<Student> search(
            @Param("studentName") String studentName,
            @Param("courseId") Long courseId,
            @Param("studentEmail") String studentEmail,
            @Param("markStart") Integer markStart,
            @Param("markEnd") Integer markEnd
    );
}


