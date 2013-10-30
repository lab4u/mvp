package co.lab4u.instruments.models;

import java.util.Calendar;

public class LaboratoryStaticFactory {
	// singleton for LaboratoryStaticFactory
	private static LaboratoryStaticFactory instance = null;
	
	private LaboratoryStaticFactory() {	}
	
	public static LaboratoryStaticFactory getInstance() {
		if (instance == null) instance = new LaboratoryStaticFactory();
		
		return instance;
	}
	
	public ILaboratory CreateLaboratory(int id, String title, String content, Calendar calendar, Calendar calendar2) {
		return new Laboratory(id, title, content, calendar, calendar2);
	}
	
	public ILaboratory CreateEmptyLaboratory() {
		return new EmptyLaboratory();
	}
	
	
	
	

}
