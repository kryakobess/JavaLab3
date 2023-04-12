package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    record InputAndResult(String inp, String res){}

    static Stream<InputAndResult> correctNamesForTest(){
        return Stream.of(
                //самое забавное что тесты потеряют актуальность в такой реализации,
                //но делать их иначе мне кажется нелогичным
                new InputAndResult("Иванов Иван Иванович 15.01.1990", "Иванов И.И.   М   33 года"),
                new InputAndResult("Курицын Никита Александрович 18.03.2003", "Курицын Н.А.   М   20 лет"),
                new InputAndResult("Шульман Екатерина Михайловна 19.08.1978", "Шульман Е.М.   Ж   44 года"),
                new InputAndResult("Тестированная Тест Тестова 03.02.2002", "Тестированная Т.Т.   Ж   21 год"),
                new InputAndResult("Ivanov Ivan Ivanovich 15.01.1990", "Ivanov I.I.   Пол не определён   33 года")
        );
    }

    static Stream<InputAndResult> incorrectDataForTest(){
        return Stream.of(
                //самое забавное что тесты потеряют актуальность в такой реализации,
                //но делать их иначе мне кажется нелогичным
                new InputAndResult("Иванов Иван 15.01.1990", "Иванов И.И.   М   33 года"),
                new InputAndResult("18.03.2003", "Курицын Н.А.   М   20 лет"),
                new InputAndResult("Екатерина Михайловна 19.08.1978", "Шульман Е.М.   Ж   44 года"),
                new InputAndResult("", "Тестированная Т.Т.   Ж   21 год"),
                new InputAndResult("Иванов Иван 1990.15.01", "Иванов И.И.   М   33 года"),
                new InputAndResult("Иванов Иван 01.29.1990", "Иванов И.И.   М   33 года")
        );
    }

    @ParameterizedTest
    @MethodSource("correctNamesForTest")
    void getShortRepresentationForCorrectNames(InputAndResult names) {
        //given
        User user = User.FromString(names.inp);

        //when
        var res = user.getShortRepresentation();

        //then
        assertEquals(names.res, res);

    }

    @ParameterizedTest
    @MethodSource("incorrectDataForTest")
    void getUserForStringForIncorrectData(InputAndResult names) {
        Assertions.assertThrows(Exception.class, ()->User.FromString(names.inp));
    }
}