package com.horizon.service.impl;

import com.horizon.domain.BookingDetail;
import com.horizon.domain.Room;
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
    public Page<RoomDto> getAll(Pageable pageable) {
        Page<Room> rooms = roomRepository.findAll(pageable);
        return rooms
                .map(roomMapper::toRoomDto);
    }

    @Override
    public Page<RoomDto> getAllIsActivated(Pageable pageable) {
        Page<Room> rooms = roomRepository.findByIsActivatedTrue(pageable);
        return rooms.map(roomMapper::toRoomDto);
    }

    @Override
    public RoomDto create(RoomDto roomDto) {
        Room room = roomMapper.toRoom(roomDto);
        room.setStatus(Room.Status.AVAILABLE);
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
        deletedRoom.setStatus(Room.Status.MAINTENANCE);
        deletedRoom.setIsActivated(false);
        roomRepository.save(deletedRoom);
    }

    @Override
    public Page<RoomDto> getByRoomTypeName(String roomTypeName, Pageable pageable) {
        return roomRepository.findByRoomTypeName(roomTypeName,pageable)
               .map(roomMapper::toRoomDto);
    }

    @Override
    public Page<RoomDto> getByStatus(Room.Status statusDescription, Pageable pageable) {
        return roomRepository
                .findByStatusAndIsActivatedTrue(statusDescription, pageable)
             .map(roomMapper::toRoomDto);
    }

    @Override
    public Page<RoomDto> getIsAvailable( Pageable pageable) {
        return roomRepository.findByStatusAndIsActivatedTrue(Room.Status.AVAILABLE,pageable)
                .map(roomMapper::toRoomDto);
    }

    @Override
    public Page<RoomDto> findAvailable(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return roomRepository.findAvailableRoomsInDateRange(startDate, endDate,pageable)
                .map(roomMapper::toRoomDto);
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
    public Page<RoomDto> search(String roomTypeName,
                                LocalDate checkIn, LocalDate checkOut,
                                int adult, int child, int baby, int roomCount,
                                Pageable pageable) {
        int avgAdult = (int) Math.ceil((double) adult / roomCount);
        int avgChild = (int) Math.ceil((double) child / roomCount);
        int avgBaby = (int) Math.ceil((double) baby / roomCount);
        Page<Room> availableRooms = roomRepository
                .searchAvailableRooms
                        (roomTypeName, checkIn, checkOut, avgAdult, avgChild, avgBaby, pageable);
        if (availableRooms.getTotalElements() < roomCount) {
            throw new IllegalArgumentException("Không đủ số phòng đáp ứng yêu cầu!");
        }
        return availableRooms.map(roomMapper::toRoomDto);
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
                room.setStatus(Room.Status.RESERVED);
            } else if (!occupiedBookingDetails.isEmpty()) {
                room.setStatus(Room.Status.OCCUPIED);
            } else {
                room.setStatus(Room.Status.AVAILABLE);
            }
        });

        roomRepository.saveAll(rooms);

        System.out.println("Room statuses updated.");
    }

}
