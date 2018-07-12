package com.viking.spring.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.viking.spring.bo.StudentBO;

public interface IStudentReponsitory extends JpaRepository<StudentBO, Integer> {
	@Query(value = "SELECT * FROM student s WHERE s.name LIKE :name LIMIT :first, :last", nativeQuery = true)
	public List<StudentBO> search(@Param("name") String name, @Param("first") Integer first,
			@Param("last") Integer last);
}
