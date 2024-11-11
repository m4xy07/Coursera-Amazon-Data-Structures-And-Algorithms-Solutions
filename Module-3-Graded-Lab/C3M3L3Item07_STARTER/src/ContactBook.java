import java.util.function.Supplier;

public class ContactBook {

    // total contacts in the Contact book
    private static final int MAX_CONTACTS = 5;

    // array to hold the contacts
    private Contact[] contacts;

    // counter to track number of contacts currently stored in array
    private int numberOfContacts;


    public ContactBook() {
        // no contacts are stored as yet
        // -1 because there are no elements
        numberOfContacts = 0;

        // allocate memory for the array
        contacts = new Contact[MAX_CONTACTS];
    }

    // returns the number of contacts
    public int getNumberOfContacts() {
        // this is used to track the number or entries made in the array.
        // if we use contacts.length, it will always return the size of
        // the array and not number of items
        return  numberOfContacts;
    }


    // returns true if contact with same number already exists
    public boolean contactWithSameNumberExists(Contact contact) {
        boolean contactExists = false;

        for(int i=1; i<=numberOfContacts; i++) {
            if(contacts[i-1].getPhoneNumber().equalsIgnoreCase(contact.getPhoneNumber())) {
                contactExists = true;
                break;
            }
        }

        return contactExists;
    }

    /** TODO 2: add a method "addContact()" which accepts a
     *          parameter of type "Contact" and returns
     *          true or false. Return the default
     *          value true
     **/

    /**
     * TODO 4: in the method "addContact()"
     *          remove the default return value of true and then:
     *              i. assign the contact object to the array
     *              ii. increment the counter "numberOfContacts" by 1
     *           then return true.
     **/

    /** TODO 5: in the method "addContact()" which you created in TODO 2
     *          and modified in TODO 4 put then  lines
     *                  contacts[numberOfContacts++] = contact;
     *                  return true;
     *          which you created in TODO 4 within an if statement.
     *          The if statement will check if adding another Contact
     *          will exceed the maximum number of contacts defined
     *          by the variable MAX_CONTACTS.
     *          If the if statement is false, use an else statement
     *          to return false.
     *
     **/

    /** TODO 6: in the method "addContact()" which you created
     *          put a nested if statement inside the if statement you
     *          created in TODO 6 so that it utilizes the method named
     *          "contactWithSameNumberExists()" provided with the starter
     *          code to check if there exists any contact with the same
     *          phone number. This method returns accepts a Contact object
     *          and returns true if there is a Contact with the same phone number.
     *          Throw an "IllegalArgumentException" if such a record is found
     *          with any suitable message.
     *
     **/
    public boolean addContact(Contact contact) {
        if( (numberOfContacts + 1) <= MAX_CONTACTS )
        {
            if(contactWithSameNumberExists(contact)) {
                throw new IllegalArgumentException("Phone number already exists.");
            }
            contacts[numberOfContacts++] = contact;
            return true;
        } else {
            return false;
        }
    }

    public Contact searchContactByPhone(String phoneNumber) {
        Contact returnContact = null;

        /** TODO 8: Add the code to loop through the existing contact object in the
         *         contacts array from the beginning. Each time check if the phone number is same.
         *         If it is return the contact and break the loop.
         *         Otherwise return the default contact object which is returnContact.
         *              Hint:  Look at method contactWithSameNumberExists()  which contains similar logic.
         **/
        for(int i=1; i<=numberOfContacts; i++) {
            if(contacts[i-1].getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return contacts[i-1];
            }
        }
        return returnContact;
    }

    public boolean deleteContactByPhone(String phoneNumber) {
        boolean recordFound = false;

        for(int i=1; i<=numberOfContacts; i++) {
            if(contacts[i-1].getPhoneNumber().equalsIgnoreCase(phoneNumber)) {

                System.arraycopy(contacts, i, contacts, i, numberOfContacts - i);
                contacts[--numberOfContacts] = null;

                recordFound = true;
                return  recordFound;
            }
        }

        /** TODO 11: replace the return statement with a "throw" statement
         *           to throw an IllegalArgumentException with any message
         *           you like to indicate the record was not found.
         **/
       throw new IllegalArgumentException("No record found to delete.");

    }
}
