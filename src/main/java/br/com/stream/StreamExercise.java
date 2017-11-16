package br.com.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamExercise implements Stream{
	private static final String VOGAL_MATCHER = "^A|E|I|O|U$"; 
	
	private int vowelIndex;
	private int index;
	private String input;

	public StreamExercise(String input){
		this.index = 0;
		this.input = input;
		List<String> chars = Arrays.asList(this.input.split("")).stream()
				.map(String::toUpperCase).collect(Collectors.toList());
		String consonant = chars.stream().filter(s -> !s.matches(VOGAL_MATCHER))
				.findFirst().orElseThrow(ConsonantException::new);
		int consonantIndex = chars.indexOf(consonant);
		String vowel = chars.subList(consonantIndex, chars.size()).stream()
				.filter(filterUniqueVowel(chars))
				.findFirst().orElseThrow(VowelException::new);
		this.vowelIndex = chars.indexOf(vowel);
		System.out.println("INPUT: " + input + ", VOGAL: " + vowel);
	}

	@Override
	public char getNext() {
		return this.input.charAt(this.index++);
	}

	@Override
	public boolean hasNext() {
		return this.index <= this.input.length() && this.vowelIndex == -1 || this.index <= this.vowelIndex;
	}

	private Predicate<? super String> filterUniqueVowel(List<String> chars) {
		return s -> s.matches(VOGAL_MATCHER) && chars.stream().filter(v -> v.equals(s)).count() == 1;
	}
}
