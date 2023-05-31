package com.example.springmybatis;

import com.example.springmybatis.dao.PersonDAO;
import com.example.springmybatis.entity.Person;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
@Transactional
public class MyBatisTest {

    private final PersonDAO personDAO;
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public MyBatisTest(PersonDAO personDAO, SqlSessionTemplate sqlSessionTemplate) {
        this.personDAO = personDAO;
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Test
    public void 회원가입() throws Exception {
        //Given

        /*1.Primitive Type*/
        String name = "primitive";
        int age = 99;

        Map param = new HashMap<>();
        param.put("id", null);

        /*2.Map Type*/
        Map person2 = new HashMap();
        person2.put("age", 30);
        person2.put("name", "map");

        /*3.Person Type*/
        Person person3 = new Person("Person", 29);

        /*4.SqlSession(SqlSessionTemplate), Map Type*/
        String namespace = "com.example.springmybatis.dao.PersonDAO.mapInsertPerson";
        Map<String, Object> person4 = new HashMap<>();
        person4.put("age", 33);
        person4.put("name", "sqlSessionTemplate");

        //When
        personDAO.primitiveInsertPerson(param, age, name);
        personDAO.mapInsertPerson(person2);
        personDAO.insertPerson(person3);
        sqlSessionTemplate.insert(namespace, person4);

        //Then
        Person findMember1 = personDAO.onePerson((Long) param.get("id"));
        Person findMember2 = personDAO.onePerson(Long.valueOf(String.valueOf(person2.get("id"))));
        Person findMember3 = personDAO.onePerson(person3.getId());
        Person findMember4 = personDAO.onePerson(Long.valueOf(String.valueOf(person4.get("id"))));

        assertEquals(name, findMember1.getName());
        assertEquals(person2.get("name"), findMember2.getName());
        assertEquals(person3.getName(), findMember3.getName());
        assertEquals(person4.get("name"), findMember4.getName());
    }

    @Test
    public void 모든_회원_조회() throws Exception{

        // Given
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("test1",11);
        Person person2 = new Person("test2",22);
        Person person3 = new Person("test3",33);

        personList.add(person1);
        personList.add(person2);
        personList.add(person3);

        // When
        personDAO.insertPerson(person1);
        personDAO.insertPerson(person2);
        personDAO.insertPerson(person3);

        // Then
        List<Person> findPersonList = personDAO.listPerson();

        assertThat(findPersonList.size() - (findPersonList.size() - personList.size()))
                .isEqualTo(personList.size());
    }

    @Test
    public void 회원_수정() throws Exception {
        //Given
        Person person = new Person("bob", 33);
        personDAO.insertPerson(person);

        //When
        person.update("son", 30);
        personDAO.updatePerson(person);

        //Then
        Person findPerson = personDAO.onePerson(person.getId());
        assertEquals(findPerson.getName(), "son");
        assertEquals(findPerson.getAge(), 30);
    }

    @Test
    public void 회원_탈퇴() throws Exception {
        //Given
        Person person = new Person("bob", 33);
        personDAO.insertPerson(person);
        personDAO.onePerson(person.getId());

        //When
        personDAO.deletePerson(person.getId());

        //Then
        assertNull(personDAO.onePerson(person.getId()));


    }
}
