package com.malimaquintino.javamongo.util;

import com.github.javafaker.Faker;
import com.malimaquintino.javamongo.dto.ColumnInputDTO;
import com.malimaquintino.javamongo.dto.DatabaseInputDTO;
import com.malimaquintino.javamongo.dto.TableInputDTO;
import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@UtilityClass
public class MockDataGen {

    public static DatabaseInputDTO generateDatabase() {
        Faker faker = new Faker(new Locale("pt-BR"));

        List<TableInputDTO> tableInputDTOList = new ArrayList<>();
        int tablesQtd = getRandomNumberUsingNextInt(3, 30);
        for (int i = 0; i < tablesQtd; i++) {
            tableInputDTOList.add(generateTable());
        }

        return DatabaseInputDTO.builder()
                .name(faker.app().name().toUpperCase() + "_" + getRandomNumberUsingNextInt(0, 999999))
                .indTechnology(randomTechnology())
                .databaseId(faker.idNumber().ssnValid())
                .containerId(faker.idNumber().ssnValid())
                .hostName(faker.internet().ipV4Address())
                .environment(randomEnv())
                .status("ACTIVE")
                .tables(tableInputDTOList)
                .build();
    }

    public static TableInputDTO generateTable() {
        Faker faker = new Faker(new Locale("pt-BR"));

        List<ColumnInputDTO> columnInputDTOList = new ArrayList<>();
        int tablesQtd = getRandomNumberUsingNextInt(11, 101);
        for (int i = 0; i < tablesQtd; i++) {
            columnInputDTOList.add(generateColumn());
        }

        return TableInputDTO.builder()
                .schema(faker.dog().name() + "_" + getRandomNumberUsingNextInt(0, 999999))
                .name(faker.cat().name() + "_" + getRandomNumberUsingNextInt(0, 999999))
                .comment(faker.rickAndMorty().quote())
                .status("ACTIVE")
                .columns(columnInputDTOList)
                .build();
    }

    public static ColumnInputDTO generateColumn() {
        Faker faker = new Faker(new Locale("pt-BR"));
        return ColumnInputDTO.builder()
                .name(faker.food().dish() + "_" + getRandomNumberUsingNextInt(0, 999999))
                .comment(faker.rickAndMorty().quote())
                .status("ACTIVE")
                .build();
    }

    public static String randomEnv() {
        List<String> env = Arrays.asList("PROD", "DEV", "QA");
        SecureRandom rand = new SecureRandom();
        return env.get(rand.nextInt(env.size()));
    }

    public static String randomTechnology() {
        List<String> technology = Arrays.asList("ORACLE", "MYSQL", "REDSHIFT", "MARIADB");
        SecureRandom rand = new SecureRandom();
        return technology.get(rand.nextInt(technology.size()));
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        SecureRandom random = new SecureRandom();
        return random.nextInt(max - min) + min;
    }
}
