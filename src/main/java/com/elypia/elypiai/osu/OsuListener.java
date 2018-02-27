package com.elypia.elypiai.osu;

import com.elypia.elypiai.osu.events.LevelUpEvent;
import com.elypia.elypiai.osu.events.PPUpEvent;

public interface OsuListener {
	void onPPUp(PPUpEvent event);
	void onLevelUp(LevelUpEvent event);
}
