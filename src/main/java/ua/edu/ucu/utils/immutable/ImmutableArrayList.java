package ua.edu.ucu.utils.immutable;

import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList {

    private final Object[] elements;

    public ImmutableArrayList() {
        this.elements = new Object[] {};
    }

    public ImmutableArrayList(Object[] e) {
        this.elements = e.clone();
    }

    private void indexCheck(int index)
            throws IndexOutOfBoundsException {
        if (index > this.elements.length - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void indexCheckPlusOne(int index)
            throws IndexOutOfBoundsException {
        if (index > this.elements.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableArrayList add(Object e) {
        return this.add(this.elements.length, e);
    }

    @Override
    public ImmutableArrayList add(int index, Object e)
            throws IndexOutOfBoundsException {
        indexCheckPlusOne(index);
        Object[] newList = Arrays.copyOf(this.elements,
                this.elements.length + 1);
        newList[index] = e;
        for (int i = index + 1; i < this.elements.length + 1; i++) {
            newList[i] = this.elements[i-1];
        }
        return new ImmutableArrayList(newList);
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        return this.addAll(this.elements.length, c);
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c)
            throws IndexOutOfBoundsException {
        indexCheckPlusOne(index);
        Object[] newList = Arrays.copyOf(this.elements,
                this.elements.length + c.length);
        for (int i = index; i < c.length + index; i++) {
            newList[i] = c[i - index];
        }
        for (int i = c.length + index;
             i < c.length + this.elements.length; i++) {
            newList[i] = this.elements[i - c.length];
        }
        return new ImmutableArrayList(newList);
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        indexCheck(index);
        return this.elements[index];
    }

    @Override
    public ImmutableArrayList remove(int index)
            throws IndexOutOfBoundsException {
        indexCheck(index);
        Object[] newList = Arrays.copyOf(this.elements,
                this.elements.length - 1);
        for (int i = index; i < this.elements.length - 1; i++) {
            newList[i] = this.elements[i+1];
        }
        return new ImmutableArrayList(newList);
    }

    @Override
    public ImmutableArrayList set(int index, Object e)
            throws IndexOutOfBoundsException {
        indexCheck(index);
        Object[] newList = Arrays.copyOf(this.elements, this.elements.length);
        newList[index] = e;
        return new ImmutableArrayList(newList);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < this.elements.length; i++) {
            if (this.elements[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.elements.length;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elements, this.elements.length);
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < this.elements.length; i++) {

            buf.append(String.valueOf(this.elements[i]));
            if (i != this.elements.length-1) {
                buf.append(", ");
            }

        }
        return buf.toString();
    }
}
