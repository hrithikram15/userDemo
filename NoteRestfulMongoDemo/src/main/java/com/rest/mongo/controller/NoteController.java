package com.rest.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.mongo.exception.NoteAlreadyExistsException;
import com.rest.mongo.exception.NoteNotFoundException;
import com.rest.mongo.model.Note;
import com.rest.mongo.service.INoteService;

@RestController
@RequestMapping("api/v1")
public class NoteController {

	
	@Autowired
	private INoteService NoteService;

//	http://localhost:8080/api/v1/
	
//	http://localhost:8080/api/v1/addNote
//	http://localhost:8080/api/v1/getAllNotes
//	http://localhost:8080/api/v1/delNote
//	http://localhost:8080/api/v1/updateNote
//	http://localhost:8080/api/v1/getNotebyid

	private ResponseEntity<?> responseEntity;
	
	@PostMapping("/addNote")
	public ResponseEntity<?> saveNoteHandler(@RequestBody Note nobj)
	{
		Note newNote=null;
		try {
			newNote = this.NoteService.saveNote(nobj);
		} catch (NoteAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseEntity = new ResponseEntity<>(newNote,HttpStatus.CREATED);
		return responseEntity;
	}
	
	
	@PutMapping("/updateNote/{uid}")
	public ResponseEntity<?> updateNoteHandler(@RequestBody Note uobj,@PathVariable int uid) throws NoteNotFoundException
	{
		Note newNote = this.NoteService.updateNote(uobj,uid);
		responseEntity = new ResponseEntity<>(newNote,HttpStatus.CREATED);
		return responseEntity;
	}
	
	@GetMapping("/getAllNotes")
	public ResponseEntity<?> getAllNotesHandler()
	{
		List<Note> allNotes = this.NoteService.getAllNotes();
		responseEntity = new ResponseEntity<>(allNotes,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/getNotebyid/{uid}")
	public ResponseEntity<?> getNoteByIdHandler(@PathVariable int uid) throws NoteNotFoundException
	{
		Note uObj = this.NoteService.getNoteById(uid);
		responseEntity = new ResponseEntity<>(uObj,HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/delNotebyid/{uid}")
	public ResponseEntity<?> DeleteNoteByIdHandler(@PathVariable int uid) throws NoteNotFoundException
	{
		boolean status = this.NoteService.delNote(uid);
		responseEntity = new ResponseEntity<>("Note Details Deleted ....",HttpStatus.OK);
		return responseEntity;
	}
}
