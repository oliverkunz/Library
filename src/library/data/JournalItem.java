package library.data;

public class JournalItem extends Item implements Comparable<JournalItem>{
	private Journal journal;

	public JournalItem(long id, Journal journal) {
		super(id);
		this.journal = journal;
	}

	public Journal getJournal() {
		return journal;
	}

	public Journal getObject() {
		return getJournal();
	}
	
	@Override
	public String toString() {
		return "Journal: " + journal + super.toString();
	}
	
	@Override
	public int compareTo(JournalItem o) {
		return journal.getTitle().compareTo(o.getJournal().getTitle());
	}
}
