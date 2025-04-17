import java.util.*;
import java.util.stream.Collectors;

public class Election {
    private List<String> candidates;
    private Map<String, Integer> voteCounts;
    private int totalVotes;
    private int p; //total electorate votes

    public Election() {
        this.candidates = new ArrayList<>();
        this.voteCounts = new HashMap<>();
        this.totalVotes = 0;
    }

    public void initializeCandidates(LinkedList<String> candidates) {
        this.candidates = new ArrayList<>(candidates);
        this.voteCounts.clear();
        for (String candidate : candidates) {
            voteCounts.put(candidate, 0);
        }
        this.totalVotes = 0;
    }

    public void castVote(String candidate) {
        if (!voteCounts.containsKey(candidate)) {
            throw new IllegalArgumentException("Invalid candidate");
        }
        voteCounts.put(candidate, voteCounts.get(candidate) + 1);
        totalVotes++;
    }

    public void castRandomVote() {
        if (candidates.isEmpty()) {
            throw new IllegalStateException("No candidates initialized");
        }
        String candidate = candidates.get(new Random().nextInt(candidates.size()));
        castVote(candidate);
    }

    public void rigElection(String candidate) {
        if (!voteCounts.containsKey(candidate)) {
            throw new IllegalArgumentException("Invalid candidate");
        }

        for (String c : candidates) {
            voteCounts.put(c, 0);
        }
        totalVotes = p;

        //gives all votes to one candidate
        voteCounts.put(candidate, p);
    }

    public List<String> getTopKCandidates(int k) {
        // Create a max heap based on vote counts
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );

        maxHeap.addAll(voteCounts.entrySet());
        List<String> topK = new ArrayList<>();
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            topK.add(maxHeap.poll().getKey());
        }
        return topK;
    }

    public void auditElection() {
        List<Map.Entry<String, Integer>> sorted = voteCounts.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .collect(Collectors.toList());

        for (Map.Entry<String, Integer> entry : sorted) { //prints results
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public void setTotalElectorateVotes(int p) {
        this.p = p;
    }
}