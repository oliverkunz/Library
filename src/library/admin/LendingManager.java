package library.admin;

import java.time.LocalDate;

import library.data.*;

public class LendingManager {
	private Lending[] lendings;
	private int lendingCounter = 0;
	
	public LendingManager() {
		lendings = new Lending[50];
	}
	
	public boolean addLending(Customer customer, Item item, LocalDate date) throws ItemNotAvailableException {
		assert item != null;
		if (!item.isAvailable()) {
			throw new ItemNotAvailableException(item);
		}
		Lending lending = new Lending(customer, item, date);
		lendings[this.lendingCounter++] = lending;
		item.setLending(lending);
		item.setState(State.LENT);
		return true;
	}
	
	public boolean addLending(Lending lending) throws ItemNotAvailableException {
		Item item = lending.getItem();
		if (!item.isAvailable()) {
			throw new ItemNotAvailableException(item);
		}
		lendings[this.lendingCounter++] = lending;
		item.setLending(lending);
		item.setState(State.LENT);
		return true;
	}
	
	
	public boolean returnItem(Item item, LocalDate date){
		assert item != null;
		item.getLending().setReturnDate(date);
		item.setState(State.AVAILABLE);
		return true;
	}
	
	public boolean isAvailable(Item item) {
		assert item!= null;
		return item.isAvailable();
	}
	
	public long[] getAvailableItems(long[] ids) throws NoItemsFoundException, NoAvailableItemsException {
		long[] result = null;
		for(long id:ids) {
			Item item = Administration.getInstance().findItem(id);
			if(item.isAvailable())
				result = Utils.addNumber(result, item.getId());
		}
		if(result == null)
			throw new NoAvailableItemsException(ids);
		return result;
	}
	
	
	public LocalDate getLendingEndDate(Item item) {
		Lending lending = item.getLending();
		if (lending != null)
			return lending.getReturnDate();
		return LocalDate.now();
	}
	
	public LocalDate getTimeLimit(Item item) {
		Lending lending = item.getLending();
		if (lending != null)
			return lending.getTimeLimit();
		return LocalDate.now();
	}
	

	public Lending[] getLendings() {
		return lendings;
	}
	

	public void setLendings(Lending[] lendings) {
		this.lendings = lendings;
	}
	
	public void printLendings() {
		for (Lending l : lendings) {
			System.out.println(l.getItem().getId());
		}
	}
}
