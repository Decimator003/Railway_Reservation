package model;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingList {
    private Queue<String> waitingQueue; // Queue of user IDs

    public WaitingList() {
        this.waitingQueue = new LinkedList<>();
    }

    public void addUser(String userId) {
        waitingQueue.add(userId);
    }

    public String getNextUser() {
        return waitingQueue.poll();
    }

    public boolean isEmpty() {
        return waitingQueue.isEmpty();
    }
}