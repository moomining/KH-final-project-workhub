package com.work.workhub.member.employ.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class ImgDTO {
   
	   private int imageId;
	   private int porestNo;
	   private String imagePath;
	   private String imageName;
	   private String imageRename;
	   private String fileType;
	   private String thumbnailPath;
	   private String imageStatus;
	 
	   private List<RestDTO> porestList;
  
}