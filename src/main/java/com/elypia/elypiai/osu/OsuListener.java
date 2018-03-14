package com.elypia.elypiai.osu;

import com.elypia.elypiai.osu.events.LevelUpEvent;
import com.elypia.elypiai.osu.events.PpUpEvent;

public interface OsuListener {
	void onPPUp(PpUpEvent event);
	void onLevelUp(LevelUpEvent event);
}
