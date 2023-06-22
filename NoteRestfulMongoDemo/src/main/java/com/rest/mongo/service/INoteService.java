package com.rest.mongo.service;

import java.util.List;

import com.rest.mongo.exception.NoteAlreadyExistsException;
import com.rest.mongo.exception.NoteNotFoundException;
import com.rest.mongo.model.Note;

public interface INoteService 
{
	public Note saveNote(Note nobj) throws NoteAlreadyExistsException;
	
	public Note updateNote(Note nobj,int nid) throws NoteNotFoundException;
	
	public Note getNoteById(int nid) throws NoteNotFoundException;
	
	public List<Note> getAllNotes();
	
	public boolean delNote(int nid)throws NoteNotFoundException;
}