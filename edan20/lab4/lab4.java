package format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import store.Pair;
import store.Triple;

/**
 * 
 * @author Pierre Nugues
 */
// The class to load a CONLL corpus 2006 and 2007 and store it into a list.
public class CONLLCorpus {

	private List<List<Word>> sentenceList;

	public List<List<Word>> loadFile(File file) throws IOException {
		sentenceList = new ArrayList<List<Word>>();
		List<Word> sentence = new ArrayList<Word>();
		Word root = new Word("0", "ROOT", "ROOT", "ROOT", "ROOT", "ROOT", "0",
				"ROOT", "0", "ROOT");

		Word curWord;

		sentence.add(root);

		int sentenceCount = 0;
		int lineCount = 0;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), "UTF-8"));
		String line = null;
		while ((line = reader.readLine()) != null) {
			lineCount++;
			curWord = makeWord(line);
			if (curWord != null) { // A word
				sentence.add(curWord);
			} else { // An empty line denoting a new sentence
				sentenceList.add(sentence);
				sentence = new ArrayList<Word>();
				sentence.add(new Word(root));
				sentenceCount++;
			}
			if ((lineCount % 10000) == 0) {
				System.out.println("Read: " + lineCount + " lines, "
						+ sentenceCount + " sentences.");
			}
		}
		reader.close();
		return sentenceList;
	}

	private Word makeWord(String line) {
		String[] wordD = line.split("\t");
		Word word = null;
		// Test set
		if (wordD.length == 6) {
			word = new Word(wordD[0], wordD[1], wordD[2], wordD[3], wordD[4],
					wordD[5]);
		}
		// Training set
		if (wordD.length == 10) {
			word = new Word(wordD[0], wordD[1], wordD[2], wordD[3], wordD[4],
					wordD[5], wordD[6], wordD[7], wordD[8], wordD[9]);
		}
		return word;
	}

	public void printFile(List<List<Word>> sentenceList) {
		for (int i = 0; i < sentenceList.size(); i++) {
			for (int j = 0; j < (sentenceList.get(i)).size(); j++) {
				System.out.print((sentenceList.get(i)).get(j).getForm() + " ");
			}
			System.out.println("");
		}
	}

	public void saveFile(File file, List<List<Word>> sentenceList)
			throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file), "UTF-8"));

		for (int i = 0; i < sentenceList.size(); i++) {
			for (int j = 0; j < (sentenceList.get(i)).size(); j++) {
				writer.write((sentenceList.get(i)).get(j).getId() + "\t");
				writer.write((sentenceList.get(i)).get(j).getForm() + "\t");
				writer.write((sentenceList.get(i)).get(j).getLemma() + "\t");
				writer.write((sentenceList.get(i)).get(j).getCpostag() + "\t");
				writer.write((sentenceList.get(i)).get(j).getPostag() + "\t");
				writer.write((sentenceList.get(i)).get(j).getFeats() + "\t");
				writer.write((sentenceList.get(i)).get(j).getHead() + "\t");
				writer.write((sentenceList.get(i)).get(j).getDeprel() + "\t");
				writer.write((sentenceList.get(i)).get(j).getPhead() + "\t");
				writer.write((sentenceList.get(i)).get(j).getPdeprel() + "\n");
			}
			if (i != sentenceList.size()) {
				writer.write("\n");
			}
		}
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		File trainingSet = new File(Constants.TRAINING_SET);
		CONLLCorpus trainingCorpus = new CONLLCorpus();
		List<List<Word>> sentenceList;
		sentenceList = trainingCorpus.loadFile(trainingSet);

		ArrayList<Pair> pairList = new ArrayList<Pair>();
		ArrayList<Triple> tripleList = new ArrayList<Triple>();

		HashMap<Pair, Integer> pairFrequency = new HashMap<Pair, Integer>();
		HashMap<Triple, Integer> tripleFrequency = new HashMap<Triple, Integer>();

		for (List<Word> l : sentenceList) {
			for (Word w : l) {

				if (w.getDeprel().equals("SS")) {
					Pair p = new Pair(w, l.get(w.getHead()));
					pairList.add(p);
					if (pairFrequency.get(p) == null) {
						pairFrequency.put(p, 1);
					} else {
						pairFrequency.put(p, pairFrequency.get(p) + 1);
					}
				}

				if (w.getDeprel().equals("OO")) {
					for (Word tmp : l) {
						if (tmp.getDeprel().equals("SS")
								&& tmp.getHead() == w.getHead()) {
							Triple t = new Triple(tmp, l.get(tmp.getHead()), w);
							tripleList.add(t);
							if (tripleFrequency.get(t) == null) {
								tripleFrequency.put(t, 1);
							} else {
								tripleFrequency.put(t,
										tripleFrequency.get(t) + 1);
							}
						}

					}
				}
			}
		}

		Set<Pair> s = pairFrequency.keySet();
		Pair[] maxPairs = new Pair[5];

		for (int i = 0; i < 5; i++) {
			int max = 0;
			Pair maxPair = null;
			for (Pair p : s) {
				if (pairFrequency.get(p) > max) {
					max = pairFrequency.get(p);
					maxPair = p;
				}
			}
			maxPairs[i] = maxPair;
			s.remove(maxPair);
			System.out.println(maxPair.toString() + " " + max);
		}

		Set<Triple> s2 = tripleFrequency.keySet();
		Triple[] maxTriples = new Triple[5];

		for (int i = 0; i < 5; i++) {
			int max = 0;
			Triple maxTriple = null;
			for (Triple t : s2) {
				if (tripleFrequency.get(t) > max) {
					max = tripleFrequency.get(t);
					maxTriple = t;
				}
			}
			maxTriples[i] = maxTriple;
			s2.remove(maxTriple);
			System.out.println(maxTriple.toString() + " " + max);
		}

		System.out.println(pairList.size());

		System.out.println(tripleList.size());
	}
}