package com.horizon.service.impl;

import com.horizon.domain.BookingDetail;
import com.horizon.domain.Room;
import com.horizon.domain.status.RoomStatus;
import com.horizon.dto.RoomDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.RoomMapper;
import com.horizon.repository.BookingDetailRepository;
import com.horizon.repository.RoomRepository;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private RoomTypeRepository roomTypeRepository;
    private RoomMapper roomMapper;
    private BookingDetailRepository bookingDetailRepository;

    @Override
    public List<RoomDto> getAll() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(roomMapper::toRoomDto)
                .toList();
    }

    @Override
    public List<RoomDto> getAllIsActivated() {
        List<Room> rooms = roomRepository.findByIsActivatedTrue();
        return rooms.stream()
                .map(roomMapper::toRoomDto)
                .toList();
    }

    @Override
    public RoomDto create(RoomDto roomDto) {
        Room room = roomMapper.toRoom(roomDto);
        room.setStatus(1);
        room.setIsActivated(true);
        Room saveRoom = roomRepository.save(room);
        return roomMapper.toRoomDto(saveRoom);
    }

    @Override
    public RoomDto getById(Integer id) {
        return roomRepository.findByIsActivatedTrueAndId(id)
                .map(roomMapper::toRoomDto)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    @Override
    public List<RoomDto> findByName(String name) {
        List<Room> rooms = roomRepository.findByNameContainingIgnoreCaseAndIsActivatedTrue(name);
        return rooms.stream()
                .map(roomMapper::toRoomDto)
                .toList();
    }

    @Override
    public List<RoomDto> find(String input) {
        try {
            Integer id = Integer.valueOf(input);
            Optional<Room> room = roomRepository.findByIsActivatedTrueAndId(id);
            if (room.isPresent()) {
                return List.of(new RoomDto[]{roomMapper.toRoomDto(room.get())});
            }
        } catch (NumberFormatException e) {
            List<Room> rooms = roomRepository.findByNameContainingIgnoreCaseAndIsActivatedTrue(input);
            return rooms.stream().map(roomMapper::toRoomDto).toList();
        }
        return List.of();
    }

    @Override
    public RoomDto update(Integer roomId, RoomDto roomDto) {
        Room updatedRoom = roomRepository.findByIsActivatedTrueAndId(roomId)
                .map(existingRoom -> {
                    existingRoom = roomMapper.toRoom(roomDto);
                    return roomRepository.save(existingRoom);
                }).orElseThrow(() -> new ResourceNotFoundException("Room not found" + roomId));
        return roomMapper.toRoomDto(updatedRoom);
    }

    @Override
    public void delete(Integer roomId) {
        Room deletedRoom = roomRepository.findByIsActivatedTrueAndId(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found" + roomId));
        deletedRoom.setStatus(3);
        deletedRoom.setIsActivated(false);
        roomRepository.save(deletedRoom);
    }

    @Override
    public List<RoomDto> getByRoomTypeName(String roomTypeName) {
        return roomRepository.findByRoomTypeName(roomTypeName)
                .stream().map(roomMapper::toRoomDto).toList();
    }

    @Override
    public List<RoomDto> getByStatus(String statusDescription) {
        Integer statusCode = RoomStatus.fromDescription(statusDescription);
        return roomRepository.findByStatusAndIsActivatedTrue(statusCode)
                .stream().map(roomMapper::toRoomDto).toList();
    }

    @Override
    public List<RoomDto> getIsAvailable() {
        return roomRepository.findByStatusAndIsActivatedTrue(0)
                .stream().map(roomMapper::toRoomDto).toList();
    }

    @Override
    public List<RoomDto> findAvailable(LocalDate startDate, LocalDate endDate) {
        return roomRepository.findAvailableRoomsInDateRange(startDate, endDate)
                .stream().map(roomMapper::toRoomDto).toList();
    }

    @Override
    public Page<Map<String, Object>> getRoomDetail(Pageable pageable) {
        return roomRepository.getRoomDetail(pageable);
    }

    @Override
    public Map<String, Object> getRoomDetailById(Integer id) {
        return roomRepository.getRoomDetailById(id);
    }

    @Override
    public List<RoomDto> search(String roomTypeName, LocalDate checkIn, LocalDate checkOut, int guestCount, int roomCount) {
        int avgGuestCount = (int) Math.ceil((double) guestCount / roomCount);
        List<Room> availableRooms = roomRepository.searchAvailableRooms(roomTypeName, checkIn, checkOut, avgGuestCount);
        if (availableRooms.size() < roomCount) {
            throw new IllegalArgumentException("Không đủ số phòng đáp ứng yêu cầu!");
        }
        return availableRooms.stream().map(roomMapper::toRoomDto).toList();
    }


    @Override
    @Scheduled(fixedRate = 60000)
    public void updateRoomStatuses() {
        LocalDate currentDate = LocalDate.now();
        List<Room> rooms = roomRepository.findAll();

        rooms.forEach(room -> {
            List<BookingDetail> reservedBookingDetails = bookingDetailRepository.findBookingDetailReserved(room.getId(), currentDate);
            List<BookingDetail> occupiedBookingDetails = bookingDetailRepository.findBookingDetailOccupied(room.getId(), currentDate);

            if (!reservedBookingDetails.isEmpty()) {
                room.setStatus(2);
            } else if (!occupiedBookingDetails.isEmpty()) {
                room.setStatus(1);
            } else {
                room.setStatus(0);
            }
        });

        roomRepository.saveAll(rooms);

        System.out.println("Room statuses updated.");
    }

}
