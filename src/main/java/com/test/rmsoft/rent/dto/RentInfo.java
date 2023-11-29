package com.test.rmsoft.rent.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class RentInfo {

    private List<RentInfoDto> rentInfo;

    @Builder
    public RentInfo(List<RentInfoDto> rentInfo){
        this.rentInfo = rentInfo;
    }

    @Getter
    public static class RentInfoDto {

        private String bookName;

        private String email;

        private LocalDate rentDate;

        private String rentState;

        @Builder
        public RentInfoDto(String bookName, String email, LocalDate rentDate, String rentState) {
            this.bookName = bookName;
            this.email = email;
            this.rentDate = rentDate;
            this.rentState = rentState;
        }

    }

}
