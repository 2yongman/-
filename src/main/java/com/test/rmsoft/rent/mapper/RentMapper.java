package com.test.rmsoft.rent.mapper;

import com.test.rmsoft.rent.dto.RentInfo;
import com.test.rmsoft.rent.entity.Rent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentMapper {

    public RentInfo rentsToRentInfo(List<Rent> rents){
        List<RentInfo.RentInfoDto> rentInfoDtos = new ArrayList<>(rents.size());
        for(Rent rent : rents){
            RentInfo.RentInfoDto rentInfoDto = RentInfo.RentInfoDto.builder()
                    .bookName(rent.getBook().getBookName())
                    .email(rent.getMember().getEmail())
                    .rentDate(rent.getRentDate())
                    .rentState(rent.getRentState().getRentStateDescription())
                    .build();
            rentInfoDtos.add(rentInfoDto);
        }

        return RentInfo.builder()
                .rentInfo(rentInfoDtos)
                .build();
    }
}
