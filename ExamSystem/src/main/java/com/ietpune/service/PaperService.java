package com.ietpune.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ietpune.dao.PaperDAO;
import com.ietpune.model.Paper;
import com.ietpune.model.Subject;

@Service
public class PaperService {

	@Autowired
	private PaperDAO paperDAO;

	public Paper addPaper(Paper p) {
		return paperDAO.save(p);
	}

	public List<Paper> getAllPaperOfSubject(Subject sub) {
		return paperDAO.findBySubject(sub);
	}

	public Paper getPaperById(int id) {
		Optional<Paper> optPaper = paperDAO.findById(id);
		if (optPaper.isPresent())
			return optPaper.get();
		return null;
	}

	public Paper getPaper(Integer id) {
		return paperDAO.findById(id).get();// optional return but we want paper so 
		
	}

	public boolean forExsits(int paperCode) {
		// TODO Auto-generated method stub
		
		return paperDAO.existsBypaperCode(paperCode);
	}

}
