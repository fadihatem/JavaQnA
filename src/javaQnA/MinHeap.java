package javaQnA;

import java.util.Arrays;

public class MinHeap {
	private int capacity = 10;
	private int size = 0;
	int[] items = new int[capacity];

	private int getLeftChildIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}

	private int getRightChildIndex(int parentIndex) {
		return 2 * parentIndex + 2;
	}

	private int getParentIndex(int childIndex) {
		return (childIndex - 1) / 2;
	}

	private boolean hasLeftChild(int index) {
		return getLeftChildIndex(index) < size;
	}

	private boolean hasRightChild(int index) {
		return getRightChildIndex(index) < size;
	}

	private boolean hasParent(int index) {
		return getParentIndex(index) >= 0;
	}

	private int leftChild(int index) {
		return items[getLeftChildIndex(index)];
	}

	private int rightChild(int index) {
		return items[getRightChildIndex(index)];
	}

	private int parent(int index) {
		return items[getParentIndex(index)];
	}

	private void ensureCapacity() {
		if (size == capacity) {
			items = Arrays.copyOf(items, capacity * 2);
			capacity *= 2;
		}
	}

	private int peek() {
		if (size == 0)
			throw new IllegalStateException();
		return items[0];
	}

	private int poll() {
		int item = peek();
		items[0] = items[size - 1];
		size--;
		heapifyDown();
		return item;
	}

	public void add(int item) {
		ensureCapacity();
		items[size] = item;
		size++;
		heapifyUp();
	}

	public void heapifyUp() {
		int index = size - 1;
		while (hasParent(index) && parent(index) > items[index]) {
			swap(getParentIndex(index), index);
			index = getParentIndex(index);
		}
	}

	public void swap(int n1, int n2) {
		int tmp = items[n1];
		items[n1] = items[n2];
		items[n2] = tmp;
	}

	public void heapifyDown() {
		int index = 0;
		while (hasLeftChild(index)) {
			int smallerLeftChildIndex = getLeftChildIndex(index);
			if (hasRightChild(index) && getRightChildIndex(index) < leftChild(index)) {
				smallerLeftChildIndex = getRightChildIndex(index);
			}
			if (items[index] < items[smallerLeftChildIndex]) {
				break;
			} else {
				swap(index, smallerLeftChildIndex);
			}
			index = smallerLeftChildIndex;
		}
	}
}
