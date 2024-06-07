package com.inkhyang.document.application;

import com.inkhyang.document.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleIdCardGeneratorTest {
    private SimpleIdCardGenerator simpleIdCardGenerator = new SimpleIdCardGenerator();
    private DataUtils dataUtils = new DataUtils();

    @Test
    void tes1() {
        //given
        User givenUser = dataUtils.createVasyaTransient();
        String expectedSubName = givenUser.getName().substring(0, SimpleIdCardGenerator.NAME_LENGTH);
        //when
        String generatedIdCard = simpleIdCardGenerator.generate(dataUtils.createVasyaTransient());
        User obtainedUser = simpleIdCardGenerator.identity(generatedIdCard);
        String actualSubName = obtainedUser.getName();
        //then
        assertEquals(expectedSubName, actualSubName);
        assertEquals(givenUser.getAge(), obtainedUser.getAge());
    }

    @Test
    void tes2() {
        //given
        User givenUser = dataUtils.createPetyaTransient();
        String expectedSubName = givenUser.getName().substring(0, SimpleIdCardGenerator.NAME_LENGTH);
        //when
        String generatedIdCard = simpleIdCardGenerator.generate(dataUtils.createPetyaTransient());
        User obtainedUser = simpleIdCardGenerator.identity(generatedIdCard);
        String actualSubName = obtainedUser.getName();
        //then
        assertEquals(expectedSubName, actualSubName);
        assertEquals(givenUser.getAge(), obtainedUser.getAge());
    }

}