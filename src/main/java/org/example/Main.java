package org.example;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите ФИО и дату рождения:");
            String data = in.nextLine();
            User user = User.FromString(data);
            System.out.println(user.getShortRepresentation());
        } catch (DateTimeParseException exception){
            System.out.println("Дата рождения должна быть в формате дд.мм.гггг");
        } catch (IllegalArgumentException e){
            System.out.println(
                    """
                    Неверный формат введённых данных.
                    Данные должны быть в формате:
                    Фамилия Имя Отчество дд.мм.гггг
                    """);
        }
    }
}