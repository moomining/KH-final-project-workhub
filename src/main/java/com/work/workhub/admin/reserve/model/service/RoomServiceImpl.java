package com.work.workhub.admin.reserve.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.workhub.admin.reserve.model.dao.RoomMapper;
import com.work.workhub.admin.reserve.model.dto.MeetingRoomDTO;

@Service("roomService")
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomMapper roomMapper;
    
    @Autowired
    public RoomServiceImpl(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    /* 미팅룸 등록 */
    @Override
    public boolean registRoom(MeetingRoomDTO room) throws Exception {
        int result = roomMapper.registRoom(room);

        if(result <= 0) {
            throw new Exception("차량 자산 등록 실패");
        }


        return result > 0 ? true : false;
    }

    /* 전체 미팅룸 조회 */
    @Override
	public List<MeetingRoomDTO> selectAllRoom() {
		return roomMapper.selectRoomList();
	}
    
    /* roomNo로 특정 미팅룸 정보 조회 */
	@Override
	public MeetingRoomDTO selectRoomInfo(int roomNo) {
		return roomMapper.selectRoomInfo(roomNo);
	}
    
    /* 미팅룸 정보 수정 */
    @Override
    public boolean modifyRoom(MeetingRoomDTO room) throws Exception {
    	int result = roomMapper.modifyRoom(room);
		
		if(result <=0) {
			throw new Exception("미팅룸 자산 수정 실패");
		}
		
		return result > 0 ? true : false;
    }

	@Override
	public boolean deleteRoom(MeetingRoomDTO room) throws Exception {
		int result = roomMapper.deleteRoom(room);
		
		if(result <=0) {
			throw new Exception("미팅룸 자산 삭제 실패");
		}
		
		return result > 0 ? true : false;
	}

	

}