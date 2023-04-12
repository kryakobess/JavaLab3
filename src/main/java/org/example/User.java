package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public record User(
        String surname,
        String name,
        String patronymic,
        LocalDate birth
){
    private String getGenderByName(){
        if (patronymic.endsWith("на") || patronymic.endsWith("ва") || patronymic.endsWith("зы")) return "Ж";
        else if (patronymic.endsWith("ич") || patronymic.endsWith("лы")) return "М";
        else return "Пол не определён";
    }

    private String getAge(){
        int age = Period.between(birth, LocalDate.now()).getYears();
        int ageLastNum = age%10;
        boolean inHundredTenths = age%100 >= 11 && age%100 <= 19;
        String suffix = "";
        if (ageLastNum == 1) suffix = "год";
        else if (ageLastNum >= 2 && ageLastNum <= 4) suffix = "года";
        else if (ageLastNum >= 5 || ageLastNum == 0) suffix = "лет";
        if (inHundredTenths) suffix = "лет";
        return age + " " + suffix;
    }
    public String getShortRepresentation(){
        return surname + " " + name.charAt(0) + "." + patronymic.charAt(0) + ".   " + getGenderByName() + "   " + getAge();
    }

    public static User FromString(String userData) {
        String[] data = userData.split(" ");
        if (data.length != 4){
            throw new IllegalArgumentException("Wrong format!");
        }
        String name = data[1];
        String surname = data[0];
        String patronymic = data[2];
        LocalDate formattedBirthDay = LocalDate.parse(data[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        return new User(surname, name, patronymic, formattedBirthDay);
    }
}