//package com.example.springmybatis.controller;
//
//
//import com.example.springmybatis.dao.PersonDAO;
//import com.example.springmybatis.entity.Person;
//import org.apache.ibatis.session.SqlSession;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class PersonController {
//
//    private final PersonDAO personDAO;
//    private final SqlSessionTemplate sqlSessionTemplate;
//
//
//
//
//    public PersonController(PersonDAO personDAO,SqlSessionTemplate sqlSessionTemplate) {
//        this.personDAO = personDAO;
//        this.sqlSessionTemplate = sqlSessionTemplate;
//    }
//
//
//
//    /*SqlSession Template을 이용한 방법*/
//    @GetMapping("/sqlsessiontemplate")
//    public int test2() {
//        String namespace = "com.example.springmybatis.dao.PersonDAO.insertPerson";
//        Map<String, Object> map = new HashMap<>();
//        map.put("age", 33);
//        map.put("name", "ss");
//        return sqlSessionTemplate.insert(namespace, map);
//    }
//
//
//
//    /*DAO를 이용한 방법*/
//    /*Select All Person*/
//    @RequestMapping("/persons")
//    public List<Person> selectAllPerson() throws SQLException {
//        List<Person> personList = personDAO.listPerson();
//        return personList;
//    }
//
//    /*Insert Person*/
//    @PostMapping("/map/persons")
//    public int mapInsertPerson() {
//        Map<String, Object> param = new HashMap<>();
//        param.put("age", 55);
//        param.put("name", "천재");
//        return personDAO.mapInsertPerson(param);
//    }
//
////    @PostMapping("/primitive/persons")
////    public int primitiveInsertPerson() {
////        return personDAO.primitiveInsertPerson(20,"abcd");
////    }
//
//    /*Delete Person*/
//    @DeleteMapping("/persons")
//    public int deletePerson() {
//        return personDAO.deletePerson(1L);
//    }
//
//    /*Update Person*/
//    @PutMapping("/persons")
//    public int updatePerson() {
//        return personDAO.updatePerson(3L, 999,"test");
//    }
//
//    @GetMapping("/test")
//    public List<Person> selectAll() {
//        return personDAO.selectAll();
//    }
//
//
//
//
//}
//
