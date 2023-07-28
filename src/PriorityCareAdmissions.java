import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Array-based min-heap implementation of a priority queue storing PatientRecords. Guarantees the
 * min-heap invariant, so that the PatientRecord at the root should be the smallest PatientRecord,
 * which corresponds to the element having the highest priority to be dequeued first, and children
 * always are greater than their parent. We rely on the PatientRecord.compareTo() method to compare
 * PatientRecords.
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class PriorityCareAdmissions {
    private PatientRecord[] queue; // array min-heap of PatientRecords representing this priority
    // queue
    private int size; // size of this priority queue


    /**
     * Creates a new empty PriorityCareAdmissions queue with the given capacity
     *
     * @param capacity Capacity of this PriorityCareAdmissions queue
     * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
     *                                  positive integer
     */
    public PriorityCareAdmissions(int capacity) throws IllegalArgumentException {
        // TODO complete this implementation
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity is not a positive integer");
        }
        this.queue = new PatientRecord[capacity];
    }

    /**
     * Checks whether this PriorityCareAdmissions queue is empty
     *
     * @return true if this PriorityCareAdmissions queue is empty, false otherwise
     */
    public boolean isEmpty() {
        // TODO complete this implementation
        return this.size == 0; // default return statement added to resolve compile errors
    }

    /**
     * Returns the size of this PriorityCareAdmissions queue
     *
     * @return the total number of PatientRecords stored in this PriorityCareAdmissions queue
     */
    public int size() {
        // TODO complete this implementation

        return this.size; // default return statement added to resolve compile errors
    }

    /**
     * Returns the capacity of this PriorityCareAdmissions queue
     *
     * @return the capacity of this PriorityCareAdmissions queue
     */
    public int capacity() {
        // TODO complete this implementation
        return this.queue.length; // default return statement added to resolve compile errors
    }


    /**
     * Removes all the elements from this PriorityCareAdmissions queue
     */
    public void clear() {
        Arrays.fill(queue, null);
        this.size = 0;
        // TODO complete this implementation
    }

    /**
     * Returns the PatientRecord at the root of this PriorityCareAdmissions queue, i.e. the
     * PatientRecord having the the highest priority.
     *
     * @return the PatientRecord at the root of this PriorityCareAdmissions queue
     * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions
     * Queue!" if this PriorityCareAdmissions queue is empty
     */
    public PatientRecord peek() throws NoSuchElementException {
        // TODO complete this implementation
        if (isEmpty()) {
            throw new NoSuchElementException("Warning: Empty Admissions Queue!");
        }
        return queue[0]; // default return statement added to resolve compile errors
    }

    /**
     * Get the index of where the parent node is based on the index of the current node
     * @param i the passed index of the current node
     * @return the index of the parent node
     */
    private int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Get the index of where the left child node is based on the index of the current node
     * @param i the passed index of the current node
     * @return the index of the left child node
     */
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Get the index of where the right child node is based on the index of the current node
     * @param i the passed index of the current node
     * @return the index of the right child node
     */
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    /**
     * Swaps the nodes at indices i and j to maintain a valid heap queue
     *
     * @param i previous node
     * @param j current node
     */
    private void swap(int i, int j) {
        PatientRecord temp = queue[i];
        queue[i] = queue[j];
        queue[j] = temp;
    }

    /**
     * Removes an element from the queue and updates its queue appropriately
     * @param index index of the current element to remove
     * @return the removed element
     */
    private PatientRecord remove(int index){
        PatientRecord temp = queue[index];

        for (int i = index; i < size() - 1; i++) {
            queue[i] = queue[i + 1];
        }

        queue[size() - 1] = null;
        size--;
        return temp;
    }


    /**
     * Adds the given PatientRecord to this PriorityCareAdmissions queue at the correct position
     * based on the min-heap ordering. This queue should maintain the min-heap invariant, so
     * that the PatientRecord at each index is less than or equal to than the PatientRecords in
     * its child nodes. PatientRecords should be compared using the PatientRecord.compareTo()
     * method.
     *
     * @param p PatientRecord to add to this PriorityCareAdmissions queue
     * @throws NullPointerException  if the given PatientRecord is null
     * @throws IllegalStateException with a the exact error message "Warning: Full Admissions
     * Queue!" if this PriorityCareAdmissions queue is full
     */
    public void addPatient(PatientRecord p) throws NullPointerException, IllegalStateException{
        if(p == null){
            throw new NullPointerException("PatientRecord is null");
        }

        if(size == capacity()){
            throw new IllegalStateException("Warning: Full Admissions Queue!");
        }

        queue[size] = p;
        size++;
        percolateUp(size() - 1);
        // TODO complete this implementation
    }

    /**
     * Recursive implementation of percolateUp() method. Restores the min-heap invariant of this
     * priority queue by percolating a leaf up the heap. If the element at the given index does not
     * violate the min-heap invariant (it is greater than its parent), then this method does not
     * modify the heap. Otherwise, if there is a heap violation, swap the element with its
     * parent and continue percolating the element up the heap.
     *
     * @param i index of the element in the heap to percolate upwards
     * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
     *                                   inclusive)
     */
    protected void percolateUp(int i) throws IndexOutOfBoundsException{
        // TODO complete this implementation. To get more practice on recursive thinking,
        // implemented this method recursively

        if(i > size() || i < 0){
            throw new IndexOutOfBoundsException("Out of the range 0 ... size() - 1 (inclusive)");
        }
        // BASE CASE 1:
        if (queue[i].compareTo(queue[parent(i)]) >= 0) {
            return;
        }

        // RECURSIVE CASE:
        swap(i, parent(i));
        i = parent(i);
        percolateUp(i);
    }

    /**
     * Removes and returns the PatientRecord at the root of this PriorityCareAdmissions queue, i.e.
     * the PatientRecord having the highest priority (the minimum one).
     *
     * @return the PatientRecord in this PriorityCareAdmissions queue at the root of this priority
     * queue.
     * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions
     *  Queue!" if this PriorityCareAdmissions queue is empty
     */
    public PatientRecord removeBestRecord() throws NoSuchElementException{
        // TODO complete this implementation
        if (isEmpty()) {
            throw new NoSuchElementException("Warning: Empty Admissions Queue!");
        }
        PatientRecord returnValue = queue[0];
// more than one thing in the heap??
        if (size() > 1) {
            // 1) swap and remove the last leaf into the root. Will throw an exception if i
            // removed the value at index 0
            queue[0] = remove(size() - 1);
            // 2) percolate DOWN until BOTH children are less than me
            percolateDown(0);
        } else {
            remove(0);
        }
        return returnValue;
    }

    /**
     * Recursive implementation of percolateDown() method. Restores the min-heap of the priority
     * queue by percolating its root down the tree. If the element at the given index does not
     * violate the min-heap ordering property (it is smaller than its smallest child), then this
     * method does not modify the heap. Otherwise, if there is a heap violation, then swap the
     * element with the correct child and continue percolating the element down the heap.
     *
     * @param i index of the element in the heap to percolate downwards
     * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
     *                                   inclusive)
     */
    protected void percolateDown(int i) throws IndexOutOfBoundsException{
        if(i > size() || i < 0){
            throw new IndexOutOfBoundsException("Out of the range 0 ... size() - 1 (inclusive)");
        }
        // TODO complete this implementation. To get more practice on recursive thinking,
        //  implemented this method recursively

        // note that it doesn't matter what child is swapped with, so long as the previous
        // condition in the recursion is met (ie. swaps with the greatest child) as long as it's
        // being percolated down such that it holds the validity of the min-heap queue

        // BASE CASE 2: checks that if the left child is greater than the size of the tree, then
        // it's impossible for that node to have any children, as if the left child is greater,
        // then the right child will also always be greater as the right child is always greater
        // than the left child
        if (leftChild(i) >= size()) {
            return;
        }

        // BASE CASE 3: checks that if the right child is greater than the size of the tree, then
        // there can only be a left child, as the 1st base case checks that there are no
        // children
        else if (rightChild(i) >= size()) {
            if (queue[i].compareTo(queue[leftChild(i)]) > 0) {
                swap(i, leftChild(i));
            }
            return;
        }

        // RECURSIVE CASE:
        // 1) check if data[i] is less than either child
        if (queue[i].compareTo(queue[leftChild(i)]) > 0 ||
                queue[i].compareTo(queue[rightChild(i)]) > 0) {
            // 2) if it is, swap with larger child and repeat
            if (queue[leftChild(i)].compareTo(queue[rightChild(i)]) < 0) {
                // if left child is smaller
                swap(i, leftChild(i));
                percolateDown(leftChild(i));
            }
            else {
                // else right child is smaller or equal
                swap(i, rightChild(i));
                percolateDown(rightChild(i));
            }
        }

        // BASE CASE 1: that if statement was false, and we don't need to swap
        return;
    }


    /**
     * Returns a deep copy of this PriorityCareAdmissions queue containing all of its elements
     * in the same order. This method does not return the deepest copy, meaning that you do not
     * need to duplicate PatientRecords. Only the instance of the heap (including the array and
     * its size) will be duplicated.
     *
     * @return a deep copy of this PriorityCareAdmissions queue. The returned new priority care
     * admissions queue has the same length and size as this queue.
     */
    public PriorityCareAdmissions deepCopy() {
        PriorityCareAdmissions deepCopy = new PriorityCareAdmissions(this.capacity());
        deepCopy.queue = Arrays.copyOf(this.queue, this.queue.length);
        deepCopy.size = this.size;
        return deepCopy;
    }

    /**
     * Returns a deep copy of the array-heap of this PriorityCareAdmissions queue <BR/>
     * <p>
     * This method can be used for testing purposes.
     *
     * @return a deep copy of the array-heap storing the ParientRecords in this queue
     */
    protected PatientRecord[] arrayHeapCopy() {
        return Arrays.copyOf(this.queue, this.queue.length);

    }

    /**
     * Returns a String representing this PriorityCareAdmissions queue, where each element
     * (PatientRecord) of the queue is listed on a separate line, in order from smallest to
     * greatest.
     *
     * @return a String representing this PriorityCareAdmissions queue, and an empty String ""
     * if this queue is empty.
     */
    public String toString() {
        // TODO complete this implementation
        PriorityCareAdmissions deepCopy = deepCopy();

        // [HINT] use a loop to traverse a deepCopy of this priority queue by removing the best
        // patient record until the queue is empty.
        String returnString = "";

        for(int i = 0; i < size(); i++){
            returnString += deepCopy.removeBestRecord().toString() + "\n";
        }

        return returnString; // default return statement added to resolve compile errors

    }


}
