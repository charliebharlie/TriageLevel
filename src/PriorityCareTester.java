import java.util.NoSuchElementException;

/**
 * This is a Utility class which contains tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 spring 2023 p10 Priority Care.
 *
 */
public class PriorityCareTester {

    /**
     * Tests whether compareTo() method implemented in PatientRecord returns a positive integer
     * when a higher triage level is compared to a lower triage level, regardless of patient
     * order of arrival. Similarly, this method tests whether compareTo() method implemented in
     * PatientRecord returns a negative integer when a lower triage level is compared to a higher
     * triage level, regardless of patient order of arrival.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     *         detected
     * @see PatientRecord#compareTo(PatientRecord)
     */
    public static boolean testPatientRecordCompareToDifferentTriage() {
        // TODO complete the implementation of this tester method
        try {
            PatientRecord redPatientRecord = new PatientRecord('M', 23, TriageLevel.RED);
            PatientRecord yellowPatientRecord = new PatientRecord('M', 23, TriageLevel.YELLOW);

            int actual = redPatientRecord.compareTo(yellowPatientRecord);
            int expected = -1;
            if (expected != actual) {
                System.out.println(actual);
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Tests whether patients in the same triage level are compared based on their order of
     * arrival. Patients of the same triage level with a lower arrival number compared to
     * patients with a higher arrival number should return a negative integer. The reverse
     * situation should return a positive integer.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     *         detected
     * @see PatientRecord#compareTo(PatientRecord)
     */
    public static boolean testPatientRecordCompareToSameTriageDifferentArrival() {
        // TODO complete the implementation of this tester method
        try {
            PatientRecord firstPatientRecord = new PatientRecord('M', 23, TriageLevel.RED);
            PatientRecord secondPatientRecord = new PatientRecord('M', 23, TriageLevel.RED);

            int actual = firstPatientRecord.compareTo(secondPatientRecord);
            int expected = -1;
            if (expected != actual) {
                System.out.println(actual);
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Tests whether patients in the same triage level and with the same order of arrival are equal
     * (compareTo should return 0). Even though this case will not be possible in your priority
     * queue, it is required for testing the full functionality of the compareTo() method. Hint:
     * you will need to use the resetCounter() to create equivalent PatientRecords.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     *         detected
     * @see PatientRecord#compareTo(PatientRecord)
     */
    public static boolean testPatientRecordCompareToSameTriageSameArrival() {
        try {
            PatientRecord.resetCounter();
            // TODO complete the implementation of this tester method
            PatientRecord firstPatientRecord = new PatientRecord('M', 23, TriageLevel.RED);
            PatientRecord.resetCounter();
            PatientRecord secondPatientRecord = new PatientRecord('F', 32, TriageLevel.RED);


            int actual = firstPatientRecord.compareTo(secondPatientRecord);
            int expected = 0;
            if (expected != actual) {
                System.out.println(actual);
                return false;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Tests the functionality of the constructor for PriorityCareAdmissions Should implement at
     * least the following tests:
     *
     * - Calling the PriorityCareAdmissions with an invalid capacity should throw an
     * IllegalArgumentException
     * - Calling the PriorityCareAdmissions with a valid capacity should not throw any errors, and
     * should result in a new PriorityCareAdmissions which is empty, has size 0, a capacity
     * equal to the capacity that was passed as a parameter.
     *
     * @return true if the constructor of PriorityCareAdmissions functions properly, false
     * otherwise
     * @see PriorityCareAdmissions#PriorityCareAdmissions(int)
     */
    public static boolean testConstructor() {
        // TODO complete the implementation of this tester method

        try {
            // (1) invalid capacity
            {
                try {
                    PriorityCareAdmissions invalidPriorityCareAdmissions =
                            new PriorityCareAdmissions(-1);
                    return false;
                }
                catch(IllegalArgumentException e){

                }
                catch(Exception e){
                    return false;
                }
            }

            // (2) valid capacity
            {
                PriorityCareAdmissions validPriorityCareAdmissions =
                        new PriorityCareAdmissions(3);
                int actualSize = validPriorityCareAdmissions.size();
                int expectedSize = 0;

                int actualCapacity = validPriorityCareAdmissions.capacity();
                int expectedCapacity = 3;

                if(!validPriorityCareAdmissions.isEmpty() || expectedSize != actualSize ||
                        expectedCapacity != actualCapacity){
                    return false;
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true; // default return statement added to resolve compiler errors
    }


    /**
     * Tests the functionality of peek() method by calling peek on an empty queue and verifying it
     * throws a NoSuchElementException.
     *
     * @return true if PriorityCareAdmissions.peek() exhibits expected behavior, false otherwise.
     */
    public static boolean testPeekEmpty() {
        // TODO complete the implementation of this tester method
        try{
            // (1) peek on empty queue
            try {
                PriorityCareAdmissions emptyQueue = new PriorityCareAdmissions(3);
                emptyQueue.peek();
                return false;
            }
            catch (NoSuchElementException e){
                String actual = e.getMessage();
                String expected = "Warning: Empty Admissions Queue!";
                if(!expected.equals(actual)){
                    return false;
                }
            }
            catch(Exception e){
                return false;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Tests the functionality of peek() method by calling peek on a non-empty queue and
     * verifying it 1) returns the PatientRecord having the highest priority (the minimum) and 2)
     * does not remove the PatientRecord from the queue.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     *         detected
     */
    public static boolean testPeekNonEmpty() {
        // TODO complete the implementation of this tester method
        try{
            PatientRecord.resetCounter();
            // (1) peek on non-empty queue
            PatientRecord patientRecord1 = new PatientRecord('M', 23, TriageLevel.RED);
            PatientRecord patientRecord2 = new PatientRecord('F', 32, TriageLevel.YELLOW);
            PatientRecord patientRecord3 = new PatientRecord('X', 39, TriageLevel.GREEN);
            PatientRecord patientRecord4 = new PatientRecord('M', 23, TriageLevel.YELLOW);
            PatientRecord patientRecord5 = new PatientRecord('F', 32, TriageLevel.YELLOW);
            PatientRecord patientRecord6 = new PatientRecord('X', 39, TriageLevel.RED);

            PriorityCareAdmissions nonEmptyQueue = new PriorityCareAdmissions(6);
            nonEmptyQueue.addPatient(patientRecord1);
            nonEmptyQueue.addPatient(patientRecord2);
            nonEmptyQueue.addPatient(patientRecord3);
            nonEmptyQueue.addPatient(patientRecord4);
            nonEmptyQueue.addPatient(patientRecord5);
            nonEmptyQueue.addPatient(patientRecord6);

            PatientRecord actual = nonEmptyQueue.peek();
            PatientRecord expected = patientRecord1;

            if(!expected.equals(actual)){
                return false;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Tests the functionality of addPatient() method by calling addPatient() on an empty queue and
     * ensuring the method 1) adds the PatientRecord and 2) increments the size.
     *
     * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
     *         otherwise.
     */
    public static boolean testAddPatientEmpty() {
        // TODO complete the implementation of this tester method

        try{
            PatientRecord.resetCounter();
            // (1) peek on non-empty queue
            PatientRecord patientRecord1 = new PatientRecord('M', 23, TriageLevel.RED);
            PatientRecord patientRecord2 = new PatientRecord('F', 32, TriageLevel.YELLOW);
            PatientRecord patientRecord3 = new PatientRecord('X', 39, TriageLevel.GREEN);
            PatientRecord patientRecord4 = new PatientRecord('M', 23, TriageLevel.YELLOW);
            PatientRecord patientRecord5 = new PatientRecord('F', 32, TriageLevel.YELLOW);
            PatientRecord patientRecord6 = new PatientRecord('X', 39, TriageLevel.RED);

            PriorityCareAdmissions emptyQueue = new PriorityCareAdmissions(6);
            emptyQueue.addPatient(patientRecord1);
            emptyQueue.addPatient(patientRecord2);
            emptyQueue.addPatient(patientRecord3);
            emptyQueue.addPatient(patientRecord4);
            emptyQueue.addPatient(patientRecord5);
            emptyQueue.addPatient(patientRecord6);

            String actual = emptyQueue.toString();
            String expected = "22302: 23M (RED) - not seen\n" +
                    "33907: 39X (RED) - not seen\n" +
                    "13203: 32F (YELLOW) - not seen\n" +
                    "22305: 23M (YELLOW) - not seen\n" +
                    "13206: 32F (YELLOW) - not seen\n" +
                    "33904: 39X (GREEN) - not seen\n";

            int actualSize = emptyQueue.size();
            int expectedSize = 6;
            if(!expected.equals(actual) || expectedSize != actualSize){
                return false;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * Tests the functionality of addPatient() method by calling addPatient() on a non-empty
     * queue and ensuring the method 1) adds the PatientRecord at the proper position and 2)
     * increments the size. Try to add at least 5 PatientRecords.
     *
     * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
     * otherwise
     */
    public static boolean testAddPatientNonEmpty() {
        // TODO complete the implementation of this tester method
        try {
            PatientRecord.resetCounter();
            PriorityCareAdmissions nonEmptyQueue = new PriorityCareAdmissions(6);
            PatientRecord patientRecord1 = new PatientRecord('M', 23, TriageLevel.RED);
            PatientRecord patientRecord2 = new PatientRecord('F', 32, TriageLevel.YELLOW);
            PatientRecord patientRecord3 = new PatientRecord('X', 39, TriageLevel.GREEN);
            nonEmptyQueue.addPatient(patientRecord1);
            nonEmptyQueue.addPatient(patientRecord2);
            nonEmptyQueue.addPatient(patientRecord3);

            try {
                PatientRecord patientRecord4 = new PatientRecord('M', 23, TriageLevel.YELLOW);
                PatientRecord patientRecord5 = new PatientRecord('X', 39, TriageLevel.RED);

                nonEmptyQueue.addPatient(patientRecord4);
                nonEmptyQueue.addPatient(patientRecord5);

                String actual = nonEmptyQueue.toString();
                String expected = "22302: 23M (RED) - not seen\n" +
                        "33906: 39X (RED) - not seen\n" +
                        "13203: 32F (YELLOW) - not seen\n" +
                        "22305: 23M (YELLOW) - not seen\n" +
                        "33904: 39X (GREEN) - not seen\n";

                int actualSize = nonEmptyQueue.size();
                int expectedSize = 5;
                if (!expected.equals(actual) || expectedSize != actualSize) {
                    System.out.println("this fails");
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }


    /**
     * Tests the functionality of addPatient() method by calling addPatient() on a full queue and
     * ensuring the method throws an IllegalStateException.
     *
     * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
     *         otherwise.
     */
    public static boolean testAddPatientFull() {
        // TODO complete the implementation of this tester method
        // (1) invalid test, full queue
        try {
            PriorityCareAdmissions fullQueue = new PriorityCareAdmissions(1);
            PatientRecord patientRecord1 = new PatientRecord('M', 23, TriageLevel.RED);
            PatientRecord patientRecord2 = new PatientRecord('F', 32, TriageLevel.YELLOW);
            try {
                fullQueue.addPatient(patientRecord1);
                fullQueue.addPatient(patientRecord2);
                return false;
            } catch (IllegalStateException e) {

            } catch (Exception e) {
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Tests the functionality of addPatient() method by calling addPatient() with a null
     * PatientRecord and ensuring the method throws a NullPointerException.
     *
     * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
     *         otherwise.
     */
    public static boolean testAddPatientNull() {
        // TODO complete the implementation of this tester method
        try{
            try{
                PriorityCareAdmissions nullQueue = new PriorityCareAdmissions(3);
                PatientRecord nullPatient = null;
                nullQueue.addPatient(nullPatient);
                return false;
            }
            catch(NullPointerException e){

            }
            catch (Exception e){
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on an
     * empty queue.
     *
     * @return true if PriorityCareAdmissions.removeBestRecord() throws a NoSuchElementException,
     *         false otherwise
     */
    public static boolean testRemoveBestRecordEmpty() {
        // TODO complete the implementation of this tester method
        try{
            try {
                PriorityCareAdmissions emptyQueue = new PriorityCareAdmissions(6);
                emptyQueue.removeBestRecord();
                return false;
            }
            catch(NoSuchElementException e){
                String actual = e.getMessage();
                String expected = "Warning: Empty Admissions Queue!";
                if(!expected.equals(actual)){
                    return false;
                }
            }
            catch (Exception e){
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true; // default return statement added to resolve compiler errors
    }


    /**
     * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on a
     * queue of size one.
     *
     * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct
     * PatientRecord and size is 0
     */
    public static boolean testRemoveBestRecordSizeOne() {
        // TODO complete the implementation of this tester method
        try{
            PriorityCareAdmissions onePatientQueue = new PriorityCareAdmissions(1);
            PatientRecord patientRecord1 = new PatientRecord('M', 23, TriageLevel.RED);
            onePatientQueue.addPatient(patientRecord1);
            PatientRecord actualRemovedPatient = onePatientQueue.removeBestRecord();
            PatientRecord expectedRemovedPatient = patientRecord1;

            int actualSize = onePatientQueue.size();
            int expectedSize = 0;
            if(expectedSize != actualSize || !expectedRemovedPatient.equals(actualRemovedPatient)){
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true; // default return statement added to resolve compiler errors
    }

    /**
     * Tests the functionality of removeBestRecord() methods.
     *
     * The removeBestRecord() method must remove, and return the patient record with the highest
     * priority in the queue. The size must be decremented by one, each time the removeBestRecord()
     * method is successfully called.
     *
     * Remove the best record from a queue whose size is at least 6. Consider cases where
     * percolate-down recurses on left and right.
     *
     * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord
     *         each time it is called and size is appropriately decremented, false otherwise
     */
    public static boolean testRemoveBestRecordNonEmpty() {
        // TODO complete the implementation of this tester method
        try{
            PatientRecord.resetCounter();
            // (1) peek on non-empty queue
            PatientRecord patientRecord1 = new PatientRecord('M', 23, TriageLevel.RED);
            PatientRecord patientRecord2 = new PatientRecord('F', 32, TriageLevel.YELLOW);
            PatientRecord patientRecord3 = new PatientRecord('X', 39, TriageLevel.GREEN);
            PatientRecord patientRecord4 = new PatientRecord('M', 23, TriageLevel.YELLOW);
            PatientRecord patientRecord5 = new PatientRecord('F', 32, TriageLevel.YELLOW);
            PatientRecord patientRecord6 = new PatientRecord('X', 39, TriageLevel.RED);

            PriorityCareAdmissions emptyQueue = new PriorityCareAdmissions(6);
            emptyQueue.addPatient(patientRecord1);
            emptyQueue.addPatient(patientRecord2);
            emptyQueue.addPatient(patientRecord3);
            emptyQueue.addPatient(patientRecord4);
            emptyQueue.addPatient(patientRecord5);
            emptyQueue.addPatient(patientRecord6);

            emptyQueue.removeBestRecord();

            String actual = emptyQueue.toString();
            String expected = "33907: 39X (RED) - not seen\n" +
                    "13203: 32F (YELLOW) - not seen\n" +
                    "22305: 23M (YELLOW) - not seen\n" +
                    "13206: 32F (YELLOW) - not seen\n" +
                    "33904: 39X (GREEN) - not seen\n";

            if(!expected.equals(actual)){
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true; // default return statement added to resolve compiler errors
    }

    /**
     * Tests the functionality of the clear() method.
     * Should implement at least the following scenarios:
     * - clear can be called on an empty queue with no errors
     * - clear can be called on a non-empty queue with no errors
     * - After calling clear(), the queue should contain zero PatientRecords.
     * - After calling clear(), the size should be 0
     *
     * @return true if PriorityCareAdmissions.clear() functions properly
     */
    public static boolean testClear() {
        // TODO complete the implementation of this tester method
        try{
            PriorityCareAdmissions emptyQueue = new PriorityCareAdmissions(3);
            emptyQueue.clear();
            int actualEmptyQueueSize = emptyQueue.size();
            int expectedEmptyQueueSize = 0;

            PriorityCareAdmissions nonEmptyQueue = new PriorityCareAdmissions(3);
            PatientRecord patientRecord1 = new PatientRecord('M', 23, TriageLevel.RED);
            nonEmptyQueue.addPatient(patientRecord1);
            nonEmptyQueue.clear();
            int actualNonEmptyQueueSize = nonEmptyQueue.size();
            int expectedNonEmptyQueueSize = 0;

            if(!emptyQueue.isEmpty() || actualEmptyQueueSize != expectedEmptyQueueSize ||
                    !nonEmptyQueue.isEmpty() || expectedNonEmptyQueueSize !=
                    actualNonEmptyQueueSize){
                System.out.println("this fails");
                return false;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true; // default return statement added to resolve compiler errors
    }


    /**
     * Tests toString() method of PriorityCareAdmissions class.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     *         detected
     */
    public static boolean testToString() {
        // TODO complete the implementation of this tester method
        try{
            PriorityCareAdmissions priorityCareAdmissions = new PriorityCareAdmissions(5);
            PatientRecord patientRecord1 = new PatientRecord('M', 23, TriageLevel.RED);
            PatientRecord patientRecord2 = new PatientRecord('F', 32, TriageLevel.YELLOW);
            PatientRecord patientRecord3 = new PatientRecord('X', 39, TriageLevel.GREEN);
            PatientRecord patientRecord4 = new PatientRecord('M', 23, TriageLevel.YELLOW);
            PatientRecord patientRecord5 = new PatientRecord('F', 32, TriageLevel.YELLOW);

            priorityCareAdmissions.addPatient(patientRecord1);
            priorityCareAdmissions.addPatient(patientRecord2);
            priorityCareAdmissions.addPatient(patientRecord3);
            priorityCareAdmissions.addPatient(patientRecord4);
            priorityCareAdmissions.addPatient(patientRecord5);


            String actual = priorityCareAdmissions.toString();
            String expected = "22310: 23M (RED) - not seen\n" +
                    "13211: 32F (YELLOW) - not seen\n" +
                    "22313: 23M (YELLOW) - not seen\n" +
                    "13214: 32F (YELLOW) - not seen\n" +
                    "33912: 39X (GREEN) - not seen\n";
            if(!expected.equals(actual)){
                return false;
            }
        }

        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true; // default return statement added to resolve compiler errors
    }


    /**
     * Runs all the tester methods defined in this class.
     *
     * @return true if no bugs are detected.
     */
    public static boolean runAllTests() {

        return testPatientRecordCompareToDifferentTriage()
                && testPatientRecordCompareToSameTriageDifferentArrival()
                && testPatientRecordCompareToSameTriageSameArrival() && testPeekEmpty()
                && testPeekNonEmpty() && testAddPatientEmpty() && testAddPatientNonEmpty()
                && testAddPatientFull() && testAddPatientNull() && testRemoveBestRecordNonEmpty()
                && testRemoveBestRecordEmpty() && testRemoveBestRecordSizeOne() && testClear()
                && testToString();
    }

    /**
     * Main method to run this tester class.
     *
     * @param args list of input arguments if any
     */
    public static void main(String[] args) {
        System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
        System.out.println("testPatientRecordCompareToDifferentTriage: "
                + (testPatientRecordCompareToDifferentTriage() ? "Pass" : "Failed!"));
        System.out.println("testPatientRecordCompareToSameTriageDifferentArrival: "
                + (testPatientRecordCompareToSameTriageDifferentArrival() ? "Pass" : "Failed!"));
        System.out.println("testPatientRecordCompareToSameTriageSameArrival: "
                + (testPatientRecordCompareToSameTriageSameArrival() ? "Pass" : "Failed!"));
        System.out.println("testConstructor: " + (testConstructor() ? "Pass" : "Failed!"));
        System.out.println("testPeekEmpty: " + (testPeekEmpty() ? "Pass" : "Failed!"));
        System.out.println("testPeekNonEmpty: " + (testPeekNonEmpty() ? "Pass" : "Failed!"));
        System.out.println("testAddPatientEmpty: " + (testAddPatientEmpty() ? "Pass" : "Failed!"));
        System.out.println("testAddPatientNonEmpty: " + (testAddPatientNonEmpty() ? "Pass" :
                "Failed!"));
        System.out.println("testAddPatientFull: " + (testAddPatientFull() ? "Pass" : "Failed!"));
        System.out.println("testAddPatientNull: " + (testAddPatientNull() ? "Pass" : "Failed!"));
        System.out.println( "testRemoveBestRecordNonEmpty: " + (testRemoveBestRecordNonEmpty() ?
                "Pass" : "Failed!"));
        System.out.println( "testRemoveBestRecordEmpty: " + (testRemoveBestRecordEmpty() ?
                "Pass" : "Failed!"));
        System.out.println( "testRemoveBestRecordSizeOne: " + (testRemoveBestRecordSizeOne() ?
                "Pass" : "Failed!"));
        System.out.println("testClear: " + (testClear() ? "Pass" : "Failed!"));
        System.out.println("testToString: " + (testToString() ? "Pass" : "Failed!"));
    }

}
