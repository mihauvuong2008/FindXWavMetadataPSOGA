package parallel;

import java.util.ArrayList;

import ga_training.EvaluatedCandidate;
import ga_training.GENE;
import ga_training.aiEvolution.AiEvolution;

public class ValueSupportStreamer extends Thread {
	private AiEvolution aiEvolution;
	private ArrayList<GENE> Populations;
	private ArrayList<EvaluatedCandidate> candidateSet;
	private ArrayList<Double> metadata;

	public ValueSupportStreamer(AiEvolution aiEvolution, ArrayList<GENE> populations, ArrayList<Double> metadata,
			ArrayList<EvaluatedCandidate> candidateSet) {
		super();
		this.aiEvolution = aiEvolution;
		this.Populations = populations;
		this.candidateSet = candidateSet;
		this.metadata = metadata;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		ArrayList<EvaluatedCandidate> _candidateSet = aiEvolution.Value(Populations, metadata);
		synchronized (candidateSet) {
			candidateSet.addAll(_candidateSet);
		}
	}
}
