package com.malimaquintino.javamongo.models;


import com.malimaquintino.javamongo.dto.ColumnInputDTO;
import com.malimaquintino.javamongo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Column {
    private String qualifiedName;
    private String name;
    private String comment;
    private Status status;

    public static Column parseFromDto(ColumnInputDTO columnInputDTO, String tableQualifiedname) {
        return Column.builder()
                .qualifiedName(generateQualifiedname(tableQualifiedname, columnInputDTO.getName()))
                .name(columnInputDTO.getName())
                .comment(columnInputDTO.getComment())
                .status(Status.valueOf(columnInputDTO.getStatus()))
                .build();
    }

    public static String generateQualifiedname(String tableQualifiedname, String name) {
        return tableQualifiedname + "-" + name.toUpperCase();
    }
}
