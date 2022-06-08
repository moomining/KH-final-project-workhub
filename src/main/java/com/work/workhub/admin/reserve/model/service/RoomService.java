package com.work.workhub.admin.reserve.model.service;

import java.util.List;

import com.work.workhub.admin.reserve.model.dto.MeetingRoomDTO;

public interface RoomService {

    boolean registRoom(MeetingRoomDTO room) throws Exception;

    boolean modifyRoom(MeetingRoomDTO room) throws Exception;

	List<MeetingRoomDTO> selectAllRoom();

	MeetingRoomDTO selectRoomInfo(int roomNo);

	boolean deleteRoom(MeetingRoomDTO room) throws Exception;


}