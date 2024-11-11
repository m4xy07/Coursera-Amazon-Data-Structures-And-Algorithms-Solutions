import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ContactBookTest {

    // common object for ContactBook
    static ContactBook contactBook;

    @BeforeAll
    static void setUp() {
        contactBook = new ContactBook();
    }

    @Test
    public void testAddContact() {
        Contact testContact = new Contact("AmazonStudent","1111122334");

        /** TODO 1: add the Contact named "AmazonStudent" to the contactBook
         *          using the method "addContact()" of the class "ContactBook"
         **/

        /** TODO 3: get the number of current contacts by calling the method
         *          "getNumberOfContacts()" of the object "contactBook" and
         *          assign the value returned to a variable named "currentNumOfContacts"
         *          of type int. Do this before the line which calls to the method "addContact()",
         *          which you did in TODO 1.
         *
         *          Also after the call to the method "addContact()"
         *          assert that the value of the value returned by a call to
         *          "getNumberOfContacts()" of the object "contactBook" is
         *          1 greater than the value of "currentNumOfContacts"
         **/

    }

    @ParameterizedTest
    @CsvSource({
            "Alex, 1239292",
            "Taylor, 23939258",
            "Alice, 33939252 ",
            "Clark, 43939251",
            "Toni, 53939257",
            "Casey, 63939258"
    })
    public void testAddContact_onSizeLimitExceed_returnsFalseAndNotException(String name, String phone) {
            Contact testContact = new Contact(name, phone);
            assertTrue(contactBook.addContact(testContact));
    }

    @Test
    public void testAddContact_onSameNumber_ThrowsIllegalArgumentException() {

        Contact testContact1 = new Contact("Alex","11111");
        Contact testContact2 = new Contact("Toni","11111");

        // first contact
        contactBook.addContact(testContact1);

        // second contact with same phone number
        //check if exception is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            contactBook.addContact(testContact2);
        });

    }


    @Test
    public void testSearchContactByPhone() {

        /** TODO 7: Do the following:
         *          i. create a contact with the name "Toni" and contact number "184048"
         *             and assign it to an object named "testContactForPhone".
         *          ii. add the contact to the contact book using the method "addContact()"
         *         iii. Now call the method searchContactByPhone() with the value "184048" being
         *              passed as a parameter of type String, for the phoneNumber.
         *          iv.	Assert that the object returned is not null.
         *          v.	Assert that the name and phone number of the Contact returned is the same
         *              as the object created having the same name and phone number.
         **/

    }

    @Test
    public void testRemoveContactByPhone_found_returnsTrue() {

        Contact testContactForPhone = new Contact("Wills","939503");
        contactBook.addContact(testContactForPhone);

        /** TODO 9: assert that when the method "deleteContactByPhone()"
         *          is called with the phone number 939503, it returns true
         **/

    }

    @Test
    public void testRemoveContactByPhone_notFound_throwsIllegalArgumentException() {
        /** TODO 10: put an assertion to check if the method "deleteContactByPhone()" in the
         *           class ContactBook throws an IllegalArgumentException, when
         *            passed a phone number which does not exist in the list of contacts like
         *            923746439503.
         **/
    }
}

