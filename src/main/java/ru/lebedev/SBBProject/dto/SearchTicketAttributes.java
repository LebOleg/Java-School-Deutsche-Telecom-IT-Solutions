package ru.lebedev.SBBProject.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SearchTicketAttributes {
    @NotEmpty(message = "Введите название станции")
    private String fromStation;
    @NotEmpty(message = "Введите название станции")
    private String toStation;
    @NotEmpty(message = "Введите время")
    private String fromTime;
    @NotEmpty(message = "Введите время")
    private String toTime;
    @NotEmpty(message = "Введите дату")
    private String date;
}
