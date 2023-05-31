package com.example.springmybatis.dao;

import com.example.springmybatis.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonDAO {
    List<Person> listPerson();

    Person onePerson(Long id);
    /*객체*/
    void insertPerson(Person person);
    /*원시타입*/
    int primitiveInsertPerson(Map<String, Long> param, int age, String name);
    /*map*/
    int mapInsertPerson(Map<String, Object> param);
    int deletePerson(Long id);
    int updatePerson(Person person);

    @Select("select * from person")
    List<Person> selectAll();

}

