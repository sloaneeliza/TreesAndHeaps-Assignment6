import java.util.*;

public class ElectionSystem {
    public static void main(String[] args) {
        Election election = new Election();

        LinkedList<String> candidates = new LinkedList<>();
        candidates.add("Marcus Fenix");
        candidates.add("Dominic Santiago");
        candidates.add("Damon Baird");
        candidates.add("Cole Train");
        candidates.add("Anya Stroud");
        election.initializeCandidates(candidates);

        int p = 5;
        election.setTotalElectorateVotes(p);

        election.castVote("Cole Train");
        election.castVote("Cole Train");
        election.castVote("Marcus Fenix");
        election.castVote("Anya Stroud");
        election.castVote("Anya Stroud");

        //top 3 before rigging
        System.out.println("Top 3 candidates after 5 votes: " +
                election.getTopKCandidates(3));
        election.auditElection();

        election.rigElection("Marcus Fenix");

        //after rigging
        System.out.println("Top 3 candidates after rigging the election: " +
                election.getTopKCandidates(3));

        System.out.println("auditElection()");
        election.auditElection();
    }
}