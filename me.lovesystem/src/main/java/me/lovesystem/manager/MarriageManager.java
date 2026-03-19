package me.lovesystem.manager;

import java.util.*;

public class MarriageManager {

    private final Map<UUID, UUID> proposals = new HashMap<>();
    private final Map<UUID, UUID> marriages = new HashMap<>();

    public void addProposal(UUID target, UUID sender) {
        proposals.put(target, sender);
    }

    public UUID getProposal(UUID player) {
        return proposals.get(player);
    }

    public void removeProposal(UUID player) {
        proposals.remove(player);
    }

    public void marry(UUID p1, UUID p2) {
        marriages.put(p1, p2);
        marriages.put(p2, p1);
    }

    public boolean isMarried(UUID player) {
        return marriages.containsKey(player);
    }

    public UUID getPartner(UUID player) {
        return marriages.get(player);
    }

    public void divorce(UUID player) {
        UUID partner = marriages.remove(player);
        if (partner != null) {
            marriages.remove(partner);
        }
    }
}