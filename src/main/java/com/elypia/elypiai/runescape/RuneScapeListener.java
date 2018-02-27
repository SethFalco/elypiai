package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.events.Level120Event;
import com.elypia.elypiai.runescape.events.LevelUpEvent;
import com.elypia.elypiai.runescape.events.MaxXpEvent;

public interface RuneScapeListener {
	void onLevelUp(LevelUpEvent event);
	void onLevel120(Level120Event event);
	void onMaxXP(MaxXpEvent event);
}
