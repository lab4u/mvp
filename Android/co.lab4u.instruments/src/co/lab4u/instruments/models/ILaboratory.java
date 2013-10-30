package co.lab4u.instruments.models;

import java.util.Calendar;

public interface ILaboratory {

	public abstract int getId();
	public abstract String getTitle();
	public abstract String getContent();
	public abstract Calendar getCreationDate();
	public abstract Calendar getLastModifiedDate();
	public abstract boolean isEmpty();
	
}