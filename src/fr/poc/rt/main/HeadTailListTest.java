package fr.poc.rt.main;



import org.junit.Assert;
import org.junit.Test;

public class HeadTailListTest {

	@Test
	public void testAddBarrier_directlyAddedToHead(){
		HeadTailList bc = new HeadTailList(3);
		Item b1  = new Item("A", "A", "2");
		Item b2  = new Item("B", "A", "1");
		Item b3  = new Item("C", "A", "3");
		Item b4  = new Item("D", "A", "2.87");
		String[] rb1 = bc.add(b1);
		String[] rb2 = bc.add(b2);
		String[] rb3 = bc.add(b3);
		String[] rb4 = bc.add(b4);
		
		Assert.assertEquals(rb1[0], "A:A:2.0");
		Assert.assertEquals(rb1[1], null);
		Assert.assertEquals(rb2[0], "B:A:1.0");
		Assert.assertEquals(rb2[1], null);
		Assert.assertEquals(rb3[0], "C:A:3.0");
		Assert.assertEquals(rb3[1], null);
		Assert.assertEquals(rb4[0], "D:A:2.87");
		Assert.assertEquals(rb4[1], "C:A");
		
		Assert.assertEquals(3, bc.getHeadSize());
		Assert.assertEquals(1, bc.getTailSize());
		Assert.assertEquals(b2, bc.getHeadFirst());
		Assert.assertEquals(b4, bc.getHeadLast());
		Assert.assertTrue(bc.getTail().contains(b3));
		Assert.assertTrue(bc.getHead().contains(b1));		
	}
	
	@Test
	public void testAddBarrier_mustSwapWithTailElement(){
		HeadTailList bc = new HeadTailList(3);
		Item b1  = new Item("A", "A", "2");
		Item b2  = new Item("B", "A", "1");
		Item b3  = new Item("C", "A", "3");
		Item b4  = new Item("D", "A", "4.5");
		Item b5  = new Item("E", "A", "44.5");
		Item b6  = new Item("F", "A", "4642.5");
		Item b7  = new Item("G", "A", "4.35");
		Item b8  = new Item("H", "A", "0.01");		
		Item b1_ = new Item("A", "A", "53.5");
		String[] rb1 = bc.add(b1);
		String[] rb2 = bc.add(b2);
		String[] rb3 = bc.add(b3);
		String[] rb4 = bc.add(b4);
		String[] rb5 = bc.add(b5);
		String[] rb6 = bc.add(b6);
		String[] rb7 = bc.add(b7);
		String[] rb8 = bc.add(b8);
		
		Assert.assertEquals(rb1[0], "A:A:2.0");
		Assert.assertEquals(rb1[1], null);
		Assert.assertEquals(rb2[0], "B:A:1.0");
		Assert.assertEquals(rb2[1], null);
		Assert.assertEquals(rb3[0], "C:A:3.0");
		Assert.assertEquals(rb3[1], null);
		Assert.assertEquals(rb4[0], null);
		Assert.assertEquals(rb4[1], null);
		Assert.assertEquals(rb5[0], null);
		Assert.assertEquals(rb5[1], null);
		Assert.assertEquals(rb6[0], null);
		Assert.assertEquals(rb6[1], null);
		Assert.assertEquals(rb7[0], null);
		Assert.assertEquals(rb7[1], null);
		Assert.assertEquals(rb8[0], "H:A:0.01");
		Assert.assertEquals(rb8[1], "C:A");
		
		Assert.assertEquals(3, bc.getHeadSize());
		Assert.assertEquals(5, bc.getTailSize());
		Assert.assertEquals(b8, bc.getHeadFirst());
		Assert.assertEquals(b1, bc.getHeadLast());
		
		String[] rb1_ = bc.add(b1_); // Should be swapped in tail with b3
		Assert.assertEquals(rb1_[0], "C:A:3.0");
		Assert.assertEquals(rb1_[1], "A:A");
		
		Assert.assertEquals(3, bc.getHeadSize());
		Assert.assertEquals(5, bc.getTailSize());
		Assert.assertEquals(b8, bc.getHeadFirst());
		Assert.assertEquals(b3, bc.getHeadLast());
		Assert.assertTrue(bc.getTail().contains(b1_));
		Assert.assertTrue(bc.getHead().contains(b2));
	}	
	
	@Test
	public void testAddBarrier_mustKeepInHeadCauseTailEmpty(){
		HeadTailList bc = new HeadTailList(3);
		Item b1  = new Item("A", "A", "2");
		Item b2  = new Item("B", "A", "1");
		Item b3  = new Item("C", "A", "3");
		Item b4  = new Item("A", "A", "4.5");		
		bc.add(b1);
		bc.add(b2);
		bc.add(b3);
		bc.add(b4);
		
		Assert.assertEquals(3, bc.getHeadSize());
		Assert.assertEquals(0, bc.getTailSize());
		Assert.assertFalse(bc.getTail().contains(b4));
		Assert.assertTrue(bc.getHead().contains(b1));
		Assert.assertEquals(b2, bc.getHeadFirst());
		Assert.assertEquals(b4, bc.getHeadLast());
	}	
	
	@Test
	public void testAddBarrier_barrierSentToTail(){
		HeadTailList bc = new HeadTailList(3);
		Item b1  = new Item("A", "A", "2");
		Item b2  = new Item("B", "A", "1");
		Item b3  = new Item("C", "A", "3");
		Item b4  = new Item("D", "A", "4.5");
		bc.add(b1);
		bc.add(b2);
		bc.add(b3);
		bc.add(b4);
		
		Assert.assertEquals(3, bc.getHeadSize());
		Assert.assertEquals(1, bc.getTailSize());
		Assert.assertTrue(bc.getTail().contains(b4));
		Assert.assertFalse(bc.getHead().contains(b4));
	}
	
	@Test
	public void testAddBarrier_barrierKeptInHead(){
		HeadTailList bc = new HeadTailList(3);
		Item b1  = new Item("A", "A", "2");
		Item b2  = new Item("B", "A", "1");
		Item b3  = new Item("C", "A", "3");
		Item b1_ = new Item("A", "A", "0.34");
		Item b2_ = new Item("B", "A", "0.134");
		bc.add(b1);
		bc.add(b2);
		bc.add(b3);
		bc.add(b1_);
		
		Assert.assertEquals(3, bc.getHeadSize());
		Assert.assertEquals(0, bc.getTailSize());		
		Assert.assertEquals(b1_, bc.getHeadFirst());
		Assert.assertEquals(Double.valueOf(0.34), bc.getHeadFirst().getValue());
		Assert.assertEquals(b3, bc.getHeadLast());
		Assert.assertTrue(bc.getHead().contains(b2));
		
		bc.add(b2_);
		
		Assert.assertEquals(3, bc.getHeadSize());
		Assert.assertEquals(0, bc.getTailSize());	
		Assert.assertEquals(b2_, bc.getHeadFirst());
		Assert.assertEquals(Double.valueOf(0.134), bc.getHeadFirst().getValue());
		Assert.assertEquals(b3, bc.getHeadLast());
		Assert.assertTrue(bc.getHead().contains(b1_));
	}
	
	
	@Test
	public void testAddBarrierGlobal(){
		HeadTailList bc = new HeadTailList(3);
		Item b1 = new Item("A", "A", "2");
		Item b2 = new Item("B", "A", "1");
		Item b3 = new Item("C", "A", "3");
		Item b4 = new Item("D", "A", "0.12");
		Item b5 = new Item("E", "A", "4.12");		
		Item b6 = new Item("D", "A", "4.42");
		
		Assert.assertEquals(b6, b4);
		Assert.assertEquals(0, bc.getHeadSize());
		Assert.assertEquals(0, bc.getTailSize());
		Assert.assertEquals(0, bc.size());
		
		bc.add(b1);		
		Assert.assertEquals(1, bc.getHeadSize());
		Assert.assertEquals(0, bc.getTailSize());
		Assert.assertEquals(1, bc.size());
		
		bc.add(b2);		
		Assert.assertEquals(2, bc.getHeadSize());
		Assert.assertEquals(0, bc.getTailSize());
		Assert.assertEquals(2, bc.size());
		
		bc.add(b3);		
		Assert.assertEquals(3, bc.getHeadSize());
		Assert.assertEquals(0, bc.getTailSize());
		Assert.assertEquals(3, bc.size());
		
		bc.add(b4);		
		Assert.assertEquals(3, bc.getHeadSize());
		Assert.assertEquals(1, bc.getTailSize());
		Assert.assertEquals(4, bc.size());
		
		bc.add(b5);		
		Assert.assertEquals(3, bc.getHeadSize());
		Assert.assertEquals(2, bc.getTailSize());
		Assert.assertEquals(5, bc.size());
				
		Assert.assertEquals(b4, bc.getHeadFirst());
		Assert.assertTrue(bc.getHead().contains(b2));
		Assert.assertEquals(b1, bc.getHeadLast());
	
		bc.add(b6);		
		Assert.assertEquals(3, bc.getHeadSize());
		
		Assert.assertEquals(b2, bc.getHeadFirst());
		Assert.assertTrue(bc.getHead().contains(b1));
		Assert.assertEquals(b3, bc.getHeadLast());
		
		Assert.assertEquals(2, bc.getTailSize());
		Assert.assertEquals(5, bc.size());
	}
	
	@Test
	public void testCompare() {
		Item b1 = new Item("A", "A", "1");
		Item b2 = new Item("A", "A", "2");
		Assert.assertTrue(b1.compareTo(b2) < 0 );
		Assert.assertTrue(b2.compareTo(b1) > 0 );
		Assert.assertTrue(b2.compareTo(b2) == 0 );
	}
	
	@Test
	public void testSmallestInTail(){
		HeadTailList bc = new HeadTailList(50);
		Item b1 = new Item("A", "A", "1");
		Item b2 = new Item("B", "A", "2");
		Item b3 = new Item("c", "A", "3");
		Item b4 = new Item("c", "A", "0.12");
		bc.addToTail(b1);
		bc.addToTail(b2);
		bc.addToTail(b3);		
		Assert.assertEquals(b1, bc.getTail().get(bc.getTailSmallestId()));
		bc.addToTail(b4);
		Assert.assertEquals(b4, bc.getTail().get(bc.getTailSmallestId()));
	}

}
