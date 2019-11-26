package com.hvcg.api.task_management.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * RowMapper implement to use for Spring JDBC
 * 
 * @author JY
 *
 */

public class ListStaffIdRowMapper implements RowMapper<Integer> {

	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Integer result = rs.getInt("staff_id");
		return result;
	}



}
