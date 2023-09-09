package ga_training.selection;

import java.util.ArrayList;
import java.util.Random;

import java.util.List;

import ga_training.EvaluatedCandidate;
import ga_training.GENE;
import ga_training.Selector;
import ga_training.aiEvolution.AiEvolution;
import parallel.Accelerater;

public class TrippelSelection {

	private ArrayList<EvaluatedCandidate> candidateSet;
	private ArrayList<EvaluatedCandidate>[] _fakeCandidateSet;
	@SuppressWarnings("unused")
	private ArrayList<GENE> populations;
	private ArrayList<GENE>[] _fakePopulations;
	private Random[] spaceRandom;
	private boolean cPUprioritize;
	private static final int TRIPLE = 1;
	private Accelerater accelerater;

	public TrippelSelection(AiEvolution aiEvolution, boolean cPUprioritize, Selector selector) {
		super();
		this.cPUprioritize = cPUprioritize;
		accelerater = new Accelerater(aiEvolution, cPUprioritize, selector);
	}

	public Accelerater getAccelerater() {
		return accelerater;
	}

	public void setAccelerater(Accelerater accelerater) {
		this.accelerater = accelerater;
	}

	@SuppressWarnings("unchecked")
	public void setupGate(ArrayList<GENE> populations, ArrayList<EvaluatedCandidate> candidateSet,
			boolean cPUprioritize) {
		this.candidateSet = candidateSet;
		this.populations = populations;
		this.cPUprioritize = cPUprioritize;
		_fakeCandidateSet = new ArrayList[TRIPLE];
		_fakePopulations = new ArrayList[TRIPLE];
		spaceRandom = new Random[TRIPLE];
		for (int i = 0; i < TRIPLE; i++) {
			_fakeCandidateSet[i] = new ArrayList<>();
			_fakePopulations[i] = new ArrayList<>();
			spaceRandom[i] = new Random();
		}
	}

	public ArrayList<GENE> TrippelSelect(boolean naturalFitnessScores, Random rouletteRandom, int size,
			ArrayList<Double> metadata) throws InterruptedException {
		ArrayList<GENE> populationsResult = new ArrayList<>();
		int resize = size / TRIPLE;

		accelerater.setupGate(null, candidateSet, cPUprioritize);
		ArrayList<GENE> firstStepPopnsTripSel = accelerater.SelectionSuport(naturalFitnessScores, rouletteRandom, size);

		accelerater.setupGate(firstStepPopnsTripSel, null, cPUprioritize);
		ArrayList<EvaluatedCandidate> firstCandidateSet = accelerater.ValueSuport(metadata);

		for (int i = 0; i < TRIPLE; i++) {
			accelerater.setupGate(null, firstCandidateSet, cPUprioritize);
			_fakePopulations[i] = accelerater.SelectionSuport(naturalFitnessScores, spaceRandom[i], resize);
//			System.out.println("_fakePopulations[i]: " + _fakePopulations[i].size());
		}

		for (int i = 0; i < TRIPLE; i++) {
			populationsResult.addAll(_fakePopulations[i]);
		}

//		System.out.println("populationsResult: " + populationsResult.size());
		return populationsResult;
	}
}
