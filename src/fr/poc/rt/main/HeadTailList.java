package fr.poc.rt.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeadTailList {
	
	private final static int DEFAULT_HEAD_SIZE = 50;
	private List<Item> tail;
	private List<Item> head;
	private int maxHeadSize = DEFAULT_HEAD_SIZE;
			
	public HeadTailList(int headSize){
		if ( headSize > 0 ) this.maxHeadSize = headSize;
	}
	
	/**
	 * Add a barrier to this Collection.
	 * 
	 * 
	 * @param newItem
	 * @return An array of two Strings.
	 * 		- The first String is the item entering the view (ref:distance)
	 * 		- The second String is the item leaving the view (ref)
	 */
	public String[] add( Item newItem ){
		int indexOfItemInHead = getHead().indexOf(newItem);
		String[] result = new String[2];
		if ( head.size()  < maxHeadSize ){
			// Head is too small, add or replace element in head
			if (indexOfItemInHead > -1 ){
				getHead().set(indexOfItemInHead, newItem);
				result[0] = newItem.toString();				
			} else {
				addToHead(newItem);
				result[0] = newItem.toString();
			}			
		} else if ( indexOfItemInHead > -1 ){		
			if ( newItem.compareTo(getHeadLast()) > 0 ){
				// Barrier should not be kept in head
				int tailSmallest = getTailSmallestId();
				if ( tailSmallest > -1 ){
					result[0] = getTail().get(tailSmallest).toString();
					result[1] = newItem.getReference();
					getHead().set(indexOfItemInHead, getTail().get(tailSmallest) );
					getTail().set(tailSmallest, newItem);					
				} else {
					// Exception : Tail is empty, we are forced to keep this element in head
					getHead().set(indexOfItemInHead, newItem);
					result[0] = newItem.toString();
				}					
			} else {
				// Item should be kept in head
				getHead().set(indexOfItemInHead,newItem); 
				result[0] = newItem.toString();
			}			
		} else {
			if ( newItem.compareTo(getHeadLast()) < 0 ){
				// Item should be added in head
				result[0] = newItem.toString();
				result[1] = getHeadLast().getReference();
				addToTail(getHeadLast());
				getHead().set(head.size()-1, newItem);				
			}
			else {
				// Item should be added to tail
				addToTail(newItem);				
			}			
		}
		Collections.sort(head);
		return result;
	}
	
	
	public void addToTail(Item item){
		getTail().add(item);
	}
	
	public void addToHead(Item item){
		getHead().add(item);
	}
	
	public int size(){
		return getHeadSize() + getTailSize();
	}
	
	public int getHeadSize(){
		return getHead().size();
	}
	
	public int getTailSize(){
		return getTail().size();
	}
	
	public int getTailSmallestId(){
		int position = -1;
		Item smallest = null;
		int i = 0;
		for ( Item itemInTail : getTail() ){
			if ( smallest == null || itemInTail.compareTo(smallest) < 0 ){
				smallest = itemInTail;
				position = i;
			}	
			i++;
		}
		return position;
	}
	
	public Item getHeadFirst(){
		return getHead().get(0);
	}	
	
	public Item getHeadLast(){
		return getHead().get(getHead().size()-1);
	}	
	
	public List<Item> getTail() {
		if ( tail == null ) tail = new ArrayList<>();
		return tail;
	}

	public void setTail(List<Item> content) {
		this.tail = content;
	}	

	public int getMaxHeadSize() {
		return maxHeadSize;
	}

	public void setMaxHeadSize(int maxHeadSize) {
		this.maxHeadSize = maxHeadSize;
	}

	public List<Item> getHead() {
		if ( head == null ) head = new ArrayList<>();
		return head;
	}
	public void setHead(List<Item> head) {
		this.head = head;
	}
}
