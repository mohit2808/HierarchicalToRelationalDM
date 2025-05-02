package com.db.hierarchicaltorelationaldm.Controller;

import com.db.hierarchicaltorelationaldm.XQueryToSQLTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/xquery")
public class QueryController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    // 1. INSERT operation
    @PostMapping("/insert")
    public ResponseEntity<?> insertData(@RequestBody String xquery) {
        try {
            String sql = XQueryToSQLTranslator.translateInsertQuery(xquery);
            int rowsAffected = jdbcTemplate.update(sql);
            return ResponseEntity.ok("Insert successful. Rows affected: " + rowsAffected);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error parsing or executing insert query: " + e.getMessage());
        }
    }

    // 2. READ Operation
    @PostMapping("/read")
    public ResponseEntity<?> readData(@RequestBody String xquery) {
        try {
            String sql = XQueryToSQLTranslator.translateReadQuery(xquery);
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error parsing or executing query: " + e.getMessage());
        }
    }

    // 3. UPDATE Operation
    @PostMapping("/update")
    public ResponseEntity<?> updateData(@RequestBody String xquery) {
        try {
            String sql = XQueryToSQLTranslator.translateUpdateQuery(xquery);
            int rowsAffected = jdbcTemplate.update(sql);
            return ResponseEntity.ok("Rows updated: " + rowsAffected);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error parsing or executing update: " + e.getMessage());
        }
    }

    // 4. DELETE Operation
    @PostMapping("/delete")
    public ResponseEntity<?> deleteData(@RequestBody String xquery) {
        try {
            String sql = XQueryToSQLTranslator.translateDeleteQuery(xquery);
            int rowsAffected = jdbcTemplate.update(sql);
            return ResponseEntity.ok("Rows deleted: " + rowsAffected);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error parsing or executing delete: " + e.getMessage());
        }
    }
}