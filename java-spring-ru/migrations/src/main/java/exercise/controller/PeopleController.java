package exercise.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(path = "")
    public List getAllPersones() {
        String query = "SELECT * FROM person";
        return jdbc.query(query, new BeanPropertyRowMapper(Person.class));
    }

    @GetMapping(path = "/{id}")
    public Object getPersone(@PathVariable int id) {
        String query = "SELECT * FROM person WHERE ID = ?";
        try {
            var result = jdbc.queryForObject(
                    query,
                    new Object[]{id},
                    new BeanPropertyRowMapper(Person.class));
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    // END
}
