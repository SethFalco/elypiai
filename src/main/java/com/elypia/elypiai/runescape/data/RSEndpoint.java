package com.elypia.elypiai.runescape.data;

public enum RSEndpoint {

	RUNEDATE("https://secure.runescape.com/m=itemdb_rs/api/info.json"),
	CATELOG_CATEGORY("http://services.runescape.com/m=itemdb_rs/api/catalogue/category.json"),
	CATALOG_ITEMS("http://services.runescape.com/m=itemdb_rs/api/catalogue/items.json"),
	CATALOG_DETAILS("http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json"),
	ITEM_IMAGE("http://services.runescape.com/m=itemdb_rs/obj_big.gif"),
	ITEM_SPRITE("http://services.runescape.com/m=itemdb_rs/obj_sprite.gif"),
	ITEM_GRAPH("http://services.runescape.com/m=itemdb_rs/api/graph/{ID}.json"),
	HISCORES("http://services.runescape.com/m=hiscore/ranking.json"),
	SEASONAL_HISCORES("http://services.runescape.com/m=temp-hiscores/getHiscoreDetails.json"),
	CLAN_RANKING("http://services.runescape.com/m=clan-hiscores/clanRanking.json"),
	CLAN_MEMBERS("http://services.runescape.com/m=clan-hiscores/members_lite.ws"),
	SOLOMAN_STORE("https://secure.runescape.com/m=mtxn_rs_shop/api/config?context%5B0%5D=0"),
	PLAYER_DETAILS("http://services.runescape.com/m=website-data/playerDetails.ws"),
	RUNEMETRICS_PROFILE("https://apps.runescape.com/runemetrics/profile/profile"),
	RUNEMETRICS_QUESTS("https://apps.runescape.com/runemetrics/quests"),
	PLAYER_COUNT("http://www.runescape.com/player_count.js?varname=iPlayerCount&callback=jQuery000000000000000_0000000000&_=0"),
	NXT_CHANGELOG("http://content.runescape.com/downloads/changelog.json"),
	WINDOWS_INSTALLER("http://content.runescape.com/downloads-info/windows/RuneScape-Setup.exe.json"),
	OSX_INSTALLER("http://content.runescape.com/downloads-info/osx/RuneScape.dmg.json");

	private String endpoint;

	RSEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getEndpoint() {
		return endpoint;
	}
}
