package com.rest.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.mongo.exception.NoteAlreadyExistsException;
import com.rest.mongo.exception.NoteNotFoundException;
import com.rest.mongo.model.Note;
import com.rest.mongo.repository.INoteRepository;

@Service
public class NoteServiceImpl implements INoteService {

	@Autowired
	private INoteRepository noterepository;
	
	
	@Override
	public Note saveNote(Note nobj) throws NoteAlreadyExistsException 
	{
		Optional<Note> optional = this.noterepository.findById(nobj.getId());
		Note addnobj = null;
		
		if(optional.isPresent())
		{
			System.out.println("User Details already exists ...");
			throw new NoteAlreadyExistsException();
		}
		else
		{		
			addnobj = this.noterepository.save(nobj);
		}
		return addnobj;
	}

	@Override
	public Note updateNote(Note nobj, int nid) throws NoteNotFoundException {
		
		
//		get the user details which to be updated by passing the user id
		Optional<Note> noteOptional = this.noterepository.findById(nid);

    	Note nObj = null;
    	Note updatedData = null;
    	
//    	Checking whether user id exists or not
        if(noteOptional.isPresent())
        {
        	System.out.println("Record Exists and ready for Update !!!");
        	
//        	Extracting the user details as user object from optional
        	nObj = noteOptional.get();
 
//        	setting the updated value to setter method by taking from user through getter
           	nObj.setTitle(nobj.getTitle());
        	nObj.setText(nobj.getText());
 
//        	saving the final updated value to db
        	updatedData = this.noterepository.save(nObj);	
        }
        else
        {
        	throw new NoteNotFoundException();
        }
//        returning the updated value to user
        return updatedData;
	}

	@Override
	public Note getNoteById(int nid) throws NoteNotFoundException 
	{
//		get the user details which to be updated by passing the user id
		Optional<Note> noteOptional = this.noterepository.findById(nid);

    	Note nObj = null;
    	
//    	Checking whether user id exists or not
        if(noteOptional.isPresent())
        {
        	System.out.println("Record Exists and ready for Update !!!");
        	
//        	Extracting the user details as user object from optional
        	nObj = noteOptional.get();
        }
        else
        {
        	System.out.println("Note does not exists");
        	throw new NoteNotFoundException();
        }

        return nObj;
		
	}

	@Override
	public List<Note> getAllNotes() {
		return this.noterepository.findAll();
	}

	@Override
	public boolean delNote(int nid) throws NoteNotFoundException {
	
		Optional<Note> noteOptional = this.noterepository.findById(nid);

    	boolean status=false;
    	
//    	Checking whether user id exists or not
        if(noteOptional.isPresent())
        {
        	System.out.println("Record Exists and ready for Delete !!!");
        	
//        	Extracting the user details as user object from optional
        	this.noterepository.delete(noteOptional.get());
        	status=true;
        }
        else
        {
        	System.out.println("Note details does not exits for delete ..");
        	throw new NoteNotFoundException();
        }
        return status;
	}
}
