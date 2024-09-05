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

    private final List<String> dataBaseNames = List.of("OTERO", "MONET", "IA_TEST", "MAGNETO", "MAGENTO", "MILLIAN",
            "AMARO", "VIVA_BEM", "ANOMALIA", "LUDWICKING", "MAESTRIA", "ALFA", "MEGAZORD", "BRITALO", "JARDINS", "ACCAPULCO",
            "GAUGA", "JAMAL", "WEB_MOV", "ATROVEM", "ULTRA_JOVEM", "NEO_WEB", "SPUTINICK", "FRAM", "GIRA_MUNDO", "CARD_CRED",
            "ITA_TAX", "JINMES", "HOOK2B", "WORLD_DOMINATION", "Sinonyas", "Esxeowos", "Noisvuar", "Kozozosi", "Urnaekoy",
            "Biufovul", "Ashiavui", "Xyozalal", "Gueonzyo", "Ricleten", "Olgotafi", "Dubruern", "Voyvonai", "VOLARE",
            "KRIST_KROST", "MITRAL", "LOVE_MORE", "TENAK", "OLAF_MASTER", "GUINIL", "GEMINIE", "TAM", "DESTINEY",
            "LIFE_WAY", "AIR_JETS", "KMIKAZE", "DROP_THE_BOMB", "SOND_CORE", "MUSIC_STATION", "METRO2033",
            "SUMMER_FEST", "RAT_CAT", "FROID", "TRABUCO_DONOZOR", "MANJERICAO", "AMBROZIL", "TAMAINDO", "IOI201",
            "NEO_MILLENIUM", "VAM_MORE", "TRALES", "MONGOLIANO", "TRIBOLINA", "KRISTAL", "MONGOLIANA", "LOVINARIA",
            "foguete", "bandeira", "abacaxi", "animais", "sinopse", "sintaxe", "noitada", "cabra", "mesas", "porteiras",
            "auditor", "taxistas", "biologo");

    private List<String> tablesNames = List.of("CAVALO", "MAMACO", "VACALO", "TAX", "TABUCO", "GULHOTINA",
            "DEBIT", "PAY_MORE", "FRUITAS", "XAROPE", "RATOS", "USUARIOS", "ENDERECO", "PATHS", "IMAGENS", "COISAS",
            "NEGOCIO", "BUSINESS", "GENERO", "HOMEM_MULHER", "ENDERECO_ELETRONICO", "FABRICAS", "CORES", "FLORES",
            "TIPOS", "VENDAS", "REPORTS", "TEXT_CHOISE", "ELEMENTOS", "GARGANTAS", "TOENS", "CONTA", "IPS", "MASAS",
            "LOG", "SYSTEM_EMAIL", "EMAIL", "INTERNAL", "LIRAS", "SEMENTES", "TERRENO", "GEOMETRIA", "JUSTICA",
            "CALIBRE", "DRONE", "VEICULOS", "LISTAS", "RANK");

    public static DatabaseInputDTO generateDatabase() {
        Faker faker = new Faker(new Locale("pt-BR"));

        List<TableInputDTO> tableInputDTOList = new ArrayList<>();
        int tablesQtd = getRandomNumberUsingNextInt(3, 30);
        for (int i = 0; i < tablesQtd; i++) {
            tableInputDTOList.add(generateTable());
        }

        return DatabaseInputDTO.builder()
                .name(randomDataBaseName().toUpperCase() + "_" + getRandomNumberUsingNextInt(0, 999999))
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
                .schema(randomDataBaseName().toUpperCase() + "_" + getRandomNumberUsingNextInt(0, 999999))
                .name(randomTableName().toUpperCase() + "_" + getRandomNumberUsingNextInt(0, 999999))
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

    public static String randomDataBaseName() {
        SecureRandom rand = new SecureRandom();
        return dataBaseNames.get(rand.nextInt(dataBaseNames.size()));
    }

    public static String randomTableName() {
        SecureRandom rand = new SecureRandom();
        return tablesNames.get(rand.nextInt(tablesNames.size()));
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        SecureRandom random = new SecureRandom();
        return random.nextInt(max - min) + min;
    }
}
