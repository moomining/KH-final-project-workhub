package com.work.workhub.member.reserve.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.work.workhub.admin.reserve.model.dto.MeetingRoomDTO;
import com.work.workhub.member.reserve.model.dto.ResMeetingDTO;

@Mapper
public interface MeetingMapper {

	List<MeetingRoomDTO> selectAllLocation();

	List<MeetingRoomDTO> selectRoomList();

	int reserveRoom(ResMeetingDTO resRoom);

	List<ResMeetingDTO> selectAllResMeeting();

	int modifyMeeting(ResMeetingDTO meeting);

	int updateMeetingStatus(ResMeetingDTO meeting);



}
