package com.work.workhub.admin.reserve.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.work.workhub.admin.reserve.model.dto.MeetingRoomDTO;

@Mapper
public interface RoomMapper {

    List<MeetingRoomDTO> selectRoomList();

	int registRoom(MeetingRoomDTO room);

	MeetingRoomDTO selectRoomInfo(int roomNo);

	int modifyRoom(MeetingRoomDTO room);

	int deleteRoom(MeetingRoomDTO room);

}