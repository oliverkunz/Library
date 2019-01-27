package persistence;

import library.admin.ItemNotAvailableException;
import library.admin.NoItemsFoundException;
import library.data.Lending;

public interface ILendingDAO {

	Lending[] getLendings() throws NoItemsFoundException, ItemNotAvailableException;

}