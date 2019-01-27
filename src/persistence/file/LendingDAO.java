package persistence.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Logger;

import library.admin.Administration;
import library.admin.ItemNotAvailableException;
import library.admin.LendingManager;
import library.admin.NoItemsFoundException;
import library.data.Customer;
import library.data.Item;
import library.data.Lending;
import persistence.ILendingDAO;

public class LendingDAO implements ILendingDAO {

	private File lendingFile;
	private String delimiter;
	private Lending[] lendings;
	private static final Logger logger = Logger.getLogger(FileDAOFactory.class.getName());
	Administration admin;

	public LendingDAO(File lendingFile, String delimiter) {
		this.lendingFile = lendingFile;
		this.delimiter = delimiter;
		admin = Administration.getInstance();
		loadLendings();
	}

	private void loadLendings() {
		LendingManager lendingManager = admin.getLendingManager();
		Customer[] customers = admin.getCustomers();
		lendings = new Lending[50];
		log("load lendings");
		try (Scanner scanner = new Scanner(new FileReader(lendingFile))) {
			while (scanner.hasNextLine()) {
				String[] tokens = scanner.nextLine().split(delimiter);
				int cNr = Integer.parseInt(tokens[0]);
				long id = Long.parseLong(tokens[1]);
				Item item = admin.findItem(id);
				int year = Integer.parseInt(tokens[2]);
				int month = Integer.parseInt(tokens[3]);
				int day = Integer.parseInt(tokens[4]);
				Lending lending = new Lending(customers[cNr], item, LocalDate.of(year, month, day));
				lendingManager.addLending(lending);
				if (tokens.length > 5) {
					year = Integer.parseInt(tokens[5]);
					month = Integer.parseInt(tokens[6]);
					day = Integer.parseInt(tokens[7]);
					lendingManager.returnItem(item, LocalDate.of(year, month, day));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Lending Datei nicht gefunden");
		} catch (NumberFormatException | ItemNotAvailableException | NoItemsFoundException  e) {
			System.out.println("Lending Datei ist korrput");
		}
	}

	@Override
	public Lending[] getLendings() {
		if (lendings == null)
			loadLendings();
		return lendings;
	}

	private void log(String stmt) {
		String text = stmt.toString();
		logger.info(text.substring(text.indexOf(":") + 1));
	}

}
