package com.karim.exercise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class Exercise1Test {
    private final Exercise1 underTest = new Exercise1();

    @ParameterizedTest
    @CsvSource({
            "90, A", "91, A", "100, A",
            "80, B", "81, B", "89, B",
            "70, C", "71, C", "79, C",
            "60, D", "61, D", "69, D",
            "50, E", "51, E", "59, E",
            "49, F", "0, F", "1, F",
    })
    void canGetTheCorrectGrade(int grade, String expectedGrade) {
        // when
        String actual = underTest.getGrade(grade);
        // then
        assertThat(actual).isEqualTo(expectedGrade);
    }

    @ParameterizedTest
    @CsvSource({
            "Karim, 2",
            "Mark , 1",
            "JFK, 0",
            "'', 0"
    })
    void canCountVowels(String name, int expected) {
        // when
        int actual = underTest.countVowels(name);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canCountVowelsWhenNull() {
        // given
        String name = null;
        // when
        int actual = underTest.countVowels(name);
        // then
        int expected = 0;
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "-1 ",
        "101",
        "150",
        "-100"
    })
    void wilLThrowWhenInvalidGrade(int grade){
        assertThatThrownBy(() -> underTest.getGrade(grade))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Score must be between 0 and 100");

    }
    @ParameterizedTest
    @CsvSource({
            "S1234, true",
            "s1234, false",
            "C1234, false",
            "S12345, false",
            "S123, false"
    })
    void canValidateStudentId(String studentId, boolean expected) {
        // when
        boolean actual = underTest.isValidStudentId(studentId);
        // then
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void canValidateStudentIdWhenNull() {
        // given
        String studentId = null;
        // when
        boolean actual = underTest.isValidStudentId(studentId);
        // then
        boolean expected = false;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canCalculateAverage() {
        // given
        var scores = List.of(1,2,3,4,5);
        // when
        double actual = underTest.calculateAverage(scores);
        // then
        int expected = 3;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canCalculateAverageWhenNull() {
        // given
        List<Integer> scores = null;
        // when
        double actual = underTest.calculateAverage(scores);
        // then
        var expected = 0.0;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canCalculateAverageWhenEmptyList() {
        // given
        List<Integer> scores = List.of();
        // when
        double actual = underTest.calculateAverage(scores);
        // then
        var expected = 0.0;
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "Amigos Code, acode",
            "Amigos Code Academy, acode ",
            "Amigos, ''",
    })
    void canGenerateUsername(String fullName, String expected) {
        // when
        String actual = underTest.generateUsername(fullName);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canGenerateUsernameWhenNull() {
        // given
        String fullName = null;
        // when
        String actual = underTest.generateUsername(fullName);
        // then
        String expected = "";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canGetTopStudents() {
        // given
        var students = List.of(
                new Student("Alex", 10),
                new Student("Jamila", 80),
                new Student("Anna", 7),
                new Student("George", 18),
                new Student("Sally", 54)
        );

        var threshHold = 50;
        // when
        var actual = underTest.getTopStudents(students, threshHold);
        // then
        var expected = List.of(
                new Student("Jamila", 80),
                new Student("Sally", 54)
        );
        assertThat(actual).isEqualTo(expected);

    }
    @Test
    void willReturnEmptyListWhenStudentsAreNull() {
        // given
        List<Student> students = null;
        var threshHold = 50;
        // when
        var actual = underTest.getTopStudents(students, threshHold);
        // then
        var expected = List.of();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideStudentLists")
    void canCheckWhetherListHasDuplicateNames(List<Student> students, boolean expected) {
        // when
        var actual = underTest.hasDuplicateNames(students);
        // then
        assertThat(actual).isEqualTo(expected);
    }
    static Stream<Arguments> provideStudentLists(){
        return Stream.of(
                Arguments.of(List.of(
                        new Student("Nelson", 50),
                        new Student("Ian", 50),
                        new Student("Nelson", 50)), true),

                Arguments.of(
                        List.of(
                                new Student("Nelson", 50),
                                new Student("Ian", 50)), false),

                Arguments.of(List.of(), false),
                Arguments.of(null, false)
        );

    }

    @ParameterizedTest
    @MethodSource("provideCourseLists")
    void canReverseCourses(List<String> courses, List<String> expected) {
        // when
        List<String> actual = underTest.reverseCourses(courses);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideCourseLists(){
        return Stream.of(
                Arguments.of(List.of("Physics", "Math", "Chemistry"),List.of("Chemistry", "Math", "Physics")),
                Arguments.of(List.of(), List.of()),
                Arguments.of(null, List.of())
        );


    }

    @ParameterizedTest
    @CsvSource({
            "50, true",
            "51, true",
            "99, true",
            "49, false",
            "1, false",
            "0, false",
    })
    void canSeparatePassingFromFailing(int score, boolean expected) {
        // when
        boolean actual = underTest.hasPassed(score);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "true, Star Student",
            "false, Needs Improvement "
    })
    void canAssignBadge(boolean isHelpful, String expected) {
        // when
        String actual = underTest.assignBadge(isHelpful);
        // then
        assertThat(actual).isEqualTo(expected);
    }
}