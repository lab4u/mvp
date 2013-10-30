package co.lab4u.instruments.models;

import java.util.Calendar;
import java.util.Date;

public class Laboratory implements ILaboratory {
	private int id;
	private String title;
	private String content;
	private Calendar creationDate;
	private Calendar lastModifiedDate;

	public Laboratory(int id, String title, String content, Calendar creationDate,
			Calendar lastModifiedDate) {
		super();
		
		this.id = id;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.lastModifiedDate = lastModifiedDate;
	}
	
	/* (non-Javadoc)
	 * @see co.lab4u.instruments.models.ILaboratory#getId()
	 */
	@Override
	public int getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see co.lab4u.instruments.models.ILaboratory#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}
	/* (non-Javadoc)
	 * @see co.lab4u.instruments.models.ILaboratory#setTitle(java.lang.String)
	 */
	
	/* (non-Javadoc)
	 * @see co.lab4u.instruments.models.ILaboratory#getContent()
	 */
	@Override
	public String getContent() {
		return content;
	}
	/* (non-Javadoc)
	 * @see co.lab4u.instruments.models.ILaboratory#setContent(java.lang.String)
	 */
	
	/* (non-Javadoc)
	 * @see co.lab4u.instruments.models.ILaboratory#getCreationDate()
	 */
	@Override
	public Calendar getCreationDate() {
		return creationDate;
	}
	/* (non-Javadoc)
	 * @see co.lab4u.instruments.models.ILaboratory#setCreationDate(java.util.Date)
	 */
	
	/* (non-Javadoc)
	 * @see co.lab4u.instruments.models.ILaboratory#getLastModifiedDate()
	 */
	@Override
	public Calendar getLastModifiedDate() {
		return lastModifiedDate;
	}
	/* (non-Javadoc)
	 * @see co.lab4u.instruments.models.ILaboratory#setLastModifiedDate(java.util.Date)
	 */

	@Override
	public boolean isEmpty() {
		return false;
	}
}
