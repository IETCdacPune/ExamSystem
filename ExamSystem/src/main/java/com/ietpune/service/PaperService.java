package com.ietpune.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ietpune.dao.PaperDAO;
import com.ietpune.model.Paper;

@Service
public class PaperService {
 
	@Autowired
	private PaperDAO paperDAO;
	public Paper addPaper(Paper p) {
		// TODO Auto-generated method stub
		return paperDAO.save(p);
	}


}
