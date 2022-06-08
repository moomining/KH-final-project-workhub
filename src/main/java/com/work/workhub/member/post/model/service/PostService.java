package com.work.workhub.member.post.model.service;

import java.util.List;

import com.work.workhub.member.post.model.dto.CategoryDTO;
import com.work.workhub.member.post.model.dto.PostDTO;
import com.work.workhub.member.post.model.dto.ReplyDTO;

public interface PostService {

	List<PostDTO> findAllPost();

	List<PostDTO> findAllNotice();
	
	List<CategoryDTO> findAllCategory();

	PostDTO findPostByNo(Integer postNo);
	
	boolean writePost(PostDTO post) throws Exception;

	int deletePost(Integer postNo);

	List<PostDTO> showNotice();

	
}
