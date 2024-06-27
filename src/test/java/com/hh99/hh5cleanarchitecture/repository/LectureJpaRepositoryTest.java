package com.hh99.hh5cleanarchitecture.repository;

import com.hh99.hh5cleanarchitecture.domain.entity.Lecture;
import com.hh99.hh5cleanarchitecture.infra.jpa.LectureJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LectureJpaRepositoryTest {
    @Autowired
    LectureJpaRepository lectureJpaRepository;
    private Lecture lecture = Lecture.builder()
            .id(1l)
            .name("엘런의 특강")
            .build();

    @Test
    public void success_save(){
        //given
        Lecture result = lectureJpaRepository.save(lecture);
        //then
        assert lecture.getName().equals(result.getName());
    }

    @Test
    public void success_find() {
        //given
//        Lecture savedLecture = lectureJpaRepository.save(lecture);
        //when
        Lecture result = lectureJpaRepository.findById(lecture.getId()).get();
        //then
        assert lecture.getName().equals(result.getName());
        assert lecture.getId() == result.getId();
    }

    @Test
    public void success_findByName() {
        //given
//        lectureJpaRepository.save(lecture);
        //when
        Lecture result = lectureJpaRepository.findLectureByName(lecture.getName());
        //then
        System.out.println(result.toString());
        assertEquals(1l, result.getId());
    }

}