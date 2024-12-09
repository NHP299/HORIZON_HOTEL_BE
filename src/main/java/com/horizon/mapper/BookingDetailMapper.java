package com.horizon.mapper;

import com.horizon.domain.BookingDetail;
import com.horizon.dto.BookingDetailDto;

public interface BookingDetailMapper {

    BookingDetailDto toBookingDetailDto(BookingDetail bookingDetail);

    BookingDetail toBookingDetail(BookingDetailDto bookingDetailDto);
}
