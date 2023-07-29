package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Запрашиваем данные у пользователя
        System.out.print("Введите данные (Фамилия Имя Отчество дата рождения номер телефона пол): ");
        String input = scanner.nextLine();

        // Разделяем данные по пробелу
        String[] data = input.split(" ");

        // Проверяем количество данных
        if (data.length != 6) {
            System.out.println("Ошибка: некорректное количество данных!");
            return;
        }

        // Извлекаем данные из массива
        String lastName = data[0];
        String firstName = data[1];
        String patronymic = data[2];
        String dateOfBirth = data[3];
        String phoneNumberStr = data[4];
        String genderStr = data[5];

        // Проверяем и преобразуем данные с помощью методов parse*
        try {
            // Проверка формата даты рождения
            if (!dateOfBirth.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                throw new InvalidDataFormatException("Некорректный формат даты рождения!");
            }

            // Проверка формата номера телефона
            if (!phoneNumberStr.matches("\\d+")) {
                throw new InvalidDataFormatException("Некорректный формат номера телефона!");
            }

            long phoneNumber = Long.parseLong(phoneNumberStr);

            // Проверка формата пола
            if (!genderStr.matches("[fm]")) {
                throw new InvalidDataFormatException("Некорректный формат пола!");
            }

            char gender = genderStr.charAt(0);

            // Создание строки данных
            String dataLine = lastName + " " + firstName + " " + patronymic + " " + dateOfBirth + " " + phoneNumber + " " + gender;

            // Создание файла с данными
            BufferedWriter writer = new BufferedWriter(new FileWriter(lastName + ".txt", true));
            writer.append(dataLine + "\n");
            writer.close();

            System.out.println("Данные успешно сохранены!");
        } catch (InvalidDataFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректный формат номера телефона!");
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static class InvalidDataFormatException extends Exception {
        public InvalidDataFormatException(String message) {
            super(message);
        }
    }
}