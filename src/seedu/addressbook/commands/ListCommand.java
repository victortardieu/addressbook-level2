package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.List;


/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        List<ReadOnlyPerson> sortedPersonList =  new ArrayList<ReadOnlyPerson>();
        for (int i = 0 ; i<allPersons.size() ; i++){
            sortedPersonList.add(allPersons.get(i));
        }
        int sizeSortedPersonList = sortedPersonList.size();
        for (int i = 0; i < sizeSortedPersonList-1; i++)
            for (int j = 0; j < sizeSortedPersonList-i-1; j++)
                if (sortedPersonList.get(j).getName().toString().toLowerCase().compareTo(sortedPersonList.get(j+1).getName()
                        .toString().toLowerCase()) > 0) {
                    ReadOnlyPerson temp = sortedPersonList.get(j);
                    sortedPersonList.set(j,sortedPersonList.get(j+1));
                    sortedPersonList.set(j+1,temp);
                }
        return new CommandResult(getMessageForPersonListShownSummary(sortedPersonList), sortedPersonList);
    }

}
