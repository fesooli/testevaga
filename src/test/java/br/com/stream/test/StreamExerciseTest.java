package br.com.stream.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.stream.ConsonantException;
import br.com.stream.StreamExercise;
import br.com.stream.VowelException;

public class StreamExerciseTest {

	@Test(expected = ConsonantException.class)
	public void notContainsConsonant(){
		new StreamExercise("AaeEUiOiU");
	}
	
	@Test(expected = VowelException.class)
	public void notContainsVowel(){
		new StreamExercise("FWstRL");
	}
	
	@Test(expected = VowelException.class)
	public void notContainsVowelAfterConsonant(){
		new StreamExercise("AEeiUFWstRL");
	}
	
	@Test(expected = VowelException.class)
	public void vowelRepetThemselves(){
		new StreamExercise("AcTaEiteIOuUo");
	}
	
	@Test
	public void findVowel(){
		String input = " aAbBABacafe";
		StreamExercise stream = new StreamExercise(input);
		for (int i = 0; i < input.length(); i++){
			if (i == (input.length() - 1)){
				Assert.assertNotNull(stream.getNext());
				Assert.assertFalse(stream.hasNext());
			} else {
				Assert.assertNotNull(stream.getNext());
				Assert.assertTrue(stream.hasNext());
			}
		}
	}
	
	@Test
	public void findVowelNotLast(){
		String input = "aAbBABacfeAfU";
		StreamExercise stream = new StreamExercise(input);
		int index = 0;
		do {
			stream.getNext();
			index++;
		}while(stream.hasNext());
		Assert.assertEquals(10, index);
	}
	
	@Test
	public void findVowelSecond(){
		String input = "TaEbBiBIcfeOfU";
		StreamExercise stream = new StreamExercise(input);
		int index = 0;
		do {
			stream.getNext();
			index++;
		}while(stream.hasNext());
		Assert.assertEquals(2, index);
	}
}
